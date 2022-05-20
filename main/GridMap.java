package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GridMap {
    
    //PROPERTIES
    private static Node[][] grid;


    //GETTERS && SETTERS
    public void SetGrid(Node[][] grid) { this.grid = grid; }
    public Node[][] GetGrid() { return grid; }


    //CTOR
    public GridMap(Vector2 size) {
        grid = new Node[size.x][size.y];
    }


    //METHODS
    public void InitGrid(Vector2 gridSize)
    {
        grid = new Node[gridSize.x][gridSize.y];

        for (int x = 0; x < gridSize.x; x++) {
            for (int y = 0; y < gridSize.y; y++) {
                
                Vector2 pos = new Vector2(x, y);
                grid[x][y] = new Node(pos);
            }
        }
    }

    public void SetUnitsOnMap(int amount, Entity ent, Vector2 gridSize)
    {
        Random rand = new Random();

        for (int i = 0; i < amount; i++) 
        {
            Vector2 r = new Vector2(rand.nextInt(gridSize.x), rand.nextInt(gridSize.y));

            if(grid[r.x][r.y].GetOccupant() == null)
            {
                ent.SetPosition(r);
                grid[r.x][r.y].SetOccupant(ent);
            }
            else
            {
                int x = r.x;
                int y = r.y + 1;
                while (true) {
                    
                    if(y == gridSize.y) { y = 0; x++; }
                    if(x == gridSize.x) x = 0;

                    if(grid[x][y].GetOccupant() == null)
                    {
                        ent.SetPosition(new Vector2(x, y));
                        grid[x][y].SetOccupant(ent);
                        break;
                    }
                    y++;
                }
            }
        }
    }


    public static List<Node> GetNeighbourNodes(Vector2 center, int deepness)
    {
        List<Node> neigh = new ArrayList<Node>();

        for (int i = -deepness; i < deepness + 1; i++) {
            for (int j = -deepness; j < deepness + 1; j++) {
                
                if(center.x + i < 0 || center.x + i >= grid.length - 1) { continue; }
                if(center.y + j < 0 || center.y + j >= grid[i + center.x].length - 1) { continue; }
                if(center.x == i && center.y == j) continue;

                neigh.add(grid[center.x + i][center.y + j]);
            }
        }
        return neigh;
    }
}
