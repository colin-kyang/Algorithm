import java.util.ArrayList;
import java.util.List;

/**
 * 基于无序链表构建的符号表
 * 泛型设计思想
 */
public class unorded_ST<Key,Value> {
    private Node first;//链表首节点
    int size;
    public class Node
   {
       Key key;
       Value value;
       Node next;
       Node(){}
       Node(Key key,Value value,Node next){
           this.key=key;
           this.value=value;
           this.next=next;
       }
   }

   public unorded_ST()
   {
       this.size=0;
   }

    /**
     * 插值操作
     * @param key
     * @param value
     */
    public void put(Key key,Value value)
    {
        for(Node x=first;x!=null;x=x.next)
        {
            if(x.key.equals(key))
            {
                x.value=value;
                return ;
            }
        }
        first=new Node(key,value,first);
        size++;
    }

    /**
     * 根据键值Key 查找目标值 Value
     * @param key
     * @return
     */
    public Value get(Key key)
    {
        for(Node x=first;x!=null;x=x.next)
        {
            if(x.key.equals(key))
            {
                return x.value;
            }
        }
        return null;
    }

    /**
     * 删除key 值
     */
    public void delete(Key key)
    {
        //1. 如果是头节点
        if(first.key==key)
        {
            // 更换头节点即可
            first=first.next;
            System.out.println("删除头节点成功");
            size--;
            return ;
        }

        //2. 其他部分节点
        for(Node x=first;x!=null;x=x.next)
        {
            if(x.next!=null&&x.next.key==key)
            {
                x.next=x.next.next;
                System.out.println("删除目标节点成功");
                size--;
                return ;
            }
        }

        //3. 并未含有该节点
        System.out.println("不存在目标节点");
        return;
    }

    /**
     * 获取所有目标键值
     * @return
     */
    public List<Key> Keys()
    {
        List<Key> keyList=new ArrayList<>();
        for(Node x=first;x!=null;x=x.next)
        {
           keyList.add(x.key);
        }
        return keyList;
    }

    public static void main(String[] args) {
        //test
        unorded_ST st=new unorded_ST();
        st.put(1,"a");
        st.put(1,"b");
        st.delete(1);
        System.out.println(st.get(1));
    }
}
