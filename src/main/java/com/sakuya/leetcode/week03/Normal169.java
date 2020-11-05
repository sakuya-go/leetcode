package com.sakuya.leetcode.week03;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal169 {

    /**
     * 利用题意，排序后的中间元素一定是多数元素
     *
     * 时间复杂度O(NlogN)
     * 空间复杂度O(1)
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 遍历数组，用hash表存下数字的出现次数，当次数大于 n / 2时返回该数字
     *
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     */
    public int _majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int needCount = nums.length / 2 + 1;
        for (int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            if (count >= needCount) return num;
            map.put(num, count);
        }
        return -1;
    }

    /**
     * 分治算法
     *
     * 时间复杂度O(NlogN)
     * 空间复杂度O(logN) 栈空间消耗
     */
    public int __majorityElement(int[] nums) {
        return recursion(nums, 0, nums.length - 1);
    }

    public int recursion(int[] nums, int low, int high) {
        if (low == high) {
            return nums[low];
        }

        int mid = (high - low ) / 2 + low;

        int lNum = recursion(nums, low, mid);
        int hNum = recursion(nums, mid + 1, high);

        if (lNum == hNum) return lNum;

        int lCount = count(nums, lNum, low, mid);
        int hCount = count(nums, hNum, mid + 1, high);
        return lCount > hCount ? lNum : hNum;
    }

    public int count(int[] nums, int num, int low, int high) {
        int count = 0;
        for (int i = low; i <= high; i++) {
            if (nums[i] == num) count++;
        }
        return count;
    }

    /**
     * 投票算法
     *
     * 时间复杂度O(N)
     * 空间复杂度O(1)
     */
    public int ___majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += candidate == num ? 1 : -1;
        }
        return candidate;
    }
}
