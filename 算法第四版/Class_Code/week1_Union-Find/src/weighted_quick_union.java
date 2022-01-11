/**
 * 基于路径压缩的-加权quick-union UF算法
 */

public class weighted_quick_union {
    private int [] id;  //触点分量
    private int [] size; //记录树的大小
    private int count;  //连通分量个数
    public weighted_quick_union(){}

    /**
     * 初始化函数，每一个触点的标识符是它自己
     * 初始化每一个触点的size为1
     * @param N
     */
    public weighted_quick_union(int N)
    {
       this.id=new int[N];
       this.size=new int[N];
       for(int i=0;i<N;i++)
       {
           this.id[i]=i;
           this.size[i]=1;
       }
       count=N;
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
            //路径压缩
            id[p]=id[id[p]];
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

        //小树指向大树的根触点
        if(size[pRoot]>size[qRoot])
        {
            id[qRoot]=id[pRoot];
            size[pRoot]+=size[qRoot];
        }
        else
        {
            id[pRoot]=id[qRoot];
            size[qRoot]+=size[pRoot];
        }
        count--;
    }

}
