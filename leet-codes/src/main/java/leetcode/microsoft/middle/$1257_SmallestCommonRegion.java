package leetcode.microsoft.middle;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given some lists of regions where the first region of each list includes all other regions in that list.

Naturally, if a region X contains another region Y then X is bigger than Y. Also by definition a region X contains itself.

Given two regions region1, region2, find out the smallest region that contains both of them.

If you are given regions r1, r2 and r3 such that r1 includes r3, it is guaranteed there is no r2 such that r2 includes r3.

It's guaranteed the smallest region exists.


Input:
regions = [["Earth","North America","South America"],
["North America","United States","Canada"],
["United States","New York","Boston"],
["Canada","Ontario","Quebec"],
["South America","Brazil"]],

region1 = "Quebec",
region2 = "New York"


Output: "North America"



 */
public class $1257_SmallestCommonRegion {

    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {

        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < regions.size(); i++) {
            List<String> rs = regions.get(i);
            for (int j = 1; j < rs.size(); j++) {
                map.put(rs.get(j), rs.get(0));
            }
        }

        // 获取两个区域的从小到大排序的父区域序列
        List<String> res1 = find(region1, map);
        List<String> res2 = find(region2, map);
        for (String s : res1) {
            //因为父节点队列是有序的，排在前面的是最小的父节点，所以遍历返回的是最小的公共区域。
            if (res2.contains(s)) {
                return s;
            }
        }
        // 如果父节点队列中找不到公共区域，那结果就是regions.get(0).get(0)
        return regions.get(0).get(0);

    }

    /**
     * 返回从小到大的父区域
     * @param region
     * @param map
     * @return
     */
    public List<String> find(String region, Map<String, String> map) {
        List<String> res = new ArrayList<>();

        String parent = region;
        while (map.get(parent) != null) {
            res.add(parent);
            parent = map.get(parent);   // 父区域的父区域
        }
        return res;
    }

    public String findSmallestRegionV2(List<List<String>> regions, String region1, String region2) {
        for (int i = regions.size() - 1; i >= 0; i--) {
            List<String> region = regions.get(i);
            if (region.contains(region1)) region1 = region.get(0);
            if (region.contains(region2)) region2 = region.get(0);
            if (region1.equals(region2)) return region1;
        }
        return "";
    }

    public static void main(String[] args) {


    }
}
