import java.util.Scanner;
import java.util.Stack;

/**
 * 基于双栈实现简单符号计算器
 * 支持 + - * /
 */
public class Accumulator {
    Stack<String> ops;
    Stack<Double> nums;
    Accumulator()
    {
        ops=new Stack<>();
        nums=new Stack<>();
    }

    public void compute()
    {
        //进行计算
        String op=ops.pop();
        double num1=nums.pop();
        double num2=nums.pop();
        if(op.equals("+"))
        {
            nums.push(num1+num2);
        }
        else if(op.equals("-"))
        {
            nums.push(num2-num1);
        }
        else if(op.equals("*"))
        {
            nums.push(num1*num2);
        }
        else
        {
            nums.push(num2/num1);
        }
    }

    public double evalute()
    {
        while(!ops.empty())
        {
            compute();
        }
        return nums.pop();
    }


    public static void main(String[] args) {
        Accumulator accumulator=new Accumulator();
        Scanner scanner=new Scanner(System.in);
        String line= scanner.nextLine();
        for(int i=0;i<line.length();i++)
        {
            String tmpNum="";
            String tmpOp="";
            while(i<line.length()&&((line.charAt(i)>='0'&&line.charAt(i)<='9')||line.charAt(i)=='.'))
            {
                tmpNum+=line.charAt(i);
                i++;
            }
            if(!tmpNum.equals(""))
            {
                accumulator.nums.push(Double.parseDouble(tmpNum));
                i--;
                continue;
            }
            if(line.charAt(i)=='(')
            {
                continue;
            }
            else if(line.charAt(i)==')')
            {
               accumulator.compute();
            }
            else
            {
                accumulator.ops.push(""+line.charAt(i));
                continue;
            }
        }
        System.out.println(accumulator.evalute());
    }
}
