/**
 * 优先队列
 * 基于 二叉堆实现
 */
public class priority_queue {
    Comparable [] heap;
    int size;
    priority_queue()
    {
        heap=new Comparable[2];
        size=0;
    }

    public void resize(int N)
    {
        Comparable [] tmp =new Comparable[N];
        for(int i=0;i<size;i++)
        {
            tmp[1+i]=heap[i+1];
        }
        heap=tmp;
    }

    public void add(Comparable e)
    {
        if(size>=heap.length-1)
        {
            resize(heap.length*2);
        }
        heap[size]=e;
        swim(size);
        size++;
    }

    public void swim(int k)
    {
        while(k>=1&&(k/2)>=1&&less(heap[k/2],heap[k]))
        {
            swap(k/2,k);
            k=k/2;
        }
    }

    public boolean less(Comparable a,Comparable b)
    {
        return a.compareTo(b)<0;
    }

    public void swap(int k1,int k2)
    {
        Comparable tmp=heap[k1];
        heap[k1]=heap[k2];
        heap[k2]=tmp;
    }

    public Comparable deleteMax()
    {
        Comparable maxx=heap[1];
        swap(1,size-1);
        heap[--size]=null;
        sink(1);
        return maxx;
    }

    //sink有点麻烦啊
    public void sink(int k)
    {
      while(k>=1&&k<=(1+size))
      {
         if(k*2+1<=1+size)
         {
             int m;
             if(less(heap[k*2+1],heap[k*2]))
             {
                 m=k*2;
             }
             else
             {
                 m=k*2+1;
             }

             if(less(heap[k],heap[m]))
             {
                 swap(k,m);
                 k=m;
             }
             else
             {
                 break;
             }
         }
         else if (k*2<=1+size)
         {
             if(less(heap[k],heap[k*2]))
             {
                 swap(k,k*2);
                 k=k*2;
             }
             else
             {
                 break;
             }
         }
         else
         {
             //无子节点
             break;
         }
      }
    }

    public static void main(String[] args) {
        priority_queue queue=new priority_queue();
        queue.add(1);
        queue.add(5);
        System.out.println(queue.heap[1]);
        System.out.println(queue.heap[2]);
    }



}
