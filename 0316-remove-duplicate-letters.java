class Solution {
    public String removeDuplicateLetters(String s) {

        int[] lastIndex = new int[26];

        for(int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        boolean[] seen = new boolean[26];
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++) {

            char curr = s.charAt(i);

            if(seen[curr - 'a']) {
                continue;
            }

            while(!stack.isEmpty()
                    && curr < stack.peek()
                    && i < lastIndex[stack.peek() - 'a']) {

                seen[stack.pop() - 'a'] = false;
            }

            stack.push(curr);
            seen[curr - 'a'] = true;
        }

        StringBuilder result = new StringBuilder();

        for(char c : stack) {
            result.append(c);
        }

        return result.toString();
    }
}
