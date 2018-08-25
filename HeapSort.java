package org.algorithm;

/**
 * @author stary
 * @date 2018/8/23 下午6:47
 */
public class HeapSort {

    //堆排序O(nlogn)
    /**
     * 由于于排序的时间性能劣于归并与快速排序，因此在系统级别的程序中很少使用，多用于动态数据的维护
     * 堆和优先队列
     * 普通队列：先进先出，后进后出
     * 优先队列：出队顺序和入队顺序无关，和优先级有关；例如操作系统执行任务
     *      操作系统对每个任务标记优先级，对逐渐来临的任务，进行动态生成优先队列
     *
     * 二叉堆Binary heap是一棵完全二叉树：
     *      1、堆中某个节点的值总是不大于其父亲节点的值（最大堆）
     *      2、堆总是一棵完全二叉树。
     *
     * 用数组存储二叉堆
     *
     * 局限性：1、对数组元素的众多交换操作，当元素是较为复杂的数据结构时，交换这些元素本身的消耗就是巨大的
     *        2、由于元素在数组中的位置发生了改变，使得当堆建成以后很难索引到它，
     *           当我们难以索引到它的时候，我们就很难改变它。（索引堆可以解决该问题）
     *
     * 堆得应用：
     *         1、实现优先队列
     *         2、多路归并排序
     */


    //1：一般堆排序：需要将数组插入到堆中，额外开辟了len+1的空间
    public void heapSort1(int []arr,int len){
        MaxHeap maxHeap = new MaxHeap(len);
        for (int i:arr)
            maxHeap.insert(i);
        for (int i=len-1;i>=0;i--)
            arr[i]=maxHeap.maxExit();
    }
    //一般堆排序优化
    public void heapSort2(int []arr,int len){
        MaxHeap maxHeap = new MaxHeap(arr,len);
        for (int i=len-1;i>=0;i--)
            arr[i]=maxHeap.maxExit();
    }


    //原地堆排序的shiftDown操作
    private void __shiftDown(int []arr,int len,int k){
        while (2*k+1<len){
            int j=2*k+1;
            if (j+1<len && arr[j+1]>arr[j])
                j=j+1;
            if (arr[k]>arr[j])
                break;
            SortHelper.swap(arr,k,j);
            k=j;
        }
    }
    //2：原地堆排序：不需要占用额外空间
    public void heapSort(int []arr,int len){
        //heapify
        for (int i=len/2-1;i>=0;i--)
            __shiftDown(arr,len,i);

        for (int i=len-1;i>0;i--){
            SortHelper.swap(arr,i,0);
            __shiftDown(arr,i,0);
        }
    }

    //3：索引堆排序
    public void indexHeapSort(int []arr,int len){
        IndexHeap indexHeap = new IndexHeap(arr,len);
        for (int i=len-1;i>=0;i--)
            arr[i]=indexHeap.maxExit();
    }
}
