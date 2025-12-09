public class Teacher {
    private final int id;
    private String name;
    private String role;       // e.g., Professor, HOD, Principal
    private String department;

    public Teacher(int id, String name, String role, String department) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.department = department;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getDepartment() { return department; }

    public void setName(String name) { this.name = name; }
    public void setRole(String role) { this.role = role; }
    public void setDepartment(String department) { this.department = department; }

    public String toJson() {
        return String.format("{\"id\":%d,\"name\":\"%s\",\"role\":\"%s\",\"department\":\"%s\"}",
                id, Util.escape(name), Util.escape(role), Util.escape(department));
    }
}
