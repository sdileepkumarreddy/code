import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            buckets.add(new ArrayList<>());
        }

        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            buckets.get(entry.getValue()).add(entry.getKey());
        }

        int[] result = new int[k];
        int index = 0;
        for (int count = buckets.size() - 1; count >= 0 && index < k; count--) {
            for (int value : buckets.get(count)) {
                result[index++] = value;
                if (index == k) {
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TopKFrequentElements solver = new TopKFrequentElements();
        printArray(solver.topKFrequent(new int[] {1, 1, 1, 2, 2, 3}, 2));
        printArray(solver.topKFrequent(new int[] {4, 4, 4, 6, 6, 7, 8, 8}, 2));
    }

    private static void printArray(int[] values) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                output.append(' ');
            }
            output.append(values[i]);
        }
        System.out.println(output);
    }
}
