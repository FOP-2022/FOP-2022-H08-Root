package h08;


import h08.roommanagement.InsufficientNumberOfSeatsException;
import h08.roommanagement.NoCertificateException;
import h08.roommanagement.Room;
import h08.roommanagement.Student;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The JUnit tests for H7.
 */
@TestForSubmission("h08")
public class H7_Test {

    String names = "AhcusahNaisieboiLohghuGaeRiuewophahlahaquahghaiquievaepievoyaikauXaekaegeekeiyeCixueghodieSivaiphieC";

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
        int numberOfSeats = 126;
        int seatsMissing;
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
            assertEquals(tmpName + " has not enough seats", e.getMessage(),
                "InsufficientNumberOfSeatsException has wrong message: " + e.getMessage());
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
        assertTrue(1 == allconstructors.length,
            "there is more than one constructor in InsufficientNumberOfSeatsException");

    }


    @Test
    void testContentNoCertificateException() {
        int studNr = 20; //bei Erhöhung muss names verlängert werden
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
        assertEquals(Arrays.stream(students).map(stud -> stud.name).collect(Collectors.joining(", "))
            + " has/have no certificate(s)", e.getMessage(), "NoCertificateException has wrong message: "
            + e.getMessage());


    }

    @Test
    void testCheckRegistration() {
        int studNr = 20;
        Student[] studentsOK = new Student[studNr];
        Student[] studentsNoCert = new Student[studNr];
        String realName;
        for (int i = 0; i < studNr; i++) {
            realName = "";
            for (int j = 0; j < 5; j++) {
                realName += names.charAt(i + j);
            }
            studentsOK[i] = new Student(realName, true);
            studentsNoCert[i] = new Student(realName, i != studNr / 2);
        }

        Room bigRoom = new Room("big", studNr * 2);
        Room smallRoom = new Room("small", studNr * 2 - 2);
        boolean excThrown = false;

        //works
        try {
            Main.checkRegistration(studentsOK, bigRoom);
        } catch (Exception e) {
            fail("Exceptino was thrown although registration should not fail: " + e.getMessage());
        }

        //noCertificate
        try {
            Main.checkRegistration(studentsNoCert, bigRoom);
        } catch (NoCertificateException e) {
            excThrown = true;
        } catch (Exception e) {
            fail("wrong Exception was thrown: " + e.getClass() + " " + e.getMessage());
        }
        assertTrue(excThrown, "no NoCertificateException was thrown although it should");
        excThrown = false;

        //room too small
        try {
            Main.checkRegistration(studentsOK, smallRoom);
        } catch (InsufficientNumberOfSeatsException e) {
            excThrown = true;
        } catch (Exception e) {
            fail("wrong Exception was thrown: " + e.getClass() + " " + e.getMessage());
        }
        assertTrue(excThrown, "no InsufficientNumberOfSeatsException was thrown although it should");
        excThrown = false;

        //both fail --> InsufficientNumberOfSeatsException
        try {
            Main.checkRegistration(studentsNoCert, smallRoom);
        } catch (InsufficientNumberOfSeatsException e) {
            excThrown = true;
        } catch (Exception e) {
            fail("wrong Exception was thrown: " + e.getClass() + " " + e.getMessage());
        }
        assertTrue(excThrown, "no InsufficientNumberOfSeatsException was thrown although it should");
        excThrown = false;

    }

}
