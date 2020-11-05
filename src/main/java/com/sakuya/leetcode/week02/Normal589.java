package com.sakuya.leetcode.week02;

import com.sakuya.leetcode.util.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/description/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal589 {

    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<Integer>();
        preorder(root, ans);
        return ans;
    }

    /**
     * 普通的递归
     */
    public void preorder(Node node, List<Integer> ans) {
        if (node == null) return;
        ans.add(node.val);
        if (node.children != null) {
            for (Node child : node.children) {
                preorder(child, ans);
            }
        }
    }

    /**
     * 自己模拟一个栈
     */
    public List<Integer> _preorder(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            output.add(node.val);
            Collections.reverse(node.children);
            stack.addAll(node.children);
        }
        return output;
    }

}
