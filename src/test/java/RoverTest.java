import org.junit.Assert;
import org.junit.Test;

public class RoverTest {

    private Rover testee;

    @Test
    public void test_drive_north() throws Exception {
        testee = new Rover(0, 4,4, Direction.N);
        testee.move(Movement.M);
        Assert.assertEquals(4, testee.getCurrentX().intValue());
        Assert.assertEquals(5, testee.getCurrentY().intValue());
    }

    @Test
    public void test_drive_west() throws Exception {
        testee = new Rover(0, 4,4, Direction.W);
        testee.move(Movement.M);
        Assert.assertEquals(3, testee.getCurrentX().intValue());
        Assert.assertEquals(4, testee.getCurrentY().intValue());
    }

    @Test
    public void test_drive_south() throws Exception {
        testee = new Rover(0, 4,4, Direction.S);
        testee.move(Movement.M);
        Assert.assertEquals(4, testee.getCurrentX().intValue());
        Assert.assertEquals(3, testee.getCurrentY().intValue());
    }

    @Test
    public void test_drive_east() throws Exception  {
        testee = new Rover(0, 4,4, Direction.E);
        testee.move(Movement.M);
        Assert.assertEquals(5, testee.getCurrentX().intValue());
        Assert.assertEquals(4, testee.getCurrentY().intValue());
    }

    @Test
    public void test_turn_left() throws Exception  {
        testee = new Rover(0, 4,4, Direction.N);
        testee.move(Movement.L);
        Assert.assertEquals(Direction.W, testee.getCurrentDirection());
        testee.move(Movement.L);
        Assert.assertEquals(Direction.S, testee.getCurrentDirection());
        testee.move(Movement.L);
        Assert.assertEquals(Direction.E, testee.getCurrentDirection());
        Assert.assertEquals(4, testee.getCurrentX().intValue());
        Assert.assertEquals(4, testee.getCurrentY().intValue());
    }

    @Test
    public void test_turn_right() throws Exception  {
        testee = new Rover(0, 4,4, Direction.N);
        testee.move(Movement.R);
        Assert.assertEquals(Direction.E, testee.getCurrentDirection());
        testee.move(Movement.R);
        Assert.assertEquals(Direction.S, testee.getCurrentDirection());
        testee.move(Movement.R);
        Assert.assertEquals(Direction.W, testee.getCurrentDirection());
        Assert.assertEquals(4, testee.getCurrentX().intValue());
        Assert.assertEquals(4, testee.getCurrentY().intValue());
    }


    @Test
    public void test_drive_can_handle_negative_values_x() throws Exception  {
        testee = new Rover(0, 0,0, Direction.W);
        testee.move(Movement.M);
        Assert.assertEquals(-1, testee.getCurrentX().intValue());
        Assert.assertEquals(0, testee.getCurrentY().intValue());
    }

    @Test
    public void test_drive_can_handle_negative_values_y() throws Exception  {
        testee = new Rover(0, 0,0, Direction.S);
        testee.move(Movement.M);
        Assert.assertEquals(0, testee.getCurrentX().intValue());
        Assert.assertEquals(-1, testee.getCurrentY().intValue());
    }

}
