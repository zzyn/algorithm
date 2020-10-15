package com.leetcodes.string;

public class KmpAcStateMachineVersion {

    int KmpSearch(String source, String pattern)
    {
        int i = 0;
        int j = 0;
        int sLen = source.length();
        int pLen = pattern.length();
        char[] s = source.toCharArray();
        char[] p = pattern.toCharArray();
        int[] next = new int[pLen];
        GetNext(pattern, next);

        while (i < sLen && j < pLen)
        {
            //①如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++
            if (j == -1 || s[i] == p[j])
            {
                i++;
                j++;
            }
            else
            {
                //②如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]
                //next[j]即为j所对应的next值
                j = next[j];
            }
        }
        if (j == pLen)
            return i - j;
        else
            return -1;
    }

    void GetNext(String pattern,int next[])
    {
        int pLen = pattern.length();
        char[] p = pattern.toCharArray();
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < pLen - 1)
        {
            //p[k]表示前缀，p[j]表示后缀
            if (k == -1 || p[j] == p[k])
            {
                ++k;
                ++j;
                next[j] = k;
            }
            else
            {
                k = next[k];
            }
        }
    }


}
