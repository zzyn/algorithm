package books.cartoon._questions;

/**
 * 是否是2的次幂
 */
public class NumberTwo {

    public static boolean isPowerOfTwo(int num) {
        return (num & (num - 1)) == 0;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(2));
        System.out.println(isPowerOfTwo(3));
        System.out.println(isPowerOfTwo(9));
        System.out.println(isPowerOfTwo(16));
    }
}
