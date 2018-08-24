package org.algorithm;

/**
 * @author stary
 * @date 2018/8/20 下午1:46
 */
public class SortHelper {
    //生成随机数组
    public static int[] generateRandomArray(int n,int rangeL,int rangeR){
        assert (rangeL<=rangeR);
        int[] result = new int[n];
        for (int i=0;i<n;i++)
            result[i] = (int)(Math.random()*(rangeR-rangeL+1)+1);
        return result;
    }
}
