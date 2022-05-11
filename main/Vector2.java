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

    public boolean Compare(Vector2 vec)
    {
        if(x == vec.x && y == vec.y) return true;
        else return false;
    }

    public static Vector2 AddVectors(Vector2 first, Vector2 second)
    {
        return new Vector2(first.x + second.x, first.y + second.y);
    }
}
