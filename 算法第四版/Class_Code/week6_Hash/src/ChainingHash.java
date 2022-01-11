import java.util.*;

/**
 * 基于拉链法实现符号表
 */
public class ChainingHash<Key,Value> {
    private int N; //键值对个数
    private int M;//散列表大小
    private unorded_ST<Key,Value> [] unorded_st;

    /**
     * 无参数初始化
     */
    public ChainingHash()
    {
        this(997);
    }

    /**
     * 有参数初始化
     * 1. 创建M条链表
     * 2. 对每一个链表元素进行初始化
     * @param M
     */
    public ChainingHash(int M)
    {
        //创建M条链表
        this.M=M;
        unorded_st=new unorded_ST[M];
        //对每一个元素进行初始化
        for(int i=0;i<M;i++) {
            unorded_st[i] = new unorded_ST<>();
        }
    }

    /**
     * 求hash值
     * @param key
     * @return
     */
    private int hash(Key key)
    {
        return key.hashCode()&0x7fffffff % M;
    }

    /**
     * 获取key 所对应对value值
     * @param key
     * @return
     */
    private  Value get(Key key)
    {
        return (Value)unorded_st[hash(key)].get(key);
    }

    /**
     * 以键值对对形式（Key，Value）插值
     * @param key
     * @param value
     */
    private void put(Key key,Value value)
    {
        unorded_st[hash(key)].put(key,value);
    }

    /**
     * 获取所有的键值
     * @return
     */
    public List<Key> keys()
    {
        //遍历所有链表实现
        List<Key> KeyList=new ArrayList<Key>();
        for(int i=0;i<unorded_st.length;i++)
        {
           KeyList.addAll(unorded_st[i].Keys());
        }
        return KeyList;
    }

    /**
     * 删除键值为key的节点
     * @param key
     */
    public void delete(Key key)
    {
        unorded_st[hash(key)].delete(key);
    }


    public static void main(String[] args) {
        ChainingHash<String,Integer> ch=new ChainingHash<>();
        ch.put("yang",1);
        ch.put("ke",2);
        ch.put("colin",3);
        ch.put("yang",4);
        System.out.println(ch.get("yang"));
        System.out.println(ch.keys());
    }
}
