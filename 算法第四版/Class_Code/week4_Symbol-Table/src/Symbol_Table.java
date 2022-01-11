import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

/**
 * 符号表：类似于 map<key,value>
 * 基于二叉搜素树实现
 */
public class Symbol_Table<Key extends Comparable<Key>,Value> {

    class Node{
        Key key;
        Value value;
        Node left;
        Node right;
        int size;
        //init without param
        public Node(){
            size=0;
        }
        //init with param

        public Node(Key key,Value value)
        {
            this.key=key;
            this.value=value;
        }
    }

    //根节点
    private Node root;

    /**
     * 初始化条件
     */
    public Symbol_Table()
    {
    }

    /**
     * 供对象调用 插值
     * @param key
     * @param value
     */
    public void put(Key key,Value value)
    {
        //由根节点开始递归地插入节点
        this.root=put(this.root,key,value);
        //更新总数
        root.size=size(this.root);
    }

    /**
     * 符号表添加新表项，若已存在目标key值，则覆盖；否则添加新表项
     * @param key
     * @param value
     */
    public Node put(Node current,Key key,Value value)
    {
        if(current==null)
        {
            //插入节点
           return new Node(key,value);
        }
        int flag=key.compareTo(current.key);
        if(flag==0)
        {
            //若插入节点 key==current.value : overwritten
            current.value=value;
        }
        else if(flag<0)
        {
            //若插入节点 key < current.value : 左子树搜索
           current.left=put(current.left,key,value);
        }
        else
        {
            //若插入节点 key > current.value : 右子树搜索
            current.right=put(current.right,key,value);
        }
        //更新当前节点总数
        current.size=size(current);
        return current;
    }

    /**
     * 根据key 取值
     * @param key
     * @return
     */
    public Value get(Key key)
    {
        return get(root,key);
    }

    /**
     * 寻找key 对应的节点值
     * @param current
     * @param key
     * @return
     */
    public Value get(Node current,Key key)
    {
        //若寻到空节点，则返回null
        if(current==null){return null;}
        int flag=key.compareTo(current.key);
        if(flag==0){return current.value;}
        else if(flag<0){
            return get(current.left,key);
        }
        else
        {
            return get(current.right,key);
        }
    }

    /**
     * 节点size的获取（以cuurent 节点为根的子树，共存在多少节点）
     * @param current
     * @return
     */
    public int size(Node current)
    {
        if(current==null){return 0;}
        return (current.size= 1+size(current.left)+size(current.right));
    }

    public int size()
    {
        return root.size;
    }

    /**
     * 获取最大值
     * @return
     */
    public Value getMax()
    {
        return getMax(root).value;
    }

    /**
     * 获取最小值
     * @return
     */
    public Value getMin()
    {
        return getMin(root).value;
    }

    /**
     * 获取key 值最大节点
     * @return
     */
    public Node getMax(Node root)
    {
        Node current=root;
        while(current.right!=null)
        {
            current=current.right;
        }
        return current;
    }

    /**
     * 获取key 值最小节点
     * @return
     */
    public Node getMin(Node root)
    {
        Node current=root;
        while(current.left!=null)
        {
            current=current.left;
        }
        return current;
    }


    /**
     * largest key <= a given key
     * @param key
     * @return
     */
    public Node Floor(Key key)
    {
      return Floor(this.root,key);
    }


    /**
     * 找到键值小于key 值的最大的节点的value
     * @param current
     * @param key
     * @return
     */
    public Node Floor(Node current,Key key)
    {
        if(current ==null)
        {
            return null;
        }
        int flag=key.compareTo(current.key);
        if(flag==0)
        {
            return current;
        }
        else if(flag<0)
        {
            return Floor(current.left,key);
        }
        //试探，若向右能继续找到小于key的节点，则继续；否则，当前节点就是目标点
        Node tmp=Floor(current.right,key);
        if(tmp==null)
        {//试探：可能存在回退操作
            return current;
        }
        else
        {
            return tmp;
        }
    }

    /**
     * 从root 开始扫描，并开始删除点
     * @param key
     */
    public void remove(Key key)
    {
        remove(this.root,key);
    }

    public Node remove(Node current,Key key)
    {
        if(current==null)
        {
            //不存在目标节点
            return null;
        }
        int flag=key.compareTo(current.key);
        if(flag==0)
        {
           if(current.right==null){ return current.left;}
           if(current.left==null){return current.right;}
           //左右子树都存在：将右子树，最小的哪个节点获取
            Node rMin=getMin(current.right);
            remove(current.right,rMin.key);
            rMin.left=current.left;
            rMin.right=current.right;
            current=rMin;
        }
        else if(flag>0)
        {
            //右子树删除操作
            current.right=remove(current.right,key);
        }
        else if(flag<0)
        {
            //左子树删除操作
            current.left=remove(current.left,key);
        }
        size(current);
        return current;
    }

    /**
     * 范围查找
     * @param lo
     * @param hi
     * @return
     */
    public Queue<Key> keys(Key lo,Key hi)
    {
        Queue<Key> queue=new ArrayDeque<>();
        keys(root,queue,lo,hi);
        return  queue;

    }

    /**
     * 范围查找实现api
     * @param root
     * @param queue
     * @param lo
     * @param hi
     */
    private void keys(Node root, Queue<Key> queue, Key lo, Key hi) {
        if(root==null){return;}
        int cmpLo= root.key.compareTo(lo);
        int cmpHigh=root.key.compareTo(hi);
        if(cmpLo>0){keys(root.left,queue,lo,hi);}
        if(cmpLo>=0&&cmpHigh<=0){
            System.out.println(root.key);
            queue.add(root.key);}
        if(cmpHigh<0){keys(root.right,queue,lo,hi);}
    }


    public static void main(String[] args) {
        //基本构建
        Symbol_Table<Integer,Integer> st=new Symbol_Table<>();
        st.put(3,9190);
        st.put(2,9890);
        st.put(1,9999);
        st.put(4,10);
        st.keys(1,4);
//        st.remove(1);
//        System.out.println(st.get(3));
//        System.out.println(st.size());
    }
}
