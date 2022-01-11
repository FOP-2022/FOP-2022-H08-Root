package h08.roommanagement;

public class Room {

    public final String name;
    public final int numberOfSeats;

    public Room(String name, int numberOfSeats) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Room{" + "name='" + name + '\'' + ", numberOfSeats=" + numberOfSeats + '}';
    }
}
