package fr.formiko.model.field;

import fr.formiko.model.game.Player;

/**
 * This class represents the cards that deal with player's money.
 */
public class MoveCard extends Card
{
        /**
         * The location in which we bring the player.
         */
        private int value;

        private boolean relative;
        public int getValue()
        {
                return this.value;
        }

        public MoveCard(String content, int value, boolean relative)
        {
                super(content);
                this.value = value;
                this.relative = relative;
        }

        public void use(Player player)
        {
          int lastLocation = player.getPawn().getLocation();
                if (relative)
                {
                        if (this.value < 0)
                        {
                                // player.setLocation(((player.getLocation() + this.value) + getLength()) % Field.getLength());
                                player.getPawn().setLocation(((player.getPawn().getLocation() + this.value) + Field.getLength()) % Field.getLength());
                        }
                        else
                        {
                                // player.setLocation((player.getLocation() + this.value) % Field.getLength() );
                                player.getPawn().setLocation((player.getPawn().getLocation() + this.value) % Field.getLength());
                        }
                }
                else
                {
                // player.setLocation(this.value);
                player.getPawn().setLocation(this.value);
                }
          if(player.getPawn().getLocation() <= lastLocation)
          {
            player.setMoney(player.getMoney() + Start.getReward());
          }
        }

        public String toString()
        {
                return (super.toString() + "\nvalue : " + this.value);
        }
}
