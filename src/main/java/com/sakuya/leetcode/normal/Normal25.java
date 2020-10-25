package com.sakuya.leetcode.normal;

import com.sakuya.leetcode.util.ListNode;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 给你这个链表：1->2->3->4->5
 * <p>
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * <p>
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 *  
 * <p>
 * 说明：
 * <p>
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) return head;
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        // temp作为每组的头节点
        ListNode temp = dummyNode;
        while (temp != null) {
            temp = reverseKNode(temp, k);
        }
        return dummyNode.next;
    }

    public ListNode reverseKNode(ListNode temp, int k) {
        ListNode pre = null;
        ListNode cur = temp.next;
        // 下一个temp节点
        ListNode nextTemp = temp.next;
        ListNode test = cur;
        // 测试这组节点是不是有k个，没有就直接返回
        int n = k;
        while (test != null) {
            test = test.next;
            n--;
        }
        if (n > 0) return null;
        // 反转k个节点
        while (k-- > 0 && cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 翻转后这组的尾节点指向下组的头节点
        temp.next.next = cur;
        // 翻转后上组的尾节点指向这组的头节点
        temp.next = pre;
        return nextTemp;
    }
}
