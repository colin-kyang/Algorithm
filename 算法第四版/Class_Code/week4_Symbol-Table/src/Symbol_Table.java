/**
 * 符号表：类map
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
    private Node root;
    /**
     * 初始化条件
     */
    public Symbol_Table()
    {
    }

    /**
     * 返回根节点
     * @return
     */
    public Node getRoot()
    {
        return this.root;
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
            current=new Node(key,value);
            return current;
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
     * @param key
     * @return
     */
    public Value get(Key key)
    {
        Node current=root;
        while(current!=null)
        {
            int flag=key.compareTo(current.key);
            if(flag==0)
            {
                return current.value;
            }
            else if(flag<0)
            {
                current=current.right;
            }
            else
            {
                current=current.left;
            }
        }
        return null;
    }

//    public delete(Key key)
//    {
//
//    }
//

    public static void main(String[] args) {
        //基本构建
        Symbol_Table<Integer,Integer> st=new Symbol_Table<>();
        st.put(st.getRoot(),1,9190);
        st.put(st.getRoot(),2,9890);
        st.put(st.getRoot(),3,9999);
        System.out.println(st.get(1));
        System.out.println(st.get(2));
        System.out.println(st.get(3));
    }
}
