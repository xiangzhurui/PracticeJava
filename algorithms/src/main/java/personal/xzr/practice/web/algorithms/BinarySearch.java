package personal.xzr.practice.web.algorithms;

/**
 * 二分查找
 * 
 * @author XiangZhuRui
 *
 */
public class BinarySearch {
    /*
     * 二分查找，递归
     */
    public static int binarySearch(int[] a, int key, int low, int high) {
        int middleIndex = (low + high) >>> 1;
        if (low > high || low < 0 || key < low || key > high) {
            return -1;
        } else if (key < a[middleIndex]) {
            return binarySearch(a, key, low, middleIndex - 1);
        } else if (key > a[middleIndex]) {
            return binarySearch(a, key, middleIndex + 1, high);
        } else
            return middleIndex;
    }

    // 二分查找，递归
    public static int binarySearch0(int[] a, int key, int low, int high) {
        int middleIndex = (low + high) >>> 1;
        if (low < 0 || high < 0 || low > high) {
            // 参数输入错误
            return -1;
        } else if (key < a[middleIndex]) {
            high = middleIndex - 1;
        } else if (key > a[middleIndex]) {
            low = middleIndex + 1;
        } else {
            return middleIndex;
        }
        return binarySearch(a, key, low, high);
    }

    // 非递归，二分查找
    public static int binarySerach1(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (key < a[mid]) {
                high = mid - 1;
            } else if (key > a[mid]) {
                low = mid + 1;
            } else
                return mid;
        }
        return -1;
    }
}
