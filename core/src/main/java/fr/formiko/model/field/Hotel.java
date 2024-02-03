package fr.formiko.model.field;

/**
 * A Hotel object is a House object with a bonus rent
 */
public class Hotel extends House {

    /**
     * The bonus rent of this Hotel
     */
    private int bonusRent;

    /**
     * Instantiates a new Hotel object with the given build price, selling price and
     * bonus rent
     * @param buildPrice the build price of this Hotel object
     * @param sellingPrice the selling price of this Hotel object
     * @param bonusRent the bonus rent of this Hotel object
     */
    public Hotel(int buildPrice, int sellingPrice, int bonusRent){
	super(buildPrice, sellingPrice);
	this.bonusRent = bonusRent;
    }

    /**
     * Gets the bonus rent of this Hotel object
     * @param the bonus rent of this Hotel object
     */
    public int getBonusRent(){
	return bonusRent;
    }

    @Override
    public String toString(){
	return "Hotel (" + getBonusRent() + ")";
    }
}
