/**
 * 属于同一连通分量的元素id值相同，每一次union操作，将会对整个数组进行遍历
 */
public class quick_find {
    private int [] id;  //触点分量
    private int count;  //连通分量个数
    public quick_find(){}

    /**
     * 初始化函数，每一个触点的标识符是它自己
     * @param N
     */
    public quick_find(int N)
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
        return find(p)==find(q);
    }

    /**
     * 查找触点p所处连通分量（集合）标识符
     * @param p
     * @return
     */
    public int find(int p)
    {
        //找到该触点所处连通分量的根节点标识
        while(id[p]!=p)
        {
            p=id[p];
        }
        return p;
    }

    /**
     * 连通节点p与q
     * @param p
     * @param q
     */
    public void union(int p,int q)
    {
        //查找p触点的连通分量标识
        int pRoot=find(p);
        //查找q触点的连通分量标识
        int qRoot=find(q);

        //若p与q本处于同一连通分量，则停止合并
        if(pRoot==qRoot){return;}

        //将q触点所处连通分量中根触点指向p触点所处连通分量中的根节点
        id[qRoot]=id[pRoot];
        count--;
    }
}
