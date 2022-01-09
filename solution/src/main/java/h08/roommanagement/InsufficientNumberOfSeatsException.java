package h08.roommanagement;

public class InsufficientNumberOfSeatsException extends RuntimeException{
  int numberOfMissingSeats;
  public InsufficientNumberOfSeatsException(Room room, int numberOfMissingSeats) {
    super(room.name + " has not enough seats");
    this.numberOfMissingSeats = numberOfMissingSeats;
  }

  public int getNumberOfMissingSeats() {
    return numberOfMissingSeats;
  }


}
