import java.util.Random;

public class SelectSort extends SortTemplate{
    /**
     * 排序接口：选择排序
     * @param a
     */
    public static void sort(Comparable [] a)
    {

        for(int i=0;i<a.length;i++)
        {
            /**
             * 注意：
             * 每次默认第一元素最小，若第一个元素最小，则自我交换
             */
            int minn_index=i;
            for(int j=i;j<a.length;j++)
            {
                //若 a[j]<a[minn_index]交换两者位置
                if(less(a[j],a[minn_index]))
                {
                    minn_index=j;
                }
            }
            exchange(a,i,minn_index);
        }
    }

    /**
     * 若v<m：返回true，否则：返回true
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w)<0;
    }

    /**
     * 交换序列中的元素a[i]、a[j]
     * @param a
     * @param i
     * @param j
     */
    private static void exchange(Comparable [] a,int i, int j)
    {
        Comparable tmp=(int)a[i];
        a[i]=a[j];
        a[j]=tmp;
    }

    /**
     * 调试方法，展示序列
     * @param a
     */
    private static void show(Comparable [] a)
    {
        //在单行中打印数组
        for(int i=0;i<a.length&&a[i]!=null;i++)
        {
            System.out.print(a[i]+" ");
        }
        System.out.println(" ");
    }

    /**
     * 测试序列a是否有序
     * @param a
     * @return
     */
    public static boolean isSorted(Comparable[] a)
    {
        for(int i=1;i<a.length;i++)
        {
            if(less(a[i],a[i-1]))
            {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Comparable [] a=new Comparable[20];
        Random randn=new Random();
        for(int i=0;i<20;i++)
        {
            a[i]= randn.nextInt(2000);
        }
        show(a);
        sort(a);
        show(a);
    }



}
