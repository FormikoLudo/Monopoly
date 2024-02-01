package game;

import field.Field;
import field.FieldElement;
import field.Dice;
import field.Property;
import field.Domain;
import field.ElectricityProvider;
import field.WaterProvider;


/**
* This class is the actual game, it links all object in order to play a game of Monopoly.
*/
public class Game
{

  /**
  * The players in this game
  */
  private Player[] players;

  /**
  * Number of doubles of a player in row
  */
  private int numberOfDouble = 0;

  /**
  * The field where they play
  */
  private Field field;

  /**
  * Index of the current player.
  */
  private int current = 0;

  /**
   * The field element on which is located the current player.
   */
  private FieldElement actualLocation;



    private Dice dice;
/**
* Getter of current.
*
* @return the value of current
*/
public int getCurrent() {
  return (this.current);
}


/**
 * Getter of actual location.
 * @return The actual location.
 */
public FieldElement getActualLocation()
{
        this.actualLocation = field.get(players[current].getPawn().getLocation());
  return (this.actualLocation);
}

/**
 * Constructor of a Game.
 * @param field the field where this game is taking place
 * @param nb_dices The number of dices of the game.
 */
    public Game(Player[] players, int nb_dices)
    {
	dice = new Dice(nb_dices);
	this.field = new Field(dice);
	this.players = players;
    }

    public Game(){}

    /**
     * Gets the players of this game
     * @return the array of players in this game
     */
    public Player[] getPlayers() {
	return (this.players);
    }

    /**
     * Gets the field of this game
     * @return the field of the game
     */
    public Field getField() {
	return (this.field);
    }

    /**
     * Getter of the current player
     * @return an instance of the current player
     */
    public Player getCurrentPlayer(){
	return players[current];
    }

    /**
     * Getter of dice.
     * @return The dice.
     */
    public Dice getDice()
    {
      return (this.dice);
    }

    /**
     * Verifies if the game is finished or not.
     *
     * @return true if this game is finished, false otherwise
     */
    public boolean isFinished() {
	return (nbOfPlayersPlaying() < 2);
    }

    /**
     * Counts the number of players that are still playing.
     *
     * @return the number of players that are still playing
     */
    public int nbOfPlayersPlaying() {
	int s = 0;
	for (int i = 0; i < this.players.length; i++) {
	    if (this.players[i].getStillPlaying()) {
		s++;
	    }
	}
	return s;
    }

    /**
     * Ends the current round, passes to the next player
     */
    public void endOfRound()
    {
      dice.setRolled(false);
	//if the current player finishes the round without any money
	//they loose
	if(getCurrentPlayer().getMoney() <= 0){
	    //they do not play anymore
	    getCurrentPlayer().setStillPlaying(false);

	    //their properties are restored
	    getCurrentPlayer().restoreProperties();

    }

    if(dice.getCountDoubles() == 0){
      this.current = (this.current + 1)%(this.players.length);
    }

	    //while there are players that do not play
	  while(!getCurrentPlayer().getStillPlaying()){
		//go to the next one
		current = (current+1)%(players.length);
	    }

    }

    /**
     * Lets us know if someone other than the current player owns the property.
     * @return true if the owner exists and isn't the current player.
     */
    public boolean someoneOwnsTheProperty()
    {
      Player owner =  ((Property)getActualLocation()).getOwner();
      return (owner != null && owner != this.players[current]);
    }


    /**
     * Gets the winner of the game.
     * @return The winner of the game.
     */
    public Player getGameWinner()
    {
      for(int i = 0; i < players.length; i++)
      {
        if(players[i].getStillPlaying())
        {
          return players[i];
        }
      }
      return null;
    }

    public ElectricityProvider getElectricityProvider()
    {
      for(int i = 0; i < field.getFieldElements().length; i++)
      {
        if(field.getFieldElements()[i] instanceof ElectricityProvider)
        {
          ElectricityProvider ep = (ElectricityProvider)field.getFieldElements()[i];
          return ep;
        }
      }
      return null;
    }

    public WaterProvider getWaterProvider()
    {
      for(int i = 0; i < field.getFieldElements().length; i++)
      {
        if(field.getFieldElements()[i] instanceof WaterProvider)
        {
          WaterProvider ep = (WaterProvider)field.getFieldElements()[i];
          return ep;
        }
      }
      return null;
    }


}
