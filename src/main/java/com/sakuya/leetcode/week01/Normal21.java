package com.sakuya.leetcode.week01;

import com.sakuya.leetcode.util.ListNode;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal21 {

    /**
     * 迭代解法
     * <p>
     * 时间复杂度O(n + m)
     * 空间复杂度O(1)
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        ListNode temp = dummyNode;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        temp.next = l1 == null ? l2 : l1;
        return dummyNode.next;
    }

    /**
     * 递归解法
     * <p>
     * 递归需要消耗栈空间，函数最多调用n + m次，所以空间复杂度为O(n + m)
     * <p>
     * 时间复杂度O(n + m)
     * 空间复杂度O(n + m)
     */
    public ListNode mergeTwoLists_(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists_(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists_(l1, l2.next);
            return l2;
        }
    }

}
