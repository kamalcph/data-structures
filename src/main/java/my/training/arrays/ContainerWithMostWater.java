package my.training.arrays;

import java.util.Arrays;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container and n is at least 2.
 */
public class ContainerWithMostWater {

    private static int[] linesWithMaxArea(int[] heights) {
        int maxArea = 0;
        int[] walls = new int[2];
        int left = 0;
        int right = heights.length - 1;

        while (left < right) {
            int area = Math.min(heights[left], heights[right]) * (right - left);
            System.out.println("Area of wall [" + left + ", " + right + "] = " + area );
            if (area > maxArea) {
                walls[0] = left;
                walls[1] = right;
                maxArea = area;
            }
            if (heights[left] < heights[right])
                left++;
            else
                right--;
        }
        System.out.println("Maximum area is " + maxArea);
        return walls;
    }

    public static void main(String[] args) {
        int[] heights = {6, 5, 9, 2, 11, 19, 55};
        System.out.println(Arrays.toString(linesWithMaxArea(heights)));
    }
}
