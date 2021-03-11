package books.cartoon._questions;

public class BigNumberSum {

    public static String bigNumberSum(String bigNumberA, String bigNumberB) {

        int maxLen = bigNumberA.length() > bigNumberB.length() ? bigNumberA.length() : bigNumberB.length();

        //1.逆序存储
        int[] arrayA = new int[maxLen + 1];
        int[] arrayB = new int[maxLen + 1];
        for (int i = 0; i < bigNumberA.length(); i++) {
            arrayA[i] = bigNumberA.charAt(bigNumberA.length() - 1 - i) - '0';
        }

        for (int i = 0; i < bigNumberB.length(); i++) {
            arrayB[i] = bigNumberB.charAt(bigNumberB.length() - 1 - i) - '0';
        }

        //2.倒序相加
        int[] result = new int[maxLen + 1];
        for (int i = 0; i < result.length; i++) {
            int tmp = result[i];
            tmp += arrayA[i];
            tmp += arrayB[i];
            if (tmp >= 10) {
                tmp -= 10;
                result[i + 1] = 1;
            }
            result[i] = tmp;
        }

        //3.
        StringBuilder sb = new StringBuilder();
        boolean findFirst = false;
        for (int i = result.length - 1; i >0; i--) {
            if(!findFirst){
              if(result[i] == 0){
                  continue;
              }
              findFirst = true;
            }
            sb.append(result[i]);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(bigNumberSum("426709752318", "95481253129"));
    }
}
