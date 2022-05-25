package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import main.StaticSubclass.Egzamin;
import main.StaticSubclass.Piwo;

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

    //*Inits grid size and inicialize each node
    //Vector2 gridSize - indicates grid size
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

    //*Setup neighbours for all entities on the grid
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

    //*Sets unit on given position position
    //Vector2 pos - point on map where entity will be placed
    //Entity unit - instanciated Entity object
    public static void PlaceUnitOnMap(Vector2 pos, Entity unit)
    {
        Entity.ChangeAmountOfGivenSubclass(grid[pos.x][pos.y].GetOccupant(), -1);
        Entity.ChangeAmountOfGivenSubclass(unit, 1);

        grid[pos.x][pos.y].SetOccupant(unit);
        unit.SetPosition(pos);
    }

    //*Returns global position of random node (without any occupant) in grid
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

    //*Returns the closest position to given target from positions list
    //List<Vector2> points - list of points to choose from
    //Vector2 target - position to choose the smallest distance to
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

    //*Returns closest (free) node to target position from nodes which surrounds given point (center).
    //*Nodes occupied by Egzamin or Piwo are treated as free. 
    //*Will return null if there is no free node in surrounding
    //Vector2 center - point from which surranding nodes are being choosen
    //Vector2 targetPos - position to choose the smallest distance to
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

    //*Checks whether position is iside grid boundries
    //Vector2 pos - position to check
    public static boolean IsOnGrid(Vector2 pos)
    {
        if(pos.x < 0 || pos.x >= grid.length) { return false; }
        if(pos.y < 0 || pos.y >= grid[0].length) { return false; }
        return true;
    }

    //*Returns position of random free node from nodes in surrounding of node with given position.
    //*Nodes occupied by Egzamin or Piwo are treated as free. 
    //*Returns Vector2 = [0,0] if there is no free node
    //Vector2 position - point from which surranding nodes are being choosen
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


    //*Returns list of nodes which surrounds node with given cords (center)
    //*Deepness indicates to which extends nodes as treated as surranding 
    //*deepness = n: returns list of nodes which distance to center is smaller or equal to sqrt(2*n^2)
    //Vector2 center - point from which surranding nodes are being choosen
    //int deepness - indicates how many neighbours should be taken into consideration
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
