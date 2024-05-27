package Model;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Dungeon {
    private final Room[][] maze;
    private final int SIZE = 4;
    private int myNumberOfEntrances;
    private int myNumberOfExits;
    private int myNumberOfPillars;
    private static Dungeon myUniqueInstance;

    public int getSIZE() {
        return SIZE;
    }

    private Dungeon() {
        maze = new Room[SIZE][SIZE];
        generateMaze();

    }

    public Room[][] getMaze() {
        return maze;
    }

    private void generateMaze() {
        boolean validMaze = false;
        while (!validMaze) {
            // Initialize maze with empty rooms
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    maze[i][j] = new Room(); // This will automatically set up the room items
                    maze[i][j].setMyX(j);
                    maze[i][j].setMyY(i);
                }
            }

            // Place the pillars
            List<int[]> pillarPositions = new ArrayList<>();
            while (pillarPositions.size() < 4) {
                int x = (int) (Math.random() * SIZE);
                int y = (int) (Math.random() * SIZE);
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

    private void setEntranceAndExit() {
        int entranceX;
        int entranceY = (int) (Math.random() * SIZE);
        int exitX;
        int exitY;
        if(entranceY==0 || entranceY==SIZE-1) {
            entranceX = (int) (Math.random() * SIZE);
            exitY = SIZE - 1 - entranceY;
            exitX = (int) (Math.random() * SIZE);
        } else{
                int[]indexes = new int []{0,SIZE-1};
                int randomIndex = (int) (Math.random() * indexes.length);
                entranceX = indexes[randomIndex];
                exitX = SIZE - 1;
                exitX -= entranceX;
                exitY =  (int) (Math.random() * SIZE);
        }

        // Place entrance and exit
        maze[entranceY][entranceX].setMyEntrance(true);
        maze[exitY][exitX].setMyExit(true);
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

    private boolean isTraversable() {
        boolean[][] visited = new boolean[SIZE][SIZE];
        return dfs(0, 0, visited) && myNumberOfPillars==4;
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
    public static Dungeon getInstance() {
        if (myUniqueInstance == null) {
            myUniqueInstance = new Dungeon();
        }
        return myUniqueInstance;
    }

    public static void main(String[] args) {
      Dungeon d = new Dungeon();
      System.out.println(d);
        for (int i = 0; i < d.maze.length; i++) {
            for (int j = 0; j < d.maze[i].length; j++) {
                System.out.print(d.maze[i][j]);
            }
            System.out.println();
        }



    }
}