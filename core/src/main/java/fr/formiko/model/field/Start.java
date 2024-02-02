package fr.formiko.model.field;

public class Start extends FieldElement {


    /**
     * The reward that the players get after doing a lap
     */
    private static final int reward = 200;

    public static int getReward(){
	return reward;
    }
    public Start(int location, String name){
	super(location, name);
    }
}
