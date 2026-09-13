import java.util.*;

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {

        // Stores the final answer.
        // Each inner list represents one level of the binary tree.
        List<List<Integer>> result = new ArrayList<>();

        // If the tree is empty, return an empty list.
        if (root == null) {
            return result;
        }

        // Queue is used for BFS because it follows FIFO:
        // First In, First Out.
        Queue<TreeNode> q = new LinkedList<>();

        // Add the root node to begin the traversal.
        q.add(root);

        // Null acts as a marker to indicate the end of a level.
        q.add(null);

        // Stores the values of the current level.
        List<Integer> level = new ArrayList<>();

        // Continue until the queue becomes empty.
        while (!q.isEmpty()) {

            // Remove the front element from the queue.
            TreeNode currentNode = q.remove();

            // A null marker means the current level is complete.
            if (currentNode == null) {

                // Store the completed level in the final result.
                result.add(level);

                // Create a fresh list for the next level.
                level = new ArrayList<>();

                // If no nodes remain, the traversal is complete.
                if (q.isEmpty()) {
                    break;
                } else {
                    // Mark the end of the next level.
                    q.add(null);
                }

            } else {

                // Add the current node's value to the current level.
                level.add(currentNode.val);

                // Add the left child if it exists.
                if (currentNode.left != null) {
                    q.add(currentNode.left);
                }

                // Add the right child if it exists.
                if (currentNode.right != null) {
                    q.add(currentNode.right);
                }
            }
        }

        // Return the level-by-level traversal.
        return result;
    }
}
