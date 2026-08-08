class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        int[] suffix = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {

            suffix[i] = suffix[i + 1];

            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                suffix[i]++;
                j--;
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        boolean usedMismatch = false;

        while (i < n && j < m) {

            if (word1.charAt(i) == word2.charAt(j)) {

                ans[j] = i;
                j++;
                i++;

            } else {
                if (!usedMismatch &&
                    suffix[i + 1] >= m - j - 1) {

                    ans[j] = i;
                    j++;
                    i++;

                    usedMismatch = true;

                } else {
                    i++;
                }
            }
        }

        if (j == m) {
            return ans;
        }

        return new int[0];
    }
}
