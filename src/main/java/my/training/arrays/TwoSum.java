package my.training.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You must assume that each input would have exactly one solution, and you may not use the same element twice.
 */
public class TwoSum {

    // my solution with 2 loops
    private static Integer[] findIndex(int[] nums, int target) {
        Integer[] indices = new Integer[2];
        for (int i=0; i<nums.length; i++) {
            indices[0] = i;
            for (int j=i+1; j<nums.length; j++) {
                if (nums[j] == (target - nums[i])) {
                    indices[1] = j;
                    break;
                }
            }
            if (indices[1] != null) {
                break;
            }
        }
        return indices;
    }

    // improved solution with single loop
    private static int[] twoSum(int[] nums, int target) {
        int[] arr = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            Integer val = map.get(target - nums[i]);
            if (val != null) {
                arr[0] = val;
                arr[1] = i;
                break;
            } else {
                map.put(nums[i], i);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] inputs = {4, 5, 3, 2, 1, 6, 7};
        Scanner scanner = new Scanner(System.in);
        int target = scanner.nextInt();
        System.out.println(Arrays.toString(findIndex(inputs, target)));
        System.out.println(Arrays.toString(twoSum(inputs, target)));
        scanner.close();
    }
}
