package com.sakuya.leetcode.week04;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
 *
 * 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
 *
 * 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
 *
 * 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
 *
 * 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1。
 *
 * 注意:
 *
 * 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。
 * 所有的目标基因序列必须是合法的。
 * 假定起始基因序列与目标基因序列是不一样的。
 * 示例 1:
 *
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 *
 * 返回值: 1
 * 示例 2:
 *
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 *
 * 返回值: 2
 * 示例 3:
 *
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 *
 * 返回值: 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-genetic-mutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal433 {

    /**
     * 广度优先
     *
     * 时间复杂度O(N ^ 2) ? 看起来是最多两个for循环把bank遍历完
     * 空间复杂度O(N) N是bank的大小
     */
    public int minMutation(String start, String end, String[] bank) {
        int ans = 0;
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(-1);
        while (!queue.isEmpty()) {
            //是否有变化为1且没被访问过的基因
            boolean flag = false;
            //最新这层有多少个基因
            int size = queue.size();
            //全部拿出来并找下一层的基因
            for (int i = 0; i < size; i++) {
                int index = queue.remove();
                String g = index == -1 ? start : bank[index];
                for (int j = 0; j < bank.length; j++) {
                    if (visited.contains(j)) continue;
                    String b = bank[j];
                    if (diff(g, b) == 1) {
                        flag = true;
                        visited.add(j);
                        queue.add(j);
                        // 找到结束基因，变化加1后返回
                        if (b.equals(end)) {
                            ans++;
                            return ans;
                        }
                    }
                }
            }
            // 找到的话变化度加1，没找到直接返回
            if (flag) ans++;
            else return -1;
        }
        return -1;
    }

    public int diff(String g1, String g2) {
        int diff = 0;
        for (int i = 0; i < g1.length(); i++) {
            if (g1.charAt(i) != g2.charAt(i)) diff++;
        }
        return diff;
    }
}
