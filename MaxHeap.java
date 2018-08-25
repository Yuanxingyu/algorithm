package org.algorithm;

/**
 * @author stary
 * @date 2018/8/23 下午7:08
 */
public class MaxHeap {

    private int[] data;
    private int count;

    private void shiftUp(int k) {
        while (k > 0 && data[k] > data[(k - 1) / 2]) {
            SortHelper.swap(data, k, (k - 1) / 2);
            k = (k - 1) / 2;
        }
    }

    private void shiftDown(int k) {
        while (2 * k + 1 < count) {
            int j = 2 * k + 1;
            if (j + 1 < count && data[j + 1] > data[j])
                j = j + 1;
            if (data[k] > data[j])
                break;
            SortHelper.swap(data, k, j);
            k = j;
        }
    }

    public MaxHeap(int capacity) {
        data = new int[capacity];
        count = 0;
    }

    //heapify：将数组堆化
    public MaxHeap(int[] arr, int capacity) {
        data = new int[capacity];
        for (int i = 0; i < capacity; i++)
            data[i] = arr[i];
        count = capacity;
        for (int i = count / 2 - 1; i >= 0; i--)
            shiftDown(i);
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(int item) {
        data[count] = item;
        count++;
        shiftUp(count - 1);
    }

    public int maxExit() {
        int result = data[0];
        data[0] = data[count - 1];//swap(data,0,count)
        count--;
        shiftDown(0);
        return result;
    }

}
