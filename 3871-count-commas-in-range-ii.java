//x = 1000 → x *= 1000 → ans += n - x + 1 -> Start 1000 → multiply by 1000 → n - x + 1 add karo.

class Solution {
    public long countCommas(long n) {

        long ans = 0;

        // Every 1000x threshold adds one more comma
        for (long x = 1000; x <= n; x *= 1000) {

            // Numbers from x to n contain this comma position
            ans += n - x + 1;
        }

        return ans;
    }
}
