
public class Rover {

    Integer id;
    Direction currentDirection;
    Integer currentX;
    Integer currentY;

    public Rover(int id, int x, int y, Direction direction) {
        this.id = id;
        currentX = x;
        currentY = y;
        currentDirection = direction;
    }

    public void move(Movement aMovement) {
        throw new NotYetImplementedException();

    }

    public Integer getId() {
        return id;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public Integer getCurrentX() {
        return currentX;
    }

    public Integer getCurrentY() {
        return currentY;
    }


}
