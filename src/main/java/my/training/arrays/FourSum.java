package my.training.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    private static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }

        int max = nums[nums.length - 1];
        if (4 * nums[0] > target || 4 * max < target)
            return res;

        int z;
        for (int i=0; i<nums.length; i++) {
            z = nums[i];

            if (i > 0 && z == nums[i-1]) // to eliminate duplicates
                continue;

            if (z + 3 * max < target) { // small value
                continue;
            }

            if (4 * z == target) { // boundary
                if (i+3 < nums.length && nums[i+3] == z) {
                    res.add(Arrays.asList(z, z, z, z));
                    break;
                }
            }

            find3Sum(nums, target-z, i+1, res, z);
        }
        return res;
    }

    private static void find3Sum(int[] nums, int target, int low, List<List<Integer>> res, int z1) {
        for (int i=low; i<nums.length-2; i++) {
            if (i==0 || (i > 0 && nums[i] != nums[i-1])) { // to eliminate duplicates
                int left = i+1;
                int right = nums.length-1;

                while (left < right) {
                    int result = nums[i] + nums[left] + nums[right];
                    if (result == target) {
                        res.add(Arrays.asList(z1, nums[i], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left+1]) left++;
                        while (left < right && nums[right] == nums[right-1]) right--;
                        left++; right--;
                    } else if (result < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] S = {1, 0, -1, 0, -2, 2};
        System.out.println(fourSum(S, 0));
    }
}
