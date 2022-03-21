package field;
import game.Player;
public abstract class Card
{
        private String content;
        private int action;
        public String getContent()
        {
                return (this.content);
        }

        public Card(String content)
        {
                this.content = content;
        }
        public abstract void use(Player current);

        public String toString()
        {
                return ("Text : " + this.content);
        }
}
