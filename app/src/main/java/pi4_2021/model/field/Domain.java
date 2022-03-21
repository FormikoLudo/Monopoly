package field;

import game.Player;
import java.util.ArrayList;

/**
 * A Domain represents a property object that has a rent that has to be
 * payed by an other player if they step by it and the domain is
 * it represents the streets that a player can buy in the Monopoly.
 */
public class Domain extends Property
{

    /**
     * The basic rent of this domain
     */
    private final int BASE_RENT;

    /**
     * The district in which this domain is
     */
    private District district;

    /**
     * The construction factory of this domain
     */
    private ConstructionFactory constructionFactory;

    private ArrayList <Contract> contracts;

    /**
     * The constructor of the class Domain
     * @param price the value of this domain
     * @param percent represents the multiplier used to compute the value of the rent of the domain
     * @param location the location of the domain on the field
     * @param district the district of this domain
     * @param buildPrice the buildPrice for a house or hotel on this domain
     */
    public Domain(String name, int location, int price, int base_rent, int buildPrice, int mortgageValue)
    {
	super(location,name,mortgageValue);
	this.price = price;
	this.BASE_RENT = base_rent;
	this.rent = base_rent;
	this.owner = null;
	this.district = null;
	this.constructionFactory = new ConstructionFactory(this,buildPrice);
  this.contracts = new ArrayList<Contract>();
    }

    /**
     * Sets the price of this Domain
     * @param price the new price of this Domain
     */
    public void setPrice(int price){
	this.price = price;
    }

    /**
     * Gets the rent of this Domain
     * @return the rent of this Domain
     */
    public int getRent()
    {
	//if the property is mortgaged
	if(isMortgaged){

	    //it has no rent
	    return 0;
	}

	//update the value of the rent then return it
	rent = updateRent();

  //update rent according to subscribed Contracts
  double rise = 0;
  if (this.totallyEquiped())
  {
    rise = this.getElectricityContract().getEquipedRentBonus() + this.getWaterContract().getEquipedRentBonus();
  }
  else if (this.waterEquiped())
  {
    rise = this.getWaterContract().getEquipedRentBonus();
  }
  else if (this.electricicyEquiped())
  {

    rise = this.getElectricityContract().getEquipedRentBonus();
  }
  int toAdd = (int)(rent * rise);
	return rent + toAdd;
    }

    /**
     * Sets the rent of this Domain
     * @param the new rent of this Domain
     */
    public void setRent(int rent) {
	this.rent = rent;
    }


    /**
     * Verifies if this domain is in the same District than the given domain
     * @param domain the domain to which we compare the district
     * @return true if this domain is in the same District than the given domain,
     * false otherwise
     */
    public boolean sameGroup( Domain domain )
    {
	return (district.getColor() == domain.district.getColor());
    }

    /**
     * Sets the district of this domain
     * @param district The new district for this domain
     */
    public void setDistrict(District district)
    {
	this.district = district;
    }

    /**
     * Gets the district of this domain
     * @return the district of this domain
     */
    public District getDistrict(){
	return district;
    }

    /**
     * Gets this domain's construction factory
     * @return this domain's construction factory
     */
    public ConstructionFactory getConstructionFactory(){
	return constructionFactory;
    }

    /**
     * Updates the rent of this domain
     */
    private int updateRent(){

	//if this domain has an owner
	if(owner != null){

	    //check if they own the entire district
	    if(owner.ownsDistrict(district)){

		//check if a hotel is built, if so return the correct rent
		if(constructionFactory.isHotelBuilt()){
		    return 90 * BASE_RENT + constructionFactory.getHotel().getBonusRent();
		}

		//recover the number of houses (0 if there are not)
		int nbrOfHouses =
		    constructionFactory.getNumberOfHouses();

		switch(nbrOfHouses){
		case 0:
		    return 2 * BASE_RENT;
		case 1:
		    return 5 * BASE_RENT;
		case 2:
		    return 15 * BASE_RENT;
		case 3:
		    return 45 * BASE_RENT;
		case 4:
		    return 65 * BASE_RENT;
		}
	    }
	    return rent;
	}
	return rent;
    }

    @Override
    public boolean mortgage(){

	//if there are no buildings in the district
	if(district.isEmpty()){
	    return  super.mortgage();
	}
	return false;
    }

    public String toString(){
	return getName();
    }

    public int getBASERENT()
    {
      return BASE_RENT;
    }

    /**
     * Gets the list of contracts of the domain
     * @return the contaracts attribut
     */
    public ArrayList<Contract> getContracts()
    {
      return this.contracts;
    }

    /**
     * This method allows us to know if a WaterContract is installed for the domain.
     * @return true if a WaterContract was found
     */
    public boolean waterEquiped()
    {
      if (this.contracts.size() != 0)
      {
        for (Contract provider : this.contracts)
        {
          if (provider instanceof WaterContract)
          {
            return true;
          }
        }
        return false;
      }
      return false;
    }

    /**
     * This method allows us to know if a ElectricityContract is installed for the domain.
     * @return true if a ElectricityContract was found
     */
    public boolean electricicyEquiped()
    {
      if (this.contracts.size() != 0)
      {
        for (Contract provider : this.contracts)
        {
          if (provider instanceof ElectricityContract)
          {
            return true;
          }
        }
        return false;
      }
      return false;
    }

    public boolean totallyEquiped()
    {
      return this.electricicyEquiped() && this.waterEquiped();
    }

    public boolean notEquiped()
    {
      return ((!this.electricicyEquiped()) && (!this.waterEquiped()));
    }

    public WaterContract getWaterContract()
    {
      if (this.waterEquiped())
      {
        for (Contract contract : this.contracts)
        {
          if (contract instanceof WaterContract)
          {
            return (WaterContract)contract;
          }
        }
      }
      return null;
    }

    public ElectricityContract getElectricityContract()
    {
      if (this.electricicyEquiped())
      {
        for (Contract contract : this.contracts)
        {
          if (contract instanceof ElectricityContract)
          {
            return (ElectricityContract)contract;
          }
        }
      }
      return null;
    }
}
