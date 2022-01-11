import java.util.Random;

public class Percolation {
    private site grid[][];
    private int size[][];
    private int sizeN;
    private int count;

    //init
    public Percolation(int N) {
        grid = new site[N][N];
        size = new int[N][N];
        sizeN = N;
        count = N * N;
        for (int i = 0; i < sizeN; i++) {
            for (int j = 0; j < sizeN; j++) {
                grid[i][j] = new site(i, j);
                size[i][j] = 1;
            }
        }
    }

    //find
    public site find(site A)
    {
        int x,y;
        while(A.root_x!=A.x||A.root_y!=A.y)
        {
            x=A.root_x;
            y=A.root_y;
            //path compression
            A.root_x=grid[x][y].root_x;
            A.root_y=grid[x][y].root_y;
            A=grid[A.root_x][A.root_y];
        }
        return A;
    }

    public void open(site A)
    {
        A.open=true;
        //4-Union
        if(A.x-1>=0) {
            union(A, grid[A.x - 1][A.y]);
        }

        if(A.y-1>=0)
        {
            union(A,grid[A.x][A.y-1]);
        }

        if(A.x+1<sizeN)
        {
            union(A,grid[A.x+1][A.y]);
        }

        if(A.y+1<sizeN)
        {
            union(A,grid[A.x][A.y+1]);
        }
    }

    //union
    public void union(site A,site B)
    {

        //if open and union
        if(A.open!=true||B.open!=true){return;}
        site rootA=find(A);
        site rootB=find(B);
            if(size[rootA.root_x][rootA.root_y]>=size[rootB.root_x][rootB.root_y])
            {
                rootB.root_x=rootA.root_x;
                rootB.root_y=rootA.root_y;
                size[rootA.root_x][rootA.root_y]+=size[rootB.root_x][rootB.root_y];
            }
            else
            {
             rootA.root_x=rootB.root_x;
             rootA.root_y=rootB.root_y;
             size[rootB.root_x][rootB.root_y]+=size[rootA.root_x][rootA.root_y];
            }

    }

    //percolates?
    public boolean percolates()
    {
        for(int i=0;i<sizeN;i++)
        {
            for(int j=0;j<sizeN;j++)
            {
                int root1_x=find(grid[0][i]).root_x;
                int root1_y=find(grid[0][i]).root_y;
                int root2_x=find(grid[sizeN-1][j]).root_x;
                int root2_y=find(grid[sizeN-1][j]).root_y;
                if(root1_x==root2_x&&root1_y==root2_y)
                {
                    return true;
                }

            }
        }
        return false;
    }

    void print()
    {
        for(int i=0;i<sizeN;i++)
        {
            for(int j=0;j<sizeN;j++)
            {
                System.out.print("("+find(grid[i][j]).root_x+","+find(grid[i][j]).root_y+")");
                System.out.print(" ");
            }
            System.out.println(" ");
        }

    }

    void print_open()
    {
        for(int i=0;i<sizeN;i++)
        {
            for(int j=0;j<sizeN;j++)
            {
                System.out.print("("+grid[i][j].open+")");
                System.out.print(" ");
            }
            System.out.println(" ");
        }
    }

    public static void main(String[] args) {
            //10*10
            //20000times
        int m=0;
        double sum=0;
        int time=200000;
        int n=10;
        for(int times=0;times<time;times++)
        {
            Percolation percolation=new Percolation(n);
            double count=0;
            System.out.println(times);
            while(!percolation.percolates())
            {

                Random r=new Random();
                int xx=r.nextInt(n);
                int yy=r.nextInt(n);
                while(percolation.grid[xx][yy].open&&count<n*n)
                {
                    xx=r.nextInt(n);
                    yy=r.nextInt(n);
                }
                percolation.open(percolation.grid[xx][yy]);
                count++;
            }
            sum+=(count/(n*n));
        }
        System.out.println(sum/time);
    }


}
