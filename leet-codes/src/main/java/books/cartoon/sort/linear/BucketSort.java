package books.cartoon.sort.linear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class BucketSort {

    public static double[] bucketSort(double[] array){
        //1.得到最大值max和最小值min以及差值d
        double max = array[0];
        double min = array[0];
        for(int i =0; i < array.length; i++){
            if(array[i] > max) {
                max = array[i];
            }
            if(array[i] < min) {
                min = array[i];
            }
        }

        double d = max - min;

        //2.初始化bucket
        int bucketNum = array.length;
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<>(bucketNum);
        for(int i =0; i < bucketNum; i++){
            bucketList.add(new LinkedList<>());
        }

        //3.遍历原始数组，将每个元素放入bucket中
        for(int i = 0; i < array.length; i++){
            int bucketIndex = (int) ((array[i] - min) * (bucketNum - 1) / d);
            bucketList.get(bucketIndex).add(array[i]);
        }

        //4.对每个bucket内部进行排序
        for(int i =0; i< bucketNum; i++){
            Collections.sort(bucketList.get(i));
        }

        //5.输出
        double[] sortedArray = new double[array.length];
        int index = 0;
        for(LinkedList<Double> list: bucketList){
            for(double v: list){
                sortedArray[index] = v;
                index++;
            }
        }
        return sortedArray;
    }

    public static void main(String[] args){

        double[] array = new double[] {4.12,6.421,0.0023,3.0,2.123,8.122,4.12,10.09};
        double[] sorted = bucketSort(array);
        System.out.println(Arrays.toString(sorted));

    }
}
