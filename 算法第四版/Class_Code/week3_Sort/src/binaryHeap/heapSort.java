package binaryHeap;

import org.w3c.dom.ls.LSInput;

import java.util.Random;

public class heapSort extends heap{

    /**
     * 无参数初始化
     */
    public heapSort()
    {
        super();
    }


    /**
     * 带参数的初始化
     * @param tmp
     */
    public heapSort(Comparable [] tmp)
    {
        super(tmp);
    }

    /**
     * Kunth_Shuffle
     * @param a：打乱顺序的数组
     */
    public static Comparable [] Kunth(Comparable a[])
    {
        Random random=new Random();
        for(int i=0;i<a.length;i++)
        {
            //产生[i,a.length-1]范围内的数
            int m=random.nextInt(a.length-i)+i;
            //swap
            Comparable temp=a[i];
            a[i]=a[m];
            a[m]=temp;
        }
        return a;
    }



    public static void main(String[] args) {
        heapSort hs=new heapSort(Kunth(new Comparable[]{2,3,7,9,10}));
        //实现了heapSort 但非in-place 排序
        //此处是对有子节点的父节点进行 sink操作，使之满足 heap[1]为 maxx
        for(int i=hs.size/2;i>=1;i--)
        {
            hs.sink(i);
        }
        while(hs.size>=1)
        {
            System.out.println(hs.delMax());
        }
    }


}
