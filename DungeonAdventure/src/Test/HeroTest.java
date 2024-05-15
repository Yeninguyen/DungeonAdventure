/*
package Test;


import Model.Hero;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
  private Hero myHero1;


    @BeforeEach
     void setUp(){

      myHero1 = Model.Warrior.TEST_getInstance();
    }

    @org.junit.jupiter.api.Test
    void attack() {

    }

    @org.junit.jupiter.api.Test
    void getMyChanceForSpecialAttack() {
      assertEquals(0.4, myHero1.getMyChanceForSpecial());
    }

    @org.junit.jupiter.api.Test
    void setMyChanceForSpecialAttackException() {
      assertThrows(IllegalArgumentException.class, () -> myHero1.setMyChanceForSpecial(23));
    }

  @org.junit.jupiter.api.Test
  void setMyChanceForSpecialAttackValid() {
    myHero1.setMyChanceForSpecial(0.8);
    assertEquals(0.8,myHero1.getMyChanceForSpecial());

  }

    @org.junit.jupiter.api.Test
    void setMyChanceToBlockException() {
      assertThrows(IllegalArgumentException.class, () -> myHero1.setMyChanceToBlock(-3747734));

    }

  @org.junit.jupiter.api.Test
  void setMyChanceToBlockValid() {
    myHero1.setMyChanceToBlock(0.8);
    assertEquals(0.8,myHero1.getMyChanceToBlock());

  }

    @org.junit.jupiter.api.Test
    void getMyChanceToBlock() {
      assertEquals(0.2, myHero1.getMyChanceToBlock());
    }

    @org.junit.jupiter.api.Test
    void getMySpecialAttackMinDamage() {
      assertEquals(75, myHero1.getMySpecialMin());
    }

    @org.junit.jupiter.api.Test
    void getMySpecialAttackMaxDamage() {
      assertEquals(175, myHero1.getMySpecialMax());
    }

    @org.junit.jupiter.api.Test
    void setMySpecialAttackMaxDamageException() {
      assertThrows(IllegalArgumentException.class,()-> myHero1.setMySpecialMax(-3837));
    }
  @org.junit.jupiter.api.Test
  void setMySpecialAttackMaxDamageValid() {
    myHero1.setMySpecialMax(1000);
    assertEquals(1000,myHero1.getMySpecialMax());
  }

    @org.junit.jupiter.api.Test
    void setMySpecialAttackMinDamageException() {
      assertThrows(IllegalArgumentException.class,()-> myHero1.setMySpecialMin(-3837));
    }

  @org.junit.jupiter.api.Test
  void setMySpecialAttackMinDamageValid() {
   myHero1.setMySpecialMin(20);
   assertEquals(20,myHero1.getMySpecialMin());
  }

}

 */