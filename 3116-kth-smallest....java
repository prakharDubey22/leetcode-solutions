class Solution {

    // Euclidean Algorithm to find GCD
    long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    // LCM = (a / GCD(a,b)) * b
    // Divide first to reduce the chance of overflow
    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    /*
     * Count how many positive integers <= x
     * are divisible by at least one coin.
     *
     * We use Inclusion-Exclusion:
     * Odd number of selected coins  -> add
     * Even number of selected coins -> subtract
     *
     * Bitmask is used to generate every subset of coins.
     */
    long count(long x, int[] coins) {

        long ans = 0;
        int n = coins.length;

        // Generate all non-empty subsets of coins
        for (int mask = 1; mask < (1 << n); mask++) {

            long multiple = 1;
            int bits = 0;

            for (int i = 0; i < n; i++) {

                // Check if coin[i] is included in this subset
                if ((mask & (1 << i)) != 0) {

                    bits++;

                    // LCM gives numbers divisible by
                    // all selected coins
                    multiple = lcm(multiple, coins[i]);

                    // No number <= x can be divisible by
                    // this LCM, so we can stop processing this subset
                    if (multiple > x) {
                        break;
                    }
                }
            }

            if (multiple > x) {
                continue;
            }

            // Number of multiples of 'multiple' <= x
            long value = x / multiple;

            // Inclusion-Exclusion:
            // 1 coin selected  -> add
            // 2 coins selected -> subtract
            // 3 coins selected -> add
            // ...
            if (bits % 2 == 1) {
                ans += value;
            } else {
                ans -= value;
            }
        }

        return ans;
    }

    public long findKthSmallest(int[] coins, int k) {

        // Binary Search on the answer.
        // We need the smallest x such that
        // count(x) >= k.
        long low = 1;

        // Initially, coin * k is an upper bound.
        long high = (long) coins[0] * k;

        // Use the smallest coin to get a tighter upper bound
        for (int coin : coins) {
            high = Math.min(high, (long) coin * k);
        }

        while (low < high) {

            long mid = low + (high - low) / 2;

            // If there are already at least k valid numbers
            // up to mid, the answer can be mid or smaller.
            if (count(mid, coins) >= k) {
                high = mid;
            } else {
                // Not enough valid numbers, so answer is larger.
                low = mid + 1;
            }
        }

        // low == high = smallest number having
        // at least k valid numbers <= it
        return low;
    }
}



//for understand this problem
1. Binary Search
   → Find the smallest x where count(x) >= k

2. count(x)
   → How many valid numbers are <= x?

3. Inclusion-Exclusion
   → Count multiples of every subset using LCM
   → Odd subset size: +
   → Even subset size: -

4. Bitmask
   → Generate every subset of coins

  //overall flow
  Binary Search on answer
        ↓
      count(x)
        ↓
Generate all coin subsets using Bitmask
        ↓
      Find LCM
        ↓
Count multiples using x / LCM
        ↓
Inclusion-Exclusion
        ↓
count(x) >= k ?
        ↓
Yes → search left
No  → search right
