package books.cartoon._questions;

/**
 * 状态转移方程
 * w 工人个数
 * n 金矿数量
 * g 金矿含量
 * p 金矿开采所需人数
 * 当 n=0 或 w=0
 * F(n,w) = 0
 * 当 n>=1, w<p[n-1]
 * F(n,w) = F(n-1,w)
 * 当 n>=1, w>=p[n-1]
 * F(n,w) = max(F(n-1,w), F(n-1, w - p[n-1]) + g[n-1])
 */
public class FindBestGoldMining {

    /**
     * 递归版本 O(2^n)
     * 缺点: 做了很多重复计算
     *
     * @param w
     * @param n
     * @param p
     * @param g
     * @return
     */
    public static int findBestGoldMiningV1(int w, int n, int[] p, int[] g) {
        if (w == 0 || n == 0) {
            return 0;
        }

        if (w < p[n - 1]) {
            return findBestGoldMiningV1(w, n - 1, p, g);
        } else {
            return Math.max(findBestGoldMiningV1(w, n - 1, p, g), findBestGoldMiningV1(w - p[n - 1], n - 1, p, g) + g[n - 1]);
        }
    }

    /**
     * 递推版本
     * 浪费存储空间
     *
     * @param w
     * @param n
     * @param p
     * @param g
     * @return
     */
    public static int findBestGoldMiningV2(int w, int n, int[] p, int[] g) {
        int[][] table = new int[g.length + 1][w + 1];

        for (int i = 1; i <= g.length; i++) {
            for (int j = 1; j <= w; j++) {
                if (j < p[i - 1]) {
                    table[i][j] = table[i - 1][j];
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i - 1][j - p[i - 1]] + g[i - 1]);
                }
            }
        }

        return table[g.length][w];
    }

    public static int findBestGoldMiningV3(int w, int n, int[] p, int[] g) {
        int[] result = new int[w + 1];

        for (int i = 1; i <= g.length; i++) {
            for (int j = w; j >= 1; j--) {
                if (j >= p[i - 1]) {
                    result[j] = Math.max(result[j], result[j - p[i - 1]] + g[i - 1]);
                }
            }
        }

        return result[w];
    }

    public static void main(String[] args) {
        int w = 10;
        int[] p = {5, 5, 3, 4, 3};
        int[] g = {400, 500, 200, 300, 350};
        System.out.println("最优收益V1:" + findBestGoldMiningV1(w, g.length, p, g));
        System.out.println("最优收益V2:" + findBestGoldMiningV2(w, g.length, p, g));
        System.out.println("最优收益V3:" + findBestGoldMiningV3(w, g.length, p, g));
    }
}
