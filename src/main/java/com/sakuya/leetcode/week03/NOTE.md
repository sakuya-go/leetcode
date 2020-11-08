学习笔记

递归的关键就是找到重复子问题

递归代码模板

```
public void recur(int level, int param) { 
 // terminator 
 if (level > MAX_LEVEL) { 
   // process result 
   return; 
 }
 // process current logic 
 process(level, param); 
 // drill down 
 recur( level: level + 1, newParam); 
 // restore current status 
}
```

分治代码模板，比递归多了一个合并结果的操作
```
private static int divide_conquer(Problem problem, ) {
  
  if (problem == NULL) {
    int res = process_last_result();
    return res;     
  }
  subProblems = split_problem(problem)
  
  res0 = divide_conquer(subProblems[0])
  res1 = divide_conquer(subProblems[1])
  
  result = process_result(res0, res1);
  
  return result;
}
```