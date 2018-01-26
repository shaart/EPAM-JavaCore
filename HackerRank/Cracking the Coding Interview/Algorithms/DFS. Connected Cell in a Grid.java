
// Task Source: https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid/problem

import java.util.Scanner;

public class Solution {
    public static int connectionLength(int[][] grid, int[][] path, int r, int c) {
        if (r < 0 || r >= grid.length) return 0;
        if (c < 0 || c >= grid[r].length) return 0;
        
        if (grid[r][c] == 0) return 0;        
        if (path[r][c] == 1) return 0;
        
        path[r][c] = 1;        
        return 1
            + connectionLength(grid, path, r-1, c-1) + connectionLength(grid, path, r-1, c) + connectionLength(grid, path, r-1, c+1)
            + connectionLength(grid, path, r, c-1)                                          + connectionLength(grid, path, r, c+1)
            + connectionLength(grid, path, r+1, c-1) + connectionLength(grid, path, r+1, c) + connectionLength(grid, path, r+1, c+1);
    }
    
    public static int largestConnection(int[][] grid, int[][] path) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1 && path[i][j] == 0) {
                    int len = connectionLength(grid, path, i, j);
                    if (len > max) {
                        max = len;
                    }
                }
            }
        }
        
        return max;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int grid[][] = new int[n][m];
        for(int grid_i=0; grid_i < n; grid_i++){
            for(int grid_j=0; grid_j < m; grid_j++){
                grid[grid_i][grid_j] = in.nextInt();
            }
        }
        System.out.println(largestConnection(grid, new int[n][m]));
    }
}
