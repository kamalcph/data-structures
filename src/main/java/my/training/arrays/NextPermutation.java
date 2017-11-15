package my.training.arrays;

import java.util.Arrays;

public class NextPermutation {

    public void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    public void reverseSort(int[] num, int start, int end) {
        if (start > end)
            return;

        for (int i=start; i<=(end+start)/2; i++) {
            swap(num, i, start + end - i);
        }
    }

    public static void main(String[] args) {
        NextPermutation permutation = new NextPermutation();
        int[] S = {-2, 3, 5, 2, 1, 6, -10, 5, 1};
        permutation.reverseSort(S, 0, S.length-1);
        System.out.println(Arrays.toString(S));

    }
}
