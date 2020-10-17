/**
 * @author Spencer Li aka Spencillian
 * @assigned 9/24/2020 - 24 September 2020
 * @submitted 9/28/2020 - 28 September 2020
 * @functionality
 * Should work with 1x1, 1xn, and nx1 image cases.
 * Won't work with bad data.
 * The reader function is unable to tell the size of the image file so these numbers must be manually entered correctly.
 * Deals with min and max issues with 1x1 images that would result in divide by zero error.
 */

import java.util.*;
import java.io.*;
import java.awt.*;

public class MountainPaths {

    public static void main(String[] args) throws Exception {

        //construct DrawingPanel, and get its Graphics context
        DrawingPanel panel = new DrawingPanel(844, 480);
        Graphics g = panel.getGraphics();

        //Test Step 1 - construct mountain map data
        Scanner S = new Scanner(new File("Colorado_844x480.dat"));
        int[][] grid = read(S, 480, 844);

        //Test Step 2 - min, max
        int min = findMinValue(grid);
        System.out.println("Min value in map: " + min);

        int max = findMaxValue(grid);
        System.out.println("Max value in map: " + max);


        //Test Step 3 - draw the map
        drawMap(g, grid);

        //Test Step 4 - draw a greedy path

        // 4.1 implement indexOfMinInCol
        int minRow = indexOfMinInCol(grid, 0); // find the smallest value in col 0
        System.out.println("Row with lowest val in col 0: " + minRow);

        // 4.2 use minRow as starting point to draw path
        g.setColor(Color.RED); //can set the color of the 'brush' before drawing, then method doesn't need to worry about it
        int totalChange = drawLowestPathBranchRecurseRight(g, grid, minRow, 0); //
        System.out.println("Lowest-Elevation-Change Path starting at row " + minRow + " gives total change of: " + totalChange);

        //Test Step 5 - draw the best path
        g.setColor(new Color(0,0,0, 0));
//        int[] bestRow = indexOfLowestElevPath(g, grid);
        int bestRow = indexOfLowestElevPath(g, grid);

        //map.drawMap(g); //use this to get rid of all red lines
        g.setColor(Color.GREEN); //set brush to green for drawing best path
//        totalChange = drawLowestPathRecurse(g, grid, bestRow[0], bestRow[1]);
        totalChange = drawLowestPathRecurse(g, grid, bestRow, 0);
        System.out.println("The Lowest-Elevation-Change Path starts at row and col: " + bestRow + " and gives a total change of: " + totalChange);


    }

