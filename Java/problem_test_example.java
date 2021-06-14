public class problem_test_example {
    public static void main(String args[]) {
        Integer smaller = 1;
        Integer bigger = 99;
        print(smaller.compareTo(bigger) + "\n"); // Returns -1
        print(bigger.compareTo(smaller) + "\n"); // Returns 1
    }

    public static <T> void print(T text) {
        System.out.print(text);
    }
}
