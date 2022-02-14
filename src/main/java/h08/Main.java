package h08;

import h08.roommanagement.InsufficientNumberOfSeatsException;
import h08.roommanagement.NoCertificateException;
import h08.roommanagement.Room;
import h08.roommanagement.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * class for h08.
 */
public class Main {

    public static void main(String[] args) {
    }

    /**
     * Throws exceptions if ether at least one student has no certificate or
     * the students are more than half of the room capacity.
     *
     * @param students array of students who want to access the room
     * @param room room object they want to access
     * @throws NoCertificateException if at least one student is not 3g
     */
    public static void checkRegistration(Student[] students, Room room) throws NoCertificateException {
        if (students.length > room.numberOfSeats / 2) {
            throw new InsufficientNumberOfSeatsException(room, 2 * students.length - room.numberOfSeats);
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
