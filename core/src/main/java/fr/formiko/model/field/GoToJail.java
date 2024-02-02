package fr.formiko.model.field;

/**
 * The go to prison class allows you to create a square that will transport the player, when he finds himself on it,
 * into the prison square. You have to stay 3 rounds into Jail except if you pay or you make a double with the dices.
 */
public class GoToJail extends FieldElement{

    /**
     * Constructor of the GoToJail class
     * @param location : it's the location of the case of go to jail.
     */
    public GoToJail(int location, String name)
    {
        super(location,name);
    }


}
