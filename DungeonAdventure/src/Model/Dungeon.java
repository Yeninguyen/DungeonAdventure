package Model;//package Model;
//import java.util.*;
//public class Dungeon {
//    private Room[][] myDungeon;
//    private int myAdventurerLocation;
//    private int mySize;
//    private Random random;
//    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//    public Dungeon(int theSize) {
//        this.mySize = theSize;
//        myDungeon = new Room[theSize][theSize];
//        random = new Random();
//    }
//
//    private void generateDungeon() {
//        // Initialize each room in the dungeon
//        for (int i = 0; i < mySize; i++) {
//            for (int j = 0; j < mySize; j++) {
//                //myDungeon[i][j] = new Room(); // Initialize each room as a new Room object
//            }
//        }
//
//        // Check if the dungeon is traversable before placing exit, entrance, and pillars
//        while (!isTraversable()) {
//            // Reset entrance, exit, and pillars
//            for (int i = 0; i < mySize; i++) {
//                for (int j = 0; j < mySize; j++) {
//                    myDungeon[i][j].setMyEntrance(false);
//                    myDungeon[i][j].setExit(false);
//                    myDungeon[i][j].setMyPillar("");
//                }
//            }
//        }
//        placeExit();
//
//    }
//
//    private boolean isTraversable(){
//        Queue<int[]> queue = new LinkedList<>();
//        boolean[][] visited = new boolean[mySize][mySize];
//        queue.offer(new int[]{0, 0}); // Start from the entrance
//        visited[0][0] = true;
//
//        while (!queue.isEmpty()) {
//            int[] current = queue.poll();
//            int x = current[0];
//            int y = current[1];
//
//            // Check if the current room is the exit
//            if (myDungeon[x][y].isMyExit()) {
//                return true; // Found exit
//            }
//
//            // Check all four directions
//            for (int[] dir : directions) {
//                int newX = x + dir[0];
//                int newY = y + dir[1];
//
//                // Check if the new position is within bounds and not visited
//                if (newX >= 0 && newX < mySize && newY >= 0 && newY < mySize && !visited[newX][newY]) {
//                    visited[newX][newY] = true;
//                    queue.offer(new int[]{newX, newY});
//                }
//            }
//        }
//        return false; // Exit not found
//    }
//
//    public Room[][] getMyDungeon() {
//        return myDungeon;
//    }
//
//    public void setMyDungeon(final Room[][] theDungeon) {
//        myDungeon = theDungeon;
//    }
//
//    public int getMyAdventurerLocation() {
//        return myAdventurerLocation;
//    }
//
//    public void setMyAdventurerLocation(final int theAdventurerLocation) {
//        myAdventurerLocation = theAdventurerLocation;
//    }
//
//    private void placeExit(){
//    }
//
//
//
//    private void placeEntrance(){
//        //It sets the entrance attribute of the room at the top-left corner of the maze to true.
//        myDungeon[0][0].setMyEntrance(true);
//    }
//
//    private void placePillars(){
//        int numOfPillars = 0;
//        Set<Integer> usedPositions = new HashSet<>();
//        while (numOfPillars < 4) {
//            int x = random.nextInt(mySize);
//            int y = random.nextInt(mySize);
//            int position = x * mySize + y; // Unique position identifier
//
//            // Check if the position is already used or if it's at the entrance or exit
//            if (!usedPositions.contains(position) && !myDungeon[x][y].isMyEntrance() && !myDungeon[x][y].isMyExit()) {
//                myDungeon[x][y].setMyPillar("");
//                usedPositions.add(position);
//                numOfPillars++;
//            }
//        }
//
//    }
//
//    @Override
//    public String toString() {
//        return "Dungeon{" +
//                "myDungeon=" + Arrays.toString(myDungeon) +
//                ", myAdventurerLocation=" + myAdventurerLocation +
//                '}';
//    }
//
//    public static void main(String[] args) {
//        Dungeon d = new Dungeon(5);
//        System.out.println(d);
//    }
//}

import java.util.*;

public class Dungeon {
    private static final int MAZE_SIZE = 4; // 4x4 maze
    private static final int NUM_PILLARS = 4; // Number of pillars to place
    private static final String[] PILLAR_NAMES = {"A", "E", "I", "P"};
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private Room[][] maze;
    private Room entrance;
    private Room exit;
    private List<Room> pillars;
    private Room adventurerLocation;

    public Dungeon() {
        generateMaze();
        placePillars();
    }

    private void generateMaze() {
        maze = new Room[MAZE_SIZE][MAZE_SIZE];
        pillars = new ArrayList<>();

        // Create rooms
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int j = 0; j < MAZE_SIZE; j++) {
                maze[i][j] = new Room();
            }
        }

        // Place entrance and exit
        entrance = maze[0][0];
        entrance.setMyEntrance(true);
        exit = maze[MAZE_SIZE - 1][MAZE_SIZE - 1];
        exit.setMyExit(true);

        // Ensure traversal from entrance to exit is possible
        ensureTraversability();
    }

    private void ensureTraversability() {
        while (!isTraversable()) {
            generateMaze();
        }
    }

    private boolean isTraversable() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[MAZE_SIZE][MAZE_SIZE];
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (maze[x][y].isMyExit()) {
                return true;
            }

            for (int[] dir : DIRECTIONS) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < MAZE_SIZE && newY >= 0 && newY < MAZE_SIZE && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.offer(new int[]{newX, newY});
                }
            }
        }
        return false;
    }

    private void placePillars() {
        Random random = new Random();
        List<Room> availableRooms = new ArrayList<>();

        // Collect available rooms (excluding entrance and exit)
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int j = 0; j < MAZE_SIZE; j++) {
                Room room = maze[i][j];
                if (!room.isMyEntrance() && !room.isMyExit()) {
                    availableRooms.add(room);
                }
            }
        }

        // Place pillars randomly in available rooms
        for (int i = 0; i < NUM_PILLARS; i++) {
            int randomIndex = random.nextInt(availableRooms.size());
            Room pillarRoom = availableRooms.get(randomIndex);
            pillarRoom.setMyPillar(PILLAR_NAMES[i]);
            pillars.add(pillarRoom);
            availableRooms.remove(randomIndex);
        }
    }

    public void setAdventurerLocation(Room room) {
        adventurerLocation = room;
    }

    public Room getAdventurerLocation() {
        return adventurerLocation;
    }

    public Room getEntrance() {
        return entrance;
    }

    public Room getExit() {
        return exit;
    }

    public List<Room> getPillars() {
        return pillars;
    }

    public Room[][] getMaze() {
        return maze;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int j = 0; j < MAZE_SIZE; j++) {
                Room room = maze[i][j];
                sb.append(room.toString());
                if (j != MAZE_SIZE - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Dungeon d = new Dungeon();
        System.out.println(d);
    }
}
