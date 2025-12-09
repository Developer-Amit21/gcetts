import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.stream.Collectors;

public class Server {
    public static void main(String[] args) throws Exception {
        // add some sample data
        Database.addTeacher("Dr. A. K. Sharma", "Principal", "Administration");
        Database.addTeacher("Prof. R. Mukherjee", "HOD", "Computer Science");
        Database.addStudent("Amit Shaw", "B.Tech", "21CSE124");
        Database.addExam("Midterm", "2025-02-20");

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/students", new StudentsHandler());
        server.createContext("/teachers", new TeachersHandler());
        server.createContext("/exams", new ExamsHandler());
        server.createContext("/results", new ResultsHandler());
        server.setExecutor(null);
        System.out.println("Server started at http://localhost:8000");
        server.start();
    }

    // Handler for /students
    static class StudentsHandler implements HttpHandler {
        @Override public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            if ("GET".equalsIgnoreCase(method)) {
                // if id query param provided -> return single, else list
                Map<String, String> q = queryToMap(exchange.getRequestURI().getQuery());
                if (q.containsKey("id")) {
                    int id = Integer.parseInt(q.get("id"));
                    Student s = Database.students.get(id);
                    String resp = s == null ? "{\"error\":\"not found\"}" : s.toJson();
                    send(exchange, 200, resp);
                } else {
                    List<String> list = Database.students.values().stream().map(Student::toJson).collect(Collectors.toList());
                    send(exchange, 200, Util.toJsonArray(list));
                }
            } else if ("POST".equalsIgnoreCase(method)) {
                String body = Util.readBody(exchange.getRequestBody());
                Map<String,String> form = Util.parseForm(body);
                String name = form.getOrDefault("name","Unknown");
                String course = form.getOrDefault("course","-");
                String roll = form.getOrDefault("roll","-");
                Student s = Database.addStudent(name, course, roll);
                send(exchange, 201, s.toJson());
            } else if ("DELETE".equalsIgnoreCase(method)) {
                Map<String, String> q = queryToMap(exchange.getRequestURI().getQuery());
                if (!q.containsKey("id")) {
                    send(exchange, 400, "{\"error\":\"id required\"}");
                    return;
                }
                int id = Integer.parseInt(q.get("id"));
                boolean ok = Database.deleteStudent(id);
                send(exchange, ok ? 200 : 404, ok ? "{\"status\":\"deleted\"}" : "{\"error\":\"not found\"}");
            } else {
                send(exchange, 405, "{\"error\":\"method not allowed\"}");
            }
        }
    }

    // Handler for /teachers
    static class TeachersHandler implements HttpHandler {
        @Override public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            if ("GET".equalsIgnoreCase(method)) {
                Map<String, String> q = queryToMap(exchange.getRequestURI().getQuery());
                if (q.containsKey("id")) {
                    int id = Integer.parseInt(q.get("id"));
                    Teacher t = Database.teachers.get(id);
                    send(exchange, t == null ? 404 : 200, t == null ? "{\"error\":\"not found\"}" : t.toJson());
                } else {
                    List<String> list = Database.teachers.values().stream().map(Teacher::toJson).collect(Collectors.toList());
                    send(exchange, 200, Util.toJsonArray(list));
                }
            } else if ("POST".equalsIgnoreCase(method)) {
                String body = Util.readBody(exchange.getRequestBody());
                Map<String,String> form = Util.parseForm(body);
                String name = form.getOrDefault("name","Unknown");
                String role = form.getOrDefault("role","Teacher");
                String dept = form.getOrDefault("department","General");
                Teacher t = Database.addTeacher(name, role, dept);
                send(exchange, 201, t.toJson());
            } else if ("DELETE".equalsIgnoreCase(method)) {
                Map<String, String> q = queryToMap(exchange.getRequestURI().getQuery());
                if (!q.containsKey("id")) { send(exchange,400,"{\"error\":\"id required\"}"); return; }
                int id = Integer.parseInt(q.get("id"));
                boolean ok = Database.deleteTeacher(id);
                send(exchange, ok ? 200 : 404, ok ? "{\"status\":\"deleted\"}" : "{\"error\":\"not found\"}");
            } else {
                send(exchange, 405, "{\"error\":\"method not allowed\"}");
            }
        }
    }

    // Handler for /exams
    static class ExamsHandler implements HttpHandler {
        @Override public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            if ("GET".equalsIgnoreCase(method)) {
                List<String> list = Database.exams.values().stream().map(Exam::toJson).collect(Collectors.toList());
                send(exchange, 200, Util.toJsonArray(list));
            } else if ("POST".equalsIgnoreCase(method)) {
                String body = Util.readBody(exchange.getRequestBody());
                Map<String,String> form = Util.parseForm(body);
                String title = form.getOrDefault("title","Exam");
                String date = form.getOrDefault("date","");
                Exam e = Database.addExam(title, date);
                send(exchange, 201, e.toJson());
            } else if ("DELETE".equalsIgnoreCase(method)) {
                Map<String, String> q = queryToMap(exchange.getRequestURI().getQuery());
                if (!q.containsKey("id")) { send(exchange,400,"{\"error\":\"id required\"}"); return; }
                int id = Integer.parseInt(q.get("id"));
                boolean ok = Database.deleteExam(id);
                send(exchange, ok ? 200 : 404, ok ? "{\"status\":\"deleted\"}" : "{\"error\":\"not found\"}");
            } else {
                send(exchange, 405, "{\"error\":\"method not allowed\"}");
            }
        }
    }

    // Handler for /results
    static class ResultsHandler implements HttpHandler {
        @Override public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            if ("GET".equalsIgnoreCase(method)) {
                Map<String,String> q = queryToMap(exchange.getRequestURI().getQuery());
                if (q.containsKey("studentId")) {
                    int sid = Integer.parseInt(q.get("studentId"));
                    List<String> list = Database.results.values().stream()
                            .filter(r -> r.getStudentId() == sid)
                            .map(Result::toJson)
                            .collect(Collectors.toList());
                    send(exchange, 200, Util.toJsonArray(list));
                } else {
                    List<String> list = Database.results.values().stream().map(Result::toJson).collect(Collectors.toList());
                    send(exchange, 200, Util.toJsonArray(list));
                }
            } else if ("POST".equalsIgnoreCase(method)) {
                String body = Util.readBody(exchange.getRequestBody());
                Map<String,String> form = Util.parseForm(body);
                int sid = Integer.parseInt(form.getOrDefault("studentId","0"));
                int eid = Integer.parseInt(form.getOrDefault("examId","0"));
                int marks = Integer.parseInt(form.getOrDefault("marks","0"));
                // simple validation
                if (!Database.students.containsKey(sid)) {
                    send(exchange, 400, "{\"error\":\"student not found\"}");
                    return;
                }
                if (!Database.exams.containsKey(eid)) {
                    send(exchange, 400, "{\"error\":\"exam not found\"}");
                    return;
                }
                Result r = Database.addResult(sid, eid, marks);
                send(exchange, 201, r.toJson());
            } else if ("DELETE".equalsIgnoreCase(method)) {
                Map<String, String> q = queryToMap(exchange.getRequestURI().getQuery());
                if (!q.containsKey("id")) { send(exchange,400,"{\"error\":\"id required\"}"); return; }
                int id = Integer.parseInt(q.get("id"));
                boolean ok = Database.deleteResult(id);
                send(exchange, ok ? 200 : 404, ok ? "{\"status\":\"deleted\"}" : "{\"error\":\"not found\"}");
            } else {
                send(exchange, 405, "{\"error\":\"method not allowed\"}");
            }
        }
    }

    // small helpers
    private static void send(HttpExchange exchange, int code, String body) throws IOException {
        byte[] bytes = body.getBytes("UTF-8");
        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=utf-8");
        exchange.sendResponseHeaders(code, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }

    // parse query string into map
    private static Map<String,String> queryToMap(String query) {
        if (query == null || query.isEmpty()) return Collections.emptyMap();
        Map<String,String> map = new HashMap<>();
        String[] pairs = query.split("&");
        for (String p : pairs) {
            String[] kv = p.split("=",2);
            try {
                String k = java.net.URLDecoder.decode(kv[0], "UTF-8");
                String v = kv.length>1 ? java.net.URLDecoder.decode(kv[1], "UTF-8") : "";
                map.put(k,v);
            } catch(Exception e) { /* ignore */ }
        }
        return map;
    }
}
