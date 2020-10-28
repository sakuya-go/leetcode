package com.sakuya.leetcode.normal;

import java.util.Arrays;
import java.util.Stack;

/**
 * 有效括号字符串为空 ("")、"(" + A + ")" 或 A + B，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
 * <p>
 * 如果有效字符串 S 非空，且不存在将其拆分为 S = A+B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。
 * <p>
 * 给出一个非空有效字符串 S，考虑将其进行原语化分解，使得：S = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
 * <p>
 * 对 S 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 S 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："(()())(())"
 * 输出："()()()"
 * 解释：
 * 输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
 * 删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。
 * 示例 2：
 * <p>
 * 输入："(()())(())(()(()))"
 * 输出："()()()()(())"
 * 解释：
 * 输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
 * 删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
 * 示例 3：
 * <p>
 * 输入："()()"
 * 输出：""
 * 解释：
 * 输入字符串为 "()()"，原语化分解得到 "()" + "()"，
 * 删除每个部分中的最外层括号后得到 "" + "" = ""。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-outermost-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal1021 {

    /**
     * 记录括号的层数，当层数不为1的时候就表示是内层括号，把字符放进字符数组里
     * <p>
     * 时间复杂度O(N)
     * 空间复杂度O(N)
     */
    public String removeOuterParentheses(String S) {
        char[] chars = new char[S.length()];
        int index = 0;
        int layer = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '(') layer++;
            // 注意判断层数的顺序
            if (layer != 1) chars[index++] = c;
            if (c == ')') layer--;
        }
        return String.valueOf(Arrays.copyOfRange(chars, 0, index));
    }

    /**
     * 利用辅助栈，只存左括号，遇到右括号就消一个左括号
     * <p>
     * 时间复杂度O(N)
     * 空间复杂度O(N)，但是使用了栈，实际的消耗略比上面多一些
     */
    public String _removeOuterParentheses(String S) {
        Stack<Character> stack = new Stack<>();
        char[] chars = new char[S.length()];
        int index = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '(') {
                // 在压栈之前如果栈不为空表示是内层括号，加入结果
                if (!stack.empty()) chars[index++] = c;
                stack.push(c);
            } else {
                stack.pop();
                // 出栈之后如果栈不为空表示是内层括号，加入结果
                if (!stack.empty()) chars[index++] = c;
            }
        }
        return String.valueOf(Arrays.copyOfRange(chars, 0, index));
    }
}
