import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class boolean_search {
    private static ArrayList<ArrayList<Integer>> list_of_lists = new ArrayList<ArrayList<Integer>>();
    private static ArrayList<Integer> time_identity = new ArrayList<Integer>();
    private static ArrayList<Long> time_value = new ArrayList<Long>();
    private static ArrayList<Integer> search_for_values = new ArrayList<Integer>();

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        // Get user input for list dimensions
        println("How many numbers per list?");
        int num_per_list = scan.nextInt();
        println("How many lists?");
        int num_of_lists = scan.nextInt();

        println("\n\n\n");

        // Generate Random number to search for in the list
        search_for_values = generate_search_data(num_of_lists);

        // Generate Lists with random seed each pass
        for (int i = 0; i < num_of_lists; i++) {
            ArrayList<Integer> pass = generateData(num_per_list, rand.nextInt(9999999));
            data_in_list(pass);
        }

        // Run brute search algorithm (#0) on Data and time it
        for (int i = 0; i < num_of_lists; i++) {
            ArrayList<Integer> testList = list_of_lists.get(i);
            long start_time = 0;
            long end_time = 0;
            int search_value = search_for_values.get(i);

            start_time = System.nanoTime();
            search_brute(testList, search_value);
            end_time = System.nanoTime();

            data_process_time(0, end_time - start_time);
        }

        println(">>>> RESULTS <<<<\n");
        print("Average Brute Search Time: ");
        print(data_process_average_time(time_identity, time_value, 0) * 1e-6);
        println(" ms");

        // Run short search algorithm (#1) on Data and time it
        for (int i = 0; i < num_of_lists; i++) {
            ArrayList<Integer> testList = list_of_lists.get(i);
            long start_time = 0;
            long end_time = 0;
            int search_value = search_for_values.get(i);

            start_time = System.nanoTime();
            search_short_stop(testList, search_value);
            end_time = System.nanoTime();

            data_process_time(1, end_time - start_time);
        }
        print("Average Short Search Time: ");
        print(data_process_average_time(time_identity, time_value, 1) * 1e-6);
        println(" ms");

        println("");
        print("Worst Time in set Brute: ");
        print(data_process_long_worstTime(data_get_times_by_id(time_identity, time_value, 0)) * 1e-6);
        println(" ms");
        print("Worst Time in set Short: ");
        print(data_process_long_worstTime(data_get_times_by_id(time_identity, time_value, 1)) * 1e-6);
        println(" ms");

        /*
         * println(""); println(">>>> Clearing Values <<<<\n");
         * println(">>>> WORST CASE RESULTS <<<<\n");
         * 
         * // Clears time table data time_identity.clear(); time_value.clear();
         * 
         * // Worst Case for Brute Force Search for (int i = 0; i < num_of_lists; i++) {
         * ArrayList<Integer> testList = list_of_lists.get(i); long start_time = 0; long
         * end_time = 0;
         * 
         * start_time = System.nanoTime(); // Note: -1 will never occur in the list,
         * generated values >= 0 search_brute(testList, -1); end_time =
         * System.nanoTime();
         * 
         * data_process_time(0, end_time - start_time); }
         * print("Brute Search Worst Case Time Average: ");
         * print(data_process_average_time(time_identity, time_value, 0) * 1e-6);
         * println(" ms");
         * 
         * // Worst Case for Short Search for (int i = 0; i < num_of_lists; i++) {
         * ArrayList<Integer> testList = list_of_lists.get(i); long start_time = 0; long
         * end_time = 0;
         * 
         * start_time = System.nanoTime(); // Note: -1 will never occur in the list,
         * generated values >= 0 search_short_stop(testList, -1); end_time =
         * System.nanoTime();
         * 
         * data_process_time(1, end_time - start_time); }
         * print("Short Search Worst Case Time Average: ");
         * print(data_process_average_time(time_identity, time_value, 1) * 1e-6);
         * println(" ms");
         * 
         * println(""); print("Worst Time in set Worst Case Brute: ");
         * print(data_process_long_worstTime(data_get_times_by_id(time_identity,
         * time_value, 0)) * 1e-6); println(" ms");
         * print("Worst Time in set Worst case Short: ");
         * print(data_process_long_worstTime(data_get_times_by_id(time_identity,
         * time_value, 1)) * 1e-6); println(" ms"); println("");
         */

        // Close Scanner Object
        scan.close();
    }

    public static Long data_process_average_time(ArrayList<Integer> ids_list, ArrayList<Long> times_list, int id_in) {
        Long total = 0L;
        int num_of_elements = 0;
        for (int i = 0; i < ids_list.size(); i++) {
            if (ids_list.get(i) == id_in) {
                total += times_list.get(i);
                num_of_elements++;
            }
        }
        return total / num_of_elements;
    }

    public static void data_process_time(int id, long time) {
        // id = identity of algorithim used
        time_identity.add(id);
        time_value.add(time);
    }

    public static Long data_process_long_worstTime(ArrayList<Long> a) {
        Long result = 0L;
        for (Long element : a) {
            if (element > result) {
                result = element;
            }
        }
        return result;
    }

    public static ArrayList<Long> data_get_times_by_id(ArrayList<Integer> ids_in, ArrayList<Long> time_in, int id) {
        ArrayList<Long> result = new ArrayList<Long>();
        for (int i = 0; i < ids_in.size(); i++) {
            if (ids_in.get(i) == id) {
                result.add(time_in.get(i));
            }
        }
        return result;
    }

    public static void data_in_list(ArrayList<Integer> listIn) {
        list_of_lists.add(listIn);
    }

    public static ArrayList<Integer> generate_search_data(int size) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Random rando = new Random();
        for (int i = 0; i < size; i++) {
            result.add(rando.nextInt(100000));
        }
        return result;
    }

    public static ArrayList<Integer> generateData(int size, int seed) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Random rando = new Random(seed);

        for (int i = 0; i < size; i++) {
            result.add(rando.nextInt(100000));
        }
        return result;
    }

    private static <T> boolean search_brute(ArrayList<T> a, T target) {
        boolean found = false;
        for (T element : a) {
            if (element.equals(target)) {
                found = true;
            }
        }
        return found;
    }

    private static <T> boolean search_short_stop(ArrayList<T> a, T target) {
        for (T element : a) {
            if (element.equals(target)) {
                return true;
            }
        }
        return false;
    }

    public static <T> void println(T text) {
        System.out.println(text);
    }

    public static <T> void print(T text) {
        System.out.print(text);
    }
}