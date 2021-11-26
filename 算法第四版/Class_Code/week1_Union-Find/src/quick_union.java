/**
 * 加快union过程，将触点指向其相邻的出点，通过find来获取根节点的值
 */

public class quick_union{
    private int [] id;  //触点分量
    private int count;  //连通分量个数
    public quick_union(){}

    /**
     * 初始化函数，每一个触点的标识符是它自己
     * @param N
     */
    public quick_union(int N)
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
        return id[p];
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

        //将q触点所处连通分量中所有的触点标识改为pRoot
        for(int i=0;i<id.length;i++)
        {
            if(id[i]==qRoot)
            {
                id[i]=pRoot;
            }
        }
        count--;
    }

}
