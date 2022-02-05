package h08;

import h08.roommanagement.InsufficientNumberOfSeatsException;
import h08.roommanagement.NoCertificateException;
import h08.roommanagement.Room;
import h08.roommanagement.Student;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
    }

    public static void checkRegistration(Student[] students, Room room) throws NoCertificateException {
        if (students.length > room.numberOfSeats / 2) {
            throw new InsufficientNumberOfSeatsException(room, students.length - room.numberOfSeats / 2);
        }

        List<Student> studentsWithoutCertificate = new ArrayList<>();

        for (Student stud : students) {
            if (!stud.hasCertificate) {
                studentsWithoutCertificate.add(stud);
            }
        }

        if (studentsWithoutCertificate.size() > 0) {
            throw new NoCertificateException(studentsWithoutCertificate.toArray(new Student[0]));
        }
    }
}
