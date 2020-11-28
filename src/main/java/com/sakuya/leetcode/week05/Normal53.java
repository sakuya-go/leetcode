package com.sakuya.leetcode.week05;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal53 {

    /**
     * 正常DP
     *
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int ans = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    /**
     * 优化空间后的DP
     *
     * dp数组里面其实只会用到当前和之前两个数，可以用滚动数组的思想优化空间
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    public int _maxSubArray(int[] nums) {
        int ans = nums[0], pre = 0;
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            ans = Math.max(pre, ans);
        }
        return ans;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        int left = 0, right = m * n - 1;
        int mid;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            int m0 = mid / n;
            int n0 = mid % n;
            int num = matrix[m0][n0];
            if (num == target) return true;
            if (num > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
