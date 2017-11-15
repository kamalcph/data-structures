package my.training.arrays;

import java.util.Arrays;

public class LongestIncreasingSubseq {

    public static void main(String[] args) {
        int[] arr = {3, 10, 2, 1, 20};
        int[] lis = new int[arr.length];
        for (int i=0; i<lis.length; i++) {
            lis[i] = 1;
        }

        for (int i=1; i<arr.length; i++) {
            for (int j=0; j<i; j++) {
                if ((arr[j] < arr[i]) && (lis[j] + 1 > lis[i])) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        System.out.println("Lis array : " + Arrays.toString(lis));
        int max = 1;
        for (int i=0; i<lis.length; i++) {
            if (max < lis[i]) {
                max = lis[i];
            }
        }
        System.out.println("The longest increasing subsequence : " + max);
    }
}
