package field;

/**
 * This class represents Train Station case which is a specific type of companies
 */
public class TrainStation extends Property
{
    /**
    * The basic rent of this domain
    */
    private final int BASE_RENT = 25;


    /**
     * Constructor of a train station.
     * @param name     The name of the train station.
     * @param price    The price to buy it.
     * @param location Its location.
     */
    public TrainStation(String name, int price, int location, int mortgageValue)
    {
	super(location,name,mortgageValue);
	this.price = price;
  rent = BASE_RENT;
    }

    /**
     * This functions calculates the price to pay according to some rules.
     * @param  numberOfTrainStations the number of train stations the owner of this one have.
     * @return        The value that must be paid.
     */
    public void updateRent()
    {
      if(owner != null)
      {
        rent = BASE_RENT * (int)(Math.pow(2,(owner.getNumberOfTrainStations() - 1)));
      }
    }

    public int getRent()
    {
	//if the property is mortgaged
	if(isMortgaged){

	    //then its rent is equal to 0
	    return 0;
	}

	//otherwise, update the rent and return it
      updateRent();
      return rent;
    }

    public int getBASERENT()
    {
      return BASE_RENT;
    }
}
