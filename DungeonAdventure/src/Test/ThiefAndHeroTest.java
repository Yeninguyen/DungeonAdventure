package Test;

import Model.Thief;
import Model.DungeonCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThiefAndHeroTest {
    Thief myThief;

    @BeforeEach
    void setUp() {
        myThief = Model.Thief.Test_getMyUniqueInstance();
    }

    @Test
    void setMyChanceForSpecialValid() {
        myThief.setMyChanceForSpecial(0.4);
        assertEquals(0.4, myThief.getMyChanceForSpecial());
    }

    @Test
    void setMyChanceForSpecialInvalid() {
        assertThrows(IllegalArgumentException.class, () -> myThief.setMyChanceForSpecial(-2.0));
    }

    @Test
    void setMyChanceToBlockValid() {
        myThief.setMyChanceToBlock(0.7);
        assertEquals(0.7, myThief.getMyChanceToBlock());
    }

    @Test
    void setMyChanceToBlockInvalid() {
        assertThrows(IllegalArgumentException.class, () -> myThief.setMyChanceToBlock(-2.5));
    }

    @Test
    void setMySpecialMaxValid() {
        myThief.setMySpecialMax(220);
        assertEquals(220, myThief.getMySpecialMax());
    }

    @Test
    void setMySpecialMaxInvalid() {
        assertThrows(IllegalArgumentException.class, () -> myThief.setMySpecialMax(-22));
    }

    @Test
    void setMySpecialMinValid() {
        myThief.setMySpecialMin(34);
        assertEquals(34, myThief.getMySpecialMin());
    }

    @Test
    void setMySpecialMinInvalid() {
        assertThrows(IllegalArgumentException.class, () -> myThief.setMySpecialMin(-34));
    }

    @Test
    void specialTest() {
        DungeonCharacter dummyTarget = new DungeonCharacter("Dummy", 100, 1, 1.0, 10, 5, 0, 0) {};
        myThief.setMyRandomForSpecial(0.3); // Ensure special happens
        int originalHitPoints = dummyTarget.getMyHitPoints();
        myThief.special(dummyTarget);
        assertTrue(originalHitPoints > dummyTarget.getMyHitPoints());
    }
}
