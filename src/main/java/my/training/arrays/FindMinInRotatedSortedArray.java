package my.training.arrays;

public class FindMinInRotatedSortedArray {

    private static int findMin(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;

        if (arr.length == 1)
            return arr[0];

        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (mid > 0 && arr[mid] < arr[mid - 1])
                return arr[mid];

            if (arr[low] <= arr[mid] && arr[mid] > arr[high])
                low = mid + 1;
            else
                high = mid - 1;
        }
        return arr[low];
    }

    private static int findPivot(int[] num, int low, int high) {
        if (low > high)
            return -1;

        if (low == high)
            return low;

        int mid = (low + high) / 2;
        if (mid < high && num[mid] > num[mid+1]) {
            return mid;
        } else if (mid > low && num[mid] < num[mid-1]) {
            return mid-1;
        }

        if (num[low] > num[mid]) {
            return findPivot(num, low, mid-1);
        } else {
            return findPivot(num, mid+1, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 6, 7, 8, 9, 10, -92, -90, 1, 2, 4};
        final int pivot = findPivot(arr, 0, arr.length - 1);
        System.out.println(pivot);
        if (pivot == -1) {
            System.out.println(arr[0]);
        } else {
            System.out.println(arr[pivot+1]);
        }
        //System.out.println(findMin(arr));
    }
}
