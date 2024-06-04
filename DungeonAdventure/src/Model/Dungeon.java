package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Dungeon {
    private Room[][] maze;
    public int mySize = 3;
    private int myNumberOfEntrances;
    private int myNumberOfExits;
    private int myNumberOfPillars;
    private static Dungeon myUniqueInstance;
    public Dungeon() {
    }



    public void generateMaze(int theSize) {
        mySize = theSize;
        maze = new Room[theSize][theSize];
        boolean validMaze = false;
        while (!validMaze) {
            // Initialize maze with empty rooms
            for (int i = 0; i < mySize; i++) {
                for (int j = 0; j < mySize; j++) {
                    maze[i][j] = new Room(mySize); // This will automatically set up the room items
                    maze[i][j].setMyX(j);
                    maze[i][j].setMyY(i);
                }
            }

            // Place the pillars
            List<int[]> pillarPositions = new ArrayList<>();
            while (pillarPositions.size() < 4) {
                int x = (int) (Math.random() * mySize);
                int y = (int) (Math.random() * mySize);
                if (!maze[x][y].getMyHasPillar() && !maze[x][y].isMyEntrance() && !maze[x][y].isMyExit()) {
                    pillarPositions.add(new int[]{x, y});
                }
            }

            // Assign pillars to the selected positions
            char[] pillars = {'A', 'P', 'E', 'I'};
            Collections.shuffle(Arrays.asList(pillars));

            for (int i = 0; i < 4; i++) {
                int[] pos = pillarPositions.get(i);
                maze[pos[0]][pos[1]].setMyItem(pillars[i]);
                maze[pos[0]][pos[1]].setMyHasPillar(true);
            }

            setEntranceAndExit();

            // Check if the maze is traversable
            if (isTraversable()) {
                validMaze = true;
            } else {
                myNumberOfEntrances = 0;
                myNumberOfExits = 0;
                myNumberOfPillars = 0;
            }
        }
    }
//    public void generateMaze(int theSize) {
//        SIZE = theSize;
//        maze = new Room[theSize][theSize];
//        boolean validMaze = false;
//        while (!validMaze) {
//            // Initialize maze with empty rooms
//            for (int i = 0; i < theSize; i++) {
//                for (int j = 0; j < theSize; j++) {
//                    maze[i][j] = new Room(theSize); // This will automatically set up the room items
//                    maze[i][j].setMyX(j);
//                    maze[i][j].setMyY(i);
//                }
//            }
//
//            // Place the pillars
//            List<int[]> pillarPositions = new ArrayList<>();
//            while (pillarPositions.size() < 4) {
//                int x = (int) (Math.random() * SIZE);
//                int y = (int) (Math.random() * SIZE);
//                if (!maze[x][y].getMyHasPillar() && !maze[x][y].isMyEntrance() && !maze[x][y].isMyExit()) {
//                    pillarPositions.add(new int[]{x, y});
//                }
//            }
//
//            // Assign pillars to the selected positions
//            char[] pillars = {'A', 'P', 'E', 'I'};
//            Collections.shuffle(Arrays.asList(pillars));
//
//            for (int i = 0; i < 4; i++) {
//                int[] pos = pillarPositions.get(i);
//                maze[pos[0]][pos[1]].setMyItem(pillars[i]);
//                maze[pos[0]][pos[1]].setMyHasPillar(true);
//            }
//
//            setEntranceAndExit();
//
//            // Check if the maze is traversable
//            if (isTraversable()) {
//                validMaze = true;
//            } else {
//                myNumberOfEntrances = 0;
//                myNumberOfExits = 0;
//                myNumberOfPillars = 0;
//            }
//        }
//    }

    private void setEntranceAndExit() {
        int entranceX;
        int entranceY = (int) (Math.random() * mySize);
        int exitX;
        int exitY;
        if(entranceY==0 || entranceY== mySize -1) {
            entranceX = (int) (Math.random() * mySize);
            exitY = mySize - 1 - entranceY;
            exitX = (int) (Math.random() * mySize);
        } else{
            int[]indexes = new int []{0, mySize -1};
            int randomIndex = (int) (Math.random() * indexes.length);
            entranceX = indexes[randomIndex];
            exitX = mySize - 1;
            exitX -= entranceX;
            exitY =  (int) (Math.random() * mySize);
        }

        // Place entrance and exit
        maze[entranceY][entranceX].setMyEntrance(true);
        maze[exitY][exitX].setMyExit(true);
    }


    public boolean dfs(int x, int y, boolean[][] visited) {
        if (x < 0 || x >= mySize || y < 0 || y >= mySize || visited[x][y])
            return false;
        visited[x][y] = true;
        if (maze[x][y].isMyExit()) {
            myNumberOfExits++;
        } else if (maze[x][y].isMyEntrance()) {
            myNumberOfEntrances++;
        } else if(maze[x][y].getMyHasPillar()){
            myNumberOfPillars++;
        }
        if (myNumberOfEntrances > 1 || myNumberOfExits > 1 || myNumberOfPillars > 4 ||
                maze[x][y].isMyExit() && maze[x][y].isMyEntrance()) {
            return false; // More than one entrance or exit found, maze is invalid
        }
        if (maze[x][y].isMyExit())
            return true;
        boolean up = dfs(x - 1, y, visited);
        boolean down = dfs(x + 1, y, visited);
        boolean left = dfs(x, y - 1, visited);
        boolean right = dfs(x, y + 1, visited);
        return up || down || left || right;
    }

    public boolean isTraversable() {
        boolean[][] visited = new boolean[mySize][mySize];
        return dfs(0, 0, visited) && myNumberOfPillars==4;
    }


    public void writeMazeToFile(String theFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DungeonAdventure/src/Maps/" + theFilePath))) {
            // Write the top parts of the rooms in the first row
            StringBuilder topRow = new StringBuilder();
            StringBuilder bottomRow = new StringBuilder();
            StringBuilder middleRow = new StringBuilder();
            StringBuilder fillerRow = new StringBuilder();

            String filler = "* - - - * ";
            for (int i = 0; i < mySize; i++) {
                fillerRow.append(filler);
            }

            for (int row = 0; row < maze.length; row++) {
                for (int col = 0; col < maze[row].length; col++) {
                    topRow.append(maze[row][col].getPartOfTheRoom(maze[row][col], 0));
                    middleRow.append(maze[row][col].getPartOfTheRoom(maze[row][col], 1));
                    bottomRow.append(maze[row][col].getPartOfTheRoom(maze[row][col], 2));
                }
                writer.write(topRow.toString() + "\n");
                writer.write(fillerRow.toString() + "\n");
                writer.write(middleRow.toString() + "\n");
                writer.write(fillerRow.toString() + "\n");
                writer.write(bottomRow.toString() + "\n");
                topRow.setLength(0);
                bottomRow.setLength(0);
                middleRow.setLength(0);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mySize; i++) {
            sb.append("+---".repeat(mySize));
            sb.append("+\n");
            for (int j = 0; j < mySize; j++) {
                sb.append("| ");
                sb.append(maze[i][j].getMyItem()).append(" ");
            }
            sb.append("|\n");
        }
        sb.append("+---".repeat(mySize));
        sb.append("+\n");
        return sb.toString();
    }
    public static Dungeon getInstance() {
        if (myUniqueInstance == null) {
            myUniqueInstance = new Dungeon();
        }
        return myUniqueInstance;
    }
    public static Dungeon get_TEST_instance(){
        return new Dungeon();
    }

    public int getMySize() {
        return mySize;
    }
    public Room[][] getMaze() {
        return maze;
    }

    public static void main(String[] args) {
        new Dungeon();
    }
}