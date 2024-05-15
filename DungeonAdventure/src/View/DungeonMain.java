package View;

import java.awt.*;

public class DungeonMain {

    public static void main(final String... theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DungeonGUI();

            }
        });
    }
}


//            SQLiteDataSource ds = null;
//
//            //establish connection (creates db file if it does not exist :-)
//            try {
//                ds = new SQLiteDataSource();
//                ds.setUrl("jdbc:sqlite:monster.db");
//            } catch ( Exception e ) {
//                e.printStackTrace();
//                System.exit(0);
//            }
//
//            System.out.println( "Opened database successfully" );
//
//            //now create a table
//            String query = "CREATE TABLE IF NOT EXISTS monsters (" +
//                    "name TEXT NOT NULL, " +
//                    "hitPoints INTEGER, " +
//                    "attackSpeed INTEGER, " +
//                    "chanceToHit REAL, " +
//                    "minDamage INTEGER, " +
//                    "maxDamage INTEGER, " +
//                    "chanceToHeal REAL, " +
//                    "minHealPoints INTEGER, " +
//                    "maxHealPoints INTEGER)";
//
//            try (Connection conn = ds.getConnection();
//                 Statement stmt = conn.createStatement()) {
//                // Execute table creation query
//                boolean tableCreated = stmt.execute(query);
//                if (tableCreated) {
//                    System.out.println("Table created successfully");
//                } else {
//                    System.out.println("Table already exists");
//                }
//            } catch (SQLException e) {
//                // Print any SQL errors that occur during table creation
//                e.printStackTrace();
//                System.exit(0);
//            }
//            //next insert two rows of data
//            System.out.println( "Attempting to insert two rows into questions table" );
//
////            String query1 = "INSERT INTO monsters (name, hitPoints, attackSpeed, chanceToHit, minDamage, maxDamage, chanceToHeal, minHealPoints, maxHealPoints ) VALUES " +
////                    "('Ogre', 200, 2, 0.6, 30, 60, 0.1, 30, 60), " +
////                    "('Gremlin', 70, 5, 0.8, 15, 30, 0.4, 20, 40), " +
////                    "('Skeleton', 100, 3, 0.8, 30, 50, 0.3, 30, 50)";
////            try ( Connection conn = ds.getConnection();
////                  Statement stmt = conn.createStatement(); ) {
////                int rv = stmt.executeUpdate( query1 );
////                System.out.println( "1st executeUpdate() returned " + rv );
////
//////                rv = stmt.executeUpdate( query2 );
//////                System.out.println( "2nd executeUpdate() returned " + rv );
////            } catch ( SQLException e ) {
////                e.printStackTrace();
////                System.exit( 0 );
////            }
//
//
//            //now query the database table for all its contents and display the results
//            System.out.println( "Selecting all rows from questions table" );
//            query = "SELECT * FROM monsters";
//
//            try ( Connection conn = ds.getConnection();
//                  Statement stmt = conn.createStatement(); ) {
//
//                ResultSet rs = stmt.executeQuery(query);
//
//                //walk through each 'row' of results, grab data by column/field name
//                // and print it
//                while (rs.next()) {
//                    String name = rs.getString("name");
//                    int hitPoints = rs.getInt("hitPoints");
//                    int attackSpeed = rs.getInt("attackSpeed");
//                    double chanceToHit = rs.getDouble("chanceToHit");
//                    int minDamage = rs.getInt("minDamage");
//                    int maxDamage = rs.getInt("maxDamage");
//                    double chanceToHeal = rs.getDouble("chanceToHeal");
//                    int minHealPoints = rs.getInt("minHealPoints");
//                    int maxHealPoints = rs.getInt("maxHealPoints");
//                    System.out.println("Result: NAME = " + name + ", HIT_POINTS = " + hitPoints +
//                            ", ATTACK_SPEED = " + attackSpeed + ", CHANCE_TO_HIT = " + chanceToHit +
//                            ", MIN_DAMAGE = " + minDamage + ", MAX_DAMAGE = " + maxDamage +
//                            ", CHANCE_TO_HEAL = " + chanceToHeal + ", MIN_HEAL_POINTS = " + minHealPoints +
//                            ", MAX_HEAL_POINTS = " + maxHealPoints);
//                }
//            } catch ( SQLException e ) {
//                e.printStackTrace();
//                System.exit( 0 );
//            }
//            System.out.println("press enter to close program/window");
//            Scanner input = new Scanner(System.in);
//            input.nextLine();
//        }
//
//
//}
//
