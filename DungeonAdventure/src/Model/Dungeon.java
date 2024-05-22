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
    public Dungeon() {
        maze = new Room[SIZE][SIZE];
        generateMaze();

    }

    private void generateMaze() {
        boolean validMaze = false;
        while (!validMaze) {
            // Initialize maze with empty rooms
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    maze[i][j] = new Room(); // This will automatically set up the room items
                    maze[i][j].setMyX(i);
                    maze[i][j].setMyY(j);
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

            // Place entrance and exit
            maze[0][0].setMyEntrance(true);
            maze[SIZE - 1][SIZE - 1].setMyExit(true);

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

    public static void main(String[] args) {

        for(int i=0;i< 10;i++){
            Dungeon dungeon = new Dungeon();
            System.out.println(dungeon);
        }


    }
}