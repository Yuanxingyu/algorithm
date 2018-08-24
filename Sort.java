package org.algorithm;

/**
 * @author stary
 * @date 2018/8/20 上午11:54
 */
public class Sort {

    //交换
    public static void swap(int[] arr,int x,int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public void testTime(char index,int arr[],int len){
        int[] result = new int[len];
        long startTime = System.currentTimeMillis();
        switch (index){
            case 'A':
                bubbleSort(arr,len);
                System.out.println("test");
                break;
            case 'B':
                selectionSort(arr,len);
                break;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("使用时间："+(endTime-startTime));
    }

    //1:冒泡排序
    public void bubbleSort(int arr[],int len){
        for (int i=0;i<len-1;i++)
            for (int j=0;j<len-1-i;j++)
                if (arr[j]>arr[j+1])
                    swap(arr,j,j+1);
    }

    //2:选择排序：无需swag方法进行交换操作。O(n2)
    public void selectionSort(int arr[],int len){
        int mixIndex;
        for (int i=0;i<len-1;i++){
            mixIndex = i;
            for (int j = i+1;j<len;j++)
                if(arr[j]<arr[mixIndex])
                    mixIndex = j;
            swap(arr,i,mixIndex);
        }
    }

    //3:插入排序：相比于其他排序，其内层循环可以提前结束。O(n2)
    //  如果这组数据大致有序，插入排序较为首选。
    public void insertSort(int arr[],int len){
        for (int i=1;i<len;i++){
            for (int j=i;j>0;j--){
                //由于前j个数据已经排序完成，因此若出现前者小于或等于后者则已经排序完成，无需再向前遍历，直接break。
                if(arr[j-1]>arr[j])
                    swap(arr,j-1,j);
                else
                    break;
            }
        }
    }

    //插入排序优化：不用swag方法交换操作，一步赋值便可完成
    public void betterInsertSort(int arr[],int len){
        for (int i=1;i<len;i++){
            int e = arr[i];//保存需要排序的数
            int j;//保存e最终需要插入的位置
            for (j=i;j>0&&arr[j-1]>e;j--){
                arr[j]=arr[j-1];//将j-1位置的数向后移一位
            }
            arr[j]=e;
        }
    }

    //4:希尔排序

    /**
     * 归并排序
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    //将arr[l......mid]与arr[mid+1......r]进行归并
    private void __merge(int arr[],int l,int mid,int r){
        int aux[] = new int[r-l+1];
        for (int i=l;i<=r;i++)
            aux[i-l]=arr[i];
        int i=l,j=mid+1;
        for (int k=l;k<=r;k++){
            if (i>mid){
                arr[k]=aux[j-l];
                j++;
            }else if (j>r){
                arr[k]=aux[i-l];
                i++;
            }else if (aux[i-l]<aux[j-l]){
                arr[k]=aux[i-l];
                i++;
            }else{
                arr[k]=aux[j-l];
                j++;
            }
        }
    }
    //递归使用归并排序，对arr[l......r](前闭后闭)的范围进行排序
    private void __mergeSort(int arr[],int l,int r){
        //先写出递归结束条件
        if (l>=r)
            return;

        //当数据小时使用插入排序优化归并排序，值未必是15，需要尝试
//        if (r-l<=15){
//            betterInsertSort(arr,r-l+1);
//            return;
//        }
        int mid = (l+r)/2;
        __mergeSort(arr,l,mid);
        __mergeSort(arr,mid+1,r);
        if (arr[mid]>arr[mid+1])//归并排序优化，在近乎有序的数组
            __merge(arr,l,mid,r);
    }
    //5:归并排序O(nlogn)
    public void mergeSort(int arr[],int len){
        __mergeSort(arr,0,len-1);
    }

    //自底向上的归并排序法：比递归稍慢、但未使用数组下标，因此可对链表进行排序
    public void mergeSortBU(int arr[],int len){
        for (int size=1;size<=len;size+=size)
            for (int i=0;i+size<len;i+=size+size)
                //对arr[i...i+size-1]与arr[i+size...i+size+size-1]归并
                __merge(arr,i,i+size-1,Math.min(i+size+size-1,len-1));
    }

    //快速排序
    //对arr[l...r]部分进行partition操作
    //返回p，使得arr[l...p-1]<arr[p]、arr[p+1...r]>arr[p]
    public int __partition(int arr[],int l,int r){
        //优化：在数组中随机选择一个元素交换到起始位置作为partition初始值，以防近乎有序的数组
        swap(arr,l,(int)(Math.random()*(r-l+1)+l));
        int v=arr[l];
        /*下列内容为快速排序的一般写法
        int j=l;
        //初始化i为l+1，终止条件为i=r
        for (int i=l+1;i<=r;i++)
            //若arr[i]>v，则跳过对arr[i+1]进行判断
            if (arr[i]<v)
                swap(arr,++j,i);
        swap(arr,l,j);
        return j;
        */
        //快速排序优化，设定两个标定点，处理数组中有大量重复数值的情况
        int i=l+1,j=r;
        while (true){
            while (i<=r&&arr[i]<v) i++;
            while (j>=l+1&&arr[j]>v) j--;
            if (i>j)break;
            swap(arr,i,j);
            i++;
            j--;
        }
        swap(arr,l,j);
        return j;
    }

    public void __quickSort(int arr[],int l,int r){
        if(l>=r)
            return;
//        if (r-l<=15){
//            betterInsertSort(arr,r-l+1);
//            return;
//        }
        int p=__partition(arr,l,r);
        __quickSort(arr,l,p-1);
        __quickSort(arr,p+1,r);
    }

    //6:快速排序O(nlogn):partition、最坏的情况为O(n2)
    /**
     * 时间性能在大多情况下基本优于归并排序
     * 在选择最左边的值作为起始值时，快速排序在完全有序的数组情况下，时间复杂度将成为O(n2)
     * @param arr
     * @param len
     */
    public void quickSort(int[] arr, int len){
        __quickSort(arr,0,len-1);
    }

    //三路快排
    public void __quickSort3Ways(int arr[],int l,int r){
        if (l>=r)
            return;
        //partition
        swap(arr,l,(int)(Math.random()*(r-l+1)+l));
        int v = arr[l];//设定初始值
        int lt = l;//arr[l+1...lt]<v
        int gt = r+1;//arr[gt...r]>v
        int i=l+1;//arr[lt+1...i]=v
        while (i<gt){
            if (arr[i]<v){
                swap(arr,lt+1,i);
                lt++;
                i++;
            }else if (arr[i]>v){
                swap(arr,i,gt-1);
                gt--;
            }else
                i++;
        }
        swap(arr,l,lt);

        __quickSort3Ways(arr,l,lt-1);
        __quickSort3Ways(arr,gt,r);
    }

    //7:三路快速排序:在有大量重复数据时比快速排序性能要好很多，其余情况稍逊于快速排序，在大型系统中常用
    public void quickSort3Ways(int arr[],int len){
        __quickSort3Ways(arr,0,len-1);
    }

    /**
     * mergeSort 与 QuickSort
     * 1、都使用了分治算法
     * 2、使用归并求逆序对
     * 3、使用快排取数组中第n大的元素O(n)
     */

}
