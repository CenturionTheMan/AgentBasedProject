package main;
public class Static_Entity extends Entity {

    public Static_Entity(Vector2 position) {
        super(position);
    }

    public void RemoveFromGrid(Node[][] grid)
    {
        grid[GetPosition().x][GetPosition().y].SetOccupant(null);
    }
}
