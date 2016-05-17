package com.felit.lucene;

/**
 * 样例数据：
 * 1999999999000000000
 * t2-t1: 1811ms
 * 平均计算次数: 1104362000次/秒
 */
public class Perf {
    public static void main(String args[]) {
        long t1 = System.currentTimeMillis();
        long sum = 0;
        long num = 2000000000;
        for (int i = 0; i < num; i++) {
            sum += i;
        }
        System.out.println(sum);
        long t2 = System.currentTimeMillis();
        System.out.println("t2-t1: " + (t2 - t1) + "ms");
        System.out.println("平均计算次数: " + (num / (t2 - t1) * 1000) + "次/秒");
        System.out.println(Integer.MAX_VALUE);
    }
}