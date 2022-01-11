package h08;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import h08.roommanagement.InsufficientNumberOfSeatsException;
import h08.roommanagement.NoCertificateException;
import h08.roommanagement.Room;
import h08.roommanagement.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Calendar;
import java.util.stream.Collectors;

import static java.lang.reflect.Modifier.isAbstract;
import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isPublic;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The JUnit tests for H7.
 */
@TestForSubmission("h08")
public class H7_Test {

    @Test
    void testConstructorExistenceInsufficientNumberOfSeatsException() {
        Constructor[] allconstructors = null;
        try {
            Constructor constructor = InsufficientNumberOfSeatsException.class.getDeclaredConstructor(Room.class, int.class);
            allconstructors = InsufficientNumberOfSeatsException.class.getConstructors();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            fail("constructor of UpdateTimeBeforeLastUpdateException wrong");
        }
        assertTrue(1 == allconstructors.length, "there is more than one constructor in InsufficientNumberOfSeatsException");

    }


    @Test
    void testContentInsufficientNumberOfSeatsException() {
        int numberOfSeats = 126, seatsMissing;
        String names = "AhcusahNaisieboiLohghuGaeRiewophahlahaquahghaiquievaepievoyaikauXaekaegeekeiyeCixueghodieSivaiphieC";
        InsufficientNumberOfSeatsException e;
        Room r;

        //test getNumberOfSeats
        r = new Room("einsA", numberOfSeats);
        for (seatsMissing = 1; seatsMissing < 500; seatsMissing += 7) {
            e = new InsufficientNumberOfSeatsException(r, seatsMissing);
            assertEquals(seatsMissing, e.getNumberOfMissingSeats(), "method getNumberOfMissingSeats does not work");
        }

        //test getMessage()
        for (int i = 0; i < 99; i += 3) {
            String tmpName = "" + names.charAt(i) + names.charAt(i + 1) + names.charAt(i + 2);
            r = new Room(tmpName, numberOfSeats);
            e = new InsufficientNumberOfSeatsException(r, seatsMissing);
            assertEquals(tmpName + " has not enough seats", e.getMessage(), "InsufficientNumberOfSeatsException has wrong message: " + e.getMessage());
        }

        //missing!!!!: getMessage() is NOT overwritten by studi


    }


    @Test
    void testConstructorExistenceNoCertificateException() {
        Constructor[] allconstructors = null;
        try {
            Constructor constructor = NoCertificateException.class.getDeclaredConstructor(Student[].class);
            allconstructors = InsufficientNumberOfSeatsException.class.getConstructors();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            fail("constructor of UpdateTimeBeforeLastUpdateException wrong");
        }
        assertTrue(1 == allconstructors.length, "there is more than one constructor in InsufficientNumberOfSeatsException");

    }


    @Test
    void testContentNoCertificateException() {
        int studNr = 20; //bei Erhöhung muss names verlängert werden
        String names = "AhcusahNaisieboiLohghuGaeRiuewophahlahaquahghaiquievaepievoyaikauXaekaegeekeiyeCixueghodieSivaiphieC";
        NoCertificateException e;
        Student[] students = new Student[studNr];
        String realName;
        for (int i = 0; i < studNr; i++) {
            realName = "";
            for (int j = 0; j < 5; j++) {
                realName += names.charAt(i + j);
            }
            students[i] = new Student(realName, false);
        }

        //test getMessage()
        e = new NoCertificateException(students);
        assertEquals(Arrays.stream(students).map(stud -> stud.name).collect(Collectors.joining(", ")) + " has/have no certificate(s)", e.getMessage(), "NoCertificateException has wrong message: " + e.getMessage());


    }

}
