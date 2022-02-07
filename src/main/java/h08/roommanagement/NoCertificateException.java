package h08.roommanagement;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * class for h08.
 */
public class NoCertificateException extends Exception {
    public NoCertificateException(Student[] students) {
        super(Arrays.stream(students).map(stud -> stud.name).collect(Collectors.joining(", ")) + " has/have no certificate(s)");
    }
}
