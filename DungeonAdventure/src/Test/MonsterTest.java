package Test;

import Model.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {
    @BeforeEach
    void setUp() {
        SQLiteDataSource ds = null;
        try{
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:DungeonAdventureDB.db");
        } catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        String query = "SELECT * FROM monster WHERE name='"+"Ogre"+"'";
        try(Connection con = ds.getConnection();
            Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            int hitPoints = rs.findColumn("HIT POINTS");
            int attackSpeed = rs.findColumn("ATTACK SPEED");
            int chanceToHit = rs.findColumn("CHANCE TO HIT");
            int minDamage = rs.findColumn("MIN DAMAGE");
            int maxDamage = rs.findColumn("MAX DAMAGE");
            int chanceToHeal = rs.findColumn("CHANCE TO HEAL");
            int minHealPoints = rs.findColumn("MIN HEAL POINTS");
            int maxHealPoints = rs.findColumn("MAX HEAL POINTS");

        } catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }


    }
        @Test
    void getMyChanceToHeal() {
    }

    @Test
    void getMyMaxHealPoints() {
    }

    @Test
    void getMyMinHealPoints() {
    }

    @Test
    void attack() {
    }

    @Test
    void heal() {
    }
}