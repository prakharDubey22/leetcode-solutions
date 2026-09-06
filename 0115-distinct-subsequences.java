class Solution {
    public int numDistinct(String s, String t) {

        int m = s.length();
        int n = t.length();

        // dp[i][j] = ways to make first j chars of t
        // using first i chars of s
        int[][] dp = new int[m + 1][n + 1];

        // Empty t can always be formed by taking nothing
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                // Don't take current character of s
                dp[i][j] = dp[i - 1][j];

                // If characters match, we have two choices:
                // 1. Skip s[i-1]
                // 2. Take s[i-1] and move to j-1
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }

        return dp[m][n];
    }
}
