import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Util {
    // parse "a=1&b=two" into map
    public static Map<String,String> parseForm(String s) {
        Map<String,String> map = new HashMap<>();
        if (s == null || s.isEmpty()) return map;
        String[] parts = s.split("&");
        for (String p : parts) {
            String[] kv = p.split("=", 2);
            try {
                String k = URLDecoder.decode(kv[0], StandardCharsets.UTF_8.name());
                String v = kv.length > 1 ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8.name()) : "";
                map.put(k, v);
            } catch (UnsupportedEncodingException e) {
                // ignore
            }
        }
        return map;
    }

    public static String readBody(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    public static String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "");
    }

    // build JSON array from collection of toJson() strings
    public static String toJsonArray(Collection<String> items) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean first = true;
        for (String it : items) {
            if (!first) sb.append(",");
            sb.append(it);
            first = false;
        }
        sb.append("]");
        return sb.toString();
    }
}
