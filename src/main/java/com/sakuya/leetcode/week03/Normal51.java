package com.sakuya.leetcode.week03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 *  
 *
 * 示例：
 *
 * 输入：4
 * 输出：[
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *  
 *
 * 提示：
 *
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal51 {

    private List<List<String>> ans = new ArrayList<>();
    private StringBuilder templete = new StringBuilder();

    /**
     * 优化后
     *
     * 皇后的位置用一个一维数组来存，下标是第几行，值是皇后的位置，输出的时候把数组解析成List<String>就行
     *
     * 时间复杂度O(N!)
     * 空间复杂度O(N)
     */
    public List<List<String>> solveNQueens(int n) {
        while(templete.length() < n) {
            templete.append(".");
        }
        int[] queen = new int[n];
        Arrays.fill(queen, -1);
        recursion(n, 0, queen);
        return ans;
    }

    public void recursion(int n, int layer, int[] queen) {
        if (n == layer) {
            ans.add(parse(queen));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (check(n, layer, i, queen)) {
                queen[layer] = i;
                recursion(n, layer + 1, queen);
                queen[layer] = -1;
            }
        }
    }

    /**
     * 验证这个位置是否能放皇后
     */
    public boolean check(int n, int layer, int index, int[] queen) {
        // 验证纵向
        for (int i = 0; i < layer; i++) {
            if (queen[i] == index) return false;
        }
        // 验证左上右上
        for (int i = layer - 1, j = 1; i >= 0; i--, j++) {
            int leftIndex = index - j;
            int rightIndex = index + j;
            if (leftIndex >= 0 && queen[i] == leftIndex) return false;
            if (rightIndex < n && queen[i] == rightIndex) return false;
        }
        return true;
    }

    public List<String> parse(int[] queen) {
        List<String> list = new ArrayList<>(queen.length);
        for (int index : queen) {
            templete.setCharAt(index, 'Q');
            list.add(templete.toString());
            templete.setCharAt(index, '.');
        }
        return list;
    }

    /**
     * 优化之前的解法
     *
     * 老老实实的把整个字符串数组全存下来了，处理过程全是字符操作
     */
    public List<List<String>> _solveNQueens(int n) {
        while(templete.length() < n) {
            templete.append(".");
        }
        _recursion(n, 0, new ArrayList<>());
        return ans;
    }

    public void _recursion(int n, int layer, List<String> list) {
        if (n == layer) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (_check(n, layer, i, list)) {
                templete.setCharAt(i, 'Q');
                list.add(templete.toString());
                templete.setCharAt(i, '.');
                _recursion(n, layer + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }

    public boolean _check(int n, int layer, int index, List<String> list) {
        // 验证纵向
        for (int i = 0; i < layer; i++) {
            if (list.get(i).charAt(index) == 'Q') return false;
        }
        // 验证左上右上
        for (int i = layer - 1, j = 1; i >= 0; i--, j++) {
            int leftIndex = index - j;
            int rightIndex = index + j;
            if (leftIndex >= 0 && list.get(i).charAt(leftIndex) == 'Q') return false;
            if (rightIndex < n && list.get(i).charAt(rightIndex) == 'Q') return false;
        }
        return true;
    }
}
