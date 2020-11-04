package com.sakuya.leetcode.week02;

import com.sakuya.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {

    /**
     * 递归
     *
     * 最常规解法
     *
     * 时间复杂度O(N)
     * 空间复杂度O(N)
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        traversal(root, ans);
        return ans;
    }

    public void traversal(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        traversal(node.left, ans);
        ans.add(node.val);
        traversal(node.right, ans);
    }

    /**
     * 手动维护一个栈，有左子节点就压栈
     */
    public List<Integer> _inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stk = new LinkedList<>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }
}