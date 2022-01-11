import java.util.Iterator;

/**
 * 基于可变数组构造的栈
 */
public class ResizingArrayStack<Item> implements Iterable<Item>{
    Item [] Stack;
    int N;// 栈中元素个数
    public ResizingArrayStack()
    {
        //实现泛型数组
        Stack= (Item[]) new Object[2];
        N=0;
    }

    /**
     * 返回栈大小
     * @return
     */
    int size()
    {
        return N;
    }

    /**
     * 栈中压入元素
     * @param e
     */
    public void push(Item e)
    {
        //溢出检查
        if(N>=Stack.length)
        {
            resize(Stack.length*2);
        }
        Stack[N]=e;
        N++;
    }

    /**
     * 动态调整数组
     * @param size
     */
    public void resize(int size)
    {
        Item [] temp=(Item[])new Object[size];
        for(int i=0;i<N;i++)
        {
            temp[i]=Stack[i];
        }
        Stack=temp;
    }

    /**
     * 弹出栈顶元素
     * @return
     */
    public Item pop()
    {
        Item e=Stack[N--];
        //一旦栈大小小于等于数组大小顶四分之一，调整其至少为半满状态：保证还可以进行多次push、pop操作
        if(N>0&&N==Stack.length/4)
        {
            resize(Stack.length/2);
        }
        return e;
    }

    public Iterator<Item> iterator() {
        return (Iterator<Item>) new ReverseArrayIterator();
    }

    /**
     *实现迭代器类
     */
    public class ReverseArrayIterator implements Iterator<Item>{
        private int i=N;

        @Override
        public boolean hasNext() {
            return i>0;
        }

        public Item next(){return Stack[--i];}

        public void remove(){}
    }

    public static void main(String[] args) {
            ResizingArrayStack<String> stack=new ResizingArrayStack<>();
            stack.push("yang");
            stack.push("ke");
            stack.push("hello");
            stack.push("word!");
        for (String e:stack
             ) {
            System.out.println(e);
        }

    }


}
