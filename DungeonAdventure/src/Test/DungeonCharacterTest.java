package Test;

import Model.DungeonCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DungeonCharacterTest {
    private DungeonCharacter myCharacter;
    @BeforeEach
    void setUp() {
        myCharacter = new DungeonCharacter("TestCharacter",200,10,0.6,
                100,100,10,10) {
            @Override
            public void setMyChanceToHit(double theChanceToHit) {
                super.setMyChanceToHit(theChanceToHit);
            }
        };
        myCharacter.setMyRandom(0.6);

    }

    @Test
    void setMyChanceToHitValidInput() {
        myCharacter.setMyChanceToHit(0.7);
        assertEquals(0.7, myCharacter.getMyChanceToHit());
    }
    @Test
    void setMyChanceToHitInvalidInput() {
        myCharacter.setMyChanceToHit(-1.0);
        assertThrows(IllegalArgumentException.class, () -> myCharacter.setMyChanceToHit(-0.1));
    }

    @Test
    void setMyYValidInput() {
        myCharacter.setMyY(45);
        assertEquals(45, myCharacter.getMyY());
    }
    @Test
    void setMyYInvalidInput() {
        assertThrows(IllegalArgumentException.class,() -> myCharacter.setMyY(-45));
    }

    @Test
    void setMyXValidInput() {
        myCharacter.setMyX(55);
        assertEquals(55, myCharacter.getMyX());
    }
    @Test
    void setMyXInvalidInput() {
        assertThrows(IllegalArgumentException.class,() -> myCharacter.setMyX(-5500));
    }

    @Test
    void setMyMinDamageValidInput() {
        myCharacter.setMyMinDamage(40);
        assertEquals(40, myCharacter.getMyMinDamage());
    }
    @Test
    void setMyMinDamageInvalidInput() {
        assertThrows(IllegalArgumentException.class,() -> myCharacter.setMyMinDamage(-100));
    }

    @Test
    void setMyMaxDamageValidInput() {
        myCharacter.setMyMaxDamage(100);
        assertEquals(100, myCharacter.getMyMaxDamage());
    }
    @Test
    void setMyMaxDamageInvalidInput() {
        assertThrows(IllegalArgumentException.class,() -> myCharacter.setMyMaxDamage(-100));
    }

    @Test
    void setMyAttackSpeedValidInput() {
        myCharacter.setMyAttackSpeed(99);
        assertEquals(99,myCharacter.getMyAttackSpeed());
    }
    @Test
    void setMyAttackSpeedInvalidInput(){
        assertThrows(IllegalArgumentException.class,() -> myCharacter.setMyAttackSpeed(-90));
    }

    @Test
    void setMyHitPointsValidInput() {
        myCharacter.setMyHitPoints(324);
        assertEquals(324,myCharacter.getMyHitPoints());
    }
    @Test
    void setMyHitPointsInvalidInput(){
        assertThrows(IllegalArgumentException.class,() -> myCharacter.setMyHitPoints(-324));
    }

    @Test
    void setMyNameValidInput() {
        myCharacter.setMyName("Superman");
        assertEquals("Superman",myCharacter.getMyName());
    }
    @Test
    void setMyNameInvalidInput() {
        assertThrows(IllegalArgumentException.class,() -> myCharacter.setMyName(null));
    }
    @Test
    void setMyRandomValidInput() {
        myCharacter.setMyRandom(0.5);
        assertEquals(0.5,myCharacter.getMyRandom());
    }

    @Test
    void attackTest(){
        DungeonCharacter other = new DungeonCharacter("Zombie",101,4,0.2,34,12,2,3) {
            @Override
            public void setMyChanceToHit(double theChanceToHit) {
                super.setMyChanceToHit(theChanceToHit);
            }
        };
        myCharacter.attack(other);
        assertNotEquals(101,other.getMyHitPoints());


    }

}