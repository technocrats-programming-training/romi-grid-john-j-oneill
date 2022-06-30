package frc.robot;

import edu.wpi.first.hal.HAL;
import lib.Romi;
import org.junit.Before;
import org.junit.Test;

import static lib.Romi.initRomiSubsystems;
import static org.junit.Assert.assertEquals;


public class RobotTest {

    Position position;

    @Before
    public void setup() {
        assert HAL.initialize(500, 0);
        Romi.enableTestMode(true);
        position = new Position();
    }

    @Test
    public void testInitialPositionCorrect() {
        assertEquals(position.toString(), "(0, 0)");
    }

    @Test
    public void testPositionCorrectAfterUp() {
        try {
            position.driveUp();
        } catch (InterruptedException e) {
            throw new AssertionError();
        }
        assertEquals(position.toString(), "(0, 10)");
    }

    @Test
    public void testPositionCorrectAfterRight() {
        try {
            position.driveRight();
        } catch (InterruptedException e) {
            throw new AssertionError();
        }
        assertEquals(position.toString(), "(10, 0)");
    }

    @Test
    public void testPositionCorrectAfterDown() {
        try {
            position.driveDown();
        } catch (InterruptedException e) {
            throw new AssertionError();
        }
        assertEquals(position.toString(), "(0, -10)");
    }

    @Test
    public void testPositionCorrectAfterLeft() {
        try {
            position.driveLeft();
        } catch (InterruptedException e) {
            throw new AssertionError();
        }
        assertEquals(position.toString(), "(-10, 0)");
    }

    @Test
    public void testPositionCorrectAfterSequenceRLRRLUDUU() {
        try {
            position.driveRight();
            position.driveLeft();
            position.driveRight();
            position.driveRight();
            position.driveLeft();
            position.driveUp();
            position.driveDown();
            position.driveUp();
            position.driveUp();
        } catch (InterruptedException e) {
            throw new AssertionError();
        }
        assertEquals("(10, 20)", position.toString());
    }
}
