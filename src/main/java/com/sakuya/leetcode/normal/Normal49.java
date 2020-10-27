package com.sakuya.leetcode.normal;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal49 {

    /**
     * 排序哈希法
     *
     * 时间复杂度 O(NKlogK)
     * 空间复杂度 O(NK)
     * N是strs长度, K是单个字符串的最大字符数
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> res = new HashMap<>();
        // 遍历N次
        for (String str : strs) {
            char[] chars = str.toCharArray();
            //排序时间复杂度KlogK
            Arrays.sort(chars);
            //排序后生成的字符串就能作为异位词的唯一key
            String key = String.valueOf(chars);
            if (!res.containsKey(key)) res.put(key, new ArrayList<>());
            res.get(key).add(str);
        }
        return new ArrayList<>(res.values());
    }

    /**
     * 字符计数法
     *
     * 时间复杂度 O(NK)
     * 空间复杂度 O(NK)
     *
     * 虽然时间复杂度比排序哈希法低，但是时间执行时间却长了不少，常数时间复杂度的计算也可能对算法有较大影响
     */
    public List<List<String>> _groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> ans = new HashMap<>();
        int[] count = new int[26];
        // N次遍历，时间复杂度N
        for (String s : strs) {
            Arrays.fill(count, 0);
            // K次遍历，时间复杂度K
            for (char c : s.toCharArray()) count[c - 'a']++;
            // 常数时间的生成唯一key，不过好像也挺费时？
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList<>());
            ans.get(key).add(s);
        }
        return new ArrayList<>(ans.values());
    }
}
