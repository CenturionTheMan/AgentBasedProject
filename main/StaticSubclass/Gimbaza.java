package main.StaticSubclass;
import main.*;

public class Gimbaza extends Static_Entity{

    private final int roundsToPositionChange = 5;
    private int roundsLeft;

    public Gimbaza(Vector2 position) {
        super(position);
        roundsLeft = roundsToPositionChange;
    }

    public Gimbaza() {}

    
    public void DoMove(Node[][] grid)
    {
        if(!IsOpen()) return;
        if(roundsLeft == 0)
        {
            Vector2 pos = GridMap.GetEmptyPositionInMap();
            ChangePosition(pos, grid);
            roundsLeft = roundsToPositionChange;
        }
        else
        {
            roundsLeft--;
        }
    }
}
