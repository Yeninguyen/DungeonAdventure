package Test;

import Model.Monster;
import Model.MonsterDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MonsterAndDatabaseTest {
    Monster myOgre;
    Monster myGremlin;
    Monster mySkeleton;

    @BeforeEach
    void setUp() throws SQLException {
        MonsterDatabase monsterDatabase = MonsterDatabase.getMy_TEST_Instance();
        myOgre = monsterDatabase.getMonster("Ogre");
        myGremlin = monsterDatabase.getMonster("Gremlin");
        mySkeleton = monsterDatabase.getMonster("Skeleton");
    }

    @Test
    void getMyMaxHealPointsOgre() {
        assertEquals(60, myOgre.getMyMaxHealPoints());
    }

    @Test
    void getMyMaxHealPointsGremlin() {
        assertEquals(40, myGremlin.getMyMaxHealPoints());
    }

    @Test
    void getMyMaxHealPointsSkeleton() {
        assertEquals(50, mySkeleton.getMyMaxHealPoints());
    }

    @Test
    void getMyMinHealPointsOgre() {
        assertEquals(30, myOgre.getMyMinHealPoints());
    }

    @Test
    void getMyMinHealPointsGremlin() {
        assertEquals(20, myGremlin.getMyMinHealPoints());
    }

    @Test
    void getMyMinHealPointsSkeleton() {
        assertEquals(30, mySkeleton.getMyMinHealPoints());
    }

    @Test
    void getMyChanceToHealOgre() {
        assertEquals(0.1, myOgre.getMyChanceToHeal());
    }

    @Test
    void getMyChanceToHealGremlin() {
        assertEquals(0.4, myGremlin.getMyChanceToHeal());
    }

    @Test
    void getMyChanceToHealSkeleton() {
        assertEquals(0.3, mySkeleton.getMyChanceToHeal());
    }

    @Test
    void setMyMaxHealPointsOgreValidInput() {
        myOgre.setMyMaxHealPoints(25);
        assertEquals(25, myOgre.getMyMaxHealPoints());
    }

    @Test
    void setMyMaxHealPointsGremlinValidInput() {
        myGremlin.setMyMaxHealPoints(35);
        assertEquals(35, myGremlin.getMyMaxHealPoints());
    }

    @Test
    void setMyMaxHealPointsSkeletonValidInput() {
        mySkeleton.setMyMaxHealPoints(45);
        assertEquals(45, mySkeleton.getMyMaxHealPoints());
    }

    @Test
    void setMyMaxHealPointsOgreInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> myOgre.setMyMaxHealPoints(-25));
    }

    @Test
    void setMyMaxHealPointsGremlinInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> myGremlin.setMyMaxHealPoints(-45));
    }

    @Test
    void setMyMaxHealPointsSkeletonInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> mySkeleton.setMyMaxHealPoints(-35));
    }

    @Test
    void setMyMinHealPointsOgreValidInput() {
        myOgre.setMyMinHealPoints(5);
        assertEquals(5, myOgre.getMyMinHealPoints());
    }

    @Test
    void setMyMinHealPointsGremlinValidInput() {
        myGremlin.setMyMinHealPoints(12);
        assertEquals(12, myGremlin.getMyMinHealPoints());
    }

    @Test
    void setMyMinHealPointsSkeletonValidInput() {
        mySkeleton.setMyMinHealPoints(9);
        assertEquals(9, mySkeleton.getMyMinHealPoints());
    }

    @Test
    void setMyMinHealPointsOgreInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> myOgre.setMyMinHealPoints(-1));
    }

    @Test
    void setMyMinHealPointsGremlinInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> myGremlin.setMyMinHealPoints(-11));
    }

    @Test
    void setMyMinHealPointsSkeletonInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> mySkeleton.setMyMinHealPoints(-16));
    }

    @Test
    void setMyChanceToHealOgreValidInput() {
        myOgre.setMyChanceToHeal(0.1);
        assertEquals(0.1, myOgre.getMyChanceToHeal());
    }

    @Test
    void setMyChanceToHealGremlinValidInput() {
        myGremlin.setMyChanceToHeal(0.1);
        assertEquals(0.1, myGremlin.getMyChanceToHeal());
    }

    @Test
    void setMyChanceToHealSkeletonValidInput() {
        mySkeleton.setMyChanceToHeal(0.1);
        assertEquals(0.1, mySkeleton.getMyChanceToHeal());
    }

    @Test
    void setMyChanceToHealOgreInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> myOgre.setMyChanceToHeal(-0.1));
    }

    @Test
    void setMyChanceToHealGremlinInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> myGremlin.setMyChanceToHeal(-11));
    }

    @Test
    void setMyChanceToHealSkeletonInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> mySkeleton.setMyChanceToHeal(-16));
    }

    @Test
    void setMyRandomToHealValidInput() {
        myOgre.setMyRandomToHeal(0.5);
        assertEquals(0.5, myOgre.getRandomToHeal());
    }

    @Test
    void setMyRandomToHealInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> myOgre.setMyRandomToHeal(1.1));
    }

    @Test
    void healSuccess() {
        myOgre.setMyRandomToHeal(0.05); // Ensure healing happens
        int originalHitPoints = myOgre.getMyHitPoints();
        myOgre.heal();
        assertTrue(myOgre.getMyHitPoints() > originalHitPoints);
    }

    @Test
    void healFail() {
        myOgre.setMyRandomToHeal(0.15); // Ensure healing does not happen
        int originalHitPoints = myOgre.getMyHitPoints();
        myOgre.heal();
        assertEquals(originalHitPoints, myOgre.getMyHitPoints());
    }

    @Test
    void healBoundaries() {
        myOgre.setMyRandomToHeal(0.1); // Boundary condition
        int originalHitPoints = myOgre.getMyHitPoints();
        myOgre.heal();
        assertTrue(myOgre.getMyHitPoints() >= originalHitPoints);

        myOgre.setMyRandomToHeal(0.0); // Lower boundary condition
        originalHitPoints = myOgre.getMyHitPoints();
        myOgre.heal();
        assertTrue(myOgre.getMyHitPoints() >= originalHitPoints);
    }
}
