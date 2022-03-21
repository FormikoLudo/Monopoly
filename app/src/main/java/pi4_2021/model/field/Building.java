package field;

/**
 * Base class for buildings
 */
public abstract class Building {

    /**
     * The build price of a building.
     * This represents the amount of money
     * that a player has to pay in order to build
     * this building
     */
    private final int buildPrice;

    /**
     * Instantiates a new Building object with the 
     * given build price
     * @param buildPrice the build price of this Building
     * object
     */
    public Building(int buildPrice){
	this.buildPrice = buildPrice;
    }

    /**
     * Gets the build price of this building
     * @return the build price of this building
     */
    public int getBuildPrice(){
	return buildPrice;
    }
}
