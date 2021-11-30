package binaryHeap;

public class heap {
    private  Comparable [] heap;
    int size;

    /**
     * 构造函数
     */
    public heap()
    {
        heap=new Comparable[2];
        size=0;
    }

    public heap(Comparable[] tmp)
    {
        this.heap=new Comparable[tmp.length+1];
        for(int i=1;i<=tmp.length;i++)
        {
            this.heap[i]=tmp[i-1];
        }
        size=tmp.length;
    }

    /**
     * 可变数组：扩容
     * @param newSize
     */
    public void resize(int newSize)
    {
        Comparable [] tmp=new Comparable[newSize];
        for(int i=1;i<=size;i++)
        {
            tmp[i]=heap[i];
        }
        heap=tmp;
    }

    /**
     * 添加元素
     * @param e
     */
    public void add(Comparable e)
    {
        if(this.size+1>=this.heap.length)
        {
            resize(heap.length*2);
        }
        this.heap[++this.size]=e;
        swim(this.size);
    }

    /**
     * 添加一组序列
     * @param e
     */
    public void add(Comparable [] e)
    {
        if(this.size+1+e.length>=this.heap.length )
        {
            resize(heap.length*2+e.length);
        }
        for (int i=0;i<e.length;i++)
        {
            this.heap[++this.size]=e[i];
            swim(this.size);
        }
    }

    /**
     * 上浮：insert操作调用
     * @param k
     */
    public void swim(int k)
    {
        //root 节点不用进行swim
        while(k>1&&less(k/2,k))
        {
            exchange(k/2,k);
            k=k/2;
        }
    }

    /**
     * 元素大小比较
     * @param a
     * @param b
     * @return
     */
    public boolean less(int a,int b)
    {
        return this.heap[a].compareTo(this.heap[b])<0;
    }

    /**
     * 交换元素
     */
    public void exchange(int first,int second)
    {
        Comparable tmp=this.heap[first];
        this.heap[first]=this.heap[second];
        this.heap[second]=tmp;
    }

    /**
     * 下沉：delete 时调用
     * @param k
     */
    public void sink(int k)
    {
        while(2*k<=size)
        {
            int j=2*k;
            if(j<size&&less(j,j+1)){j=j+1;}
            if(less(k,j)){exchange(k,j);}
            k=j;
        }
    }

    /**
     * 删去堆顶元素
     * @return
     */
    public Comparable delMax()
    {
        Comparable maxx=this.heap[1];
        exchange(1,size);
        this.heap[size--]=null;
        sink(1);
        return maxx;
    }

    /**
     * 获取堆顶元素
     * @return
     */
    public Comparable top()
    {
        return this.heap[1];
    }

    public static void main(String[] args) {
      //测试用例 use case
        heap h=new heap();
        h.add(new Comparable[] {3,2,5,7});
        System.out.println(h.size); //4
        System.out.println(h.delMax()); //7
        System.out.println(h.size);  //3
        h.add(new Comparable[]{3,2,9,10});
        System.out.println(h.size);  //7
        System.out.println(h.delMax()); //10
        System.out.println(h.size);  //6
    }


}
