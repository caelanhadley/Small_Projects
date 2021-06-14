/**
 * Provides a sum function on arrays.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-03-26
 */

// Modified by Caelan Hadley - 06/09/2021
public class ArraySum {

    /** Returns the sum of values in the given array. */
    public static int sum(int[] a, int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += a[i];
        }
        return sum;
    }

    public static void main(String args[]) {
        int[] b = { 0, 1, 2, 3, 4, 5, 6, 7, 11 };
        println(sum(b, 2, 5));

    }

    public static <T> void println(T text) {
        System.out.println(text);
    }
}
