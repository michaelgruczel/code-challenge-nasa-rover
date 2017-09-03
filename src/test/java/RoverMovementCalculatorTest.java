import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class RoverMovementCalculatorTest {

    private RoverMovementCalculator testee;

    @Test
    public void test_file_does_not_exists() throws Exception  {

        testee = new RoverMovementCalculator();

        testee.calculateMovements("doesnotexists");

        Assert.assertFalse(testee.getResult().isFileValid());
        Assert.assertEquals("file doesnotexists not found", testee.getResult().getMessage());

    }

    @Test
    public void test_file_has_only_2_lines() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_only_2_lines").getPath()).getAbsolutePath());

        Assert.assertFalse(testee.getResult().isFileValid());
        Assert.assertTrue(testee.getResult().getMessage().endsWith("Testinput_fail_only_2_lines needs to have at least 3 lines"));
    }

    @Test
    public void test_file_has_no_valid_coords_in_first_line() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_no_coords_in_first_line").getPath()).getAbsolutePath());

        Assert.assertFalse(testee.getResult().isFileValid());
        Assert.assertTrue(testee.getResult().getMessage().endsWith("Testinput_fail_no_coords_in_first_line should start with size: <MAX X> <MAX Y>"));

    }

    @Test
    public void test_file_illegal_direction_rover1() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_illegal_direction_rover1").getPath()).getAbsolutePath());

        Assert.assertFalse(testee.getResult().isFileValid());
        Assert.assertEquals("invalid position of rover 1", testee.getResult().getMessage());

    }

    @Test
    public void test_file_field_only_one_field() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_field_only_one_field").getPath()).getAbsolutePath());

        Assert.assertFalse(testee.getResult().isFileValid());
        Assert.assertEquals("field has to be bigger than one field", testee.getResult().getMessage());

    }

    @Test
    public void test_file_no_direction_rover2() throws Exception {

        RoverMovementCalculator testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_no_direction_rover2").getPath()).getAbsolutePath());

        Assert.assertFalse(testee.getResult().isFileValid());
        Assert.assertEquals("rover start postion not valid, line4;", testee.getResult().getMessage());

    }

    @Test
    public void test_file_illegal_coords_rover2() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_illegal_coords_rover2").getPath()).getAbsolutePath());

        Assert.assertFalse(testee.getResult().isFileValid());
        Assert.assertEquals("rover start postion not valid, line 4;", testee.getResult().getMessage());

    }

    @Test
    public void test_file_illegal_direction_rover2() throws Exception {

        RoverMovementCalculator testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_illegal_direction_rover2").getPath()).getAbsolutePath());

        Assert.assertFalse(testee.getResult().isFileValid());
        Assert.assertEquals("rover start postion not valid, line 4;", testee.getResult().getMessage());

    }

    @Test
    public void test_file_illegal_movement() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("TestInput_fail_illegal_movements").getPath()).getAbsolutePath());

        Assert.assertFalse(testee.getResult().isFileValid());
        Assert.assertEquals("rover line 2 movements contain invalid symbols;", testee.getResult().getMessage());

    }


    @Test
    public void test_valid_File_1() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_valid_1").getPath()).getAbsolutePath());

        Assert.assertTrue(testee.getResult().isFileValid());
        Assert.assertTrue(testee.getResult().isMovementsValid());
        Assert.assertEquals(2, testee.getResult().getRoversInEndPositions().size());

        Rover roverOne = testee.getResult().getRoversInEndPositions().get(0);
        Assert.assertEquals(1, roverOne.getCurrentX().intValue());
        Assert.assertEquals(3, roverOne.getCurrentY().intValue());
        Assert.assertEquals(Direction.N, roverOne.getCurrentDirection());

        Rover roverTwo = testee.getResult().getRoversInEndPositions().get(1);
        Assert.assertEquals(5, roverTwo.getCurrentX().intValue());
        Assert.assertEquals(1, roverTwo.getCurrentY().intValue());
        Assert.assertEquals(Direction.E, roverTwo.getCurrentDirection());

    }

    @Test
    public void test_valid_File_2() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_valid_2").getPath()).getAbsolutePath());

        Assert.assertTrue(testee.getResult().isFileValid());
        Assert.assertTrue(testee.getResult().isMovementsValid());
        Assert.assertEquals(2, testee.getResult().getRoversInEndPositions().size());

        Rover roverOne = testee.getResult().getRoversInEndPositions().get(0);
        Assert.assertEquals(1, roverOne.getCurrentX().intValue());
        Assert.assertEquals(3, roverOne.getCurrentY().intValue());
        Assert.assertEquals(Direction.N, roverOne.getCurrentDirection());

        Rover roverTwo = testee.getResult().getRoversInEndPositions().get(1);
        Assert.assertEquals(3, roverTwo.getCurrentX().intValue());
        Assert.assertEquals(3, roverTwo.getCurrentY().intValue());
        Assert.assertEquals(Direction.E, roverTwo.getCurrentDirection());

    }

    @Test
    public void test_out_of_bounds_east() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_out_of_bounds_east").getPath()).getAbsolutePath());

        Assert.assertTrue(testee.getResult().isFileValid());
        Assert.assertFalse(testee.getResult().isMovementsValid());
        Assert.assertEquals("out of bounds x coord rover index 1(rover 2);", testee.getResult().getMessage());

    }

    @Test
    public void test_out_of_bounds_north() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_out_of_bounds_north").getPath()).getAbsolutePath());

        Assert.assertTrue(testee.getResult().isFileValid());
        Assert.assertFalse(testee.getResult().isMovementsValid());
        Assert.assertEquals("out of bounds y coord rover index 1(rover 2);", testee.getResult().getMessage());

    }

    @Test
    public void test_out_of_bounds_negative_south() throws Exception {

        RoverMovementCalculator testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_out_of_bounds_negative_south").getPath()).getAbsolutePath());

        Assert.assertTrue(testee.getResult().isFileValid());
        Assert.assertFalse(testee.getResult().isMovementsValid());
        Assert.assertEquals("out of bounds y coord rover index 1(rover 2);", testee.getResult().getMessage());

    }

    @Test
    public void test_out_of_bounds_negative_coords_west() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_out_of_bounds_negative_west").getPath()).getAbsolutePath());

        Assert.assertTrue(testee.getResult().isFileValid());
        Assert.assertFalse(testee.getResult().isMovementsValid());
        Assert.assertEquals("out of bounds x coord rover index 1(rover 2);", testee.getResult().getMessage());

    }

    @Test
    public void test_colission_1() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_collision_start").getPath()).getAbsolutePath());

        Assert.assertTrue(testee.getResult().isFileValid());
        Assert.assertFalse(testee.getResult().isMovementsValid());
        Assert.assertEquals("given the input the rovers already collide in start position", testee.getResult().getMessage());

    }

    @Test
    public void test_colission_2() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_collision_r1_into_r2").getPath()).getAbsolutePath());

        Assert.assertTrue(testee.getResult().isFileValid());
        Assert.assertFalse(testee.getResult().isMovementsValid());
        Assert.assertEquals("Rover 1 collision with Rover 2 at (3,3)", testee.getResult().getMessage());

    }

    @Test
    public void test_colission_3() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_collision_r2_into_r1").getPath()).getAbsolutePath());

        Assert.assertTrue(testee.getResult().isFileValid());
        Assert.assertFalse(testee.getResult().isMovementsValid());
        Assert.assertEquals("Rover 2 collision with Rover 1 at (1,3)", testee.getResult().getMessage());

    }


    @Test
    public void test_extractRoverPositionData() throws Exception {

        RoverMovementCalculator testee1 = new RoverMovementCalculator();
        Assert.assertEquals(true, testee1.extractRoverPositionData(0, 0, "3 7 N"));
        Assert.assertEquals(3, testee1.roversInStartPosition.get(0).getCurrentX().intValue());
        Assert.assertEquals(7, testee1.roversInStartPosition.get(0).getCurrentY().intValue());
        Assert.assertEquals(Direction.N, testee1.roversInStartPosition.get(0).getCurrentDirection());

        RoverMovementCalculator testee2 = new RoverMovementCalculator();
        Assert.assertEquals(true, testee2.extractRoverPositionData(0, 0, "22 2 S"));
        Assert.assertEquals(22, testee2.roversInStartPosition.get(0).getCurrentX().intValue());
        Assert.assertEquals(2, testee2.roversInStartPosition.get(0).getCurrentY().intValue());
        Assert.assertEquals(Direction.S, testee2.roversInStartPosition.get(0).getCurrentDirection());

        RoverMovementCalculator testee3 = new RoverMovementCalculator();
        Assert.assertEquals(true, testee3.extractRoverPositionData(0, 0, "4 4 W"));
        Assert.assertEquals(4, testee3.roversInStartPosition.get(0).getCurrentX().intValue());
        Assert.assertEquals(4, testee3.roversInStartPosition.get(0).getCurrentY().intValue());
        Assert.assertEquals(Direction.W, testee3.roversInStartPosition.get(0).getCurrentDirection());

        RoverMovementCalculator testee4 = new RoverMovementCalculator();
        Assert.assertEquals(true, testee4.extractRoverPositionData(0, 0, "6 6 E"));
        Assert.assertEquals(6, testee4.roversInStartPosition.get(0).getCurrentX().intValue());
        Assert.assertEquals(6, testee4.roversInStartPosition.get(0).getCurrentY().intValue());
        Assert.assertEquals(Direction.E, testee4.roversInStartPosition.get(0).getCurrentDirection());

        RoverMovementCalculator testee5 = new RoverMovementCalculator();
        Assert.assertEquals(false, testee5.extractRoverPositionData(0, 0, "6 6 X"));

        RoverMovementCalculator testee6 = new RoverMovementCalculator();
        Assert.assertEquals(false, testee6.extractRoverPositionData(0, 0, "6 a N"));
    }

    @Test
    public void test_extractRoverMovementData() throws Exception {

        RoverMovementCalculator testee1 = new RoverMovementCalculator();
        Assert.assertEquals(true, testee1.extractRoverMovementData(0, 0, "LLRM"));
        Assert.assertEquals(Movement.L, testee1.plannedRoverMoves.get(0).get(0));
        Assert.assertEquals(Movement.L, testee1.plannedRoverMoves.get(0).get(1));
        Assert.assertEquals(Movement.R, testee1.plannedRoverMoves.get(0).get(2));
        Assert.assertEquals(Movement.M, testee1.plannedRoverMoves.get(0).get(3));

        RoverMovementCalculator testee2 = new RoverMovementCalculator();
        Assert.assertEquals(false, testee2.extractRoverPositionData(0, 0, "LxRM"));

    }
}
