package field;

import java.util.ArrayList;

/**
 * This class manages the construction of houses and hotel 
 * on a domain.
 */
public class ConstructionFactory {

    /**
     * The list of houses of the associated domain
     */
    private ArrayList<House> houses;

    /**
     * The hotel of the associated domain
     */
    private Hotel hotel;

    /**
     * The domain managed by this class
     */
    private Domain domain;

    /**
     * The fixed price of build for houses and hotel of the 
     * associated domain
     */
    private final int buildPrice;

    public ConstructionFactory(Domain domain, int buildPrice){
	houses = new ArrayList<>();
	hotel = null;
	this.domain = domain;
	this.buildPrice = buildPrice;
    }

    /**
     * Builds a house or a hotel, depending on which is possible
     * To build a hotel, 4 houses must have been built on the domain
     * @return true if the construction factory managed to build a house
     * or a hotel, false otherwise
     */
    public boolean build(){
	if(canBuildHouse()){
	    buildHouse();
	    return true;
	}
	if(canBuildHotel()){
	    buildHotel();
	    return true;
	}
	return false;
    }
    
    /**
     * Builds a house on the associated domain if possible.
     * The selling price of a house is half its build price
     */
    private void buildHouse(){
	domain.getOwner().pay(buildPrice);
	houses.add(new House(buildPrice, buildPrice / 2));
    }

    /**
     * Builds a hotel on the associated domain if possible.
     * The selling price of a hotel is half its build price
     * and the bonus rent for the construction of a hotel is 
     * 100 * the color of its district (so higher the district,
     * higher the bonus rent)
     */
    private void buildHotel(){
	domain.getOwner().pay(buildPrice);
	hotel = new Hotel(buildPrice, buildPrice / 2, 100 * domain.getDistrict().getColor());
    }

    /**
     * Checks if building a house is possible : 
     * - The owner of the associated domain has to have enough money
     * - The owner has to own the entire district of the associated
     * domain
     * - The owner of the domain has to respect the uniformity rule:
     * -- no more than 4 houses per domain
     * -- there has to be at least as many houses or one less or one more
     *    on each of the domains in the district
     * @return true if its possible to build, false otherwise
     */
    private boolean canBuildHouse(){
	return (domain.getOwner().ownsDistrict(domain.getDistrict())
		&& (domain.getOwner().getMoney() >= buildPrice)
		&& houseUniformity());
    }

    /**
     * Checks if it is possible to build a hotel on the associated domain : 
     * - the limit of hotel per domain is one
     * - the owner of the domain has to have enough money to build it
     * - there has to be 4 houses in each domain of the district ( hotel uniformity rule)
     * @return true if its possible to build a hotel, false otherwise
     */
    private boolean canBuildHotel(){
	return (hotel == null
		&& domain.getOwner().getMoney() > buildPrice
		&& domain.getDistrict().hotelUniformity());
    }

    /**
     * Gets the number of house on the associated domain
     * @return the number of houses on the associated domain
     */
    public int getNumberOfHouses(){
	return houses.size();
    }

    /**
     * Checks if the rule of building uniformity is respected
     * @return true if the rule is respected, false otherwise
     */
    private boolean houseUniformity(){
	return (houses.size() < 4 && domain.getDistrict().isConstructionUniform(domain));
    }

    /**
     * Checks if the hotel is built
     * @return true if the hotel is built, false otherwise
     */
    public boolean isHotelBuilt(){
	return (hotel != null);
    }

    /**
     * Gets the hotel of this construction factory
     * @return the hotel of this construction factory
     */
    public Hotel getHotel(){
	return hotel;
    }

    /**
     * Gets the buildPrice of this ConstructionFactory
     * @return the buildPrice value of this CosntructionFactory
     */
    public int getBuildPrice(){
	return buildPrice;
    }

    /**
     * Gets the last construction made in this construction factory
     * @return the Hotel on the associated domain if there is a hotel,
     * the last house if there is no hotel and houses built, null otherwise
     */
    public Building getLastConstruction(){
	if(isHotelBuilt()){
	    return getHotel();
	} else if(getNumberOfHouses() > 0){
	    return houses.get(houses.size() - 1);
	} else {
	    return null;
	}
    }

    /**
     * Destroys each building in this factory and compoutes the total
     * value of the destruction
     * @return the total value of the destruction of each building in this 
     * factory
     */
    public int destroyBuildings(){
	int total = 0;
	for(House house : houses){
	    total += house.getSellingPrice();
	}
	//empty the house list (by creating a new instance)
	houses = new ArrayList<>();
	if(isHotelBuilt()){
	    total += hotel.getSellingPrice();
	    hotel = null;
	}
	return total;
    }

    /**
     * Checks if there are buildings in this factory
     * @return true if there are not, false otherwise
     */
    public boolean isEmpty(){
	return (houses.size() == 0 && hotel == null);
    }
}
