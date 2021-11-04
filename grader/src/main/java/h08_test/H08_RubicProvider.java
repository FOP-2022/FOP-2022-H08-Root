package h08_test;

import org.sourcegrade.jagr.api.rubric.*;

public class H08_RubicProvider implements RubricProvider {
  public static final Criterion H1_T1 = Criterion.builder()
    .shortDescription("Klasse TimeStamp, der Konstruktor und das Attribut lastUpdate existieren")
    .grader(Grader.testAwareBuilder()
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
        H1_Test.class.getMethod("content")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();


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

  /* muss mit and verknüpfung in eine abfrage rein

  public static final Criterion H2_T3_1 = Criterion.builder()
    .shortDescription("update(Calendar c) funktioniert im Fall mit validem Kalender korrekt")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() ->
        H2_Test.class.getMethod("testContentTimeStampUpdateWithParameterTooEarlyCase")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();





  public static final Criterion H2_T3_2 = Criterion.builder()
    .shortDescription("update(Calendar c) funktioniert immer korrekt")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() ->
        H2_Test.class.getMethod("testContentTimeStampUpdateWithParameterTooLateCase")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();


   */

  public static final Criterion H3_T1 = Criterion.builder()
    .shortDescription("Alle drei Klassen existieren korrekt")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() ->
        H3_Definition_Test.class.getMethod("checkClasses")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  //hier muss 2* eine und Verknüpfung initialisiert werden

  //fehlt noch einiges





  public static final Criterion H1 = Criterion.builder()
    .shortDescription("H1 – Vorbereitung")
    .addChildCriteria(
      H1_T1,
      H1_T2
    )
    .build();

  @Override
  public Rubric getRubric() {
    return null;
  }
}
