package game;


import field.Property;

/**
 * This class represents a deal between two players.
 */
public class Deal
{
  /**
   * The player who wants to buy the property.
   */
  Player buyer;

  /**
   * The owner of the property that the buyer wants to buy.
   */
  Player seller;

  /**
   * The highest proposition made by the buyer.
   */
  int lastPrice;

  /**
   * The property that is in deal.
   */
  Property property;

  /**
    * The property that the buyer wants to exchange with the player.
    */
  Property buyerProperty;

  /**
   * Constructor of a deal.
   * @param buyer  The buyer of the property.
   * @param seller The owner of the property.
   */
  public Deal(Player buyer, Player seller)
  {
    this.buyer = buyer;
    this.seller = seller;
    this.lastPrice = 0;
    this.property = null;
  }

  /**
   * Getter of lastPrice.
   * @return lastPrice.
   */
  public int getLastPrice()
  {
    return this.lastPrice;
  }

  /**
   * Setter of lastPrice.
   * @param lastPrice The new highest proposition.
   */
  public void setLastPrice(int lastPrice)
  {
    this.lastPrice = lastPrice;
  }

  /**
   * Getter of seller.
   * @return The seller.
   */
  public Player getSeller()
  {
    return this.seller;
  }

  /**
   * Getter of buyer.
   * @return The buyer.
   */
  public Player getBuyer()
  {
    return this.buyer;
  }

  /**
   * Getter of property.
   * @return The property.
   */
  public Property getProperty()
  {
    return this.property;
  }

  /**
   * Setter of property.
   * @param property The property.
   */
  public void setProperty(Property property)
  {
    this.property = property;
  }

  /**
    * Setter of buyerProperty.
    * @param property The property.
    */
  public void setBuyerProperty(Property property)
  {
    buyerProperty = property;
  }

  /**
   * The action made when the deal is done.
   */
  public void action()
  {
    buyer.pay(seller,lastPrice);

    if(buyerProperty != null)
    {
      buyerProperty.setOwner(seller);
      seller.addProperty(buyerProperty);
      buyer.removeProperty(buyerProperty);
      seller.autoEquip();
    }
    property.setOwner(buyer);
    buyer.addProperty(property);
    seller.removeProperty(property);
    buyer.autoEquip();
  }
}
