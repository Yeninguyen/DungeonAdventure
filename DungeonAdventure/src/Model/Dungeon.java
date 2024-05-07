package Model;
import java.util.*;
public class Dungeon {
    private Room[][] myDungeon;
    private int myAdventurerLocation;
    private int mySize;
    private Random random;
    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public Dungeon(int theSize) {
        this.mySize = theSize;
        myDungeon = new Room[theSize][theSize];
        random = new Random();
    }

    private void generateDungeon() {
        // Initialize each room in the dungeon
        for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
                //myDungeon[i][j] = new Room(); // Initialize each room as a new Room object
            }
        }

        // Check if the dungeon is traversable before placing exit, entrance, and pillars
        while (!isTraversable()) {
            // Reset entrance, exit, and pillars
            for (int i = 0; i < mySize; i++) {
                for (int j = 0; j < mySize; j++) {
                    myDungeon[i][j].setMyEntrance(false);
                    myDungeon[i][j].setExit(false);
                    myDungeon[i][j].setMyPillar("");
                }
            }
        }
        placeExit();

    }

    private boolean isTraversable(){
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[mySize][mySize];
        queue.offer(new int[]{0, 0}); // Start from the entrance
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // Check if the current room is the exit
            if (myDungeon[x][y].isMyExit()) {
                return true; // Found exit
            }

            // Check all four directions
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                // Check if the new position is within bounds and not visited
                if (newX >= 0 && newX < mySize && newY >= 0 && newY < mySize && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.offer(new int[]{newX, newY});
                }
            }
        }
        return false; // Exit not found
    }

    public Room[][] getMyDungeon() {
        return myDungeon;
    }

    public void setMyDungeon(final Room[][] theDungeon) {
        myDungeon = theDungeon;
    }

    public int getMyAdventurerLocation() {
        return myAdventurerLocation;
    }

    public void setMyAdventurerLocation(final int theAdventurerLocation) {
        myAdventurerLocation = theAdventurerLocation;
    }

    private void placeExit(){
    }



    private void placeEntrance(){
        //It sets the entrance attribute of the room at the top-left corner of the maze to true.
        myDungeon[0][0].setMyEntrance(true);
    }

    private void placePillars(){
        int numOfPillars = 0;
        Set<Integer> usedPositions = new HashSet<>();
        while (numOfPillars < 4) {
            int x = random.nextInt(mySize);
            int y = random.nextInt(mySize);
            int position = x * mySize + y; // Unique position identifier

            // Check if the position is already used or if it's at the entrance or exit
            if (!usedPositions.contains(position) && !myDungeon[x][y].isMyEntrance() && !myDungeon[x][y].isMyExit()) {
                myDungeon[x][y].setMyPillar("");
                usedPositions.add(position);
                numOfPillars++;
            }
        }

    }

    @Override
    public String toString() {
        return "Dungeon{" +
                "myDungeon=" + Arrays.toString(myDungeon) +
                ", myAdventurerLocation=" + myAdventurerLocation +
                '}';
    }
}
