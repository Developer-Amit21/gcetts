import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Database {
    // simple in-memory storage
    public static final Map<Integer, Student> students = new LinkedHashMap<>();
    public static final Map<Integer, Teacher> teachers = new LinkedHashMap<>();
    public static final Map<Integer, Exam> exams = new LinkedHashMap<>();
    public static final Map<Integer, Result> results = new LinkedHashMap<>();

    private static final AtomicInteger studentIdGen = new AtomicInteger(0);
    private static final AtomicInteger teacherIdGen = new AtomicInteger(0);
    private static final AtomicInteger examIdGen = new AtomicInteger(0);
    private static final AtomicInteger resultIdGen = new AtomicInteger(0);

    // create helpers
    public static Student addStudent(String name, String course, String roll) {
        int id = studentIdGen.incrementAndGet();
        Student s = new Student(id, name, course, roll);
        students.put(id, s);
        return s;
    }

    public static Teacher addTeacher(String name, String role, String department) {
        int id = teacherIdGen.incrementAndGet();
        Teacher t = new Teacher(id, name, role, department);
        teachers.put(id, t);
        return t;
    }

    public static Exam addExam(String title, String date) {
        int id = examIdGen.incrementAndGet();
        Exam e = new Exam(id, title, date);
        exams.put(id, e);
        return e;
    }

    public static Result addResult(int studentId, int examId, int marks) {
        int id = resultIdGen.incrementAndGet();
        Result r = new Result(id, studentId, examId, marks);
        results.put(id, r);
        return r;
    }

    // delete helpers
    public static boolean deleteStudent(int id) { return students.remove(id) != null; }
    public static boolean deleteTeacher(int id) { return teachers.remove(id) != null; }
    public static boolean deleteExam(int id) { return exams.remove(id) != null; }
    public static boolean deleteResult(int id) { return results.remove(id) != null; }
}
