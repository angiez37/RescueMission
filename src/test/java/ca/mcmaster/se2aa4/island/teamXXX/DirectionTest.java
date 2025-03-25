package ca.mcmaster.se2aa4.island.teamXXX;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Compass;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Direction;


class DirectionTest {

    @Test
    void testToDirectionValidInputs() {
        assertEquals(Direction.NORTH, Direction.toDirection("N"));
        assertEquals(Direction.SOUTH, Direction.toDirection("S"));
        assertEquals(Direction.EAST, Direction.toDirection("E"));
        assertEquals(Direction.WEST, Direction.toDirection("W"));
    }

    @Test
    void testToDirectionLowerCaseInputs() {
        assertEquals(Direction.NORTH, Direction.toDirection("n"));
        assertEquals(Direction.SOUTH, Direction.toDirection("s"));
        assertEquals(Direction.EAST, Direction.toDirection("e"));
        assertEquals(Direction.WEST, Direction.toDirection("w"));
    }

    @Test
    void testToDirectionInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> Direction.toDirection("A"));
        assertThrows(IllegalArgumentException.class, () -> Direction.toDirection("north"));
        assertThrows(IllegalArgumentException.class, () -> Direction.toDirection("1"));
        assertThrows(IllegalArgumentException.class, () -> Direction.toDirection(""));
    }

    @Test
    void testToStringMethod() {
        assertEquals("N", Direction.NORTH.toString());
        assertEquals("S", Direction.SOUTH.toString());
        assertEquals("E", Direction.EAST.toString());
        assertEquals("W", Direction.WEST.toString());
    }
}
