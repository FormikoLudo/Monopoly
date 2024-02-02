package fr.formiko.model.game;
import java.util.ArrayList;

/**
 * This class represents an auction.
 */
public class Auction
{
  /**
   * The highest value of the auction.
   */
  private int highestAuction;

  /**
   * The player who has done the highest auction.
   */
  private Player highestAuctioner;

  /**
   * An array list of the players participating to this auction.
   */
  private ArrayList<Player> auctioners;

  /**
   * The index of the actual auctioner.
   */
  private int actualAuctioner;

  /**
   * The constructor of an auction.
   * @param auctioners The players auctioning.
   */
  public Auction(ArrayList <Player> auctioners)
  {
    this.auctioners = auctioners;
  }

  /**
   * Getter of the highest auction.
   * @return The highest auction.
   */
  public int getHighestAuction()
  {
    return this.highestAuction;
  }

  /**
   * Setter of the highest auction.
   * @param highest The new highest auction.
   */
  public void setHighestAuction(int highest)
  {
    this.highestAuction = highest;
  }

  /**
   * Getter of the highest auctioner.
   * @return The highest auctioner.
   */
  public Player getHighestAuctioner()
  {
    return this.highestAuctioner;
  }

  /**
   * Setter of the highest auctioner.
   * @param highest The new highest auctioner.
   */
  public void setHighestAuctioner(Player highest)
  {
    this.highestAuctioner = highest;
  }

  /**
   * Getter of auctioners.
   * @return The array of auctioners.
   */
  public ArrayList<Player> getAuctioners()
  {
    return this.auctioners;
  }

  /**
   * Setter of auctioners.
   * @param auctioners The auctioners.
   */
  public void setAuctioners(ArrayList<Player> auctioners)
  {
    this.auctioners = auctioners;
  }

  /**
   * Getter of the actual auctioner.
   * @return The actual auctioner.
   */
  public int getActualAuctioner()
  {
    return actualAuctioner;
  }

  /**
   * Setter of the actual auctioner.
   * @param actual The actual auctioner.
   */
  public void setActualAuctioner(int actual)
  {
    this.actualAuctioner = actual;
  }

  /**
   * Changes the actual auctioner.
   * @param b The state of the last player, true if he auctioned, and false neither.
   */
  public void nextAuctioner(boolean b)
  {
    if(actualAuctioner + 1 >= auctioners.size())
    {
      actualAuctioner = 0;
    }
    else if(b)
    {
      actualAuctioner ++;
    }
    if(auctioners.get(actualAuctioner) == highestAuctioner && auctioners.size() != 1)
    {
      nextAuctioner(true);
    }
  }


}
