public class Student {
    private final int id;
    private String name;
    private String course;
    private String roll;

    public Student(int id, String name, String course, String roll) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.roll = roll;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCourse() { return course; }
    public String getRoll() { return roll; }

    public void setName(String name) { this.name = name; }
    public void setCourse(String course) { this.course = course; }
    public void setRoll(String roll) { this.roll = roll; }

    public String toJson() {
        return String.format("{\"id\":%d,\"name\":\"%s\",\"course\":\"%s\",\"roll\":\"%s\"}",
                id, Util.escape(name), Util.escape(course), Util.escape(roll));
    }
}
