package books.cartoon._questions;

/**
 * 最大公约数
 * 1.0 辗转相除法(欧几里得算法)
 * 1.1 当两个数较大 a%b的性能较差
 * 2.0 更相减损术
 * 2.1 当两个数相差过大，运算次数过多
 * 3.0 综合 1 和 2
 * 3.1 当 a 和 b 均为偶数, gcd(a,b)= 2 * gcd(a/2,b/2) = 2 * gcd(a>>1,b>>1) = gcd(a>>1,b>>1)<<1
 * 3.2 当 a 为偶数, b 为奇数, gcd(a,b) = gcd(a/2,b) = gcd(a>>1,b)
 * 3.3 当 b 为偶数, a 为奇数, gcd(a,b) = gcd(a, b/2) = gcd(a, b>>1)
 * 3.4 当 a 和 b 均为奇数, gcd(a,b) = gcd(b, a-b)
 */
public class MaxNumber {

    public static int gcd(int a, int b) {
        if(a == b) {
            return a;
        }

        //当 a 和 b 均为偶数
        if((a&1) ==0 && (b&1) ==0){
            return gcd(a>>1,b>>1)<<1;
        }
        //当 a 为偶数, b 为奇数
        else if((a&1) ==0 && (b&1) !=0){
            return gcd(a>>1,b);
        }
        //当 b 为偶数, a 为奇数
        else if((a&1) !=0 && (b&1) ==0){
            return gcd(a,b>>1);
        }
        //当 a 和 b 均为奇数
        else {
            int big = a>b?a:b;
            int small = a<b?a:b;
            return gcd(big-small, small);
        }
    }

    public static void main(String[] args) {
        System.out.println(gcd(25,5));
        System.out.println(gcd(100,80));
        System.out.println(gcd(27,14));
    }
}
