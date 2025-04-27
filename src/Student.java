public class Student {
    private int id;
    private String name;
    private double gpa;

    public Student(int studentId, String name, double gpa) {
        this.id = studentId;
        this.name = name;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', GPA=" + gpa + "}";
    }
}