package books.cartoon._questions;

public class RemoveDigits {

    public static String removeKDigits(String num, int k) {
        String numNew = num;
        for (int i = 0; i < k; i++) {
            boolean hasCut = false;
            for (int j = 0; j < numNew.length() - 1; j++) {
                if (numNew.charAt(j) > numNew.charAt(j + 1)) {
                    numNew = numNew.substring(0, j) +
                            numNew.substring(j + 1, numNew.length());
                    hasCut = true;
                    break;
                }
            }

            if (!hasCut) {
                numNew = numNew.substring(0, numNew.length() - 1);
            }

            numNew = removeZero(numNew);
        }

        if (numNew.length() == 0) {
            return "0";
        }
        return numNew;
    }

    private static String removeZero(String num) {

        for (int i = 0; i < num.length() - 1; i++) {
            if (num.charAt(0) != '0') {
                break;
            }
            num = num.substring(1, num.length());
        }

        return num;
    }

    public static String removeKDigitsStack(String num, int k) {
        int newLen = num.length() - k;
        char[] stack = new char[num.length()];
        int top = 0;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            //栈顶数字大于当前字符，栈顶出站(用新字符直接覆盖老的栈顶)
            while (top > 0 && stack[top - 1] > c && k > 0) {
                top--;
                k--;
            }
            stack[top++] = c;
        }
        //找到栈中第一个非0的位置，构建新的字符串(不为0开头的字符)
        int offset = 0;
        while (offset < newLen && stack[offset] == '0'){
            offset++;
        }
        return offset == newLen ? "0" : new String(stack, offset, newLen - offset);
    }

    public static void main(String[] args) {

        System.out.println(removeKDigits("1593212", 3));
        System.out.println(removeKDigits("30200", 1));
        System.out.println(removeKDigits("10", 2));
        System.out.println(removeKDigits("541270936", 3));

        System.out.println(removeKDigitsStack("1593212", 3));
        System.out.println(removeKDigitsStack("30200", 1));
        System.out.println(removeKDigitsStack("10", 2));
        System.out.println(removeKDigitsStack("541270936", 3));

    }
}
