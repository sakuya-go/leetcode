package com.sakuya.leetcode.week04;

import com.sakuya.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *您需要在二叉树的每一行中找到最大的值。
 *
 * 示例：
 *
 * 输入:
 *
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 *
 * 输出: [1, 3, 9]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal515 {

    /**
     * 广度优先
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                max = Math.max(node.val, max);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            ans.add(max);
        }
        return ans;
    }

    /**
     * 深度优先
     */
    public List<Integer> _largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(0, root, ans);
        return ans;
    }

    public void dfs(int level, TreeNode node, List<Integer> ans) {
        if (node == null) return;
        if (level == ans.size()) {
            ans.add(node.val);
        } else {
            ans.set(level, Math.max(ans.get(level), node.val));
        }
        dfs(level + 1, node.left, ans);
        dfs(level + 1, node.right, ans);
    }
}
