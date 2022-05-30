package main.StaticSubclass;
import main.*;

/**
 * This class represents a Gimbaza entity.
 * This entity is crucial for Podbus entities to turn into Gimbus entities.
 * Every Gimbaza randomly changes its position after a given number of rounds.
 */
public class Gimbaza extends Static_Entity{

    private final int roundsToPositionChange = 5;
    private int roundsLeft;

    /**
     * This method defines the position of Gimbaza.
     * @param position This parameter yields the position
     */
    public Gimbaza(Vector2 position) {
        super(position);
        roundsLeft = roundsToPositionChange;
    }

    public Gimbaza() {}

    /**
     * This method is used in regard of Gimbaza entity changing its position every given amount of rounds
     * @param grid This parameter represents map used for the simulation
     */
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
