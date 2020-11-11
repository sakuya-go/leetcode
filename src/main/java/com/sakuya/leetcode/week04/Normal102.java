package com.sakuya.leetcode.week04;

import com.sakuya.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 *  
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal102 {

    /**
     * 广度优先
     *
     * 用队列和哨兵节点实现，开始时依次加入根节点和哨兵节点，然后像正常的广度优先遍历
     * 当取出的是哨兵节点时，表示这层已经遍历完，把结果加入数组，然后再把哨兵放入队列中
     * 取出哨兵后且队列为空时，已经全部遍历完，手动退出循环
     *
     * 时间复杂度O(N)
     * 空间复杂度O(N)
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        List<Integer> list = new ArrayList<>();
        TreeNode dummy = new TreeNode(0);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(dummy);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == dummy) {
                ans.add(list);
                list = new ArrayList<>();
                if (queue.isEmpty()) break;
                queue.add(dummy);
                continue;
            }
            list.add(node.val);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return ans;
    }

    /**
     * 更标准的广度优先
     *
     * 每次遍历之前获取节点熟练，就能知道这层有多少个节点
     *
     * 时间复杂度O(N)
     * 空间复杂度O(N)
     */
    public List<List<Integer>> _levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while(!nodes.isEmpty()) {
            int size = nodes.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.remove();
                list.add(node.val);
                if (node.left != null) nodes.add(node.left);
                if (node.right != null) nodes.add(node.right);
            }
            ans.add(list);
        }
        return ans;
    }

    /**
     * 深度优先
     *
     * 关键是记录当前是哪层
     */
    public List<List<Integer>> __levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        bsf(0, root, ans);
        return ans;
    }

    public void bsf(int level, TreeNode node, List<List<Integer>> ans) {
        if (node == null) return;
        if (level == ans.size()) {
            ans.add(new ArrayList<>());
        }
        ans.get(level).add(node.val);
        bsf(level + 1, node.left, ans);
        bsf(level + 1, node.right, ans);
    }
}
