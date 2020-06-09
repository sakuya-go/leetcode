package com.sakuya.leetcode.interview;


import java.security.SecureRandom;
import java.util.Arrays;

/**
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 * 示例 1:
 *
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 * 提示：
 * 0 <= num < 231
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Interview46 {

    private int[] nums;
    private int count = 1;
    public int translateNum(int num) {
        int length = 1;
        int a = num;
        while((a = a / 10) != 0){
            length++;
        }
        nums = new int[length];
        a = num;
        nums[--length] = a % 10;
        while((a = a / 10) != 0){
            nums[--length] = a % 10;
        }
        next(0, "");
        return  count;
    }

    public void next(int p, String s){
        if(p == nums.length){
//            System.out.println(s);
            return;
        }
        if(p + 1 < nums.length && ((nums[p] == 1) || (nums[p] == 2 && nums[p + 1] <= 5))){
            count++;
            next(p + 2, s + parse(nums[p] * 10 + nums[p + 1]));
        }
        next(p + 1, s + parse(nums[p]));
    }

    private char parse(int i){
        return (char) (i + 'a');
    }

    /**
     * 官方答案，利用动态规划
     * 方程式 f(i)=f(i−1)+f(i−2)[i−1≥0,10≤x≤25]
     */
    public int translateNum_(int num) {
        String src = String.valueOf(num);
        int p = 0, q = 0, r = 1;
        for (int i = 0; i < src.length(); ++i) {
            p = q;
            q = r;
            r = 0;
            r += q;
            if (i == 0) {
                continue;
            }
            String pre = src.substring(i - 1, i + 1);
            if (pre.compareTo("25") <= 0 && pre.compareTo("10") >= 0) {
                r += p;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        Interview46 interview46 = new Interview46();
        long start = System.currentTimeMillis();
        int count = interview46.translateNum(12258);
        System.out.println("执行时间: =====> " + (System.currentTimeMillis() - start) / 1000 + "---" + count);
    }
}
