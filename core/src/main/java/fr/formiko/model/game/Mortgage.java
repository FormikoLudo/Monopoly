package fr.formiko.model.game;


import fr.formiko.model.field.Property;

import java.util.ArrayList;
import java.lang.Math;

/**
 * This class represents the ability to mortgage properties
 */
public class Mortgage {

    /**
     * The player who wants to mortgage properties
     */
    private Player player;

    /**
     * Instantiates a new Mortgage object with the
     * given player
     * @param player the new player who wants to mortgage
     * properties
     */
    public Mortgage(Player player){
	this.player = player;
    }

    /**
     * Gets the mortgaged properties of this mortgage player
     * @return the mortgaged properties of this mortgage player
     */
    public ArrayList<Property> getMortgagedProperties(){
	return getSelectedProperties(true);
    }

    /**
     * Gets the unmortgaged properties of this mortgage player
     * @return the unmortgaged properties of this mortgage player
     */
    public ArrayList<Property> getUnmortgagedProperties(){
	return getSelectedProperties(false);
    }

    /**
     * Gets all the mortgaged or unmortgaged properties of this player
     * @param swap indicates whether to take mortgaged or unmortgaged properties
     * @return an array list containing all the mortgaged
     * properties of this player if swap is true, unmortgaged properties if swap is false
     */
    private ArrayList<Property> getSelectedProperties(boolean swap){
	ArrayList<Property> list = new ArrayList<>();
	for(Property p : player.getProperties()){

	    //if swap is true, it will add the mortgaged properties
	    //if swap is false, it will add the unmortgaged properties
	    if(p.getIsMortgaged() == swap) list.add(p);
	}
	return list;
    }

    /**
     * Puts inside a String object the mortgage description
     * of the given property
     * @param p the property to get the description from
     * @return the mortgage description of the property in a String
     */
    public String propertyMortgageDescription(Property p){
	return p.getName() + " (" + p.getMortgageValue() + ")";
    }

    /**
     * Puts inside a String object the unmortgage desciption of the
     * given property
     * @param p the property to get the description from
     * @return the unmortgage description of the property in a String
     */
    public String propertyUnmortgageDescription(Property p){
	return p.getName() + " (" + ((int)Math.round(p.getMortgageValue() * 1.1)) + ")";
    }
}
