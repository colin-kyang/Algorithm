import java.util.Iterator;
import java.util.ListIterator;

/**
 * 基于链表构造的栈
 */

public class LinckedStack<Item> implements Iterable<Item>{

   private Node head; //头节点，用于辅助操作，无值
   private Node current;
   int N;
    class Node{
        Node next;
        Item item;
        Node(){}
        Node(Item item){this.item=item;}
    }

    /**
     *  链表初始化
     */
    LinckedStack()
    {
        head=new Node();
        current=head;
        N=0;
    }

    /**
     * 链表是否为空
     * @return
     */
    public boolean isEmpty()
    {
        return   N==0;
    }

    /**
     * 元素压栈
     * @param itm
     */
    public void push(Item itm)
    {
        current.next=new Node(itm);
        current=current.next;
        N++;
    }

    /**
     * 元素出栈
     * @return
     */
    public Item pop()
    {
        if(isEmpty()){
            System.out.println("当前栈空");
            return null;
        }
        Item itm= current.item;
        Node tmp=head;
        while(tmp.next!=current)
        {
            tmp=tmp.next;
        }
        //null
        tmp.next=current.next;
        current=tmp;
        N--;
        return itm;
    }

    //添加迭代器实现方法
    public Iterator<Item> iterator()
    {
        return new LinckedIterator();
    }

    /**
     * 迭代器实现
     */
    public class LinckedIterator implements Iterator<Item>
    {
        Node cou=head.next;
        @Override
        public boolean hasNext() {
            return cou!=null;
        }

        public Item next()
        {
            Item itm=cou.item;
            cou=cou.next;
            return itm;
        }
    }

    public static void main(String[] args) {
        LinckedStack<String> linkedStack=new LinckedStack<String>();
        linkedStack.push("k");
        linkedStack.push("y");
        linkedStack.push("hello");
        linkedStack.push("world!");
//        linkedStack.pop();
        for (String e:linkedStack
             ) {
            System.out.println(e);
        }

    }


}
