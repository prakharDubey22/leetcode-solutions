class Solution {

    int sum = 0;

    public TreeNode convertBST(TreeNode root) {

        if (root == null) {
            return null;
        }

        // Go to greater values first
        convertBST(root.right);

        // Add current value to sum
        sum += root.val;
        root.val = sum;

        // Then process smaller values
        convertBST(root.left);

        return root;
    }
}
