/**
 * 左倾红黑树 LLRBT
 * 拓展： 删除
 */
public class LLRBT {
    private Node root;
    public class Node{
        Comparable key;
        Comparable value;
        Node left;
        Node right;
        String color;
        public Node(Comparable key,Comparable value)
        {
            this.key=key;
            this.value=value;
            //默认颜色为黑色
            this.color="Red";
        }
        public Node(){}
        int N;
    }
    public LLRBT()
    {
    }

    public boolean isRed(Node current)
    {
        if(current==null){return false;}
        return current.color=="Red";
    }

    /**
     * 供外部调用插值api
     * @param key
     * @param value
     */
    public void put(Comparable key,Comparable value)
    {
        this.root=put(root,key,value);
        //每次插值后，均需要重置this.root 的颜色
        //避免this.root 颜色为红色。每次由红-> 黑，对应 2-3                                        树高度+1
        this.root.color="Black";
    }

    /**
     * 构建左倾红黑树api
     * @param root
     * @param key
     * @param value
     * @return
     */
    private Node put(Node root, Comparable key, Comparable value) {
        //若当前节点为空，则创建新的节点返回
        if(root==null)
        {
            return new Node(key,value);
        }
        int cmp=key.compareTo(root.key);
        if(cmp<0)
        {
            //向左子树插值
            root.left=put(root.left,key,value);
        }
        else if(cmp>0)
        {
            //向右子树插值
            root.right=put(root.right,key,value);
        }
        else
        {
            //覆盖当前值
            root.value=value;
        }
        //处理
        if(isRed(root.right)&&!isRed(root.left)){root=leftRotate(root);}
        if(isRed(root.left)&&isRed(root.left.left)){root=rightRotate(root);}
        if(isRed(root.left)&&isRed(root.right)){root=colorFlip(root);}
        return root;
    }

    /**
     * 颜色翻转
     * @param current
     * @return
     */
    private Node colorFlip(Node current) {
        current.left.color="Black";
        current.right.color="Black";
        current.color="Red";
        return current;
    }

    /**
     * 右旋
     * @param current
     * @return
     */
    private Node rightRotate(Node current) {
        Node x=current.left;
        current.left=x.right;
        x.right=current;
        x.color= current.color;
        current.color="Red";
        return x;
    }

    /**
     * 左旋
     * @param current
     * @return
     */
    public Node leftRotate(Node current)
    {
        //获取当前根节点的右子节点
        Node x=current.right;
        //将当前节点的右节点指向x的左子节点（作为中键）
        current.right=x.left;
        //(x节点的颜色即current的颜色)
        x.color=current.color;
        //将x节点上升，作为根节点，current变为左子节点
        x.left=current;
        //表示current与x组成一个3-节点
        current.color="Red";
        return x;
    }

    /**
     * 面向外部的接口
     * @param key
     * @return
     */
    public Comparable get(Comparable key)
    {
        return get(this.root,key);
    }

    /**
     * 内部接口，用于递归查值
     * @param root
     * @param key
     * @return
     */
    private Comparable get(Node root, Comparable key) {
        if(root==null)
        {
            return null;
        }
        int cmp=key.compareTo(root.key);
        if(cmp<0)
        {
            return get(root.left,key);
        }
        else if(cmp>0)
        {
            return get(root.right,key);
        }
        else{
            return root.value;
        }
    }

    //--- to do--- 删除
    public void remveMax()
    {


    }

    public void removeMin()
    {


    }

    public void remove(Comparable key)
    {

    }





    public static void main(String[] args) {
        LLRBT mapp=new LLRBT();
        mapp.put(1,9);
        mapp.put(2,3);
        System.out.println(mapp.get(1));
    }

}
