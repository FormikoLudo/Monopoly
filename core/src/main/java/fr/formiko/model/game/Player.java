package fr.formiko.model.game;


import fr.formiko.model.field.*;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * This class represents a Player. A Player has a name to identify them, a Pawn they
 * move on the field, a certain amount of money to pay for Domains and pay taxes.
 */
public class Player
{

    /**
     * Represents the name of the player.
     */
    private String name;

    /**
     * Represents the pawn of the player which is moving on the field.
     */
    private Pawn pawn;

    /**
     * Represents the money of the player.
     */
    private int money;

    /**
     * Represents the location of the player on the field
     */
    private int location;

    /**
     * Makes us know if the player is still playing or already lost.
     */
    private boolean stillPlaying;

    /**
     * Makes us know if the player is in jail or not.
     */
    private boolean inJail;

    /**
     * Represents the number of laps still left before going out from jail.
     */
    private int numberOfLaps;

    /**
     * Indicate if a player have a leaveJailCard thanks to communityChest or chance card
     */
    private int leaveJailCard;

    /**
     * The list of properties this player owns
     */
    private ArrayList<Property> properties;

    /**
     * The loan that can be made from the player.
     */
    private Loan loan;

    /**
     * The set of skills of this player
     */
    private HashMap<Integer, Skill> skillsMap;

    /**
     * This method constructs a player.
     * @param name the value to give to the name attribute
     * @param id the id of this player
     */
    public Player(String name, int id)
    {
	this.name = name;
	this.money = 1500;
	this.stillPlaying = true;
	this.pawn = new Pawn (id);
	properties = new ArrayList<>();
	leaveJailCard = 0;
	initializeSkills();
    }

    /**
     * Getter of name
     * @return the name of this player
     */
    public String getName()
    {
	return this.name;
    }
    /**
     * Getter of pawn
     * @return player's pawn
     */
    public Pawn getPawn()
    {
	return (this.pawn);
    }
    /**
     * Getter of money.
     * @method getMoney
     * @return money value
     */
    public int getMoney()
    {
	return this.money;
    }

    /**
     * Setter for money.
     * @param money  The new money value of the player.
     */
    public void setMoney( int money)
    {
	this.money = money;
    }

    public int getNumberOfLaps(){return this.numberOfLaps;}

    /**
     * Setter of the number of laps.
     * @param numberOfLaps The new value of the number of laps.
     */
    public void setNumberOfLaps( int numberOfLaps)
    {
	this.numberOfLaps = numberOfLaps;
    }

    /**
     * Getter of inJail.
     * @return inJail
     */
    public boolean isInJail()
    {
	return this.inJail;
    }

    /**
     * Setter of inJail.
     * @param inJail The state of the player, true if he's in jail and false neither.
     */
    public void setInJail(boolean inJail)
    {
	this.inJail = inJail;
    }

    /**
     * The current player pays the given player a given price if they can.
     * If the current player has unlocked the Negociator skill, the amount
     * to pay is reduced, therefore, the amount that the other player receives
     * is also reduced.
     * @param  player the player that receives the money
     * @param  price  the price this player pays the given player
     * @return true if this player could pay, false otherwise
     */
    public boolean pay(Player player, int price)
    {

	//if the current player has unlocked the Negociator skill, the amount
	//to pay is reduced
	if(hasTheSkillUnlocked(1)){
	    price = ((NegociatorSkill) getSkillByID(1))
		.computeNewPriceToPay(price);
	}

	player.receive(price);
	return pay(price);
    }

    /**
     * This player pays the given price if they have enough money.
     * @param price the price this player has to pay
     * @return true if this player has enough money, false otherwise
     */
    public boolean pay(int price){
      money -= price;
	if(money - price >= 0){
	    return true;
	} return false;
    }

    /**
     * Indicates if this player owns the given domain
     * @param domain the domain that might own this player
     * @return true if this player owns the given domain, false otherwise
     */
    public boolean ownsDomain(Domain domain)
    {
	     return properties.contains(domain);
    }

    /**
     * Indicates if this player owns all the domains in the given district
     * @param district the district containing the domains we want to check if this
     * player owns
     * @return true if this player owns all the domains in the given district, false
     * otherwhise
     */
    public boolean ownsDistrict(District district){
	for(Domain domain : district.getDomains()){
	    if(!ownsDomain(domain)){
		return false;
	    }
	}
	return true;
    }

    /**
     * Getter of stillPlaying
     * @return the value of stillPlaying attribute
     */
    public boolean getStillPlaying()
    {
	return this.stillPlaying;
    }

    /**
     * Setter of stillPlaying
     * @param state The state of the player, if he's still playing or not.
     */
    public void setStillPlaying(boolean state)
    {
	this.stillPlaying = state;
    }

