package com.sakuya.leetcode.week02;

/**
 * 编写一个程序，找出第 n 个丑数。
 *
 * 丑数就是质因数只包含 2, 3, 5 的正整数。
 *
 * 示例:
 *
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:  
 *
 * 1 是丑数。
 * n 不超过1690。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ugly-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal264 {

    /**
     * 动态规划
     */
    public int nthUglyNumber(int n) {
        int[] nums = new int[1690];
        nums[0] = 1;
        int n2 = 0, n3 = 0, n5 = 0;
        for (int i = 1; i < 1690; i++) {
            // 每次选择这三个数最小的一个放到丑数数组
            int ugly = Math.min(Math.min(nums[n2] * 2, nums[n3] * 3), nums[n5] * 5);
            nums[i] = ugly;
            // 被放进去的数的因子加1
            if (ugly == nums[n2] * 2) n2++;
            if (ugly == nums[n3] * 3) n3++;
            if (ugly == nums[n5] * 5) n5++;
        }
        return nums[n - 1];
    }
}
