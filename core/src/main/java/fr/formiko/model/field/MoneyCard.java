package fr.formiko.model.field;
import fr.formiko.model.game.Player;
/**
 * This class represents the cards that deal with player's money.
 */
public class MoneyCard extends Card
{
        /**
         * The amount of money won or lost by the player.
         * if the value is negative the player wins money, if not the player loses money
         */
        private int value;

        public int getValue()
        {
                return this.value;
        }

        public MoneyCard(String content, int value)
        {
                super(content);
                this.value = value;
        }

        public void use(Player player)
        {
                player.pay(this.value);
        }

        public String toString()
        {
                return (super.toString() + "\nvalue : " + this.value);
        }
}
