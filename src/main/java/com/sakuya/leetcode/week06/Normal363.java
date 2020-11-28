package com.sakuya.leetcode.week06;

/**
 * 给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。
 *
 * 示例:
 *
 * 输入: matrix = [[1,0,1],
 *                [0,-2,3]], k = 2
 * 输出: 2
 * 解释: 矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * 说明：
 *
 * 矩阵内的矩形区域面积必须大于 0。
 * 如果行数远大于列数，你将如何解答呢？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal363 {

    /**
     * 暴力解法 + DP 超时
     * 时间复杂度 O(N ^ 2 * M ^ 2)
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length, max = Integer.MIN_VALUE;
        int[][][][] dp = new int[rows + 1][cols + 1][rows + 1][cols + 1]; // from (i1,j1) to (i2,j2)
        for (int i1 = 1; i1 <= rows; i1++) {
            for (int j1 = 1; j1 <= cols; j1++) {
                dp[i1][j1][i1][j1] = matrix[i1 - 1][j1 - 1];
                for (int i2 = i1; i2 <= rows; i2++) {
                    for (int j2 = j1; j2 <= cols; j2++) {
                        dp[i1][j1][i2][j2] = dp[i1][j1][i2 - 1][j2] + dp[i1][j1][i2][j2 - 1] - dp[i1][j1][i2 - 1][j2 - 1] + matrix[i2 - 1][j2 - 1];
                        if (dp[i1][j1][i2][j2] <= k && dp[i1][j1][i2][j2] > max) max = dp[i1][j1][i2][j2];
                    }
                }
            }
        }
        return max;
    }

    /**
     * 固定两列,先算出两列之间的每行之合,然后根据行合的数组来找出答案
     *
     * 时间复杂度O(N ^ 2 * M * 2)
     * 空间复杂度O(N)
     */
    public int _maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length, ans = Integer.MIN_VALUE;
        for (int left = 0; left < cols; left ++) {
            int[] rowSum = new int[rows];
            for (int right = left; right < cols; right++) {
                for (int i = 0; i < rows; i++) {
                    rowSum[i] += matrix[i][right];
                }
                ans = Math.max(ans,  dpMax(rowSum, k));
                if (ans == k) return ans;
            }
        }
        return ans;
    }

    /**
     * 先求出最大子序列合，如果满足要求可以把时间复杂度降低到 O(N), 不行再暴力求解
     */
    private int dpMax(int[] arr, int k) {
        int rollSum = arr[0], rollMax = rollSum;
        // O(N)
        for (int i = 1; i < arr.length; i++) {
            if (rollSum > 0) rollSum += arr[i];
            else rollSum = arr[i];
            if (rollSum > rollMax) rollMax = rollSum;
        }
        if (rollMax <= k) return rollMax;
        // O(N ^ 2)
        int max = Integer.MIN_VALUE;
        for (int l = 0; l < arr.length; l++) {
            int sum = 0;
            for (int r = l; r < arr.length; r++) {
                sum += arr[r];
                if (sum > max && sum <= k) max = sum;
                if (max == k) return k; // 尽量提前
            }
        }
        return max;
    }
}
