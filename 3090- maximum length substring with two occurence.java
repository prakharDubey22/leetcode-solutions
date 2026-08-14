class Solution {
    public int maximumLengthSubstring(String s) {

        int[] count = new int[26];

        int left = 0;
        int ans = 0;

        for (int right = 0; right < s.length(); right++) {

            // Add current character
            count[s.charAt(right) - 'a']++;

            // If current character occurs more than 2 times
            while (count[s.charAt(right) - 'a'] > 2) {

                count[s.charAt(left) - 'a']--;
                left++;
            }

            // Current window is valid
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
