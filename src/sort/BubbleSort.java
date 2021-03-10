package sort;

import utils.Print;
import utils.SwapArray;

/**
 * @description: 冒泡排序
 * @date: 2021/3/10 22:50
 * @author: zongxiong.lin
 * @version: 1.0
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] a = {3, 5, 2, 8, 1};


        for (int i = a.length - 1; i >0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[i]) {
                    SwapArray.swapArray(a,i,j);
                }
            }
        }

        Print.printArray(a);


    }


}
