package com.sakuya.leetcode.w3;

import com.sakuya.leetcode.util.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= 链表长度 <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Interview6 {

    /**
     * 链表翻转后再遍历链表，把值依次放进数组
     *
     * 时间复杂度O(N)
     * 空间复杂度O(N)
     */
    public int[] reversePrint(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        List<Integer> list = new ArrayList<>();
        while (pre != null) {
            list.add(pre.val);
            pre = pre.next;
        }
        int[] ans = new int[list.size()];
        int index = 0;
        for (int num : list) {
            ans[index++] = num;
        }
        return ans;
    }

    /**
     * 使用栈的后进先出功能
     */
    public int[] _reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int[] ans = new int[stack.size()];
        int index = 0;
        while (!stack.isEmpty()) {
            ans[index++] = stack.pop();
        }
        return ans;
    }

    /**
     * 递归
     */
    public int[] __reversePrint(ListNode head) {
        recursion(head, 0);
        return ans;
    }

    private int length;
    private int[] ans;

    public void recursion(ListNode node, int index) {
        if (node == null) {
            length = index;
            ans = new int[index];
            return;
        }
        recursion(node.next, index + 1);
        ans[length - index - 1] = node.val;
    }

    /**
     * 第一次遍历先找出长度，第二次遍历直接通过下标赋值
     */
    public int[] ___reversePrint(ListNode head) {
        int length = 0;
        ListNode tmp = head;
        while(tmp != null) {
            tmp = tmp.next;
            length++;
        }
        int[] ans = new int[length];
        while(head != null) {
            ans[--length] = head.val;
            head = head.next;
        }
        return ans;
    }
}
