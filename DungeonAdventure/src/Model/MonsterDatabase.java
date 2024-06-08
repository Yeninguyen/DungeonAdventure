
package Model;
import org.sqlite.SQLiteDataSource;

import java.sql.*;

/**
 * The MonsterDatabase class provides methods to retrieve Monster objects from a SQLite database.
 * It uses the Singleton design pattern to ensure only one instance of the database is used.
 */
public class MonsterDatabase {

    /**
     * The unique instance field of the MonsterDatabase.
     */
    private static MonsterDatabase myUniqueInstance;

    /**
     * Private constructor to prevent instantiation.
     */
    private MonsterDatabase() {
    }

    /**
     * This is the method retrieves a Monster from the database based on the monster's name.
     * @param theMonsterName The name of the monster to retrieve.
     * @return The Monster object if found, otherwise null.
     * @throws SQLException if a database access error occurs.
     */
    public static Monster getMonster(String theMonsterName) throws SQLException {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:monster.db");

        Monster monster = null;
        String selectDataQuery = "SELECT * FROM monsters WHERE name=?";

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectDataQuery)) {

            stmt.setString(1, theMonsterName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int hitPoints = rs.getInt("hitPoints");
                int attackSpeed = rs.getInt("attackSpeed");
                double chanceToHit = rs.getDouble("chanceToHit");
                int minDamage = rs.getInt("minDamage");
                int maxDamage = rs.getInt("maxDamage");
                double chanceToHeal = rs.getDouble("chanceToHeal");
                int minHealPoints = rs.getInt("minHealPoints");
                int maxHealPoints = rs.getInt("maxHealPoints");

                int x = (int) (Math.random() * Dungeon.getInstance().getMySize());
                int y = (int) (Math.random() * Dungeon.getInstance().getMySize());
                monster = new Monster(name, hitPoints, attackSpeed, chanceToHit, maxDamage, minDamage, chanceToHeal,
                        minHealPoints, maxHealPoints, x, y);
            }
        }
        return monster;
    }

    /**
     * This is the method gets the unique instance of the MonsterDatabase.
     * @return The unique instance of the MonsterDatabase.
     */
    public static MonsterDatabase getMyUniqueInstance() {
        if (myUniqueInstance == null) {
            myUniqueInstance = new MonsterDatabase();
        }
        return myUniqueInstance;
    }

    /**
     * This is a method gets a new instance of the MonsterDatabase for testing purposes.
     * @return A new instance of the MonsterDatabase.
     */
    public static MonsterDatabase getMy_TEST_Instance() {
        return new MonsterDatabase();
    }




}
