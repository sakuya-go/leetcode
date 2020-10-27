package com.sakuya.leetcode.normal;

import java.util.*;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *  
 * <p>
 * 说明：
 * <p>
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶：
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal350 {

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int value : nums1) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }
        for (int value : nums2) {
            if (map.getOrDefault(value, 0) > 0) {
                list.add(value);
                map.put(value, map.get(value) - 1);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public int[] _intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0, p2 = 0;
        int index = 0;
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] == nums2[p2]) {
                result[index++] = nums1[p1];
                p1++;
                p2++;
            } else if (nums1[p1] < nums2[p2]) {
                p1++;
            } else {
                p2++;
            }
        }
        return Arrays.copyOfRange(result, 0, index);
    }
}
