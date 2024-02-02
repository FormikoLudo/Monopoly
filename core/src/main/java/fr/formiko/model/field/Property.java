package fr.formiko.model.field;


import fr.formiko.model.game.Player;

import java.lang.Math;

/**
 * This class is the base class for every buyable field element.
 * A buyable element is represented by a field element that has an
 * owner and a buying price. A buyable element is also mortgageable
 * so in case of debt, the owner of the property can sell this
 * property to the bank and still possess the property but without
 * receiving its rent.
 */
public abstract class Property extends FieldElement {

    /**
     * The owner of this property
     */
    protected Player owner;

    /**
     * The price of this property
     */
    protected int price;

    /**
     * The rent of this property
     */
    protected int rent;

    /**
     * Indicates whether this property is mortgaged or not
     */
    protected boolean isMortgaged = false;

    /**
     * The value given to the owner when this property is mortgaged
     */
    protected int mortgageValue;

    /**
     * Base constructor for a property
     * @param location the location of this property on the field
     * @param name the name of this property
     */
    public Property(int location, String name){
	super(location, name);
    }

    /**
     * Instantiates a property with an additional mortgage value
     * @param location the location of this property on the field
     * @param name the name of this property
     * @param mortgageValue the mortgage value of this property
     */
    public Property(int location, String name, int mortgageValue){
	this(location,name);
	this.mortgageValue = mortgageValue;
    }

    /**
     * Gets the owner of this property
     * @return the player that owns this property
     */
    public Player getOwner(){
	return owner;
    }

    /**
     * Gets the price of this property
     * @return the price of this property
     */
    public int getPrice(){
	return price;
    }

    /**
     * Gets the rent of this property
     * This method must be overriden by all subclasses
     * @return the rent of this property
     */
    public abstract int getRent();

    /**
     * Mortgages this property, so the owner gets its mortgage value
     * but cannot receive money from this property
     */
    public boolean mortgage(){

	//sets this property to be mortgaged
	isMortgaged = true;

	//and the owner receives their money
	owner.setMoney(owner.getMoney() + mortgageValue);

	return true;
    }

    /**
     * Unmortgages this property so the player can receive money from it
     * again, but the player has to payer 10% more of its mortgage value
     * in order to unmortgage it
     */
    public boolean unMortgage(){

	//if the owner can pay the unmortgage price
	if(owner.pay((int)Math.round(mortgageValue * 1.1))){

	    //this property is no longer mortgaged
	    isMortgaged = false;

	    //then returns true
	    return true;
	}

	//otherwise, the player could not pay so return false
	return false;
    }


    /**
     * Gets the mortgage value of this property
     * @return the mortgage value of this property
     */
    public int getMortgageValue(){
	return mortgageValue;
    }

    /**
     * Buys this property if the buyer has enough money
     * @param buyer the buyer of this property
     * @retrun true if the player could buy this property, false
     * otherwise
     */
    public boolean buy(Player buyer){

	//if the capital of the buyer is superior to the price of this
	//property
	if(buyer.getMoney() > price){

	    //adds this property to the buyer's properties
	    buyer.addProperty(this);

	    //sets the new owner of this property to the buyer
	    owner = buyer;

	    //the buyer pays the price of this property
	    buyer.pay(price);

	    //the transaction was succesfull
	    return true;
	}

	//the transaction was not succesfull
	return false;
    }

  /**
   * Buys this property if the buyer has enough money at the amount
   * entered.
   * @param buyer the buyer of the property.
   * @param price The price of the property.
   */
  public void buy(Player buyer, int price)
  {
    //if the capital of the buyer is superior to the price of this
    //property
    if(buyer.getMoney() > price){

        //adds this property to the buyer's properties
        buyer.addProperty(this);

        //sets the new owner of this property to the buyer
        owner = buyer;

        //the buyer pays the price of this property
        buyer.pay(price);
    }
  }

    /**
     * Gets the attribute isMortgaged of this property
     * @return the value of isMortgaged of this property
     */
    public boolean getIsMortgaged(){
	return isMortgaged;
    }

  /**
   * [toString description]
   * @return [description]
   */
  public String toString()
  {
    return getName();
  }

  /**
   * Setter of owner.
   * @param owner The new owner of the property.
   */
  public void setOwner(Player owner)
  {
    this.owner = owner;
  }
}
