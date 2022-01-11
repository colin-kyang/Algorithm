import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinearProbingHashST<Key,Value> {
    private int N; //符号表中键值对个数
    private int M=16;  //线性探测表的大小
    private Key[] keys;
    private Value [] values;
    List<Key> keyList;

    /**
     * 初始化，利用object建立泛型数组
     */
    public LinearProbingHashST(int capacity)
    {
        keys=(Key[]) new Object[capacity];
        values=(Value[]) new Object[capacity];
    }

    public LinearProbingHashST()
    {
        keys=(Key[]) new Object[M];
        values=(Value[]) new Object[M];
        keyList=new ArrayList<>();
    }

    /**
     * 哈希函数
     * @param key
     * @return
     */
    private int hash(Key key)
    {
        return (key.hashCode()&0x7fffffff)%M;
    }

    /**
     * 添加键值对
     * @param key
     * @param value
     */
    private void put(Key key,Value value)
    {
        if(N>=M/2)
        {
            resize(M*2);
        }
        int rank=hash(key);
        for(;keys[rank]!=null;rank=(rank+1) %M)
        {
            if(key.equals(keys[rank]))
            {
                //覆盖
                values[rank]=value;
                return;
            }
        }
        keyList.add(key);
        keys[rank]=key;
        values[rank]=value;
        N++;
    }

    /**
     * 查表
     * @param key
     * @return
     */
    private Value get(Key key)
    {
        int rank=hash(key);
        for(;keys[rank]!=null;rank=(rank+1)%M)
        {
            //若命中，则直接返回
            if(key.equals(keys[rank]))
            {
                return values[rank];
            }
        }
        return null; //无此值
    }

    /**
     * 扩容函数，用来扩充线性探测表大小
     */
    private void resize(int capacity)
    {
        LinearProbingHashST<Key,Value> lpst=new LinearProbingHashST<>(capacity);
        for(int i=0;i<M;i++)
        {
            if(keys[i]!=null)
            {
                lpst.put(keys[i],values[i]);
            }
        }
        keys= lpst.keys;
        values=lpst.values;
    }

    /**
     * 删除元素
     * @param key
     */
    private void delete(Key key)
    {
        if(!contains(key))
        {
            return ;
        }
        int rank=hash(key);
        while(!key.equals(keys[rank]))
        {
            rank=(rank+1)%M;
        }
        keys[rank]=null;
        values[rank]=null;
        //对被删除元素右边的族簇进行重插
        rank=(rank+1)%M;
        while(keys[rank]!=null)
        {
            Key keyTemp=keys[rank];
            Value valueTemp=values[rank];
            keys[rank]=null;
            values[rank]=null;
            N--;
            put(keyTemp,valueTemp);
            rank=(rank+1)%M;
        }
        N--;
        if(N>0&&N==M/8){resize(M/2);}

    }

    /**
     * 判断符号表中是否存在该元素
     * @param key
     * @return
     */
    private boolean contains(Key key)
    {
        return keyList.contains(key);
    }

    public static void main(String[] args) {
        LinearProbingHashST<String,Integer> lpst=new LinearProbingHashST<>();
        lpst.put("yang",1);
        lpst.put("ke",2);
        lpst.put("hello",3);
        lpst.put("zkgh",4);
        System.out.println(Arrays.toString(lpst.keys));
        System.out.println(Arrays.toString(lpst.values));
        System.out.println(lpst.get("zkgh"));
    }
}
