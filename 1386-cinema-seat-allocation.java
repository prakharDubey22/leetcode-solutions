class Solution {

    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Store reserved seats as bitmask for each row
        for (int[] seat : reservedSeats) {

            int row = seat[0];
            int col = seat[1];

            int mask = map.getOrDefault(row, 0);

            mask |= (1 << col);

            map.put(row, mask);
        }

        // Initially assume every row can accommodate 2 families
        int ans = 2 * n;

        // Check only rows having reservations
        for (int mask : map.values()) {

            int families = 0;

            // Seats 2,3,4,5
            int left = (1 << 2) |
                       (1 << 3) |
                       (1 << 4) |
                       (1 << 5);

            // Seats 4,5,6,7
            int middle = (1 << 4) |
                         (1 << 5) |
                         (1 << 6) |
                         (1 << 7);

            // Seats 6,7,8,9
            int right = (1 << 6) |
                        (1 << 7) |
                        (1 << 8) |
                        (1 << 9);

            boolean canLeft = (mask & left) == 0;
            boolean canMiddle = (mask & middle) == 0;
            boolean canRight = (mask & right) == 0;

            if (canLeft && canRight) {
                families = 2;
            } else if (canLeft || canMiddle || canRight) {
                families = 1;
            }

            // This row was initially counted as 2
            // Replace its contribution with actual value
            ans -= 2;
            ans += families;
        }

        return ans;
    }
}
