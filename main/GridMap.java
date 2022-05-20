package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GridMap {
    
    //PROPERTIES
    private static Node[][] grid;


    //GETTERS && SETTERS
    public Node[][] GetGrid() { return grid; }


    //CTOR
    public GridMap(Vector2 size) {
        grid = new Node[size.x][size.y];
    }


    //METHODS
    public void InitGrid(Vector2 gridSize) //setup
    {
        grid = new Node[gridSize.x][gridSize.y];

        for (int x = 0; x < gridSize.x; x++) {
            for (int y = 0; y < gridSize.y; y++) {
                
                Vector2 pos = new Vector2(x, y);
                grid[x][y] = new Node(pos);
            }
        }
    }

    //setup neighbours for all entities on the grid
    public void SetupNeighbours()
    {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                
                Entity ent = grid[i][j].GetOccupant();

                if(ent instanceof Active_Entity)
                {
                    Active_Entity act = (Active_Entity)ent;
                    List<Entity> ents = new ArrayList<Entity>();
                    List<Node> nodes = GetNeighbourNodes(act.GetPosition(), act.GetVisionRange()); 

                    for (Node node : nodes) {
                        if(node.GetOccupant() != null) ents.add(node.GetOccupant()); 
                    }

                    act.SetNeighbours(ents);
                }

            }
        }
    }

    //Sets unit on position
    public void PlaceUnitOnMap(Vector2 pos, Entity unit)
    {
        grid[pos.x][pos.y].SetOccupant(unit);
        unit.SetPosition(pos);
    }

    //Returns position of random empty node in grid
    public static Vector2 GetEmptyPositionInMap()
    {
        Random rand = new Random();

        Vector2 r = new Vector2(rand.nextInt(grid.length), rand.nextInt(grid[0].length));

        if(grid[r.x][r.y].GetOccupant() == null)
        {
            return r;
        }
        else
        {
            int x = r.x;
            int y = r.y + 1;
            while (true) {
                
                if(y == grid[0].length) { y = 0; x++; }
                if(x == grid.length) x = 0;

                if(grid[x][y].GetOccupant() == null)
                {
                    return new Vector2(x, y);
                }
                y++;
            }
        }
    }

    //Returns list of nodes which surrounds node with given cords
    public static List<Node> GetNeighbourNodes(Vector2 center, int deepness)
    {
        List<Node> neigh = new ArrayList<Node>();

        for (int i = -deepness; i < deepness + 1; i++) {
            for (int j = -deepness; j < deepness + 1; j++) {
                
                if(center.x + i < 0 || center.x + i >= grid.length - 1) { continue; }
                if(center.y + j < 0 || center.y + j >= grid[i + center.x].length - 1) { continue; }
                if(0 == i && 0 == j) continue;

                neigh.add(grid[center.x + i][center.y + j]);
            }
        }
        return neigh;
    }
}
