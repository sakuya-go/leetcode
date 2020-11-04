package com.sakuya.leetcode.week01;

import com.sakuya.leetcode.util.ListNode;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal206 {

    /**
     * 我的解法
     * 把head当初pre节点，最后把head.next设置为null
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head.next = null;
        return pre;
    }

    /**
     * 官方解法 迭代
     */
    public ListNode _reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 官方解法 递归
     */
    public ListNode __reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = __reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
