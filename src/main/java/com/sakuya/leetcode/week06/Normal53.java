package com.sakuya.leetcode.week06;

public class Normal53 {

    /**
     * 优化空间后的DP
     *
     * F(i) = max(F(i - 1) + num, num)
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0], ans = max;
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max + nums[i], nums[i]);
            ans = Math.max(max, ans);
        }
        return ans;
    }
}