    /**
     * Getter of properties.
     * @return properties
     */
    public ArrayList<Property> getProperties()
    {
	return this.properties;
    }

    /**
     * Setter of properties
     */
    public void setProperties(ArrayList<Property> properties)
    {
	this.properties = properties;
    }

    /**
     * This function calculates the number of train stations that the player owns.
     * @return The number of train stations that the player owns.
     */
    public int getNumberOfTrainStations()
    {
	int num = 0;
	for(Property property : this.properties)
	    {
		if(property instanceof TrainStation)
		    {
			num ++;
		    }
	    }
	return num;
    }


  /**
   * This method moves the player's pawn to its location.
   * @param value The location where the pawn must be moved.
   */
  public void move(int value)
  {
    if(this.pawn.move(value))
    {
      this.money += Start.getReward();
    }
  }

  /**
   * If the player can participate to an auction or not.
   * @param  price The price of the auction.
   * @return       True if he can auction, false neither.
   */
  public boolean canAuction(int price)
  {
    return(stillPlaying && money > price && !(this instanceof BotPlayer));
  }

    /**
     * Adds the given property to this player's list of properties
     * @param p the property to add to this player's list of properties
     */
    public void addProperty(Property p){
	properties.add(p);
    }

    public void removeProperty(Property p)
    {
      properties.remove(p);
    }

    /**
     * This function calculates the number of train stations that the player owns.
     * @return The number of train stations that the player owns.
     */
    public int getNumberOfPublicServices()
    {
      int num = 0;
      for(Property property : this.properties)
      {
        if(property instanceof PublicService)
        {
          num ++;
        }
      }
      return num;
    }

    /**
     * The players goes out of jail.
     */
    public void outOfJail()
    {
      this.numberOfLaps = 0;
      this.inJail = false;
    }

    /**
     * The player goes immediatly to jail.
     */
    public void goToJail()
    {
      this.numberOfLaps = 3;
      this.inJail = true;
      this.pawn.setLocation(10);
    }

    /**
     * Getter of loan.
     * @return The loan.
     */
    public Loan getLoan()
    {
      return this.loan;
    }

    /**
     * Setter of loan.
     * @param loan The loan.
     */
    public void setLoan(Loan loan)
    {
      this.loan = loan;
      receive(loan.getValue());
    }

    /**
     * Makes the player pay his loan if he has an expired one.
     * If this player has the Negociator Skill, the loan payment is reduced.
     */
    public void payLoan()
    {
      if(loan != null)
      {
        if(loan.getNumberOfTurns() == 0)
        {
	    pay(loan.getToPay());
          if(loan.getGiver() != null)
          {
	      loan.getGiver().receive(loan.getToPay());
          }
          this.loan = null;
        }
        else
        {
          loan.setNumberOfTurns(loan.getNumberOfTurns() - 1);
        }
      }
    }

    /**
     * This method gives the player an amount of money
     */
    public void receive(int money)
    {
	this.money += money;
    }

    public String toString()
    {
      return this.name;
    }

     /**
     * Gets all the mortgaged or umortgaged properties of this player
     * @param swap indicates whether to take mortgaged or unmortgaged properties
     * @return an array list containing all the mortgaged
     * properties of this player if swap is true, unmortgaged properties if swap is false
     */
    public ArrayList<Property> getMortgagedOrUnmortgagedProperties(boolean swap){
	ArrayList<Property> list = new ArrayList<>();
	for(Property p : properties){

	    //if swap is true, it will add the mortgaged properties
	    //if swap is false, it will add the unmortgaged properties
	    if(p.getIsMortgaged() == swap) list.add(p);
	}
	return list;
    }


    public void setLeaveJailCard (int b)
    {
      this.leaveJailCard = b;
    }

    public int getLeaveJailCard()
    {
      return this.leaveJailCard;
    }

    /**
     * Gets the location of the player (their pawn)
     * on the board
     * @return an integer representing the position
     * of this palyer's pawn on the field
     */
    public int getLocationOnBoard(){
	return pawn.getLocation();
    }

    /**
     * Checks if this player owns an entire district
     * @return true if this player owns a district,
     * false otherwise
     */
    public boolean ownsAnEntireDistrict(){
	for(Property p : properties){
	    if(p instanceof Domain){
		if(ownsDistrict(((Domain)p).getDistrict())){
		    return true;
		}
	    }
	}
	return false;
    }

    /**
     * Gets all districts owned by this player
     * @return an array list containing the districts owned by this player
     */
    public ArrayList<District> getOwnedDistricts(){
	ArrayList<District> ownedDistricts = new ArrayList<>();
	for(Property p : properties){
	    if(p instanceof Domain){

		//if the district is not in the owned districts list
		//and this player owns it
		if(!ownedDistricts.contains(((Domain) p).getDistrict())
		   && ownsDistrict(((Domain) p).getDistrict())){

		    //add it to the list of owned districts
		    ownedDistricts.add(((Domain) p).getDistrict());
		}
	    }
	}
	return ownedDistricts;
    }

