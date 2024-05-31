package Test;

import Model.Priestess;
import Model.DungeonCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriestessAndHeroTest {
    Priestess myPriestess;

    @BeforeEach
    void setUp() {
        myPriestess = Priestess.Test_getMyUniqueInstance();
    }

    @Test
    void setMyChanceForSpecialValid() {
        myPriestess.setMyChanceForSpecial(0.4);
        assertEquals(0.4, myPriestess.getMyChanceForSpecial());
    }

    @Test
    void setMyChanceForSpecialInvalid() {
        assertThrows(IllegalArgumentException.class, () -> myPriestess.setMyChanceForSpecial(-2.0));
    }

    @Test
    void setMyChanceToBlockValid() {
        myPriestess.setMyChanceToBlock(0.7);
        assertEquals(0.7, myPriestess.getMyChanceToBlock());
    }

    @Test
    void setMyChanceToBlockInvalid() {
        assertThrows(IllegalArgumentException.class, () -> myPriestess.setMyChanceToBlock(-2.5));
    }

    @Test
    void setMySpecialMaxValid() {
        myPriestess.setMySpecialMax(220);
        assertEquals(220, myPriestess.getMySpecialMax());
    }

    @Test
    void setMySpecialMaxInvalid() {
        assertThrows(IllegalArgumentException.class, () -> myPriestess.setMySpecialMax(-22));
    }

    @Test
    void setMySpecialMinValid() {
        myPriestess.setMySpecialMin(34);
        assertEquals(34, myPriestess.getMySpecialMin());
    }

    @Test
    void setMySpecialMinInvalid() {
        assertThrows(IllegalArgumentException.class, () -> myPriestess.setMySpecialMin(-34));
    }

    @Test
    void setMyRandomForSpecialValid() {
        myPriestess.setMyRandomForSpecial(0.5);
        assertEquals(0.5, myPriestess.getMyRandomForSpecial());
    }

    @Test
    void setMyRandomForSpecialInvalid() {
        assertThrows(IllegalArgumentException.class, () -> myPriestess.setMyRandomForSpecial(1.5));
    }

    @Test
    void specialSuccess() {
        DungeonCharacter dummyTarget = new DungeonCharacter("Dummy", 100, 1, 1.0, 10, 5, 0, 0) {};
        myPriestess.setMyRandomForSpecial(0.2); // Ensure special happens
        int originalHitPoints = myPriestess.getMyHitPoints();
        myPriestess.special(dummyTarget);
        assertTrue(myPriestess.getMyHitPoints() > originalHitPoints);
    }

    @Test
    void specialFail() {
        DungeonCharacter dummyTarget = new DungeonCharacter("Dummy", 100, 1, 1.0, 10, 5, 0, 0) {};
        myPriestess.setMyRandomForSpecial(0.5); // Ensure special does not happen (assuming myChanceForSpecial < 0.5)
        myPriestess.setMyChanceForSpecial(0.3); // Ensure chance to special is less than random for special
        int originalHitPoints = myPriestess.getMyHitPoints();
        myPriestess.special(dummyTarget);
        assertEquals(originalHitPoints, myPriestess.getMyHitPoints());
    }
}

