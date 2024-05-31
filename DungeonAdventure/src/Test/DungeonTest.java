package Test;

import Model.Dungeon;
import Model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DungeonTest {
    Dungeon myDungeon;

    @BeforeEach
    void setUp() {
        myDungeon = Dungeon.get_TEST_instance();
    }

    @Test
    void testMazeSize() {
        Room[][] maze = myDungeon.getMaze();
        assertEquals(4, maze.length);
        assertEquals(4, maze[0].length);
    }

    @Test
    void testMazeContainsEntranceAndExit() {
        Room[][] maze = myDungeon.getMaze();
        boolean hasEntrance = false;
        boolean hasExit = false;

        for (Room[] row : maze) {
            for (Room room : row) {
                if (room.isMyEntrance()) hasEntrance = true;
                if (room.isMyExit()) hasExit = true;
            }
        }

        assertTrue(hasEntrance);
        assertTrue(hasExit);
    }

    @Test
    void testMazeContainsFourPillars() {
        Room[][] maze = myDungeon.getMaze();
        int pillarCount = 0;

        for (Room[] row : maze) {
            for (Room room : row) {
                if (room.getMyHasPillar()) pillarCount++;
            }
        }

        assertEquals(4, pillarCount);
    }

    @Test
    void testIsTraversable() {
        assertTrue(myDungeon.isTraversable());
    }

    @Test
    void testEntranceAndExitAreNotTheSame() {
        Room[][] maze = myDungeon.getMaze();
        for (Room[] row : maze) {
            for (Room room : row) {
                assertFalse(room.isMyEntrance() && room.isMyExit());
            }
        }
    }

    @Test
    void testOnlyOneEntranceAndExit() {
        Room[][] maze = myDungeon.getMaze();
        int entranceCount = 0;
        int exitCount = 0;

        for (Room[] row : maze) {
            for (Room room : row) {
                if (room.isMyEntrance()) entranceCount++;
                if (room.isMyExit()) exitCount++;
            }
        }

        assertEquals(1, entranceCount);
        assertEquals(1, exitCount);
    }
}
