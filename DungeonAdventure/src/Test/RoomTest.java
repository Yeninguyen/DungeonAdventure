package Test;

import Model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    Room myRoom;

    @BeforeEach
    void setUp() {
        myRoom = new Room(4);
    }

    @Test
    void testToString() {
        // Test the toString method when room is empty
        myRoom.setMyX(0);
        myRoom.setMyY(0);
        myRoom.setMyItem(' ');  // Empty room
        String expectedOutput = "* * * * * \n" +
                "* - N - D \n" +
                "* * D * * \n";
        assertEquals(expectedOutput, myRoom.toString());
    }

    @Test
    void setMyHasPillar() {
        myRoom.setMyHasPillar(true);
        assertTrue(myRoom.getMyHasPillar());
    }

    @Test
    void setMyEntrance() {
        myRoom.setMyEntrance(true);
        assertTrue(myRoom.isMyEntrance());
        assertEquals('i', myRoom.getMyItem());
    }

    @Test
    void setMyItem() {
        myRoom.setMyItem('H');
        assertEquals('H', myRoom.getMyItem());
    }

    @Test
    void setMyY() {
        myRoom.setMyY(3);
        assertEquals(3, myRoom.getMyY());
    }

    @Test
    void setMyYInvalid() {
        assertThrows(IllegalArgumentException.class, () -> myRoom.setMyY(-1));
    }

    @Test
    void setMyX() {
        myRoom.setMyX(3);
        assertEquals(3, myRoom.getMyX());
    }

    @Test
    void setMyXInvalid() {
        assertThrows(IllegalArgumentException.class, () -> myRoom.setMyX(-1));
    }

    @Test
    void setMyExit() {
        myRoom.setMyExit(true);
        assertTrue(myRoom.isMyExit());
        assertEquals('o', myRoom.getMyItem());
    }

    @Test
    void setMyVisionPotion() {
        myRoom.setMyVisionPotion(true);
        assertTrue(myRoom.getMyHasVisionPotion());
    }

    @Test
    void setMyBorderSize() {
        myRoom.setMyBorderSize(5);
        assertEquals(5, myRoom.getMyBorderSize());
    }

    @Test
    void setMyBorderSizeInvalid() {
        assertThrows(IllegalArgumentException.class, () -> myRoom.setMyBorderSize(-1));
    }

    @Test
    void setUpRoom() {
        Room room = new Room(4);
        assertNotNull(room);
        assertNotNull(room.getMyItem());
    }

    @Test
    void testGetPartOfTheRoomValid() {
        myRoom.setMyX(0);
        myRoom.setMyY(0);
        myRoom.setMyItem(' ');  // Empty room
        String expectedOutput = "* * * * * ";
        assertEquals(expectedOutput, myRoom.getPartOfTheRoom(myRoom, 0));
    }

    @Test
    void testGetPartOfTheRoomInvalid() {
        assertEquals("Invalid part number", myRoom.getPartOfTheRoom(myRoom, 5));
    }
}
