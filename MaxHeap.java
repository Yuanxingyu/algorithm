package org.algorithm;

/**
 * @author stary
 * @date 2018/8/23 下午7:08
 */
public class MaxHeap {

    private int [] data;
    private int count;

    private void shiftUp(int k){
        
    }

    public MaxHeap(int capacity){
        data = new int[capacity];
        count = 0;
    }

    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count==0;
    }

    public void insert(int item){
        data[count+1]=item;
        count++;
        shiftUp(count);
    }

    public static void main(String[] args){

        //shift up

    }

}
