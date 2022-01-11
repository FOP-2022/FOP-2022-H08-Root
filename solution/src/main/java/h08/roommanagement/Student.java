package h08.roommanagement;

public class Student {

    public final String name;
    public final boolean hasCertificate;

    public Student(String name, boolean hasCertificate) {
        this.name = name;
        this.hasCertificate = hasCertificate;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", hasCertificate=" + hasCertificate + '}';
    }
}
