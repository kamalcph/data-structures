package my.training.arrays;

public class FindMaxSubArray {

    public static void main(String[] args) {
        int[] arr = {4, 2, -1, 0, 4, 5, 6, -4, -2, -3, 8, 16, 19, 25};

        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int max = arr[0];

        for (int i=1; i<arr.length;  i++) {
            dp[i] = arr[i] + (dp[i-1] > 0 ? dp[i-1] : 0);
            max = Math.max(max, dp[i]);
        }
        System.out.println("The maximum sub array is " + max);
    }
}
