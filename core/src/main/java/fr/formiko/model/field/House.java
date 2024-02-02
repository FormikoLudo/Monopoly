package fr.formiko.model.field;

/**
 * This object represents a House. A house is a building with
 * a selling price
 */
public class House extends Building {

    /**
     * The selling price of this house
     */
    private final int sellingPrice;

    /**
     * Instantiates a new House object with the given build price
     * and selling price
     * @param buildPrice the build price of this new House object
     * @param sellingPrice the selling price of this new House object
     */
    public House(int buildPrice, int sellingPrice){
	super(buildPrice);
	this.sellingPrice = sellingPrice;
    }

    /**
     * Gets the selling price of this House object
     * @return the selling price of this House object
     */
    public int getSellingPrice(){
	return sellingPrice;
    }
}
