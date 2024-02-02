package fr.formiko.model.field;

/**
 * The deluxe tax class represents a case on the field that
 *  when you fall on it, you have to pay the sum of 100$.
 */
public class LuxuryTaxe extends FieldElement
{

    private final int cost;

/**
 * The constructor of LuxuryTaxe class.
 * @param location : The location of Luxury taxe case on the field.
 */
    public LuxuryTaxe(int location, String name, int cost)
{
  super(location,name);
  this.cost = cost;
}

/**
 * Getter of cost.
 * @return : The value of the taxe.
 */
public int getCost(){
  return cost;
}
}
