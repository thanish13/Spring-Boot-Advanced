import java.util.LinkedList;
import java.util.Queue;

public class Q1 {

//    """
//    Given an n x m grid, where each cell has the following values :
//
//2 - represents a rotten orange
//1 - represents a Fresh orange
//0 - represents an Empty Cell
//Every minute, if a fresh orange is adjacent to a rotten orange in 4-direction ( upward, downwards, right, and left ) it becomes rotten.
//
//Return the minimum number of minutes required such that none of the cells has a Fresh Orange. If it's not possible, return -1.
//
//Example 1
//Input: grid = [ [2, 1, 1] , [0, 1, 1] , [1, 0, 1] ]
//Output: -1
//
//Example 2
//Input: grid = [ [2,1,1] , [1,1,0] , [0,1,1] ]
//Output: 4
//"""
//
//    grid = [ [2,1,1]
//            , [1,1,0] ,
//            [0,1,1] ]

    public static void main(String[] args) {

        int[][] grid = new int[][]{{2,1,1},{1,1,0},{0,1,1}};

        //int[][] grid = new int[][] {{2, 1, 1} , {0, 1, 1} , {1, 0, 1}};

        int row = grid.length;
        int col = grid[0].length;

        boolean[][] visited = new boolean[row][col];

        Queue<int[]> queue = new LinkedList<>();

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){

                if(grid[i][j] == 2){
                    queue.add(new int[]{i,j});
                }else if(grid[i][j] == 0){
                    visited[i][j]=true;
                }

            }
        }

        int time = 0;

        int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int[] current  = queue.poll();
                int curRow = current[0];
                int curCol = current[1];

                visited[curRow][curCol] = true;


                for(int[] d : directions){

                    int nextRow = curRow + d[0];
                    int nextCol = curCol + d[1];
                    if ( nextRow >= 0 && nextRow < row  && nextCol >= 0 && nextCol < col
                            && grid[nextRow][nextCol] == 1
                            && !visited[nextRow][nextCol]){

                        grid[nextRow][nextCol] = 2;
                        queue.add(new int[] {nextRow,nextCol});

                    }
                }
            }
            if (!queue.isEmpty()) {
                time++; // increment once per minute
            }

        }

        boolean allVisited = true;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    allVisited = false;
                    break;
                }
            }
        }

        System.out.println(time);

    }
}
