package sort;

import utils.Print;
import utils.SwapArray;

/**
 * @description: 插入排序
 * @date: 2021/3/11 0:02
 * @author: zongxiong.lin
 * @version: 1.0
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] a = {4, 6, 8, 7, 9, 2, 11, 1};

        for (int i = 1; i < a.length; i++) {
            // 前i个元素总为有序的
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    SwapArray.swapArray(a, j, j - 1);
                } else {
                    break;
                }
            }
        }

        Print.printArray(a);
    }

}
