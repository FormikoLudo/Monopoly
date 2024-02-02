package fr.formiko.model.field;
/**
 * This interface represents a service provider.
 */
public interface ServiceProvider
{
        /**
         * This method makes us know if it's possible to establish a contract
         * @return true if it's possible
         */
        public boolean allowsContract();
        public double getEquipedRentBonus();
}
