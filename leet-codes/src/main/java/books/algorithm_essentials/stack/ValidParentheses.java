package books.algorithm_essentials.stack;

import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {
        final String left = "([{";
        final String right = ")]}";

        Stack<Character> stk = new Stack<>();
        for(int i =0; i < s.length(); ++i){
            final char c = s.charAt(i);
            if(left.indexOf(c) != -1){
                stk.push(c);
            } else if (!stk.isEmpty() && stk.peek() == left.charAt(right.indexOf(c))) {
                stk.pop();
            } else {
                return false;
            }
        }

        return stk.empty();
    }
}
