package utils;

import java.util.Arrays;

/**
 * @description: Print
 * @date: 2021/3/10 23:01
 * @author: zongxiong.lin
 * @version: 1.0
 */
public class Print {

    public static void printArray(int[] a) {
//        Arrays.stream(a).forEach(System.out::print);
        System.out.println(Arrays.toString(a));
    }

}
