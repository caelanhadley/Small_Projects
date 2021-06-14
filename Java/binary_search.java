public class binary_search {

    static int[] list = { 2, 4, 6, 8, 10, 12 };

    public static void main(String args[]) {
        System.out.println(search(list, 4));
    }

    public static int search(int[] a, int target) {
        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (target < a[middle]) {
                right = middle - 1;
            } else if (target > a[middle]) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

}