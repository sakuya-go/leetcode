package com.sakuya.leetcode.week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 * <p>
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 * <p>
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 * <p>
 * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 * <p>
 * 示例：
 * <p>
 * n = 15,
 * <p>
 * 返回:
 * [
 * "1",
 * "2",
 * "Fizz",
 * "4",
 * "Buzz",
 * "Fizz",
 * "7",
 * "8",
 * "Fizz",
 * "Buzz",
 * "11",
 * "Fizz",
 * "13",
 * "14",
 * "FizzBuzz"
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fizz-buzz
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Normal412 {

    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        String fb = "FizzBuzz";
        String f = "Fizz";
        String b = "Buzz";
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                if (i % 5 == 0) ans.add(fb);
                else ans.add(f);
            } else if (i % 5 == 0) {
                ans.add(b);
            } else {
                ans.add(String.valueOf(i));
            }
        }
        return ans;
    }


    public List<String> _fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        HashMap<Integer, String> map = new HashMap<>();
        map.put(3, "Fizz");
        map.put(5, "Buzz");
        for (int i = 1; i <= n; i++) {
            StringBuilder str = new StringBuilder();
            for (int key : map.keySet()) {
                if (i % key == 0) {
                    str.append(map.get(key));
                }
            }
            if (str.length() == 0) {
                str.append(i);
            }
            ans.add(str.toString());
        }
        return ans;
    }
}
