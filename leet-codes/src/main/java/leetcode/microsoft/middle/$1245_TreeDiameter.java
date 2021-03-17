package leetcode.microsoft.middle;

import java.util.ArrayList;
import java.util.List;

/*
Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.

The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v. Each node has labels in the set {0, 1, ..., edges.length}.


Input: edges = [[0,1],[0,2]]
Output: 2
Explanation:
A longest path of the tree is the path 1 - 0 - 2.

Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
Output: 4
Explanation:
A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.

 */
public class $1245_TreeDiameter {

    int res = 0;

    public int treeDiameter(int[][] edges) {
        List<Integer>[] map = new ArrayList[edges.length + 1];

        for (int i = 0; i < map.length; i++) {
            map[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            map[edge[0]].add(edge[1]);
            map[edge[1]].add(edge[0]);
        }

        dfs(map, 0, new boolean[edges.length + 1]);
        return res;
    }

    public int dfs(List<Integer>[] map, int index, boolean[] visited) {
        visited[index] = true;
        List<Integer> list = map[index];
        int max1 = 0;
        int max2 = 0;
        for (int next : list) {
            if (!visited[next]) {
                int num = dfs(map, next, visited);
                if (num > max1) {
                    max2 = max1;
                    max1 = num;
                } else if (num > max2) {
                    max2 = num;
                }
            }
        }
        res = Math.max(res, max1 + max2);

        return Math.max(max1, max2) + 1;

    }

    public static void main(String[] args) {

    }
}
