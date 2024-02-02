package fr.formiko.model.field;

import fr.formiko.model.game.Player;

/**
 * This class represents a Contract which is used to equip Domain objects in order to improve de rent.
 */
public abstract class Contract
{
  /**
   * This player is the costumer who pay the contract (he is the owner of the equiped Domain)
   */
  protected Player customer;
  /**
   * This is the servce provider (WaterProvider or ElectricityProvider) depending on which contract you choose
   */
  protected ServiceProvider provider;
  /**
   * This is the price that the costumer have to pay (once) in oreder to equip a Domain
   */
  protected int installationPrice;
  /**
   * Constructor of a Contract.
   * @param provider          the service provider(WaterProvider or ElectricityProvider) depending on chosen contract
   * @param installationPrice the price to pay in order to equip a domain
   */
  public Contract (ServiceProvider provider, int installationPrice)
  {
    this.provider = provider;
    this.installationPrice = installationPrice;
  }

  /**
   * This method equips the given Domain with a Contract.
   * @param domain the domain to be equiped
   */
  public abstract void install(Domain domain);

  /**
   * Guetter of the provider
   * @return the ServiceProvider object linked to the contract
   */
  public ServiceProvider getProvider()
  {
    return this.provider;
  }

  /**
   * Guetter of the price
   * @return the installationPrice attribut
   */
  public int getPrice()
  {
    return  this.installationPrice;
  }
}
