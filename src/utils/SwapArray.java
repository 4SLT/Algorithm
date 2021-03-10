package utils;

/**
 * @description: SwapArray
 * @date: 2021/3/10 23:06
 * @author: zongxiong.lin
 * @version: 1.0
 */
public class SwapArray {

    public static void swapArray(int[] a, int index1, int index2) {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }

}
