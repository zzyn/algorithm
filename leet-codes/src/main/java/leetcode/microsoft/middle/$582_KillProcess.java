package leetcode.microsoft.middle;


import java.util.*;

/*
You have n processes forming a rooted tree structure.
You are given two integer arrays pid and ppid,
where pid[i] is the ID of the ith process and ppid[i] is
the ID of the ith process's parent process.

Each process has only one parent process
but may have multiple children processes.
Only one process has ppid[i] = 0,
which means this process has no parent process (the root of the tree).

When a process is killed,
all of its children processes will also be killed.

Given an integer kill representing the ID of a process you want to kill,
return a list of the IDs of the processes that will be killed.
You may return the answer in any order.
 */
public class $582_KillProcess {

    /**
     * T=O(n^n)
     * S=O(n)
     *
     * @param pid
     * @param ppid
     * @param kill
     * @return
     */
    public List<Integer> killProcessV1(List<Integer> pid, List<Integer> ppid, int kill) {

        List<Integer> res = new ArrayList<>();

        if (kill == 0) return res;
        res.add(kill);
        for (int p = 0; p < ppid.size(); p++) {
            if (kill == ppid.get(p)) {
                res.addAll(killProcessV1(pid, ppid, ppid.get(p)));
            }
        }

        return res;
    }


    /**
     * T=O(n)
     * S=O(n)
     *
     * @param pid
     * @param ppid
     * @param kill
     * @return
     */
    public List<Integer> killProcessV2(List<Integer> pid, List<Integer> ppid, int kill) {

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < ppid.size(); i++) {
            if (ppid.get(i) > 0) {
                List<Integer> l = map.getOrDefault(ppid.get(i), new ArrayList<Integer>());
                l.add(pid.get(i));
                map.put(ppid.get(i), l);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> l = new ArrayList<>();
        queue.add(kill);
        while (!queue.isEmpty()) {
            int r = queue.remove();
            l.add(r);
            if (map.containsKey(r))
                for (int id : map.get(r))
                    queue.add(id);
        }
        return l;
    }

    private List<Integer> res = new ArrayList<>();

    private int[] parent;

    //并查集算法 union-find disjoint sets
    //并查集（树）是一种将一个集合以树形结构进行组合的数据结构
    //O(n+log(n))
    //https://zhuanlan.zhihu.com/p/35314141
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        //边界
        if (pid.size() == 0 || ppid.size() == 0) {
            return res;
        }
        //分配空间存储子线程ID的值为索引的父线程ID
        this.parent = new int[100000];
        //
        for (int i = 0; i < pid.size(); i++) {
            parent[pid.get(i)] = ppid.get(i);
        }
        for (int i = 0; i < pid.size(); i++) {
            if (find(pid.get(i), kill)) {
                res.add(pid.get(i));
            }
        }
        return res;

    }

    // find 函数，
    private boolean find(int val, int kill) {
        if (val == kill) {
            return true;
        }
        while (val != parent[val]) {
            if (parent[val] == kill) {
                return true;
            }
            val = parent[val];
        }
        return false;
    }
}
