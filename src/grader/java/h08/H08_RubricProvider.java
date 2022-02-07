package h08;

import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.Grader;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricForSubmission;
import org.sourcegrade.jagr.api.rubric.RubricProvider;
import org.sourcegrade.jagr.api.testing.RubricConfiguration;

@RubricForSubmission("h08")
public class H08_RubricProvider implements RubricProvider {
    //---------------------- H1 -------------------------
    public static final Criterion H1_T1 = Criterion.builder()
        .shortDescription("Klasse TimeStamp, der Konstruktor und das Attribut lastUpdate existieren")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H1_Definition_Test.class.getMethod("classExistence")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H1_Definition_Test.class.getMethod("testConstructorExistence")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H1_Definition_Test.class.getMethod("testDefinitionAndAttribute")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H1_T2 = Criterion.builder()
        .shortDescription("Void update() und Calendar getTimeStamp() existieren und diese und der Konstruktor funktionieren")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H1_Test.class.getMethod("testConstructorContent")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H1_Test.class.getMethod("testTimeStampUpdateWithoutParameter")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H1_Test.class.getMethod("testGetTimeStamp")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    //---------------------- H2 -------------------------

    public static final Criterion H2_T1 = Criterion.builder()
        .shortDescription("Methode update(Calendar c) existiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H2_Test.class.getMethod("testDefinitionTimeStampUpdateWithParameter")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H2_T2 = Criterion.builder()
        .shortDescription("Methode update(Calendar c) existiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H2_Test.class.getMethod("testContentTimeStampUpdateWithParameterCorrectCase")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H2_T3 = Criterion.builder()
        .shortDescription("update(Calendar c) funktioniert im Fall mit validem Kalender korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H2_Test.class.getMethod("testContentTimeStampUpdateWithParameterTooEarlyCase")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H2_Test.class.getMethod("testContentTimeStampUpdateWithParameterTooLateCase")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    //---------------------- H3 -------------------------

    public static final Criterion H3_T1 = Criterion.builder()
        .shortDescription("Alle drei Klassen existieren korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H3_Definition_Test.class.getMethod("checkClassBadUpdateTimeException")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H3_Definition_Test.class.getMethod("checkClassUpdateTimeBeforeLastUpdateException")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H3_Definition_Test.class.getMethod("checkClassUpdateTimeInTheFutureException")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H3_T2 = Criterion.builder()
        .shortDescription("Der Konstruktor von BadUpdateTimeException setzt den korrekten String")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H3_Test.class.getMethod("testConstructorExistenceBadUpdateTimeException")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H3_Test.class.getMethod("testConstructorContentBadUpdateTimeException")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H3_T3 = Criterion.builder()
        .shortDescription("Die Klassen UpdateTimeBeforeLastUpdateException und UpdateTimeInTheFutureException sind korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H3_Test.class.getMethod("testConstructorExistenceUpdateTimeBeforeLastUpdateException")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H3_Test.class.getMethod("testConstructorContentUpdateTimeBeforeLastUpdateException")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H3_Test.class.getMethod("testConstructorExistenceUpdateTimeInTheFutureException")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H3_Test.class.getMethod("testConstructorContentUpdateTimeInTheFutureException")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    //---------------------- H4 -------------------------

    public static final Criterion H4_T1 = Criterion.builder()
        .shortDescription("Alle fünf Methoden existieren korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H4_Test.class.getMethod("testExistenceUpdateWithExc", int.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H4_T2 = Criterion.builder()
        .shortDescription("Methoden updateWithExcn mit n aus {1,2,3} sind korrekt implementiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H4_Test.class.getMethod("testContentUpdateWithExc123")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H4_T3 = Criterion.builder()
        .shortDescription("Methode updateWithExc4 ist korrekt implementiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H4_Test.class.getMethod("testContentUpdateWithExc4")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H4_T4 = Criterion.builder()
        .shortDescription("Methode updateWithExc5 ist korrekt implementiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H4_Test.class.getMethod("testContentUpdateWithExc5")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    //---------------------- H5 -------------------------

    public static final Criterion H5_T1 = Criterion.builder()
        .shortDescription("Die Klasse TestTimeStampException und alle fünf Methoden testCatchn existieren korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H5_Definition_Test.class.getMethod("testExistenceTestCatch", int.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H5_T2 = Criterion.builder()
        .shortDescription("In allen Methoden wird der Fall, dass keine Exception geworfen wird (also keine Ausgabe)"
            + " mit allen n richtig behandelt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H5_Test.class.getMethod("testContentTestCatchShouldWork", int.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H5_T3 = Criterion.builder()
        .shortDescription("Die try-catch-Klausel bei testCatch1 und testCatch3 ist vollständig korrekt"
            + " und liefert die korrekte Ausgabe")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H5_Test.class.getMethod("testContentTestCatch1115_3135")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H5_T4 = Criterion.builder()
        .shortDescription("Die try-catch-Klausel bei testCatch2 ist vollständig korrekt"
            + " und liefert die korrekte Ausgabe")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H5_Test.class.getMethod("testContentTestCatch2125")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H5_T5 = Criterion.builder()
        .shortDescription("Die try-catch-Klausel bei testCatch4 und testCatch5 ist vollständig korrekt"
            + " und liefert die korrekte Ausgabe")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H5_Test.class.getMethod("testContentTestCatch4145_5155")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H5_T6 = Criterion.builder()
        .shortDescription("Die gesamte Aufgabe wurde korrekt bewältigt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H5_Test.class.getMethod("testContentTestCatch1115_3135")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H5_Test.class.getMethod("testContentTestCatch2125")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H5_Test.class.getMethod("testContentTestCatch4145_5155")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H5_Test.class.getMethod("testContentTestCatchShouldWork", int.class)))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H5_Test.class.getMethod("testSwitchCase")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    //---------------------- H6 -------------------------

    public static final Criterion H6_T1 = Criterion.builder()
        .shortDescription("Methode testPass existiert und ist korrekt implementiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H6_Test.class.getMethod("testExistenceTestPass")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H6_Test.class.getMethod("testTestPassContent")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H6_T2 = Criterion.builder()
        .shortDescription("Methode testCatchpassed existiert und ist korrekt implementiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H6_Test.class.getMethod("testExistenceTestCatchPassed")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H6_Test.class.getMethod("testTestCatchPassedContent")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    //---------------------- H7.1 -------------------------

    public static final Criterion H7_1_T1 = Criterion.builder()
        .shortDescription("Die beiden Exception Klassen existieren und die Methode getNumberOfMissingSeats funktioniert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H7_Definition_Test.class.getMethod("testClassExistenceInsufficentNumberOfSeatsException")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H7_Definition_Test.class.getMethod("testClassExistenceNoCertificateException")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H7_Test.class.getMethod("testGetNumberOfMissingSeats")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H7_1_T2 = Criterion.builder()
        .shortDescription("InsufficientNumberOfSeatsException existiert und die message wird korrekt gesetzt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H7_Definition_Test.class.getMethod("testConstructorExistenceInsufficientNumberOfSeatsException")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H7_Test.class.getMethod("testConstructorContentInsufficientNumberOfSeatsException")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H7_1_T3 = Criterion.builder()
        .shortDescription("NoCertificateException existiert und die message wird korrekt gesetzt")
        .maxPoints(2)
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H7_Definition_Test.class.getMethod("testConstructorExistenceNoCertificateException")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H7_Test.class.getMethod("testContentNoCertificateException")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    //---------------------- H7.2 -------------------------

    public static final Criterion H7_2_T1 = Criterion.builder()
        .shortDescription("Die Methode funktioniert im Fall, dass der Raum groß genug ist und die Studenten "
            + "alle die 3G Regel erfüllen")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H7_Test.class.getMethod("testCheckRegistrationWithoutException")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H7_2_T2 = Criterion.builder()
        .shortDescription("Die Methode funktioniert im Fall, dass irgendeine Exception geworfen werden soll")
        .maxPoints(2)
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H7_Test.class.getMethod("testCheckRegistrationWithException")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    public static final Criterion H7_2_T3 = Criterion.builder()
        .shortDescription("Die Methode funktioniert insgesamt korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() ->
                H7_Test.class.getMethod("testCheckRegistrationWithoutException")))
            .requirePass(JUnitTestRef.ofMethod(() ->
                H7_Test.class.getMethod("testCheckRegistrationWithException")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build()
        ).build();

    //---------------------- Zusammenfassungen -----------------------

    public static final Criterion H1 = Criterion.builder()
        .shortDescription("H1 – Vorbereitung")
        .addChildCriteria(
            H1_T1,
            H1_T2
        ).build();

    public static final Criterion H2 = Criterion.builder()
        .shortDescription("H2 – Mit assert arbeiten")
        .addChildCriteria(
            H2_T1,
            H2_T2,
            H2_T3
        ).build();

    public static final Criterion H3 = Criterion.builder()
        .shortDescription("H3 – Exception-Klassen definieren")
        .addChildCriteria(
            H3_T1,
            H3_T2,
            H3_T3
        ).build();

    public static final Criterion H4 = Criterion.builder()
        .shortDescription("H4 – Exception werfen")
        .addChildCriteria(
            H4_T1,
            H4_T2,
            H4_T3,
            H4_T4
        ).build();

    public static final Criterion H5 = Criterion.builder()
        .shortDescription("H5 – Exception werfen")
        .addChildCriteria(
            H5_T1,
            H5_T2,
            H5_T3,
            H5_T4,
            H5_T5,
            H5_T6
        )
        .build();

    public static final Criterion H6 = Criterion.builder()
        .shortDescription("H6 – Exception weiterreichen")
        .addChildCriteria(
            H6_T1,
            H6_T2
        )
        .build();

    public static final Criterion H7_1 = Criterion.builder()
        .shortDescription("H7.1 – Exception-Klassen")
        .addChildCriteria(
            H7_1_T1,
            H7_1_T2,
            H7_1_T3
        )
        .build();

    public static final Criterion H7_2 = Criterion.builder()
        .shortDescription("H7.2 – Überprüfung")
        .addChildCriteria(
            H7_2_T1,
            H7_2_T2,
            H7_2_T3
        )
        .build();

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H08")
        .addChildCriteria(H1, H2, H3, H4, H5, H6, H7_1, H7_2)
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }

    @Override
    public void configure(RubricConfiguration configuration) {
        configuration.addTransformer(new ExceptionConstructorVerifier1());
        // configuration.addTransformer(new TimeStampVisitor.TimeStampTransformer());
    }
}