    /**
     * @param S       a Scanner instantiated and pointing at a file
     * @param numRows the number of rows represented in the file
     * @param numCols the number of cols represented in the file
     * @return a 2D array (rows x cols) of the data from the file read
     */
    public static int[][] read(Scanner S, int numRows, int numCols) {

        // Initializing grid of data for scanner
        int[][] grid = new int[numRows][numCols];

        // Read data from file into grid[][]
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // Read next int from file and put it into grid[][]
                grid[i][j] = S.nextInt();
            }
        }

        return grid;
    }


    /**
     * @param grid a 2D array from which you want to find the smallest value
     * @return the smallest value in the given 2D array
     */
    public static int findMinValue(int[][] grid) {

        // Maximum value as a reference
        int min = Integer.MAX_VALUE;

        // Loop through grid to find min value
        for (int[] arr : grid) {
            for (int x : arr) {
                // Each time a number is less than a previous number measured, its saved to min
                // This will result in the minimum value of grid[][] being put in min
                if (x < min) {
                    min = x;
                }
            }
        }

        // Return min
        return min;

    }

    /**
     * @param grid a 2D array from which you want to find the largest value
     * @return the largest value in the given 2D array
     */
    public static int findMaxValue(int[][] grid) {

        // Minimum value as a reference
        int max = Integer.MIN_VALUE;

        // Loop through grid to find max value
        for (int[] arr : grid) {
            for (int x : arr) {
                // Each time a number is greater than a previous number measured, its saved to max
                // This will result in the max value of grid[][] being put in max
                if (x > max) {
                    max = x;
                }
            }
        }

        // Return max
        return max;

    }

    /**
     * Given a 2D array of elevation data create a image of size rows x cols,
     * drawing a 1x1 rectangle for each value in the array whose color is set
     * to a a scaled gray value (0-255).  Note: to scale the values in the array
     * to 0-255 you must find the min and max values in the original data first.
     *
     * @param g    a Graphics context to use
     * @param grid a 2D array of the data
     */
    public static void drawMap(Graphics g, int[][] grid) {

        // Get min and max values from previously defined functions
        int min = findMinValue(grid);
        int max = findMaxValue(grid);

        // Loop through all values in grid to give them each a color
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // Initialize color variable
                int c;

                // Exception case for when min and max are the same, resulting in a dividing by zero error
                try{
                    // Setting the value of c as a number between 0-255 based on its place between min and max
                    c = (int) (255 * ((double) (grid[i][j] - min)) / (max - min));
                }catch (Exception e){
                    // Set to max value if there is error setting c based on range
                    c = 255;
                    System.out.println("You fucking did 1 pixel didn't you");
                }

                // Set greyscale color
                g.setColor(new Color(c, c, c));

                // Fill rectangle with predetermined color
                g.fillRect(j, i, 1, 1);
            }
        }
    }

    /**
     * Scan a single column of a 2D array and return the index of the
     * row that contains the smallest value
     *
     * @param grid a 2D array
     * @return the index of smallest value from grid at the given col
     * @col the column in the 2D array to process
     */
    public static int indexOfMinInCol(int[][] grid, int col) {

        // Set min to max value for reference
        int min = Integer.MAX_VALUE;

        // Initializing index storage variable
        int minIndex = 0;

        // Loop through all each row in the given column
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][col] < min) { // If smaller value is found, store the value and the index of that value
                min = grid[i][col];
                minIndex = i;
            }
        }

        // Return index with minimum value in col
        return minIndex;
    }


    /**
     * Find the minimum elevation-change route from West-to-East in the given grid, from the
     * given starting row, and draw it using the given graphics context
     *
     * @param g    - the graphics context to use
     * @param grid - the 2D array of elevation values
     * @param row  - the starting row for traversing to find the min path
     * @return total elevation of the route
     */
    public static int drawLowestElevPath(Graphics g, int[][] grid, int row) {
        // Initialize temp new row storage variable
        int nRow = row;

        // Initialize variable to track deviance across whole journeys
        int deviance = 0;

        // Loop through each column to decide the next move
        for (int i = 0; i < grid[0].length - 1; i++) {
            // Initialize a variable to store smallest difference
            int minDif = Integer.MAX_VALUE;

            // Variable to track difference between current position and next possible position
            int dif;

            // Loop through the next 3 possible locations
            for (int j = 0; j < 3; j++) {
                // Initialize variable to store the row that the program is checking
                int tempRow = row + j - 1;

                try {
                    // Get difference between current position value and new possible value
                    dif = Math.abs(grid[tempRow][i + 1] - grid[row][i]);
                } catch (Exception e) {
                    // If there is an array out of bounds error it skips this part of the loop
                    continue;
                }

                if (dif < minDif) { // If dif is smallest value that the program has checked
                    minDif = dif; // Set minDif as dif
                    nRow = tempRow; // Store the row of the smallest different
                }
            }

            // Move to the next row
            row = nRow;

            // Add difference between current spot and new spot to deviance
            deviance += minDif;

            // Fill in new square
            g.fillRect(i, row, 1, 1);
        }

        // Return deviance
        return deviance;
    }

    public static int drawLowestPathRecurse(Graphics g, int[][] grid, int row, int col){
        return drawLowestPathRecurseLeft(g, grid, row, col) + drawLowestPathRecurseRight(g, grid, row, col);
    }

    public static int drawLowestPathRecurseRight(Graphics g, int[][] grid, int row, int col){
        int dif, minDif = Integer.MAX_VALUE, minRow = Integer.MAX_VALUE;
        for(int i = 0; i < 3; i++){
            int tempRow = row - 1 + i;
            try {
                // Get difference between current position value and new possible value
                dif = Math.abs(grid[tempRow][col + 1] - grid[row][col]);
            } catch (Exception e) {
                // If there is an array out of bounds error it skips this part of the loop
                continue;
            }

            if(dif < minDif){
                minDif = dif;
                minRow = tempRow;
            }
        }

        g.fillRect(col, row, 1, 1);

        if(col >= grid[0].length){
            return minDif;
        }
        return minDif + drawLowestPathRecurseRight(g, grid, minRow, col + 1);
    }


    public static int drawLowestPathRecurseLeft(Graphics g, int[][] grid, int row, int col){
        int dif, minDif = Integer.MAX_VALUE, minRow = Integer.MAX_VALUE;
        for(int i = 0; i < 3; i++){
            int tempRow = row - 1 + i;
            try {
                // Get difference between current position value and new possible value
                dif = Math.abs(grid[tempRow][col - 1] - grid[row][col]);
            } catch (Exception e) {
                // If there is an array out of bounds error it skips this part of the loop
                continue;
            }

            if(dif <= minDif){
                minDif = dif;
                minRow = tempRow;
            }
        }

        g.fillRect(col, row, 1, 1);

        if(col < 0){
            return minDif;
        }
        return minDif + drawLowestPathRecurseLeft(g, grid, minRow, col - 1);
    }

    public static int drawLowestPathBranchRecurseRight(Graphics g, int[][] grid, int row, int col){

        if(col >= grid[0].length){
            return 0;
        }

        int dif, minDif = Integer.MAX_VALUE;
        int[] difArr = new int[3];
        for(int i = 0; i < 3; i++){
            int tempRow = row - 1 + i;
            try {
                // Get difference between current position value and new possible value
                dif = Math.abs(grid[tempRow][col + 1] - grid[row][col]);
            } catch (Exception e) {
                // If there is an array out of bounds error it skips this part of the loop
                dif = Integer.MAX_VALUE;
            }

            difArr[i] = dif;

            if(dif < minDif){
                minDif = dif;
            }
        }

        g.fillRect(col, row, 1, 1);

        int min = Integer.MAX_VALUE;

        for(int i = 0; i < difArr.length; i++){
            if(difArr[i] == minDif){
                difArr[i] = drawLowestPathBranchRecurseRight(g, grid, row + i - 1, col + 1);
            }else{
                difArr[i] = Integer.MAX_VALUE;
            }

            if(difArr[i] < min){
                min = difArr[i];
            }
        }

        if(min < 0 || min == Integer.MAX_VALUE){
            min = minDif;
        }

//        System.out.println(Arrays.toString(difArr) + " " + minDif);
        return minDif + min;
    }

    /**
     * Generate all west-to-east paths, find the one with the lowest total elevation change,
     * and return the index of the row that path starts on.
     *
     * @param g    - the graphics context to use
     * @param grid - the 2D array of elevation values
     * @return the index of the row where the lowest elevation-change path starts.
     */
