package my.training.arrays;

public class SearchInRotatedSortedArray {

    // initial solution
    private static int findIndex(int[] nums, int target) {
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == target)
                return i;
        }
        return -1;
    }

    // efficient solution - 1
    private static int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;

            double num = (nums[mid] < nums[0]) == (target < nums[0]) ?
                    nums[mid] :
                    target < nums[0] ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;

            if (num < target) {
                lo = mid + 1;
            } else if (num > target) {
                hi = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // efficient solution 2
    private static int search1(int[] nums, int key) {
        int pivot = findPivot(nums, 0, nums.length -1);

        if (pivot == -1) { // then the arr is not rotated at all
            return binarySearch(nums, 0, nums.length - 1, key);
        }

        if (nums[pivot] == key)
            return pivot;

        if (nums[0] > key) {
            return binarySearch(nums, pivot+1, nums.length -1, key);
        } else {
            return binarySearch(nums, 0, pivot-1, key);
        }
    }

    private static int findPivot(int[] arr, int low, int high) {
        if (low > high) // base case
            return -1;

        if (low == high)
            return low;

        int mid = (low + high) / 2;
        if (mid < high && arr[mid] > arr[mid+1]) {
            return mid;
        }
        if (mid > low && arr[mid] < arr[mid-1]) {
            return mid -1;
        }
        if (arr[low] >= arr[mid]) {
            return findPivot(arr, low, mid-1);
        } else {
            return findPivot(arr, mid+1, high);
        }
    }

    // plain binary search
    private static int binarySearch(int[] nums, int low, int high, int key) {
        if (low > high)
            return -1;

        int mid = (low + high) / 2;

        if (nums[mid] == key)
            return mid;
        else if (nums[mid] < key)
            return binarySearch(nums, mid+1, high, key);
        else
            return binarySearch(nums, low, mid-1, key);
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(findIndex(nums, 1));

        System.out.println(search1(nums, 1));
        System.out.println(search1(nums, 10));
        System.out.println(search1(nums, 5));

        System.out.println(binarySearch(new int[] {1, 3, 5, 6}, 0, 3, 2));
    }
}
