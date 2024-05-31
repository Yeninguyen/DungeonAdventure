package Test;

import Model.Warrior;
import Model.DungeonCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarriorAndHeroTest {
    Warrior myWarrior;

    @BeforeEach
    void setUp() {
        myWarrior = Model.Warrior.TEST_getInstance();
    }

    @Test
    void setMyChanceForSpecialValid() {
        myWarrior.setMyChanceForSpecial(0.4);
        assertEquals(0.4, myWarrior.getMyChanceForSpecial());
    }

    @Test
    void setMyChanceForSpecialInvalid() {
        assertThrows(IllegalArgumentException.class, () -> myWarrior.setMyChanceForSpecial(-2.0));
    }

    @Test
    void setMyChanceToBlockValid() {
        myWarrior.setMyChanceToBlock(0.7);
        assertEquals(0.7, myWarrior.getMyChanceToBlock());
    }

    @Test
    void setMyChanceToBlockInvalid() {
        assertThrows(IllegalArgumentException.class, () -> myWarrior.setMyChanceToBlock(-2.5));
    }

    @Test
    void setMySpecialMaxValid() {
        myWarrior.setMySpecialMax(220);
        assertEquals(220, myWarrior.getMySpecialMax());
    }

    @Test
    void setMySpecialMaxInvalid() {
        assertThrows(IllegalArgumentException.class, () -> myWarrior.setMySpecialMax(-22));
    }

    @Test
    void setMySpecialMinValid() {
        myWarrior.setMySpecialMin(34);
        assertEquals(34, myWarrior.getMySpecialMin());
    }

    @Test
    void setMySpecialMinInvalid() {
        assertThrows(IllegalArgumentException.class, () -> myWarrior.setMySpecialMin(-34));
    }

    @Test
    void specialTest() {
        DungeonCharacter dummyTarget = new DungeonCharacter("Dummy", 175, 1, 1.0, 10, 5, 0, 0) {};
        myWarrior.setMyRandomForSpecial(0.1); // Ensure special happens
        int originalHitPoints = dummyTarget.getMyHitPoints();
        myWarrior.special(dummyTarget);
        assertTrue(originalHitPoints > dummyTarget.getMyHitPoints());
    }
}
