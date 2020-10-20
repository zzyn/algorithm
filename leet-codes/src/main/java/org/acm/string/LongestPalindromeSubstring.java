package org.acm.string;

import java.util.HashMap;

/**
 * 1.备忘录
 * 2.动归
 * 3.Manacher Algorithm
 */
public class LongestPalindromeSubstring {

    public class Solution_1{
        private final HashMap<Pair, String> cache = new HashMap<>();

        public String longestPalindrome(final String s) {
            cache.clear();
            return cachedLongestPalindrome(s, 0, s.length() - 1);
        }

        String longestPalindrome(final String s, int i, int j) {
            final int length = j - i + 1;
            if(length < 2) return s.substring(i, j + 1);

            final String s1 = cachedLongestPalindrome(s, i + 1, j - 1);
            if(s1.length() == length - 2 && s.charAt(i+1) == s.charAt(j-1)) {
                return s.substring(i, j + 1);
            }
            final String s2 = cachedLongestPalindrome(s, i+i, j);
            final String s3 = cachedLongestPalindrome(s, i, j -1);
            //return max(s1,s2,s3)
            if(s1.length() > s2.length()) {
                return s1.length() > s3.length() ? s1 : s3;
            } else {
                return s2.length() > s3.length() ? s2: s3;
            }

        }

        String cachedLongestPalindrome(final String s, int i, int j) {
            final Pair key = new Pair(i, j);
            if(cache.containsKey(key)) {
                return cache.get(key);
            } else {
                final String result = longestPalindrome(s, i, j);
                cache.put(key, result);
                return result;
            }
        }
    }

    public class Solution_2 {
        public String longestPalindrome(final String s) {
            final int n = s.length();
            final boolean[][] f = new boolean[n][n];
            int maxLen = 1, start = 0; //最长回文子串的长度和起点

            for(int i = 0; i < n; i++){
                f[i][i] = true;
                for (int j = 0; j < i; j ++) {
                    f[j][i] = (s.charAt(j) == s.charAt(i) && (i -j < 2 || f[j+1][i-1]));
                    if(f[j][i] && maxLen < (i - j + 1)) {
                        maxLen = i - j + 1;
                        start = j;
                    }

                }
            }

            return  s.substring(start, start + maxLen);

        }
    }

    public class Solution_3{
        public String preProcess(final String s) {
            int n = s.length();
            if( n == 0) return "^$";
            StringBuilder ret = new StringBuilder("^");
            for(int i = 0; i < n; i++) {
                ret.append("#" + s.charAt(i));
            }

            ret.append("#$");
            return ret.toString();
        }

        String longestPalindrome(String s) {
            String T = preProcess(s);
            final int n = T.length();
            int[] P = new int[n];
            int C = 0, R = 0;

            for(int i = 1; i < n - 1; i++) {
                int iMirror = 2 * C - i;
                P[i] = (R > i) ? Math.min(R - i, P[iMirror]) : 0;
                while (T.charAt(i+1 + P[i]) == T.charAt(i - 1 - P[i])) {
                    P[i]++;
                }
                if(i + P[i] > R) {
                    C = i;
                    R = i + P[i];
                }
            }

            int maxLen = 0;
            int centrIndex = 0;
            for(int i = 1; i < n - 1; i++) {
                if(P[i] > maxLen) {
                    maxLen = P[i];
                    centrIndex = i;
                }
            }

            final int start = (centrIndex - 1 - maxLen) / 2;
            return s.substring(start, start + maxLen);
        }
    }

    public class Pair {
        private int x;
        private int y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode(){
            return x * 31 + y;
        }

        @Override
        public boolean equals(Object other) {
            if(this == other) return true;
            if(this.hashCode() != other.hashCode()) return false;
            if(!(other instanceof Pair)) return false;

            final Pair o = (Pair) other;
            return this.x == o.x && this.y == o.y;
        }

    }

}
