package fr.formiko.model.field;

/**
 * This class represents the dices of the game
 */
public class Dice {

    /**
     * The dice roll
     */
    private int[] diceRoll;

    /**
     * The sum of the dice roll
     */
    private int lastRoll;

    /**
     * Keeps track of the number of doubles rolled
     */
    private int countDoubles;

    private boolean rolled;

    /**
     * Instantiates a Dice object
     * @param numberOfDices the number of dices to roll
     */
    public Dice(int numberOfDices){
	if(numberOfDices < 2){
	    diceRoll = new int[2];
	} else {
	    diceRoll = new int[numberOfDices];
	}
	lastRoll = 0;
	countDoubles = 0;
  rolled = false;
    }

    /**
     * Rolls the dices and keeps track of doubles (if all the dices are
     * equal)
     * @return the total sum of the dice roll
     */
    public int rollTheDices(){
	for(int i = 0; i < diceRoll.length; i++){
	    diceRoll[i] = (int) (6*Math.random() + 1);
	}
	if(isEqual()){
	    countDoubles++;
	} else {
	    restartDoublesCount();
	}
  rolled = true;
	return result();
    }

    /**
     * Computes the result of a dice roll
     * @retrun the total sum of the dice roll
     */
    public int result(){
	int result = 0;
	for(int i = 0; i < diceRoll.length; i++){
	    result += diceRoll[i];
	}
	lastRoll = result;
	return result;
    }

    /**
     * Checks if all dices are equal
     * @return true if all dices are equal, false otherwise
     */
    public boolean isEqual(){
	int firstDice = diceRoll[0];
	for(int i = 1; i < diceRoll.length; i++){
	    if(diceRoll[i] != firstDice) {
		countDoubles = 0;
		return false;
	    }
	}
	return true;
    }

    /**
     * Gets the value of the last dice roll * @return the value of the last dice roll
     */
    public int getLastRoll(){
	return lastRoll;
    }

    /**
     * Gets the number of doubles
     * @return the number of doubles rolled
     */
    public int getCountDoubles(){
	return countDoubles;
    }

    /**
     * Gets the array of rolled dices
     * @return the array of rolled dices
     */
    public int[] getDiceRoll(){
	return diceRoll;
    }

    /**
     * Restarts the count of doubles by setting it to 0
     */
    public void restartDoublesCount(){
	countDoubles = 0;
    }

    public boolean getRolled()
    {
      return this.rolled;
    }

    public void setRolled(boolean rolled)
    {
      this.rolled = rolled;
    }
}
