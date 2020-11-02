package org.acm.string;

public class ValidNumber {

    public static int[][] table = new int[][]{
            {-1, 0, 3, 1, 2, -1},
            {-1, 8, -1, 1, 4, 5},
            {-1, -1, -1, 4, -1, -1},
            {-1, -1, -1, 1, 2, -1},
            {-1, 8, -1, 4, -1, 5},
            {-1, -1, 6, 7, -1, -1},
            {-1, -1, -1, 7, -1, -1},
            {-1, 8, -1, 7, -1, -1},
            {-1, 8, -1, -1, -1, -1}
    };

    public boolean isNumber(String s) {
        int state = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char ch = s.charAt(i);
            InputType inputType = InputType.INVALID;

            if (Character.isSpaceChar(ch)) {
                inputType = InputType.SPACE;
            } else if (ch == '+' || ch == '-') {
                inputType = InputType.SIGN;
            } else if (Character.isDigit(ch)) {
                inputType = InputType.DIGIT;
            } else if (ch == '.') {
                inputType = InputType.DOT;
            } else if (ch == 'e' || ch == 'E') {
                inputType = InputType.EXPONENT;
            }

            state = table[state][inputType.ordinal()];

            if(state == -1) return false;
        }

        return state == 1 || state == 4 || state == 7 || state == 8;
    }
}

enum InputType {
    INVALID,
    SPACE,
    SIGN,
    DIGIT,
    DOT,
    EXPONENT,
    NUMS_INPUTS
}