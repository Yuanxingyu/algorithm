package org.algorithm;

/**
 * @author stary
 * @date 2018/8/20 下午1:46
 */
public class SortHelper {

    //交换
    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    //打印数组
    public static void print(int[] arr) {
        for (int t : arr)
            System.out.print(t + " ");
    }

    //生成随机数组
    public static int[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert (rangeL <= rangeR);
        int[] result = new int[n];
        for (int i = 0; i < n; i++)
            result[i] = (int) (Math.random() * (rangeR - rangeL + 1) + 1);
        return result;
    }
}
