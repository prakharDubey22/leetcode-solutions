class Solution {

    public boolean stoneGameIX(int[] stones) {

        int[] count = new int[3];

        // Count stones according to remainder % 3
        for (int stone : stones) {
            count[stone % 3]++;
        }

        // Try Alice taking a remainder-1 stone first
        int[] option1 = {
            count[0],
            count[1],
            count[2]
        };

        // Try Alice taking a remainder-2 stone first
        int[] option2 = {
            count[0],
            count[2],
            count[1]
        };

        return check(option1) || check(option2);
    }

    private boolean check(int[] count) {

        // Alice must take a remainder-1 stone first
        if (count[1] == 0) {
            return false;
        }

        count[1]--;

        int moves = 1;

        int pairs = Math.min(count[1], count[2]);

        // Alternating 1, 2
        moves += pairs * 2;

        count[1] -= pairs;
        count[2] -= pairs;

        // One extra remainder-1 stone can be taken
        if (count[1] > 0) {
            count[1]--;
            moves++;
        }

        // Remainder-0 stones don't change the sum,
        // but they affect whose turn it is.
        moves += count[0];

        // Alice wins if:
        // 1. Number of moves is odd
        // 2. Some non-zero-remainder stone remains
        return moves % 2 == 1 &&
               (count[1] + count[2] > 0);
    }
}
