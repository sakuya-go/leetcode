package com.sakuya.leetcode.week03;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *  
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal22 {

    List<String> ans = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        generateParenthesis("", n, 0, 0);
        return ans;
    }

    public void generateParenthesis(String str, int n, int left, int right) {
        if (left == n && right == n) {
            ans.add(str);
        }
        if (left < n) {
            generateParenthesis(str + "(", n, left + 1, right);
        }
        if (left > right) {
            generateParenthesis(str + ")", n, left, right + 1);
        }
    }
}
