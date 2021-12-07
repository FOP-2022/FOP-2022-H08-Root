package h08;

public class Student {
  public final int roomPreference;
  public final String name;

  public int actualRoom = -1;
  public OvercrowdedRoomException overcrowdedRoomException = null;

  public Student(int roomPreference, String name) {
    this.roomPreference = roomPreference;
    this.name = name;
  }

}
