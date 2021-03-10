package sort;

import utils.Print;
import utils.SwapArray;

/**
 * @description: 希尔排序(插入排序的改进版)
 * @date: 2021/3/11 0:15
 * @author: zongxiong.lin
 * @version: 1.0
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] a = {4, 6, 8, 7, 9, 2, 11, 1,3};

        for (int h = a.length / 2; h > 0; h /= 2) {
            //i:代表即将插入的元素角标，作为每一组比较数据的最后一个元素角标
            for (int i = h; i < a.length; i++) {
                //j:代表与i同一组的数组元素角标
                for (int j = i; j >= h; j -= h) {
                    if (a[j-h]>a[j]){
                        SwapArray.swapArray(a,j-h,j);
                    }else {
                        break;
                    }
                }
            }

        }

        Print.printArray(a);

    }

}
