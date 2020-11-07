package com.sakuya.leetcode.week03;

import java.util.*;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal46 {

    private List<List<Integer>> ans = new ArrayList<>();

    /**
     * 最普通的递归解法
     */
    public List<List<Integer>> permute(int[] nums) {
        recursion(nums, new ArrayList<>(), new HashMap<>());
        return ans;
    }

    public void recursion(int[] nums, List<Integer> list, Map<Integer, Boolean> added) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int num : nums) {
            if (!added.containsKey(num)) {
                added.put(num, true);
                list.add(num);
                recursion(nums, list, added);
                added.remove(num);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 存是否已使用的map省略了，把nums分成两部分，[0, now), [now, n]
     * 左边是已经使用过的数字，右边是还没用的数字，这样只需要维护这个数组就能知道哪些数被用过了
     */
    public List<List<Integer>> _permute(int[] nums) {
        _recursion(0, nums, new ArrayList<>());
        return ans;
    }

    public void _recursion(int now, int[] nums, List<Integer> list) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = now; i < nums.length; i++) {
            list.add(nums[i]);
            swap(nums, now, i);
            _recursion(now + 1, nums, list);
            swap(nums, now, i);
            list.remove(list.size() - 1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 最优化版本，直接生成个结果数组，在结果数组里面操作
     */
    public List<List<Integer>> __permute(int[] nums) {
        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }
        __recursion(nums.length, 0, output);
        return ans;
    }

    public void __recursion(int n, int now, List<Integer> output) {
        if (n == now) {
            ans.add(new ArrayList<>(output));
            return;
        }
        for (int i = now; i < n; i++) {
            Collections.swap(output, i, now);
            __recursion(n, now + 1, output);
            Collections.swap(output, i, now);
        }
    }
}
