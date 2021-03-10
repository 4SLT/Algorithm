package sort;

import utils.Print;
import utils.SwapArray;

/**
 * @description: 选择排序
 * @date: 2021/3/10 23:22
 * @author: zongxiong.lin
 * @version: 1.0
 */
public class SelectionSort {

    public static void main(String[] args) {

        int[] a = {4,6,8,7,9,2,11,1};

        for (int i = 0; i < a.length - 1; i++) {
            //最小元素的下标
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[minIndex] > a[j]) {
                    minIndex = j;
                }
            }
            SwapArray.swapArray(a, i, minIndex);
        }

        Print.printArray(a);
    }


}
