package fr.formiko.model.game;

public class FiscalParadiseSkill extends game.Skill {

    /**
     * The tax reduction of this skill
     */
    private double taxReduction = 0.0;

    /**
     * Indicates if this skill dodges jail
     */
    private boolean dodgeJail = false;

    /**
     * Instantiates a new FiscalParadiseSkill object.
     * It has an id of 3 and has 3 levels of upgrade.
     */
    public FiscalParadiseSkill(){
	super(3,3);
	setLevelCost(300);
    }

    public void updateLevelCost(){
	switch(getCurrentLevel()){
	case 0:
	    setLevelCost(400);
	    break;
	case 1:
	    setLevelCost(600);
	    break;
	case 2:
	    setLevelCost(1000);
	    break;
	case 3:
	    setDodgeJail(true);
	    break;
	}
    }

    /**
     * Gets the value of dodge jail
     * @return the value of the dodgeJail attribute
     */
    public boolean getDodgeJail(){
	return dodgeJail;
    }

    /**
     * Sets the value of dodgeJail
     * @param b the new value of dodgeJail
     */
    private void setDodgeJail(boolean b){
	dodgeJail = b;
    }

    /**
     * Sets the tax reduction with the given tax reduction
     * @param taxReduction the new tax reduction of this skill
     */
    private void setTaxReduction(double taxReduction){
	this.taxReduction = taxReduction;
    }

    /**
     * Computes the new price according to the tax reduction
     * @param price the price to modify
     * @return the new price to pay after the tax reduction
     */
    public int computeNewTax(int price){
	updateTaxReduction();
	price -= (int) price * taxReduction;
	return price;
    }

    /**
     * Updates the tax reduction based on the level
     */
    private void updateTaxReduction(){
	switch(getCurrentLevel()){
	case 1:
	    setTaxReduction(0.5);
	    break;
	case 2:
	    setTaxReduction(0.75);
	    break;
	case 3:
	    setTaxReduction(1.0);
	    setDodgeJail(true);
	}
    }

    public String toString(){
	return "Paradis Fiscal";
    }
}
