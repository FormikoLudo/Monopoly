package field;

import java.util.ArrayList;

/**
* This class represents a group of Domains.
*/
public class District {

    /**
     * The list of domains this district contains
     */
    private ArrayList<Domain> domains;

    /**
     * ID attribution
     */
    private static int idNum = 1;
    
    /**
     * The color of this district, serves as an id
     */
    private int color = idNum++;

    /**
     * Instantiates a District object with the given color
     * @param c the color of this district
     */
    public District(){
	domains = new ArrayList<>();
    }

    /**
     * Getter for the list of domains in this district
     * @return the list of domains in this district
     */
    public ArrayList<Domain> getDomains(){
	return domains;
    }

    /**
     * Getter for the color of this district
     */
    public int getColor(){
	return color;
    }
    
    /**
     * Adds the given domain to the list of domains in this district
     * @param domain the domain to add to the list of domains in this district
     */
    public void addDomain(Domain domain){
	if(!domains.contains(domain)) {

	    //adds the domain to this district
	    domains.add(domain);

	    //sets this district to the domain's district
	    domain.setDistrict(this);
	}
    }

    /**
     * Checks if the construction is uniform for the given domain
     * @param domain the domain on which its owner would like to build
     * @return true if the construction is uniform, false otherwise
     */
    public boolean isConstructionUniform(Domain domain){

	//the number of houses on the given domain
	int nbrOfHouses = domain.getConstructionFactory().getNumberOfHouses();

	//for each domain in the district
	for(Domain d : domains){

	    //if the number of houses on the domain is inferior to the number
	    //of houses on the given domain
	    if(d.getConstructionFactory().getNumberOfHouses() < nbrOfHouses) {

		//return false as the rule of uniformity would not be respected
		return false;
	    }
	}

	//return true as the uniformity rule is respected
	return true;
    }

    /**
     * Checks if the uniformity rule for hotel is respected : 
     * - to build a hotel, there has to be 4 houses in each domain of the 
     *   district
     * @return true if the rule is respected, false otherwise
     */
    public boolean hotelUniformity(){

	//for each domain in this district
	for(Domain d: domains){

	    //if the number of houses of the domain is not 4
	    if(d.getConstructionFactory().getNumberOfHouses() != 4){

		//return false as there has to be 4 houses in each domain to build a hotel
		return false;
	    }
	}

	//if this point is reached, there are 4 houses in each domain of this district,
	//so return true
	return true;
    }

    /**
     * Browse the entire district and destroys each building.
     * The owner gets the selling price of each building in return.
     * @return the total value of the destruction of each building in this district
     */
    public int destroyBuildings(){
	int total = 0;
	for(Domain domain : domains){
	    total += domain.getConstructionFactory().destroyBuildings();
	}
	return total;
    }

    /**
     * Checks if the district is empty (building wise)
     * @return true if the district is empty, false otherwise
     */
    public boolean isEmpty(){
	for(Domain domain : domains){
	    if(!domain.getConstructionFactory().isEmpty()){
		return false;
	    }
	}
	return true;
    }

    /**
     * Resets the District id attribution
     */
    public static void resetDistrictID(){
	idNum = 1;
    }
}
