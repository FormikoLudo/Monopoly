package fr.formiko.model.field;

/**
 * This class represents the public services such as electricity and
 * water that a player can own in order to make other players pay for
 * their "use"
 */
public abstract class PublicService extends Property implements ServiceProvider
{

    /**
     * The dice of the game (needed for the rent)
     */
    private Dice dice;

    /**
     * This attribute will be used by @see WaterProvider and @see ElectricityProvider.
     * It will be used to fix the rising rent multiplier
     */
    protected double equipedRentBonus;

  /**
   * Constructor of a public service.
   * @param name     The name of the public service.
   * @param price    The price to buy it.
   * @param location Its location.
   */
    public PublicService(String name, int price, int location, Dice dice, int mortgageValue)
  {
      super(location, name, mortgageValue);
      this.price = price;
      this.dice = dice;
  }

    @Override
    public int getRent(){

	//if the property is mortgaged
	if(isMortgaged){

	    //then its rent is equal to 0
	    return 0;
	}
	return toPay();
    }

  /**
   * Computes the rent. The rent is computed like so:
   * - if the owner owns 1 public service, the rent is equal to
   * 4 * the result of the dices
   * - if the owner owns 2 public services, the rent is equal to
   * 10 * the result of the dices
   * @param  result The result of the dices roll.
   * @return        The price of the rent.
   */
    private int toPay()
    {
      if (this.getOwner() != null)
      {
              if(this.getOwner().getNumberOfPublicServices() == 1)
              {
                return 4 * dice.getLastRoll();
              }
              else if(this.getOwner().getNumberOfPublicServices() == 2)
              {
		  return 10 * dice.getLastRoll();
	      }
      }
       return 0;
     }

     public boolean allowsContract()
     {
       return this.owner != null;
     }

     public String toString()
    {
      return this.getName();
    }
}
