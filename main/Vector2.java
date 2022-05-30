package main;

/**
 * This class is used to make entities be able to move
 */
public class Vector2 {
    
    //======================================VALUES
    public int x,y;

    //============================================CTOR

    /**
     * This constructor creates the vector for the entity
     * @param x This parameter represents the height value
     * @param y This parameter represents the width value
     */
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //=============================================METHODS

    /**
     * This method sets vector x and y values
     * @param x This parameter represents the value of x element of Vector
     * @param y This parameter represents the value of y element of Vector
     */
    public void Set(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * This method returns current vector which x and y elements are enlarges with given x and y element of given vector
     * @param vec This parameter represents the vector which is being added to current vector
     */
    public Vector2 AddVector(Vector2 vec)
    {
        return new Vector2(x + vec.x, y + vec.y);
    }

    /**
     * This method returns current vector which x and y elements are reduced with given x and y element of given vector
     * @param vec This parameter represents the vector which is being subtracted from current vector
     */
    public Vector2 SubtractVector(Vector2 vec)
    {
        return new Vector2(x - vec.x, y - vec.y);
    }

    /**
     * This method returns current vector multiplied by given values
     * @param multi This parameter multiplies vector
     */
    public Vector2 MultiplyVector(int multi)
    {
        return new Vector2(x*multi, y*multi);
    }

    /**
     * This method Returns length of a vector
     */
    public double GetLenght()
    {
        return Math.sqrt(x*x + y*y);
    }

    /**
     * This method returns true if current x and y values matches x and y values of given vector
     * @param vec This parameter represents the vector to compare with
     */
    public boolean Compare(Vector2 vec)
    {
        if(x == vec.x && y == vec.y) return true;
        else return false;
    }

    /**
     * This method swaps x and y elements with each other
     */
    public void Invert()
    {
        int a = x;
        x = y;
        y = a;
    }

    /**
     * This method returns vector which x and y elements are sum of x and y elements of two vectors
     * @param first This parameter represents a vector to add to
     * @param second This parameter also represents a vector to add to
     */
    public static Vector2 AddVectors(Vector2 first, Vector2 second)
    {
        return new Vector2(first.x + second.x, first.y + second.y);
    }

    /**
     * This method returns Vector2 with x = 0 and y = 0
     */
    public static Vector2 Zero()
    {
        return new Vector2(0, 0);
    }

    /**
     * This method returns Vector2 with x = 1 and y = 1
     */
    public static Vector2 One()
    {
        return new Vector2(1, 1);
    }
    
    @Override
    public String toString() {
        
        return "[" + x + "," + y + "]";
    }
}
