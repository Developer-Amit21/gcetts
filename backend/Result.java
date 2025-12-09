public class Result {
    private final int id;
    private final int studentId;
    private final int examId;
    private final int marks;

    public Result(int id, int studentId, int examId, int marks) {
        this.id = id;
        this.studentId = studentId;
        this.examId = examId;
        this.marks = marks;
    }

    public int getId() { return id; }
    public int getStudentId() { return studentId; }
    public int getExamId() { return examId; }
    public int getMarks() { return marks; }

    public String toJson() {
        return String.format("{\"id\":%d,\"studentId\":%d,\"examId\":%d,\"marks\":%d}",
                id, studentId, examId, marks);
    }
}
