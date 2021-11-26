import java.util.Iterator;
import java.util.ListIterator;

/**
 * 基于链表构造的栈
 */

public class LinckedStack<Item> implements Iterable<Item>{

    private Node first=null;
    private Node current=null;
    class Node{
        Node next;
        Item item;
    }

    public boolean isEmpty()
    {
        return first==null;
    }

    public void push(Item itm)
    {
        Node old=first;
        first =new Node();
        first.item=itm;
        first.next=old;
        current=first;
    }

    public Item pop()
    {
        Item olditm=first.item;
        first=first.next;
        return olditm;
    }

    public static void main(String[] args) {
        LinckedStack<String> linkedStack=new LinckedStack<String>();
        linkedStack.push("杨");
        linkedStack.push("珂");
        Iterator<String> iterator= linkedStack.iterator();
    }

    //添加迭代器实现方法

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>() {
            @Override
            public boolean hasNext() {
                return current!=null;
            }

            @Override
            public Item next() {
                Item item= current.item;
                current=current.next;
                return item;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Item previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(Item item) {

            }

            @Override
            public void add(Item item) {

            }

        };
    }

}
