
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
        if(aMovement == Movement.L) {
            if(currentDirection == Direction.N) {
                currentDirection = Direction.W;
            } else if(currentDirection == Direction.E) {
                currentDirection = Direction.N;
            } else if(currentDirection == Direction.S) {
                currentDirection = Direction.E;
            } else {
                currentDirection = Direction.S;
            }
        } else if(aMovement == Movement.R) {
            if(currentDirection == Direction.N) {
                currentDirection = Direction.E;
            } else if(currentDirection == Direction.E) {
                currentDirection = Direction.S;
            } else if(currentDirection == Direction.S) {
                currentDirection = Direction.W;
            } else {
                currentDirection = Direction.N;
            }
        } else {
            if(currentDirection == Direction.N) {
                currentY += 1;
            } else if(currentDirection == Direction.E) {
                currentX += 1;
            } else if(currentDirection == Direction.S) {
                currentY -= 1;
            } else {
                currentX -= 1;
            }
        }
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

    public Integer getNumber() {
        return id + 1;
    }

}
