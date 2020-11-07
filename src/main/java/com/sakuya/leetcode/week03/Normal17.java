package com.sakuya.leetcode.week03;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal17 {

    /**
     * 递归解法
     *
     * 用StringBuilder来存字符串，在拼接的时候消耗较低，而且只需要创建一个实例
     *
     * 时间复杂度 O(3 ^ M * 4 ^ N)
     * 空间复杂度 O(3 ^ M * 4 ^ N) 需要生成3 ^ M * 4 ^ N个结果
     *
     * M是不为 7或9的数字个数
     * N是7或9的个数
     */
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return ans;
        StringBuilder sb = new StringBuilder();
        add(digits, 0, sb);
        return ans;
    }

    private final List<String> ans = new ArrayList<>();

    private final String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public void add(String digits, int index, StringBuilder sb) {
        if (index == digits.length()) {
            ans.add(sb.toString());
            return;
        }
        // 通过数组的方式找字母比hash表更好
        String chars = map[digits.charAt(index) - '2'];
        if (chars != null) {
            for (int i = 0; i < chars.length(); i++) {
                add(digits, index + 1, sb.append(chars.charAt(i)));
                // 记得复原
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
