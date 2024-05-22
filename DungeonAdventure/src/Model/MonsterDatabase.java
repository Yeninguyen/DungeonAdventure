
package Model;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.Scanner;

public class MonsterDatabase {

    public static Monster getMonster(String theMonsterName) throws SQLException {
        // Create an SQLiteDataSource object
        SQLiteDataSource ds = null;
        Monster monster = null;
        // Establish connection (creates db file if it does not exist)
        ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:monsters.db");

        System.out.println("Opened database successfully");

        // Create the monsters table if it doesn't exist
        String createTableQuery = "CREATE TABLE IF NOT EXISTS monsters (" +
                "name TEXT NOT NULL, " +
                "hitPoints INTEGER, " +
                "attackSpeed INTEGER, " +
                "chanceToHit REAL, " +
                "minDamage INTEGER, " +
                "maxDamage INTEGER, " +
                "chanceToHeal REAL, " +
                "minHealPoints INTEGER, " +
                "maxHealPoints INTEGER)";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableQuery);
            //System.out.println("Table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Insert sample data into the monsters table (uncomment if needed)
        /*
            String insertDataQuery = "INSERT INTO monsters (name, hitPoints, attackSpeed, chanceToHit, minDamage, maxDamage, chanceToHeal, minHealPoints, maxHealPoints ) VALUES " +
         "('Ogre', 200, 2, 0.6, 30, 60, 0.1, 30, 60), " +
                "('Gremlin', 70, 5, 0.8, 15, 30, 0.4, 20, 40), " +
                "('Skeleton', 100, 3, 0.8, 30, 50, 0.3, 30, 50)";
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(insertDataQuery);
            System.out.println("Sample data inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

         */

        // Query the monsters table and display the results
        String selectDataQuery = "SELECT * FROM monsters WHERE name=?";

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectDataQuery)) {

            stmt.setString(1, theMonsterName); // Set the parameter value

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                int hitPoints = rs.getInt("hitPoints");
                int attackSpeed = rs.getInt("attackSpeed");
                double chanceToHit = rs.getDouble("chanceToHit");
                int minDamage = rs.getInt("minDamage");
                int maxDamage = rs.getInt("maxDamage");
                double chanceToHeal = rs.getDouble("chanceToHeal");
                int minHealPoints = rs.getInt("minHealPoints");
                int maxHealPoints = rs.getInt("maxHealPoints");
                System.out.println("Result: NAME = " + name + ", HIT_POINTS = " + hitPoints +
                        ", ATTACK_SPEED = " + attackSpeed + ", CHANCE_TO_HIT = " + chanceToHit +
                        ", MIN_DAMAGE = " + minDamage + ", MAX_DAMAGE = " + maxDamage +
                        ", CHANCE_TO_HEAL = " + chanceToHeal + ", MIN_HEAL_POINTS = " + minHealPoints +
                        ", MAX_HEAL_POINTS = " + maxHealPoints);
               monster = new Monster(name,hitPoints,attackSpeed,chanceToHit,maxDamage,minDamage,chanceToHeal,
                        minHealPoints, maxHealPoints);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Press enter to close program/window");
        Scanner input = new Scanner(System.in);
        input.nextLine();
        return monster;
    }
   public static void main(String[] args) throws SQLException {
        MonsterDatabase monsterDatabase = new MonsterDatabase();
        monsterDatabase.getMonster("Gremlin");
   }

}






