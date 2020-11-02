package org.acm.string;

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        //纵向扫描
        for (int j = 0; j < strs[0].length(); ++j) {
            for (int i = 0; i < strs.length; ++i) {
                if (j == strs[i].length() || strs[i].charAt(j) != strs[0].charAt(j)) {
                    return strs[0].substring(0, j);
                }
            }
        }
        return strs[0];
    }
}
