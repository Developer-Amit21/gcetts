import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class MainServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        System.out.println("🚀 Server running at http://localhost:8080");

        server.createContext("/api/result", new ResultHandler());
        server.createContext("/api/exam", new ExamHandler());

        server.setExecutor(null);
        server.start();
    }
}
