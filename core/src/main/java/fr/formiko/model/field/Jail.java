package fr.formiko.model.field;

/**
 * This class represents the jail and a case which a player to go to jail.
 * A player who falls in the jail case without passing by the "GO TO JAIL" case
 * can continue playing otherwise if he falls in the 'GO TO JAIL' case
 * he dont play for the 3 next rounds. if a player is in jail he can pay 1000$
 * to get out or try to roll doubles.
 */
public class Jail extends FieldElement {

  /**
  * The fine that the player in jail can pay to out immediatly of jail.
  */
  private final int FINE = 1000; //We can change this later

  /**
   * Constructor of Jail
   * @param location : it's the location of the case jail.
   * @param name : it's the name of the case.
   */
    public Jail(int location, String name)
  {
    super(location,name);
  }

  /**
   * Getter of location.
   * @return location
   */
  public int getLocation()
  {
      return super.getLocation();
  }

  /**
   * Getter of name.
   * @return name
   */
  public String getName()
  {
      return super.getName();
  }

  /**
   * Getter of fine
   * @return The value of the fine.
   */
  public int getFine()
  {
    return FINE;
  }
}
