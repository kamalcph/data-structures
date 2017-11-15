package my.training.arrays;

import java.util.Arrays;

/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 *
 * (eg) Given input array nums = [1, 1, 2]
 *
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't
 * matter what you leave beyond the new length.
 */
public class RemoveDuplicates {

    private static int removeDuplicates(int[] arr) {
        if (arr.length == 0)
            return 0;

        int j=0;
        for (int i=1; i<arr.length; i++) {
            if (arr[i] != arr[j]) {
                arr[++j] = arr[i];
            }
        }
        return ++j;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 6, 7, 9, 10, 14, 10, 9, 7, 2, 2};
        Arrays.sort(arr);
        final int length = removeDuplicates(arr);
        System.out.println(Arrays.toString(Arrays.copyOf(arr, length)));
    }
}
