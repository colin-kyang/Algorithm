package Shuffle;

import java.util.Collections;
import java.util.Random;

public class KnuthShuffle {

    /**
     * Kunth_Shuffle
     * @param a：打乱顺序的数组
     */
    public static void Kunth(int a[])
    {
        Random random=new Random();
        for(int i=0;i<a.length;i++)
        {
            //产生[i,a.length-1]范围内的数
            int m=random.nextInt(a.length-i)+i;
            //swap
            int temp=a[i];
            a[i]=a[m];
            a[m]=temp;
        }
    }


    public static void main(String[] args) {
        int [] a={2,4,9,10,1};
        /**
         * Collections有进行洗牌算法shuffle,不过针对List
         */
//        Collections.shuffle();
       Kunth(a);
        for (int element:a
             ) {
            System.out.println(element);
        }

    }
}
