package main;
public class Vector2 {
    public int x,y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void Set(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void AddVector(Vector2 vec)
    {
        x += vec.x;
        y += vec.y;
    }

    public void MultiplyVector(int multi)
    {
        x *= multi;
        y *= multi;
    }

    public void MultiplyVector(Vector2 multi)
    {
        x *= multi.x;
        y *= multi.y;
    }

    public double GetLenght()
    {
        return Math.sqrt(x*x + y*y);
    }

    public boolean Compare(Vector2 vec)
    {
        if(x == vec.x && y == vec.y) return true;
        else return false;
    }

    public static Vector2 MultiplyVecotrs(Vector2 first, Vector2 second)
    {
        return new Vector2(first.x * second.x, first.y * second.y);
    }

    public static Vector2 AddVectors(Vector2 first, Vector2 second)
    {
        return new Vector2(first.x + second.x, first.y + second.y);
    }

    @Override
    public String toString() {
        
        return "[" + x + ","+y+"]";
    }
}
