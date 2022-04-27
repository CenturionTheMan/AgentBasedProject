public class PISsowiec_Entity extends Entity {
    //CTOR
    public PISsowiec_Entity(EntityType type, Vector2 position) {
        super(type, position);
        super.SetType(EntityType.POOROWIEC);
    }


    @Override
    public boolean DoMove()
    {
        return false;
    }
}
