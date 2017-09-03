import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RoverMovementCalculator {

    // rover-id, List of planned moves
    protected Map<Integer, List<Movement>> plannedRoverMoves = new HashMap<>();
    // field size
    private Integer maxX;
    private Integer maxY;
    // rovers at the beginning and at the end
    protected List<Rover> roversInStartPosition = new ArrayList<>();
    protected List<Rover> roversInEndPosition = new ArrayList<>();
    // error handling
    boolean inputValid = false;
    boolean inputHealthy = false;
    String errorMessage = "not initialized";

    public void calculateMovements(String filePath) {
        init(filePath);
        if (inputValid) {
            calculateMovements();
        }
    }

    protected void init(String filePath) {
        plannedRoverMoves = new HashMap<>();
        roversInStartPosition = new ArrayList<>();
        roversInEndPosition = new ArrayList<>();
        inputValid = false;
        inputHealthy = false;
        errorMessage = "";

        // try with resource
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            List<String> lines = stream.collect(Collectors.toList());

            // some basic validations
            if (!validFileSize(filePath, lines)) return;
            if (!validFirstRoverPosition(filePath, lines)) return;
            if (!validateAndExtractFieldSize(filePath, lines)) return;

            // extract data from lines with rover data
            for (int i = 1; i < lines.size(); i++) {
                if (i % 2 == 1) {
                    int roverindex = i / 2;
                    String roverPosition = lines.get(i);
                    if (!extractRoverPositionData(roverindex, i, roverPosition)) return;
                    if (lines.size() > (i + 1)) {
                        if (!extractRoverMovementData(roverindex, i, lines.get(i+1))) return;
                    } else {
                        plannedRoverMoves.put(roverindex, new ArrayList<>());
                    }
                }

            }
            inputValid = true;
        } catch (NoSuchFileException e) {
            inputValid = false;
            errorMessage += "file " + filePath + " not found";
        } catch (IOException e) {
            inputValid = false;
            errorMessage += e.getMessage();
        }

    }

    private void calculateMovements() {
        inputHealthy = true;
        // order of rovers is important, since it can lead to crashes
        for (int i = 0; i < roversInStartPosition.size(); i++) {
            Rover aRover = roversInStartPosition.get(i);
            // check collision with rovers which are in start position
            for (int j = i + 1; j < roversInStartPosition.size(); j++) {
                if (aRover.getCurrentX() == roversInStartPosition.get(j).getCurrentX()
                        && aRover.getCurrentY() == roversInStartPosition.get(j).getCurrentY()) {
                    // collision
                    errorMessage += "given the input the rovers already collide in start position";
                    inputHealthy = false;
                    return;
                }
            }
            for (Movement aMovement : plannedRoverMoves.get(i)) {
                aRover.move(aMovement);
                // check out of bounds
                if (aRover.getCurrentX() > maxX) {
                    errorMessage += "out of bounds x coord rover index " + aRover.getId() + "(rover " + aRover.getNumber() + ");";
                    inputHealthy = false;
                    return;
                }
                if (aRover.getCurrentY() > maxY) {
                    errorMessage += "out of bounds y coord rover index " + aRover.getId() + "(rover " + aRover.getNumber() + ");";
                    inputHealthy = false;
                    return;
                }
                if (aRover.getCurrentX() < 0) {
                    errorMessage += "out of bounds x coord rover index " + aRover.getId() + "(rover " + aRover.getNumber() + ");";
                    inputHealthy = false;
                    return;
                }
                if (aRover.getCurrentY() < 0) {
                    errorMessage += "out of bounds y coord rover index " + aRover.getId() + "(rover " + aRover.getNumber() + ");";
                    inputHealthy = false;
                    return;
                }
                // check collisions with already moved rovers
                for (Rover aRoverPosition : roversInEndPosition) {
                    if (aRover.getCurrentX() == aRoverPosition.getCurrentX() && aRover.getCurrentY() == aRoverPosition.getCurrentY()) {
                        // collision
                        errorMessage += "Rover " + aRover.getNumber() + " collision with Rover " + aRoverPosition.getNumber()
                                + " at (" + aRover.getCurrentX() + "," + aRover.getCurrentY() + ")";
                        inputHealthy = false;
                        return;
                    }
                }
                // check collision with rovers which are in start position
                for (int j = i + 1; j < roversInStartPosition.size(); j++) {
                    if (aRover.getCurrentX() == roversInStartPosition.get(j).getCurrentX() && aRover.getCurrentY() == roversInStartPosition.get(j).getCurrentY()) {
                        // collision
                        errorMessage += "Rover " + aRover.getNumber() + " collision with Rover " + (roversInStartPosition.get(j).getNumber())
                                + " at (" + aRover.getCurrentX() + "," + aRover.getCurrentY() + ")";
                        inputHealthy = false;
                        return;
                    }
                }
            }
            // all moves done, so now let's add the rover to the end position and remove from start position
            roversInEndPosition.add(aRover);
        }
    }

    protected boolean extractRoverMovementData(int roverindex, int lineNumber, String roverMovement) {
        List<Movement> roverMoves = new ArrayList<>();
        if (roverMovement != null) {
            for (char ch : roverMovement.toCharArray()) {
                String aSingleMovement = Character.toString(ch);
                if (!(aSingleMovement.equals("L") || aSingleMovement.equals("M") || aSingleMovement.equals("R"))) {
                    inputValid = false;
                    errorMessage += "rover line " + (lineNumber + 1) + " movements contain invalid symbols;";
                    return false;
                }
                roverMoves.add(Movement.valueOf(aSingleMovement));
            }
            plannedRoverMoves.put(roverindex, roverMoves);
        }
        return true;
    }

    protected boolean extractRoverPositionData(int roverindex, int lineNumber, String roverPosition) {
        Integer x = null;
        Integer y = null;
        Direction direction = null;
        String[] parts = roverPosition.split(" ");
        if (parts.length != 3) {
            inputValid = false;
            errorMessage += "rover start postion not valid, line" + (lineNumber + 1) + ";";
            return false;
        }
        try {
            x = Integer.valueOf(parts[0]);
            y = Integer.valueOf(parts[1]);
        } catch (NumberFormatException nfe) {
            inputValid = false;
            errorMessage += "rover start postion not valid, line " + (lineNumber + 1) + ";";
            return false;
        }
        if (!(parts[2].equals("N") || parts[2].equals("E") || parts[2].equals("S") || parts[2].equals("W"))) {
            inputValid = false;
            errorMessage += "rover start postion not valid, line " + (lineNumber + 1) + ";";
            return false;
        }
        direction = Direction.valueOf(parts[2]);
        Rover aRover = new Rover(roverindex, x, y, direction);
        roversInStartPosition.add(aRover);
        return true;
    }

    protected boolean validateAndExtractFieldSize(String filePath, List<String> lines) {
        String[] fieldSizeElements = lines.get(0).split(" ");
        if (fieldSizeElements.length < 2) {
            inputValid = false;
            errorMessage += "file " + filePath + " should start with size: <MAX X> <MAX Y>";
            return false;
        }
        maxX = Integer.valueOf(fieldSizeElements[0]);
        maxY = Integer.valueOf(fieldSizeElements[1]);

        if (maxX < 2 && maxY < 2) {
            inputValid = false;
            errorMessage += "field has to be bigger than one field";
            return false;
        }

        return true;
    }

    protected boolean validFirstRoverPosition(String filePath, List<String> lines) {
        if (!Pattern.matches("[0-9]+ [0-9]+ [NWES]", lines.get(1))) {
            inputValid = false;
            errorMessage += "invalid position of rover 1";
            return false;
        }
        return true;
    }

    protected boolean validFileSize(String filePath, List<String> lines) {
        if (lines.size() < 3) {
            inputValid = false;
            errorMessage += "file " + filePath + " needs to have at least 3 lines";
            return false;
        }
        return true;
    }


    public Result getResult() {
        if(inputValid && inputHealthy) {
            return new Result(roversInEndPosition);
        } else {
            return new Result(inputValid, inputHealthy, errorMessage);
        }
    }

}
