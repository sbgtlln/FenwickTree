import java.util.Arrays;
import java.util.Random;

public class Main {
    private static final int ARRAY_SIZE = 10000;
    private static final int RANDOM_RANGE = 1000000;
    private static final int SEARCH_SIZE = 100;
    private static final int REMOVE_SIZE = 1000;

    public static void main(String[] args) {
        int[] array = new int[ARRAY_SIZE];
        Random random = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = random.nextInt(RANDOM_RANGE);
        }

        FenwickTree tree = new FenwickTree(ARRAY_SIZE + 1);

        long[] addTimes = new long[ARRAY_SIZE];
        long[] removeTimes = new long[REMOVE_SIZE];
        long[] searchTimes = new long[SEARCH_SIZE];

        long addOperations = 0;
        long removeOperations = 0;
        long searchOperations = 0;

        // Add
        for (int i = 0; i < ARRAY_SIZE; i++) {
            long start = System.nanoTime();
            tree.add(i + 1, array[i]);
            long end = System.nanoTime();
            addTimes[i] = end - start;
            addOperations += i + 1;
        }

        // Remove
        int[] removeIndexes = new int[REMOVE_SIZE];
        for (int i = 0; i < REMOVE_SIZE; i++) {
            removeIndexes[i] = random.nextInt(ARRAY_SIZE) + 1;
        }
        Arrays.sort(removeIndexes);

        for (int i = REMOVE_SIZE - 1; i >= 0; i--) {
            long start = System.nanoTime();
            tree.remove(removeIndexes[i], array[removeIndexes[i] - 1]);
            long end = System.nanoTime();
            removeTimes[i] = end - start;
            removeOperations += removeIndexes[i];
        }

        // Search
        int[] searchIndexes = new int[SEARCH_SIZE];
        for (int i = 0; i < SEARCH_SIZE; i++) {
            searchIndexes[i] = random.nextInt(ARRAY_SIZE) + 1;
        }

        for (int i = 0; i < SEARCH_SIZE; i++) {
            long start = System.nanoTime();
            int result = tree.sum(searchIndexes[i]);
            long end = System.nanoTime();
            searchTimes[i] = end - start;
            searchOperations += searchIndexes[i];
        }

        // Calculate averages
        double addTimeAvg = Arrays.stream(addTimes).average().orElse(0.0);
        double removeTimeAvg = Arrays.stream(removeTimes).average().orElse(0.0);
        double searchTimeAvg = Arrays.stream(searchTimes).average().orElse(0.0);

        double addOpAvg = (double) addOperations / ARRAY_SIZE;
        double removeOpAvg = (double) removeOperations / REMOVE_SIZE;
        double searchOpAvg = (double) searchOperations / SEARCH_SIZE;

        System.out.println("Среднее время добавления (нс): " + addTimeAvg);
        System.out.println("Среднее время удаления (нс): " + removeTimeAvg);
        System.out.println("Среднее время поиска (нс): " + searchTimeAvg);
        System.out.println("Среднее количество операций добавления: " + addOpAvg);
        System.out.println("Среднее число операций удаления: " + removeOpAvg);
        System.out.println("Среднее число поисковых операций: " + searchOpAvg);
    }
}
