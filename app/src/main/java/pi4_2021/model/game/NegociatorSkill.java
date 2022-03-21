package game;

/**
 * The NegociatorSkill class represents the "Negociator" skill
 * that a player can improve. The "Negociator" skill provides the
 * following:
 * A player with this skill will benefit of a negociator talent that
 * will allow them to pay less when stepping on another player's domain.
 * With money, they will be able to improve this ability and pay less
 * less and less
 */
public class NegociatorSkill extends Skill {

    /**
     * The multiplier decreasing the rent to pay
     */
    private int percent = getCurrentLevel() * 5;

    /**
     * Instantiates a new NegociatorSkill object with
     * an identifier equal to 1, initially 10 levels
     * of upgrade and a level cost set to 0
     */
    public NegociatorSkill(){
	super(1,10);
	updateLevelCost();
    }

    @Override
    public void updateLevelCost(){

	//the level cost will increase as the current level does
	setLevelCost(20 * (getCurrentLevel() + 1));
    }

    /**
     * Computes the amount of money a player that has this skill
     * unlocked has to pay.
     * @param price the price to reduce
     * @return the price a player that has this skill unlocked has to pay
     */
    public int computeNewPriceToPay(int price){
	percent = getCurrentLevel() * 2;
	//if the reduction has a decimal part, it is not counted (as reduction is int)
	int reduction = price * percent / 100;
	return price - reduction;
    }

    public String toString(){
	return "Négociateur";
    }
}
