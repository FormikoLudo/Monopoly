package field;
/**
 * This class represents a PublicService which brings electricicy.
 */
public class ElectricityProvider extends PublicService
{
        /**
         * Constructor of ElectricityProvider
         * @param name          the name of the ElectricityProvider
         * @param price         the price if we want to buy it
         * @param location      the location on the game field
         * @param dice          the dice linked to the game
         * @param mortgageValue the amount of money you receve if you mortgage
         */
        public ElectricityProvider(String name, int price, int location, Dice dice, int mortgageValue)
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