//    public static int[] indexOfLowestElevPath(Graphics g, int[][] grid) {
//        int min = Integer.MAX_VALUE; // Set reference for min with MAX_VALUE
//        int[] minIndex = new int[2]; // Initialize minIndex
//
//        // Loop through each starting position and find their respective shortest routes
//        for (int i = 0; i < grid.length; i++) {
//            for(int j = 0; j < grid[0].length; j++){
//                // Draw shortest path from the starting row i
//                int deviance = drawLowestPathRecurse(g, grid, i, j);
//
//                if (deviance < min) { // If the deviance from the path is smaller than all previous ones set it as min
//                    min = deviance;
//                    minIndex = new int[]{i, j}; // Store the index of the minimum deviance
//                }
//            }
//        }
//
//        // Return index of minimum deviance
//        return minIndex;
//    }

    public static int indexOfLowestElevPath(Graphics g, int[][] grid) {
        int min = Integer.MAX_VALUE; // Set reference for min with MAX_VALUE
        int minIndex = 0; // Initialize minIndex

        // Loop through each starting position and find their respective shortest routes
        for (int i = 0; i < grid.length; i++) {
            // Draw shortest path from the starting row i
            int deviance = drawLowestPathRecurse(g, grid, i, 0);

            if (deviance < min) { // If the deviance from the path is smaller than all previous ones set it as min
                min = deviance;
                minIndex = i; // Store the index of the minimum deviance
            }
        }

        // Return index of minimum deviance
        return minIndex;
    }
}