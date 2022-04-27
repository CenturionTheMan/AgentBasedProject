public class KONpromitant_Entity extends Entity {
    //CTOR
    public KONpromitant_Entity(EntityType type, Vector2 position) {
        super(type, position);
        super.SetType(EntityType.NORMIK);
    }


    @Override
    public boolean DoMove()
    {
        return false;
    }
}
