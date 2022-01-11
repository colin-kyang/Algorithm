import java.util.ArrayList;
import java.util.List;

/**
 * 基于有序数组的符号表
 */
public class ordedST <Key extends Comparable<Key>,Value>{
    private Key [] keys;
    private Value [] values;
    int size;
    ordedST(int capicity)
    {
        keys=(Key []) new Comparable[capicity];
        values=(Value [])new Object[capicity];
        size=0;
    }

    /**
     * 数组扩容
     * @param capicity
     */
    public void resize(int capicity)
    {
        Key [] new_keys=(Key[]) new Comparable[capicity];
        Value [] new_value=(Value []) new Object[capicity];
        for(int i=0;i<size;i++)
        {
            new_keys[i]=this.keys[i];
            new_value[i]=this.values[i];
        }
        keys=new_keys;
        values=new_value;
    }


    public void put(Key key,Value value)
    {
        int rank=rank(key);
        if(rank+1>this.values.length)
        {
            resize(this.values.length*2);
        }
        //若当前不存在该key值
        if(keys[rank]==null)
        {
            keys[rank]=key;
            values[rank]=value;
            size++;
        }
        //若当前存在key值 覆盖
        if(keys[rank].compareTo(key)==0)
        {
            keys[rank]=key;
            values[rank]=value;
        }
        else
        {
            //中间位置
            for(int x=size+1;x>rank;x--)
            {
                this.values[x]=this.values[x-1];
                this.keys[x]=this.keys[x-1];
            }
            this.keys[rank]=key;
            this.values[rank]=value;
            size++;
        }
    }

    /**
     * 是否存在该键
     * @param key
     * @return
     */
    public boolean isContain(Key key)
    {
        int rank=rank(key);
        return keys[rank].compareTo(key)==0;
    }

    /**
     * 找到插入位置
     * @param key
     * @return
     */
    public int  rank(Key key)
    {
        int lo=0;
        int hi=size-1;
        while(lo<=hi)
        {
            int mi=(lo+hi)/2;
            if(keys[mi].compareTo(key)==0)
            {
                return mi;
            }
            else if(key.compareTo(keys[mi])<0)
            {
                hi=mi-1;
            }
            else if(key.compareTo(keys[mi])>0)
            {
                lo=mi+1;
            }
        }
        return hi+1;

    }

    /**
     * 获取目标值
     * @param key
     * @return
     */
    public Value get(Key key)
    {
        int rank=rank(key);

        if(keys[rank]==null||rank<0||rank>=size)
        {
            return null;
        }
        else
        {
            if(keys[rank].compareTo(key)==0)
            {
                return values[rank];
            }
        }
        return null;
    }

    /**
     * 获取所有键值
     * @return
     */
    public List<Key> keys()
    {
        List<Key> keyList=new ArrayList<>();
        for(int i=0;i<this.size;i++)
        {
            keyList.add(keys[i]);
        }
        return keyList;
    }

    /**
     * 删除目标key值
     * @param key
     */
    public void delete(Key key)
    {
        int rank=rank(key);
        if(keys[rank].compareTo(key)==0)
        {
            for(int x=rank;x<size-1;x++)
            {
               keys[x]=keys[x+1];
               values[x]=values[x+1];
            }
            size--;
        }
    }

    public static void main(String[] args) {
        ordedST st=new ordedST(1);
        st.put(1,"a");
        st.put(1,"b");
        st.put(2,"c");
        st.put(3,"d");
        st.delete(3);
        System.out.println(st.get(1));
        System.out.println(st.get(2));


    }
}
