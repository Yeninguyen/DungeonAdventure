package Model;

import java.util.*;

public class Dungeon {
    private final Room[][] maze;
    private final int SIZE = 8;
    private int myNumberOfEntrances;
    private int myNumberOfExits;
    private int myNumberOfPillars;
    public Dungeon() {
        maze = new Room[SIZE][SIZE];
        generateMaze();

    }

    private void generateMaze() {
        boolean validMaze = false;
        while (!validMaze) {
            Set<Character> placedPillars = new HashSet<>(); // Initialize placedPillars set
            // Initialize maze with empty rooms
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    maze[i][j] = new Room();
                    maze[i][j].setMyX(i);
                    maze[i][j].setMyY(j);
                    if (!maze[i][j].getMyEntrance() && !maze[i][j].getMyExit()) {
                        setUpPillars(maze[i][j], placedPillars);
                    }
                }
            }

            // Place entrance and exit
            maze[0][0].setMyEntrance(true);
            maze[0][0].setMyItem('i'); // Set entrance
            maze[SIZE - 1][SIZE - 1].setMyExit(true);
            maze[SIZE - 1][SIZE - 1].setMyItem('o'); // Set exit

            // Check if the maze is traversable
            if (isTraversable()) {
                validMaze = true;
            } else {
                // Reset counts if the maze is not valid
                myNumberOfEntrances = 0;
                myNumberOfExits = 0;
                myNumberOfPillars = 0;
            }
        }
    }

    private void setUpPillars(Room theRoom, Set<Character> placedPillars) {
            // Check if the room is empty and no pillar is placed yet
            if (theRoom.getMyItem() == ' ' && myNumberOfPillars == 0) {
                // Check if the room is not too close to the entrance or exit
                if (!isTooCloseToEntranceOrExit(theRoom)) {
                    // Select a random pillar from the set of options
                    List<Character> pillarOptions = Arrays.asList('A', 'P', 'I', 'E');
                    Collections.shuffle(pillarOptions);
                    char pillar = pillarOptions.get(0); // Randomly select a pillar
                    // Place the pillar in the room
                    theRoom.setMyItem(pillar);
                    placedPillars.add(pillar);
                    myNumberOfPillars++;
                }
        }
    }

    private boolean isTooCloseToEntranceOrExit(Room room) {
        int x = room.getMyX();
        int y = room.getMyY();
        int distanceFromEntrance = Math.abs(x) + Math.abs(y); // Manhattan distance from entrance
        int distanceFromExit = Math.abs(SIZE - 1 - x) + Math.abs(SIZE - 1 - y); // Manhattan distance from exit
        return distanceFromEntrance < 3 || distanceFromExit < 3;
    }

    private boolean dfs(int x, int y, boolean[][] visited) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE || visited[x][y])
            return false;
        visited[x][y] = true;
        if (maze[x][y].isMyExit()) {
            myNumberOfExits++;
        } else if (maze[x][y].isMyEntrance()) {
            myNumberOfEntrances++;
        } else if(maze[x][y].getMyHasPillar()){
            myNumberOfPillars++;
        }
        if (myNumberOfEntrances > 1 || myNumberOfExits > 1 || myNumberOfPillars > 4) {
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

    private boolean isTraversable() {
        boolean[][] visited = new boolean[SIZE][SIZE];
        return dfs(0, 0, visited) && myNumberOfPillars==1;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            sb.append("+---".repeat(SIZE));
            sb.append("+\n");
            for (int j = 0; j < SIZE; j++) {
                sb.append("| ");
                sb.append(maze[i][j].getMyItem()).append(" ");
            }
            sb.append("|\n");
        }
        sb.append("+---".repeat(SIZE));
        sb.append("+\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        Dungeon dungeon = new Dungeon();
        System.out.println(dungeon);
        System.out.println("Testing dungeon matches room toString");
        for(int i=0;i< dungeon.SIZE;i++){
            for(int j=0;j< dungeon.SIZE;j++){
                System.out.println(dungeon.maze[i][j].toString());
                System.out.println();
            }
            System.out.println();
        }


    }
}
