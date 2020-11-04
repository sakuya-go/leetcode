package com.sakuya.leetcode.week03;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal78 {

    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        recursion(new ArrayList<>(), nums, 0);
        return ans;
    }

    /**
     * 递归
     *
     * 每层的执行逻辑是把当前下标的数放进去或者不放，然后下标+1
     *
     * 时间复杂度O(N * 2^N)
     * 空间复杂度O(N)
     */
    public void recursion(List<Integer> list, int[] nums, int index) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        recursion(list, nums, index + 1);
        list.add(nums[index]);
        recursion(list, nums, index + 1);

        //注意还原变量
        list.remove(list.size() - 1);
    }

    /**
     * 利用数学知识进行迭代
     *
     * 时间复杂度O(N * 2^N)
     * 空间复杂度O(N)
     */
    public List<List<Integer>> _subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());

        for (int num : nums) {
            int size = ans.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newOne = new ArrayList<>(ans.get(i));
                newOne.add(num);
                ans.add(newOne);
            }
        }
        return ans;
    }
}
