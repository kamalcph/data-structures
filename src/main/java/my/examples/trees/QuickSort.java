package my.examples.trees;

import java.util.Arrays;

/**
 * Created by kamal on 7/18/17.
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] a = new int[] {3, -5, 4, 2, 10, 9, 4, 13};
        QuickSort qs = new QuickSort();
        qs.sort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    private int partition(int[] a, int p, int q) {
        int x = a[p];
        int i = p;
        for (int j = p + 1; j<=q; j++) {
            if (a[j] < x) {
                i = i + 1;
                swap(a, i, j);
            }
        }
        swap(a, i, p);
        return i;
    }

    private void sort(int[] a, int p, int q) {
        if (p < q) {
            int r = partition(a, p, q);
            sort(a, p, r - 1);
            sort(a, r + 1, q);
        }
    }

    private void swap(int[] a, int i, int j) {
        if (i == j)
            return;

        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
