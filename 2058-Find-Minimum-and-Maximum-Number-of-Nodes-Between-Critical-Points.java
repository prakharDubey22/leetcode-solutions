class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int[] ans = {-1, -1};

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;

        int firstCritical = -1;
        int prevCritical = -1;
        int minDistance = Integer.MAX_VALUE;

        while (curr.next != null) {

            int prevVal = prev.val;
            int currVal = curr.val;
            int nextVal = curr.next.val;

            // Current node is a local maximum or minimum
            boolean isCritical =
                    (currVal > prevVal && currVal > nextVal) ||
                    (currVal < prevVal && currVal < nextVal);

            if (isCritical) {

                // Store the first critical point
                if (firstCritical == -1) {
                    firstCritical = index;
                }

                // Distance from previous critical point
                if (prevCritical != -1) {
                    minDistance = Math.min(
                            minDistance,
                            index - prevCritical
                    );
                }

                prevCritical = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        // Fewer than two critical points
        if (firstCritical == -1 || firstCritical == prevCritical) {
            return ans;
        }

        // Distance between first and last critical point
        int maxDistance = prevCritical - firstCritical;

        ans[0] = minDistance;
        ans[1] = maxDistance;

        return ans;
    }
}
