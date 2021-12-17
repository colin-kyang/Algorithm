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
        this.root=put(this.root,key,value);
        size(this.root);
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
        return current;
    }

    /**
     * 根据key 取值
     * @param key
     * @return
     */
    public Value get(Key key)
    {
        Node current=this.root;
        while(current!=null)
        {
            int flag=key.compareTo(current.key);
            if(flag==0)
            {
                return current.value;
            }
            else if(flag<0)
            {
                //key 较小：左子树搜索
                current=current.left;
            }
            else
            {
                //key 较大： 右子树搜索
                current=current.right;
            }
        }
        return null;
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
     *
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


    public static void main(String[] args) {
        //基本构建
        Symbol_Table<Integer,Integer> st=new Symbol_Table<>();
        st.put(3,9190);
        st.put(2,9890);
        st.put(1,9999);
        st.put(4,10);
        st.remove(1);
        System.out.println(st.get(3));
    }
}
