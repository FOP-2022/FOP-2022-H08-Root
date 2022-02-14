package h08;

import h08.roommanagement.InsufficientNumberOfSeatsException;
import h08.roommanagement.NoCertificateException;
import h08.roommanagement.Room;
import h08.roommanagement.Student;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The JUnit tests for H7.
 */
@TestForSubmission("h08")
public class H7_Test {

    String names = "AhcusahNaisieboiLohghuGaeRiuewophahlahaquahghaiquievaepievoyaikauXaekaegeekeiyeCixueghodieSivaiphieC";

    @Test
    public void testConstructorContentInsufficientNumberOfSeatsException() {
        int numberOfSeats = 126;
        int seatsMissing = 6;
        InsufficientNumberOfSeatsException e;
        Room r;

        //test getMessage()
        for (int i = 0; i < 99; i += 3) {
            String tmpName = "" + names.charAt(i) + names.charAt(i + 1) + names.charAt(i + 2);
            r = new Room(tmpName, numberOfSeats);
            e = new InsufficientNumberOfSeatsException(r, seatsMissing);
            assertEquals(tmpName + " has not enough seats", e.getMessage(),
                "InsufficientNumberOfSeatsException has wrong message: " + e.getMessage());
        }


    }

    @Test
    public void testGetNumberOfMissingSeats() {
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
    }

    @Test
    public void testContentNoCertificateException() {
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
    public void testCheckRegistrationWithoutException() {
        Method[] methods = Main.class.getDeclaredMethods();
        for (Method m : methods) {
            if (m.getName().equals("checkRegistration")) {
                assertTrue(isPublic(m.getModifiers()), "checkRegistration is not public");
                assertTrue(isStatic(m.getModifiers()), "checkRegistration is not static");
            }
        }

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

        //works
        try {
            Main.checkRegistration(studentsOK, bigRoom);
        } catch (Exception e) {
            fail("Exception was thrown although registration should not fail: " + e.getMessage());
        }
    }

    @Test
    public void testCheckRegistrationWithException() {
        int studNr = 20;
        Student[] studentsOK = new Student[studNr];
        Student[] studentsNoCert = new Student[studNr];
        String realName;
        String nameNoCert = null;
        for (int i = 0; i < studNr; i++) {
            realName = "";
            for (int j = 0; j < 5; j++) {
                realName += names.charAt(i + j);
            }
            studentsOK[i] = new Student(realName, true);
            studentsNoCert[i] = new Student(realName, i != studNr / 2);
            if (i == studNr / 2) {
                nameNoCert = realName;
            }
        }

        Room bigRoom = new Room("big", studNr * 2);
        Room smallRoom = new Room("small", studNr * 2 - 2);
        boolean excThrown = false;

        //noCertificate
        try {
            Main.checkRegistration(studentsNoCert, bigRoom);
        } catch (NoCertificateException e) {
            excThrown = true;
            Student[] noCertStud = new Student[1];
            noCertStud[0] = new Student(nameNoCert, false);
            assertEquals((new NoCertificateException(noCertStud)).getMessage(), e.getMessage(),
                "NoCertificateException has wrong student array parameter");
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
            assertEquals((new InsufficientNumberOfSeatsException(smallRoom, 1)).getMessage(), e.getMessage(),
                "InsufficientNumberOfSeatsException gets no or wrong Room");
            assertEquals(2, e.getNumberOfMissingSeats(), "InsufficientNumberOfSeatsException gets "
                + "wrong number of missing seats");
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
            assertEquals((new InsufficientNumberOfSeatsException(smallRoom, 1)).getMessage(), e.getMessage(),
                "InsufficientNumberOfSeatsException gets no or wrong Room");
            assertEquals(2, e.getNumberOfMissingSeats(), "InsufficientNumberOfSeatsException gets "
                + "wrong number of missing seats");
        } catch (Exception e) {
            fail("wrong Exception was thrown: " + e.getClass() + " " + e.getMessage());
        }
        assertTrue(excThrown, "no InsufficientNumberOfSeatsException was thrown although it should");

    }
}
