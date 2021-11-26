public class union_find {
    private int [] id;  //触点分量
    private int count;  //连通分量个数
    public union_find(){}

    /**
     * 初始化函数，每一个触点的标识符是它自己
     * @param N
     */
    public union_find(int N)
    {
        this.id=new int[N];
        for(int i=0;i<N;i++)
        {
            this.id[i]=i;
        }
        this.count=N;
    }

    /**
     * 返回连通分量个数
     * @return
     */
    public int getCount()
    {
        return count;
    }

    /**
     * 判断触点p与q是否连通（属于同一个集合）
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p,int q)
    {
        return id[p]==id[q];
    }

    /**
     * 查找触点p所处连通分量（集合）标识符
     * @param p
     * @return
     */
    public int find(int p){return 0;}
    /**
     * 连通节点p与q
     * @param p
     * @param q
     */
    public void union(int p,int q){}
}
