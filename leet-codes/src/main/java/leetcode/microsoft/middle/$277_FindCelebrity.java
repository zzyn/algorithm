package leetcode.microsoft.middle;

//双指针O(n)解法
//时间复杂度：O(n)
//空间复杂度：O(1)
public class $277_FindCelebrity extends Relation {

    public int findCelebrity(int n) {
        int left = 0, right = n - 1;
        // 先排除掉不是名人的候选人
        while (left != right)
            if (knows(left, right))
                left++;
            else
                right--;

        // 最后left和right会相遇，这个人是潜在的名人
        // 按照定义验证：
        // case1：候选人认识其他人，那么他不是名人
        // case2：存在至少一个人不认识候选人，那么他也不是名人
        for (int i = 0; i < n; i++)
            if (i != left && (knows(left, i) || !knows(i, left)))
                return -1;
        return left;
    }
}

class Relation {

    /**
     * which tells you whether A knows B
     * @param a
     * @param b
     * @return
     */
    boolean knows(int a, int b) {
        return true;
    }
}
