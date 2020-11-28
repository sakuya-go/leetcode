package com.sakuya.leetcode.week06;

/**
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2：
 * <p>
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal188 {

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int[] dp = new int[k * 2 + 1];
        int index = 1;
        while (index < dp.length) {
            dp[index] = -prices[0];
            index += 2;
        }
        for (int i = 1; i < n; i++) {
            index = 0;
            while (++index < dp.length) {
                if ((index & 1) == 1) dp[index] = Math.max(dp[index], dp[index - 1] - prices[i]);
                else dp[index] = Math.max(dp[index], dp[index - 1] + prices[i]);
            }
        }
        return dp[k * 2];
    }

    public int _maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        //当k非常大时转为无限次交易
        if (k >= n / 2) {
            int dp0 = 0, dp1 = -prices[0];
            for (int i = 1; i < n; ++i) {
                int tmp = dp0;
                dp0 = Math.max(dp0, dp1 + prices[i]);
                dp1 = Math.max(dp1, dp0 - prices[i]);
            }
            return Math.max(dp0, dp1);
        }
        //定义二维数组，交易了多少次、当前的买卖状态
        int[][] dp = new int[k + 1][2];
        for (int i = 0; i <= k; ++i) {
            dp[i][0] = 0;
            dp[i][1] = -prices[0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = k; j > 0; --j) {
                //处理第k次买入
                dp[j - 1][1] = Math.max(dp[j - 1][1], dp[j - 1][0] - prices[i]);
                //处理第k次卖出
                dp[j][0] = Math.max(dp[j][0], dp[j - 1][1] + prices[i]);
            }
        }
        return dp[k][0];
    }
}
