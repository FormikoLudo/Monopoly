package game;

/**
 * This class represents a basic Skill object, used to improve the capacities of a Player.
 */
public abstract class Skill {

    /**
     * The identifier of this Skill object
     */
    private final int ID;

    /**
     * The total number of levels this Skill
     */
    private final int TOTAL_LEVELS;

    /**
     * The current level of this Skill
     */
    private int currentLevel;

    /**
     * The cost to upgrade the level of this Skill
     */
    private int levelCost;

    /**
     * Instantiates a new Skill object with a given identifier and total number
     * of levels of upgrade. The current level of the skill is initialized to 0
     * and the level cost initial value is up to any sub class of Skill.
     * @param ID the identifier of the newly instantiated Skill object
     * @param TOTAL_LEVELS the total number of levels of upgrade of the newly
     * instantiated Skill object
     */
    public Skill(int ID, int TOTAL_LEVELS){
	this.ID = ID;
	this.TOTAL_LEVELS = TOTAL_LEVELS;
	currentLevel = 0;
    }

    /**
     * Increases the level of upgrade of this Skill object
     * if the Skill is not fully upgraded and the given player
     * has enough money to do so
     * @param player the player that would like to increase the 
     * level of this Skill object
     * @return true if the level of this Skill object increased,
     * false otherwise
     */
    public boolean increaseLevel(Player player){
	if(getCurrentLevel() + 1 <= getTOTALLEVELS()){
	    if(player.getMoney() > getLevelCost()){
		int cost = getLevelCost(); //store the cost
		currentLevel++; //increase the current level
		updateLevelCost(); //update level cost after storing the (previous) cost
		return player.pay(cost);
	    } return false;
	} return false;
    }

    /**
     * Updates the level cost of this skill
     */
    public abstract void updateLevelCost();

    /**
     * Checks if the skill is unlocked
     * @return true if the current level of this skill is superior to 0, 
     * false otherwise
     */
    public boolean isSkillUnlocked(){
	return getCurrentLevel() > 0;
    }
    
    /**
     * Gets the identifier of this Skill object
     * @return the identifier of this Skill object
     */
    public int getID(){
	return ID;
    }

    /**
     * Gets the total number of levels of this Skill object
     * @return the total number of levels of this Skill object
     */
    public int getTOTALLEVELS(){
	return TOTAL_LEVELS;
    }
    
    /**
     * Gets the current level of this Skill object
     * @return the current level of this Skill object
     */
    public int getCurrentLevel(){
	return currentLevel;
    }

    /**
     * Gets the level cost of this Skill object
     * @return the level cost of this Skill object
     */
    public int getLevelCost(){
	return levelCost;
    }

    /**
     * Sets the level cost of this skill to the given level
     * cost.
     * @param newLevelCost the new level cost of this skill
     */
    public void setLevelCost(int newLevelCost){
	levelCost = newLevelCost;
    }
}

    