    /**
     * Gets the number of properties this player owns
     * @return the number of properties this player owns
     */
    public int getNumberOfProperties()
    {
      return properties.size();
    }

    /**
     * Restores all properties of this player to the bank
     */
    public void restoreProperties(){
      if(properties.size() > 0)
      {
        for(Property p : properties){
          p.setOwner(null);
        }
      }
    }

    /**
     * Adds a Skill to the set of skills of this player
     * @param skill the skill to add to the set of skills of this player
     */
    private void addSkill(Skill skill){
	skillsMap.put(new Integer(skill.getID()),skill);
    }

    /**
     * Initializes all the skills of this player
     */
    private void initializeSkills(){
	skillsMap = new HashMap<>();

	//add all the skills available to the skillsMap here
	addSkill(new NegociatorSkill());
	addSkill(new InvestorSkill());
	addSkill(new FiscalParadiseSkill());
    }

    /**
     * Checks if this player has unlocked the given skill
     * @param id the identifier of the skill to check
     * @return true if this player has unlocked the given skill
     */
    public boolean hasTheSkillUnlocked(Integer id){
	return (skillsMap.containsKey(id)
		&& skillsMap.get(id).isSkillUnlocked());
    }

    /**
     * Gets the skill map of this player
     * @return the skill map of this player in a HashMap
     */
    public HashMap<Integer, Skill> getSkillsMap(){
	return skillsMap;
    }

    /**
     * Gets the wanted skill by identifier
     * @param id the identifier of the wanted skill
     * @return the wanted skill
     */
    public Skill getSkillByID(Integer id){
	return getSkillsMap().get(id);
    }
    /**
 * This method allows us to know if the Player owns the WaterProvider.
 * @return true if the WaterProvider is in the player's Porperty list
 */
    public boolean ownsWaterProvider()
    {
      for(Property owned : this.properties)
      {
        if (owned instanceof WaterProvider)
        {
          return true;
        }
      }
      return false;
    }

    /**
     * This method allows us to know if the Player owns the ElectricityProvider.
     * @return true if the ElectricityProvider is in the player's Porperty list
     */
    public boolean ownsElectricityProvider()
    {
      for(Property owned : this.properties)
      {
        if (owned instanceof ElectricityProvider)
        {
          return true;
        }
      }
      return false;
    }

    /**
     * Guetter for WaterProvider
     * @return the WaterProvider if it's owned by the Player
     */
     public WaterProvider getWaterProvider()
     {
       if (this.ownsWaterProvider())
       {
         for (Property owned : this.properties)
         {
           if (owned instanceof WaterProvider)
           {
             return (WaterProvider)owned;
           }
         }
       }
       return null;
     }

     /**
      * Guetter for ElectricityProvider
      * @return the ElectricityProvider if it's owned by the Player
      */
     public ElectricityProvider getElectricityProvider()
     {
       if (this.ownsElectricityProvider())
       {
         for (Property owned : this.properties)
         {
           if (owned instanceof ElectricityProvider)
           {
             return (ElectricityProvider)owned;
           }
         }
       }
       return null;
     }

     /**
      * This method equips all the domains of the Player if he owns at least WaterProvider or ElectricityProvider
      */
     public void autoEquip()
     {
       for(Property owned : this.properties)
       {
         if(this.ownsWaterProvider())
         {
           if (owned instanceof Domain)
           {
             if (!((Domain)owned).waterEquiped())
             {
               WaterContract contract = new WaterContract(this.getWaterProvider());
               contract.install((Domain)owned);
             }
           }
         }
         if(this.ownsElectricityProvider())
         {
           if (owned instanceof Domain)
           {
             if (!((Domain)owned).electricicyEquiped())
             {
               ElectricityContract contract = new ElectricityContract(this.getElectricityProvider());
               contract.install((Domain)owned);
             }
           }
         }
       }
     }

     public boolean canEquip(Player electricityOwner, Player waterOwner)
     {
       boolean canEquipWater;
       boolean canEquipElectricity;
       if (this.getNumberOfProperties() == 0)
       {
         return false;
       }

       for (Property owned : this.properties)
       {
         if (owned instanceof Domain)
         {
           canEquipWater = (!((Domain)owned).waterEquiped()) && waterOwner != null;
           canEquipElectricity = (!((Domain)owned).electricicyEquiped()) && electricityOwner != null;
           if(canEquipWater || canEquipElectricity)
           {
           return true;
           }
         }
       }
       return false;
     }
}
