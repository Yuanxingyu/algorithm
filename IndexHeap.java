package org.algorithm;

/**
 * @author stary
 * @date 2018/8/25 下午3:03
 */
//最大索引堆:最重要的性质是可以通过索引改变数组元素的值。
public class IndexHeap {
    private int[] data;
    private int[] indexs;
    private int[] reverse;
    private int count;

    public IndexHeap(int capacity) {
        data = new int[capacity];
        indexs = new int[capacity];
        reverse = new int[capacity];
        count = 0;
    }

    public IndexHeap(int[] arr, int capacity) {
        data = new int[capacity];
        indexs = new int[capacity];
        reverse = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            data[i] = arr[i];
            indexs[i] = i;
            reverse[indexs[i]]=i;
        }
        count = capacity;
        for (int i = count / 2 - 1; i >= 0; i--)
            shiftDown(i);
    }

    private void shiftUp(int k) {
        while (k > 0 && data[indexs[k]] > data[indexs[(k - 1) / 2]]) {
            SortHelper.swap(indexs, k, (k - 1) / 2);
            //SortHelper.swap(reverse, indexs[k],indexs[(k-1)/2]);
            reverse[indexs[k]]=k;
            reverse[indexs[(k-1)/2]]=(k-1)/2;
            k = (k - 1) / 2;
        }
    }

    private void shiftDown(int k) {
        int j;
        while (2 * k + 1 < count) {
            j = 2 * k + 1;
            if (j + 1 < count && data[indexs[j + 1]] > data[indexs[j]])
                j = j + 1;
            if (data[indexs[k]] > data[indexs[j]])
                break;
            SortHelper.swap(indexs, k, j);
            //SortHelper.swap(reverse,indexs[k],indexs[j]);
            reverse[indexs[k]]=k;
            reverse[indexs[j]]=j;
            k = j;
        }
    }

    public void insert(int value) {
        data[count] = value;
        indexs[count] = count;
        reverse[indexs[count]]=count;
        count++;
        shiftUp(count - 1);
    }

    public int maxExit() {
        int max = data[indexs[0]];
        indexs[0] = indexs[count - 1];//swap(indexs,0,count-1);
        //reverse[indexs[0]]=reverse[indexs[count-1]];
        reverse[indexs[0]]=0;
        count--;
        shiftDown(0);
        return max;
    }

    public void change(int index, int value) {
        data[index] = value;
        int k = reverse[index];//与reverse[indexs[index]]结果相同？
        shiftUp(k);
        shiftDown(k);
    }
}
