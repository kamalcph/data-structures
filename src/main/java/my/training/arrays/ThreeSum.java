package my.training.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0 ?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Note: The solution set must not contain duplicate triplets.
 */
public class ThreeSum {

    private static List<List<Integer>> threeSum(int[] nums, int target) {
        Arrays.sort(nums); // NOTE: Array is sorted here
        List<List<Integer>> triplets = new ArrayList<>();
        for (int i=0; i<nums.length-2; i++) {
            if (i == 0 || (i>0 && (nums[i] != nums[i-1]))) {
                int lo = i+1;
                int hi = nums.length-1;

                while (lo < hi) {
                    int result = nums[i] + nums[lo] + nums[hi];
                    if (result == target) {
                        triplets.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                        lo++; hi--;
                    } else if (result < target) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        return triplets;
    }

    public static void main(String[] args) {
        int[] S = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(S, -2));
    }
}
