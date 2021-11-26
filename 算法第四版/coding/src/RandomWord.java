import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String word, champion = null;  // champoin to be the final word for output
        int count = 0;

        while(!StdIn.isEmpty()){
            word = StdIn.readString();
            count++;
            if(StdRandom.bernoulli(1/(double)count))
                champion = word;
        }
        StdOut.println(champion);
    }
}
