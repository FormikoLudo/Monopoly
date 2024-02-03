package fr.formiko.model.game;

/**
 * The "Investor" skill allows the player to receive
 * a bonus at the end of each round according to the
 * number of properties they own
 */
public class InvestorSkill extends Skill {

    /**
     * The value of the bonus received at the
     * end of the round
     */
    private int roundBonus = 20 * getCurrentLevel();

    /**
     * Instantiates a new InventorSkill with
     * ID = 2 and 5 levels of upgrade
     */
    public InvestorSkill(){
	//this skill has an id of 2 and its max level is 5
	super(2,5);
	updateLevelCost();
    }

    public void updateLevelCost(){
	setLevelCost((getCurrentLevel() + 1) * 100);
    }

    /**
     * Computes the bonus value and returns it
     * @return the bonus that the player that has this skill
     * will receive at the end of the round
     */
    public int bonus(Player player){
	//update round bonus
	roundBonus = 10 * getCurrentLevel();
	return roundBonus * player.getNumberOfProperties();
    }

    public String toString(){
	return "Talent d'investisseur";
    }
}
