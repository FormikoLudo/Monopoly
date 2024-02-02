
package fr.formiko.model.field;
/**
* This enumeration repesents the contracts of electricicy you can choose for a Domain.
*/
public class WaterContract extends Contract
{
  /**
   * The constructor of WaterContract
   * @param provider the WaterProvider linked to the Contract
   */
  public WaterContract (WaterProvider provider)
  {
    super(provider, 200);
  }

  /**
   * This method installs the WaterContract to the given domain
   * @param domain the domain in which we want a WaterContract
   */
  public void install(Domain domain)
  {
    if (this.provider.allowsContract())
    {
      if (domain.getOwner().getMoney() - this.getPrice() > 0)
      {
        domain.getOwner().pay(((WaterProvider)this.getProvider()).getOwner(), this.getPrice());
        this.customer = domain.getOwner();
        domain.getContracts().add(this);
      }
    }
  }

  /**
   * Guetter of the rent bonus multiplier given by the contract.
   * @return a multiplier to rise the rent price of the domain which has an WaterContract
   */
  public double getEquipedRentBonus()
  {
    return this.provider.getEquipedRentBonus();
  }
}
