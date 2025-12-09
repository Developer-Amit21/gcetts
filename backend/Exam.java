public class Exam {
    private final int id;
    private String title;
    private String date; // simple string like YYYY-MM-DD or any

    public Exam(int id, String title, String date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDate() { return date; }

    public void setTitle(String title) { this.title = title; }
    public void setDate(String date) { this.date = date; }

    public String toJson() {
        return String.format("{\"id\":%d,\"title\":\"%s\",\"date\":\"%s\"}", id, Util.escape(title), Util.escape(date));
    }
}
