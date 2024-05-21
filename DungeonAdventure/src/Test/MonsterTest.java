package Test;

/*
import Model.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

import static org.junit.jupiter.api.Assertions.*;


class MonsterTest {
    private Monster ogre;
    @BeforeEach
    void setUp() {
        SQLiteDataSource ds = null;
        try{
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:DungeonAdventureDB.sqlite");
        } catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        String name;
        int hitPoints;
        int attackSpeed;
        double chanceToHit;
        int minDamage;
        int maxDamage;
        double chanceToHeal;
        int minHealPoints;
        int maxHealPoints;
        String query = "SELECT * FROM monsters WHERE name='"+"Ogre"+"'";
        try(Connection con = ds.getConnection();
            Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
             name = rs.getString("NAME");
             hitPoints = rs.getInt("HIT POINTS");
            attackSpeed = rs.getInt("ATTACK SPEED");
            chanceToHit = rs.getDouble("CHANCE TO HIT");
            minDamage = rs.getInt("MIN DAMAGE");
            maxDamage = rs.getInt("MAX DAMAGE");
            chanceToHeal = rs.getDouble("CHANCE TO HEAL");
            minHealPoints = rs.getInt("MIN HEAL POINTS");
            maxHealPoints = rs.getInt("MAX HEAL POINTS");
            ogre = Model.Monster.testMonsterInstance(name,hitPoints,attackSpeed,chanceToHit,minDamage,maxDamage,
                    chanceToHeal,minHealPoints,maxHealPoints);

        } catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }


    }
        @Test
    void getMyChanceToHeal() {
        assertEquals(0.1,ogre.getMyChanceToHeal());
    }

    @Test
    void getMyMaxHealPoints() {
        assertEquals(60,ogre.getMyMaxHealPoints());
    }

    @Test
    void getMyMinHealPoints() {
        assertEquals(30,ogre.getMyMinHealPoints());
    }

    @Test
    void attack() {
    }

    @Test
    void heal() {
    }
}

 */