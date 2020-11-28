package com.sakuya.leetcode.week06;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal152 {

    /**
     * 正常DP
     *
     * 因为负负得正，所以要额外存一个之前最小的一个数
     *
     * maxF(i) = max(a(i), maxF(i - 1), minF(i - 1))
     * minF(i) = min(a(i), maxF(i - 1), minF(i - 1))
     *
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int[] maxF = new int[n];
        int[] minF = new int[n];
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            maxF[i] = Math.max(num, Math.max(maxF[i - 1] * num, minF[i - 1] * num));
            minF[i] = Math.min(num, Math.min(maxF[i - 1] * num, minF[i - 1] * num));
        }
        for (int max : maxF) {
            ans = Math.max(max, ans);
        }
        return ans;
    }

    /**
     * 优化空间后
     *
     * 因为每次只需要上一次的结果，所以不用存整个数组
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    public int _maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int max = nums[0], min = max, ans = max;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            int tmpMax = max;
            max = Math.max(num, Math.max(max * num, min * num));
            min = Math.min(num, Math.min(tmpMax * num, min * num));
            ans = Math.max(max, ans);
        }
        return ans;
    }

}
