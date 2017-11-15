package my.training.arrays;

import java.util.Arrays;

/**
 * Given an array and a value, remove all instances of that value in place and return the new length.
 *
 */
public class RemoveElement {

    private static int removeElement(int[] arr, int num) {
        if (arr.length == 0) {
            return 0;
        }

        int slow = 0;
        for (int fast=0; fast<arr.length; fast++) {
            if (arr[fast] != num) {
                arr[slow++] = arr[fast];
            }
        }
        return slow; // length
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 6, 7, 9, 10, 14, 10, 9, 7, 2, 2};
        final int length = removeElement(arr, 2);
        System.out.println(Arrays.toString(Arrays.copyOf(arr, length)));
    }
}
