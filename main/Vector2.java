package main;
public class Vector2 {
    
    //======================================VALUES
    public int x,y;

    //============================================CTOR
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //=============================================METHODS

    //*Sets vector x and y values
    //int x - value of x element of Vector
    //int y - value of y element of Vector
    public void Set(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    //*Returns current vector which x and y elements are enlarges with given x and y element of given vector
    //Vector2 vec - vector which is being added to current vector
    public Vector2 AddVector(Vector2 vec)
    {
        return new Vector2(x + vec.x, y + vec.y);
    }

    //*Returns current vector which x and y elements are reduced with given x and y element of given vector
    //Vector2 vec - vector which is being subtracted from current vector
    public Vector2 SubtractVector(Vector2 vec)
    {
        return new Vector2(x - vec.x, y - vec.y);
    }

    //*Returns current vector multiplied by given values
    //int multi - multiplies vector
    public Vector2 MultiplyVector(int multi)
    {
        return new Vector2(x*multi, y*multi);
    }

    //*Returns lenght of vecotr
    public double GetLenght()
    {
        return Math.sqrt(x*x + y*y);
    }

    //*Returns true if current x and y values matches x and y values of given vector
    //Vector2 vec - vector to compare with
    public boolean Compare(Vector2 vec)
    {
        if(x == vec.x && y == vec.y) return true;
        else return false;
    }

    //*Changes swaps x and y elements with eachother
    public void Invert()
    {
        int a = x;
        x = y;
        y = a;
    }

    //*Returns vector which x and y elements are sum of x and y elements of two vectors
    //Vector2 first - vector to add to
    //VEctor2 second - vector to add to
    public static Vector2 AddVectors(Vector2 first, Vector2 second)
    {
        return new Vector2(first.x + second.x, first.y + second.y);
    }

    //*Returns Vector2 with x = 0 and y = 0
    public static Vector2 Zero()
    {
        return new Vector2(0, 0);
    }

    //*Returns Vector2 with x = 1 and y = 1
    public static Vector2 One()
    {
        return new Vector2(1, 1);
    }
    
    @Override
    public String toString() {
        
        return "[" + x + ","+y+"]";
    }
}
