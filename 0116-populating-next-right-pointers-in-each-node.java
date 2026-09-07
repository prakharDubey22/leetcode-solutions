//1. size = current level ke nodes
//2. current node nikalo
//3. current.next = queue.peek()
//4. children queue mein daalo

import java.util.*;
class Solution {
    public Node connect(Node root) {

        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                Node current = queue.poll();
              
                // Connect current node to the next node of the same level  
                if (i < size - 1) { // Agar current level ka last node nahi hai, tabhi next connection banao.
                    current.next = queue.peek(); //Current node ke right mein jo same-level node queue mein hai, usko next bana do.
                }
              
                // Add next level nodes to the queue
                if (current.left != null) { 
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }

        return root;
    }
}
