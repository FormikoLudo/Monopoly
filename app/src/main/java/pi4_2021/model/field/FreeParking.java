package field;

/**
 * The free parking class represents a case on the field which, when we are on it,
 * we are given the choice to either play normally and therefore continue buying and
 * selling property, or to stay on the free parking case during a turn and therefore
 * skip your turn and not play.
 */
public class FreeParking extends FieldElement
{

    /**
     * Constructor of the free parking class.
     * @param location
     */
    public FreeParking(int location,String name)
    {
	super(location,name);
    }
}
