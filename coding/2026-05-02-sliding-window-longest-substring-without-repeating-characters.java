import java.util.HashMap;
import java.util.Map;

class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastSeenIndex = new HashMap<>();
        int left = 0;
        int best = 0;

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);
            if (lastSeenIndex.containsKey(current)) {
                left = Math.max(left, lastSeenIndex.get(current) + 1);
            }
            lastSeenIndex.put(current, right);
            best = Math.max(best, right - left + 1);
        }

        return best;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters solver = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(solver.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solver.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solver.lengthOfLongestSubstring("pwwkew"));
    }
}
