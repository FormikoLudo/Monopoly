package fr.formiko.model.game;


/**
 * This class represents a loan.
 */
public class Loan
{
  /**
   * The amount of money the player got.
   */
  private int value;

  /**
   * The number of turns (laps) before the player returns the loan.
   */
  private int numberOfTurns;

  /**
   * The amount of money the player must return.
   */
  private int toPay;

  /**
    * The player that may make a loan to the loaner.
    */
  private Player giver;

  /**
   * Constructor of Loan.
   * @param value         The value of the loan.
   * @param numberOfTurns Number of turns of the loan.
   */
  public Loan(int value, int numberOfTurns)
  {
    this.value = value;
    this.numberOfTurns = numberOfTurns;
    this.toPay = (int)(value + value * (numberOfTurns * 0.1));
    this.giver = null;
  }

  /**
   * Constructor of Loan.
   * @param value         The value of the loan.
   * @param numberOfTurns Number of turns of the loan.
   * @param giver The player who gives the loan.
   */
  public Loan(int value, int numberOfTurns, Player giver)
  {
    this.value = value;
    this.numberOfTurns = numberOfTurns;
    this.toPay = (int)(value + value * (numberOfTurns * 0.1));
    this.giver = giver;
  }

  /**
   * Getter of value.
   * @return value.
   */
  public int getValue()
  {
    return this.value;
  }

  /**
   * Getter of number of turns.
   * @return numberOfTurns.
   */
  public int getNumberOfTurns()
  {
    return this.numberOfTurns;
  }

  /**
   * Setter of number of turns.
   * @param nb The number of turns.
   */
  public void setNumberOfTurns(int nb)
  {
    this.numberOfTurns = nb;
  }

  /**
   * Getter of toPay.
   * @return toPay.
   */
  public int getToPay()
  {
    return this.toPay;
  }

  /**
   * Getter of giver.
   * @return giver.
   */
  public Player getGiver()
  {
    return this.giver;
  }
}
