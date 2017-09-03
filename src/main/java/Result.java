import java.util.ArrayList;
import java.util.List;

/**
 * Created by gruczm on 03.09.2017.
 */
public class Result {

    private List<Rover> roversInEndPositions;
    private String message;
    private boolean fileValid;
    private boolean movementsValid;


    /**
     * onstructor for Result for use in case success.
     * @param roversInEndPositions list of rovers after calculation.
     */
    public Result(List<Rover> roversInEndPositions) {
        this.roversInEndPositions = roversInEndPositions;
        this.fileValid = true;
        this.movementsValid = true;
        this.message = "";
    }

    /**
     * Constructor for Result for use in case of en error.
     * Rover Endpoints will be an empty list.
     * @param fileValid is file syntactically valid.
     * @param movementsValid are the movements valid or would they lead to damage.
     * @param message more concete error message.
     */
    public Result(boolean fileValid, boolean movementsValid, String message) {
        this.roversInEndPositions = new ArrayList<Rover>();
        this.fileValid = fileValid;
        this.movementsValid = movementsValid;
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public boolean isFileValid() {
        return fileValid;
    }

    public boolean isMovementsValid() {
        return movementsValid;
    }

    public List<Rover> getRoversInEndPositions() {
        return roversInEndPositions;
    }
}
