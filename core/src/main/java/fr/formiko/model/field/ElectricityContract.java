package fr.formiko.model.field;
/**
 * This class repesents the contracts of electricicy that can be installed for a Domain.
 */
public class ElectricityContract extends Contract
{
  /**
   * The constructor of ElectricityContract
   * @param provider the ElectricityProvider linked to the Contract
   */
  public ElectricityContract (ElectricityProvider provider)
  {
    super(provider, 200);
  }

  /**
   * This method installs the ElectricityContract to the given domain
   * @param domain the domain in which we want an ElectricityContract
   */
  public void install(Domain domain)
  {
    if (this.provider.allowsContract())
    {
      if (domain.electricicyEquiped())
      {
        return ;
      }
      if (domain.getOwner().getMoney() - this.getPrice() > 0)
      {
        domain.getOwner().pay(((ElectricityProvider)this.getProvider()).getOwner(), this.getPrice());
        this.customer = domain.getOwner();
        domain.getContracts().add(this);
      }
    }
  }

  /**
   * Guetter of the rent bonus multiplier given by the contract.
   * @return a multiplier to rise the rent price of the domain which has an ElectricityContract
   */
  public double getEquipedRentBonus()
  {
    return this.provider.getEquipedRentBonus();
  }
}
