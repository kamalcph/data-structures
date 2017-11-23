package my.training.arrays;

public class Find2ndMax {

    private static int find2ndMax(int[] nums) {
        int left=0;
        int right=nums.length - 1;
        int max2 = -1;

        while (left < right) {
            if (nums[left] < nums[right]) {
                if (max2 < nums[left]) {
                    max2 = nums[left];
                }
                left++;
            } else {
                if (max2 < nums[right]) {
                    max2 = nums[right];
                }
                right--;
            }
        }
        return max2;
    }

    public static void main(String[] args) {
        int[] nums = {56, 6, 5, 9, 2, 11, 19, 55, 45, 32, 195};
        System.out.println(find2ndMax(nums));
    }
}
