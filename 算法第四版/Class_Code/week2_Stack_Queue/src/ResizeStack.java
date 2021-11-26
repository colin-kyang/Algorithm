/**
 * 基于可变数组构造的栈
 */


public class ResizeStack {
    String [] stack=new String[1];
    int N=0;//元素个数
    public void push(String str)
    {
        if(N==stack.length){resize(2*N);}
        stack[N++]=str;

    }

    public String pop()
    {
        String old=stack[N-1];
        stack[N-1]=null;
        N--;
        if(N<=(1/4)*N){resize((N/2));}
        return old;
    }

    public  void resize(int n)
    {
        String [] temp=new String[n];
        for(int i=0;i<n&&i< stack.length;i++)
        {
            temp[i]=stack[i];
        }
        stack=temp;
    }

    public Boolean isEmpty()
    {
        return N==0;
    }

    public static void main(String[] args) {
        ResizeStack resizeStack =new ResizeStack();
        System.out.println(resizeStack.isEmpty());
        resizeStack.push("杨珂");
        resizeStack.push("珂珂");

        while(!resizeStack.isEmpty())
        {
            System.out.println(resizeStack.pop());
        }
    }

}
