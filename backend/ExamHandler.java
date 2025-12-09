import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.DBConnection;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ExamHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";

        if (!"POST".equals(exchange.getRequestMethod())) {
            response = "{\"error\":\"Invalid Request\"}";
        } else {
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8));
                 Connection con = DBConnection.getConnection()) {

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) sb.append(line);

                // Assuming JSON: {"roll":101,"exam":"mid1","answers":"A,B,C,D"}
                String body = sb.toString();
                String roll = body.split("\"roll\":")[1].split(",")[0].trim();
                String exam = body.split("\"exam\":\"")[1].split("\"")[0];
                String answers = body.split("\"answers\":\"")[1].split("\"")[0];

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO exam_submissions(roll, exam_name, answers) VALUES (?, ?, ?)");
                ps.setInt(1, Integer.parseInt(roll));
                ps.setString(2, exam);
                ps.setString(3, answers);
                ps.executeUpdate();

                response = "{\"status\":\"Exam submitted successfully\"}";

            } catch (Exception e) {
                e.printStackTrace();
                response = "{\"error\":\"Database error\"}";
            }
        }

        exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }
}
