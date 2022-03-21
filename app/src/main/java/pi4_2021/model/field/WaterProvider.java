package field;
/**
 * This class represents a PublicService which brings water.
 */
public class WaterProvider extends PublicService
{
        public WaterProvider(String name, int price, int location, Dice dice, int mortgageValue)
        {
          super(name, price, location,  dice, mortgageValue);
          this.equipedRentBonus = 0.2;
        }
        /**
         * Guetter of the bonus rent multiplier
         * @return the percentage value of the rent increase
         */
        public double getEquipedRentBonus()
        {
          return this.equipedRentBonus;
        }
}
