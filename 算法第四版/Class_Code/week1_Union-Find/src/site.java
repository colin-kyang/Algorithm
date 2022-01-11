public class site {
    public int root_x;
    public int root_y;
    public int x;
    public int y;
    public boolean open;
    site(int x,int y)
    {
        this.x=x;
        this.y=y;
        root_x=x;
        root_y=y;
        open=false;
    }
}
