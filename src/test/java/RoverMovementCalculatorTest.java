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
        Assert.assertEquals("?", testee.getResult().getMessage());
    }

    @Test
    public void test_file_has_no_valid_coords_in_first_line() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_no_coords_in_first_line").getPath()).getAbsolutePath());

        Assert.assertFalse(testee.getResult().isFileValid());
        Assert.assertEquals("?", testee.getResult().getMessage());

    }

    @Test
    public void test_file_illegal_direction_rover1() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_illegal_direction_rover1").getPath()).getAbsolutePath());

        Assert.assertFalse(testee.getResult().isFileValid());
        Assert.assertEquals("?", testee.getResult().getMessage());

    }

    @Test
    public void test_file_field_only_one_field() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_field_only_one_field").getPath()).getAbsolutePath());

        Assert.assertFalse(testee.getResult().isFileValid());
        Assert.assertEquals("?", testee.getResult().getMessage());

    }

    @Test
    public void test_file_no_direction_rover2() throws Exception {

        RoverMovementCalculator testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_no_direction_rover2").getPath()).getAbsolutePath());

        Assert.assertFalse(testee.getResult().isFileValid());
        Assert.assertEquals("?", testee.getResult().getMessage());

    }

    @Test
    public void test_file_illegal_coords_rover2() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_illegal_coords_rover2").getPath()).getAbsolutePath());

        Assert.assertFalse(testee.getResult().isFileValid());
        Assert.assertEquals("?", testee.getResult().getMessage());

    }

    @Test
    public void test_file_illegal_direction_rover2() throws Exception {

        RoverMovementCalculator testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_illegal_direction_rover2").getPath()).getAbsolutePath());

        Assert.assertFalse(testee.getResult().isFileValid());
        Assert.assertEquals("?", testee.getResult().getMessage());

    }

    @Test
    public void test_file_illegal_movement() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("TestInput_fail_illegal_movements").getPath()).getAbsolutePath());

        Assert.assertFalse(testee.getResult().isFileValid());
        Assert.assertEquals("?", testee.getResult().getMessage());

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
        Assert.assertEquals("?", testee.getResult().getMessage());

    }

    @Test
    public void test_out_of_bounds_north() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_out_of_bounds_north").getPath()).getAbsolutePath());

        Assert.assertTrue(testee.getResult().isFileValid());
        Assert.assertFalse(testee.getResult().isMovementsValid());
        Assert.assertEquals("?", testee.getResult().getMessage());

    }

    @Test
    public void test_out_of_bounds_negative_south() throws Exception {

        RoverMovementCalculator testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_out_of_bounds_negative_south").getPath()).getAbsolutePath());

        Assert.assertTrue(testee.getResult().isFileValid());
        Assert.assertFalse(testee.getResult().isMovementsValid());
        Assert.assertEquals("?", testee.getResult().getMessage());

    }

    @Test
    public void test_out_of_bounds_negative_coords_west() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_out_of_bounds_negative_west").getPath()).getAbsolutePath());

        Assert.assertTrue(testee.getResult().isFileValid());
        Assert.assertFalse(testee.getResult().isMovementsValid());
        Assert.assertEquals("?", testee.getResult().getMessage());

    }

    @Test
    public void test_colission_1() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_collision_start").getPath()).getAbsolutePath());

        Assert.assertTrue(testee.getResult().isFileValid());
        Assert.assertFalse(testee.getResult().isMovementsValid());
        Assert.assertEquals("?", testee.getResult().getMessage());

    }

    @Test
    public void test_colission_2() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_collision_r1_into_r2").getPath()).getAbsolutePath());

        Assert.assertTrue(testee.getResult().isFileValid());
        Assert.assertFalse(testee.getResult().isMovementsValid());
        Assert.assertEquals("?", testee.getResult().getMessage());

    }

    @Test
    public void test_colission_3() throws Exception {

        testee = new RoverMovementCalculator();

        testee.calculateMovements(new File(getClass().getClassLoader().getResource("Testinput_fail_collision_r2_into_r1").getPath()).getAbsolutePath());

        Assert.assertTrue(testee.getResult().isFileValid());
        Assert.assertFalse(testee.getResult().isMovementsValid());
        Assert.assertEquals("?", testee.getResult().getMessage());

    }

}
