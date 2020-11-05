package com.sakuya.leetcode.week01;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal70 {

    /**
     * 我的解法，用缓存把中间过程保存下来避免多次计算
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    int[] mem = null;

    public int climbStairs(int n) {
        if (mem == null) mem = new int[n + 1];
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (mem[n] == 0) {
            int count = climbStairs(n - 1) + climbStairs(n - 2);
            mem[n] = count;
        }
        return mem[n];
    }

    /**
     * 官方解法1
     * 动态规划，公式f(x)=f(x−1)+f(x−2)，临界条件f(1) = 1, f(2) = 2;
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public int climbStairs_1(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
