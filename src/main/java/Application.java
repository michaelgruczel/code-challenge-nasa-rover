
public class Application {

    public static void main(String args[]) throws Exception {

        if(args.length !=1) {
            System.out.println("Usage: java -jar RoverCli.jar <PATH TO FILE WITH COMMANDS>, more details by java -jar RoverCli.jar -help");
        } else if(args[0].equalsIgnoreCase("-help")) {
            System.out.println("Usage: java -jar RoverCli.jar <PATH TO FILE WITH COMMANDS>");
            printFileFormatHelp();
        }  else {
            RoverMovementCalculator roverMovementCalculator = new RoverMovementCalculator();
            roverMovementCalculator.calculateMovements(args[0]);
            if(roverMovementCalculator.getResult().isFileValid() && roverMovementCalculator.getResult().isMovementsValid()) {
                System.out.println("rover end positions:");
                for(Rover aRover : roverMovementCalculator.getResult().getRoversInEndPositions()) {
                    System.out.println("Rover " + aRover.getNumber() + " position:"
                            + aRover.getCurrentX() + " " + aRover.getCurrentY() + " "
                            + aRover.getCurrentDirection().name());
                }
            } else {
                System.out.println("your data is not valid (" + roverMovementCalculator.getResult().getMessage() + ").");
                if(roverMovementCalculator.getResult().isFileValid()) {
                    System.out.println("Your input file in correct, but the execution of the movements would lead to possible damage.");
                } else {
                    System.out.println("your input file is not correct");
                    printFileFormatHelp();
                }
            }
        }
    }

    private static void printFileFormatHelp() {
        System.out.println("File format:");
        System.out.println("<MAX X COORDINATE> <MAX Y COORDINATE>");
        System.out.println("<X COORDINATE ROVER 1> <Y COORDINATE ROVER 1> <DIRECTION ROVER 1>");
        System.out.println("<COMMAND ROVER 1><COMMAND ROVER 1><COMMAND ROVER 1><COMMAND ROVER 1> ...");
        System.out.println("<X COORDINATE ROVER 2> <Y COORDINATE ROVER 2> <DIRECTION ROVER 2>");
        System.out.println("<COMMAND ROVER 2><COMMAND ROVER 2><COMMAND ROVER 1><COMMAND ROVER 2> ...");
        System.out.println("...");
        System.out.println("legal commands are: M for move, L for turning left, R for turning right, ...");
        System.out.println("legal directions are: N for North, W for West, E for East and S for South");
        System.out.println("for example:");
        System.out.println("5 5");
        System.out.println("1 2 N");
        System.out.println("LMLMLMLMM");
        System.out.println("3 3 E");
        System.out.println("MMRMMRMRRM");
    }
}
