package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import main.StaticSubclass.Egzamin;
import main.StaticSubclass.Piwo;

/**
 * This class defines the gridMap used for the simulation
 */
public class GridMap {
    
    //PROPERTIES
    private static Node[][] grid;


    //GETTERS && SETTERS

    /**
     * This getter is used to return the grid property
     */
    public Node[][] GetGrid() { return grid; }


    //CTOR

    /**
     * This constructor is used to generate a new gridMap
     * @param size This parameter indicates the size of the gridMap
     */
    public GridMap(Vector2 size) {
        grid = new Node[size.x][size.y];
    }


    //METHODS

    /**
     * This method initiates grid size and initialises each node
     * @param gridSize This parameter indicates grid size
     */
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

    /**
     * This method sets a unit on a given position
     * @param pos This parameter represents a point on map where entity will be placed
     * @param unit This parameter represents an instanced Entity object
     */
    public static void PlaceUnitOnMap(Vector2 pos, Entity unit)
    {
        Entity.ChangeAmountOfGivenSubclass(grid[pos.x][pos.y].GetOccupant(), -1);
        Entity.ChangeAmountOfGivenSubclass(unit, 1);

        boolean isLocalOpen = (grid[pos.x][pos.y].GetOccupant() == null || grid[pos.x][pos.y].GetOccupant() instanceof Static_Entity)? true:false;

        grid[pos.x][pos.y].SetOccupant(unit);
        unit.SetPosition(pos);
        if(isLocalOpen == false)unit.IsOpen();
    }

    /**
     * This method returns global position of random node (without any occupant) in grid
     */
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

    /**
     * This method returns the closest position to given target from positions list
     * @param points This parameter lists points to choose from
     * @param target This parameter represents a position to choose the smallest distance to
     */
    public static Vector2 GetTheClosestPointToTargetFromPoints(List<Vector2> points, Vector2 target)
    {
        if(points == null || points.size() < 1)return null;
        
        Vector2 res = points.get(0);

        for (int i = 1; i < points.size(); i++) {
            if(points.get(i).SubtractVector(target).GetLenght() < res.SubtractVector(target).GetLenght())
            {
                res = points.get(i);
            }
        }

        return res;
    }

    /**
     * This method returns closest (free) node to target position from nodes which surrounds given point (center).
     * Nodes occupied by Egzamin or Piwo are treated as free.
     * Will return null if there is no free node in surrounding
     * @param center This parameter represents a point from which surrounding nodes are being chosen
     * @param targetPos This parameter represents a position to choose the smallest distance to
     */
    public static Node GetClosestToPointNodeInUnitSquare(Vector2 center, Vector2 targetPos)
    {
        List<Node> neigh = GridMap.GetNeighbourNodes(center, 1);
        List<Node> sorted = new ArrayList<Node>();

        Node c = null;
        for (int i = 0; i < neigh.size(); i++) {
            c = neigh.get(0);
            for (int j = 1; j < neigh.size(); j++) {
                if(neigh.get(j).GetPosition().SubtractVector(targetPos).GetLenght() < c.GetPosition().SubtractVector(targetPos).GetLenght())
                {
                    c = neigh.get(j);
                }
            }
            sorted.add(c);
            neigh.remove(c);
        }

        for (int i = 0; i < sorted.size(); i++) {
            if(sorted.get(i).GetOccupant() == null) 
            {
                return sorted.get(i);
            }

            if(!targetPos.Compare(sorted.get(i).GetPosition()))
            {
                if(sorted.get(i).GetOccupant() instanceof Egzamin || sorted.get(i).GetOccupant() instanceof Piwo)
                {
                    return sorted.get(i);
                }
            }
        }
        return null;
    }

    /**
     * This method checks whether position is inside a grid's boundaries
     * @param pos This parameter represents a position to be checked
     */
    public static boolean IsOnGrid(Vector2 pos)
    {
        if(pos.x < 0 || pos.x >= grid.length) { return false; }
        if(pos.y < 0 || pos.y >= grid[0].length) { return false; }
        return true;
    }

    /**
     * This method returns position of random free node from nodes in surrounding of node with given position.
     * Nodes occupied by Egzamin or Piwo are treated as free.
     * Returns Vector2 = [0,0] if there is no free node.
     * @param position This parameter represents a point from which surrounding nodes are being chosen
     */
    public static Vector2 GetFreePositionInNeighbourhood(Vector2 position)
    {
        List<Node> neigh = GetNeighbourNodes(position, 1);
        List<Node> filtred = new ArrayList<Node>();
        neigh.forEach( (k) -> {
            if(k.GetOccupant() == null)
            {
                filtred.add(k);
            }
            else if (k.GetOccupant() instanceof Egzamin || k.GetOccupant() instanceof Piwo)
            {
                filtred.add(k);
            }
        }); 

        if(filtred.size() == 0) return new Vector2(0, 0);
        return filtred.get(new Random().nextInt(filtred.size())).GetPosition();
    }

    /**
     * This method returns a list of nodes which surround the node with given coordinates (center).
     * Deepness indicates to which extends nodes as treated as surrounding.
     * @param center This parameter represents a point from which surrounding nodes are being chosen
     * @param deepness This parameter ndicates how many neighbours should be taken into consideration
     */
    public static List<Node> GetNeighbourNodes(Vector2 center, int deepness)
    {
        List<Node> neigh = new ArrayList<Node>();

        for (int i = -deepness; i < deepness + 1; i++) {
            for (int j = -deepness; j < deepness + 1; j++) {
                
                if(center.x + i < 0 || center.x + i >= grid.length) { continue; }
                if(center.y + j < 0 || center.y + j >= grid[i + center.x].length) { continue; }
                if(0 == i && 0 == j) continue;

                neigh.add(grid[center.x + i][center.y + j]);
            }
        }
        return neigh;
    }
}
