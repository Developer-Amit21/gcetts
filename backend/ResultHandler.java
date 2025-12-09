import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.DBConnection;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResultHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";

        if (!"GET".equals(exchange.getRequestMethod())) {
            response = "{\"error\":\"Invalid Request\"}";
        } else {
            String query = exchange.getRequestURI().getQuery();
            String roll = query != null && query.startsWith("roll=")
                    ? query.split("=")[1]
                    : "";

            if (roll.isEmpty()) {
                response = "{\"error\":\"Roll number required\"}";
            } else {
                try (Connection con = DBConnection.getConnection()) {
                    PreparedStatement ps = con.prepareStatement(
                            "SELECT * FROM results WHERE roll = ?");
                    ps.setInt(1, Integer.parseInt(roll));
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        response = String.format("{\"name\":\"%s\",\"mid1\":%d,\"mid2\":%d,\"final\":%d}",
                                rs.getString("name"),
                                rs.getInt("mid1"),
                                rs.getInt("mid2"),
                                rs.getInt("final"));
                    } else {
                        response = "{\"error\":\"Result Not Found\"}";
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    response = "{\"error\":\"Database error\"}";
                }
            }
        }

        exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }
}
