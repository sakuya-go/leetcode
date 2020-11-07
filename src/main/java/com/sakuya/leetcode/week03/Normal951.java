package com.sakuya.leetcode.week03;

import com.sakuya.leetcode.util.TreeNode;

/**
 * 我们可以为二叉树 T 定义一个翻转操作，如下所示：选择任意节点，然后交换它的左子树和右子树。
 * <p>
 * 只要经过一定次数的翻转操作后，能使 X 等于 Y，我们就称二叉树 X 翻转等价于二叉树 Y。
 * <p>
 * 编写一个判断两个二叉树是否是翻转等价的函数。这些树由根节点 root1 和 root2 给出。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * 输出：true
 * 解释：我们翻转值为 1，3 以及 5 的三个节点。
 * <p>
 *  
 * <p>
 * 提示：
 * <p>
 * 每棵树最多有 100 个节点。
 * 每棵树中的每个值都是唯一的、在 [0, 99] 范围内的整数。
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flip-equivalent-binary-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal951 {


    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        else if (root1 == null || root2 == null || root1.val != root2.val) return false;
        return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)
                || flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
    }


    public int[] plusOne(int[] digits) {
        boolean carry = false;
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]--;
            if (digits[i] != -1) return digits;
        }
        if (digits[0] != -1)
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public int[] multiple(int[] digits, int multiple) {
        if (multiple == 1) return digits;
        int carray = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] *= multiple;
            if (carray > 0) digits[i] += carray;
            if (digits[i] >= 10) {
                carray = digits[i] / 10;
                digits[i] %= 10;
            }
        }
        if (carray > 0) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = carray;
            System.arraycopy(digits, 0, newDigits , 1, digits.length);
            return newDigits;
        }
        return digits;
    }
}
