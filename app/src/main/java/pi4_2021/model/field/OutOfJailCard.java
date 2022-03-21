package field;
import game.Player;


public class OutOfJailCard extends Card
{

        public OutOfJailCard(String content)
        {
                super(content);
        }

        public void use(Player player)
        {
          player.setLeaveJailCard(player.getLeaveJailCard() + 1);
        }
}
