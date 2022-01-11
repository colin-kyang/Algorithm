/**
 * 排序算法模板
 */
public class SortTemplate {
    /**
     * 排序接口：对序列a进行排序操作
     * @param a
     */
    public static void sort(Comparable [] a)
    {
        //实现见 insertsort、mergesort、quicksort
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
        Comparable tmp=a[i];
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
        for(int i=0;i<a.length;i++)
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

}
