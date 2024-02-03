package fr.formiko.model.field;
import fr.formiko.utils.CSVReader;

import java.util.ArrayList;
import java.lang.Math;
public class FortuneWheel extends FieldElement
{
        private ArrayList <Card> deck;

        public ArrayList<Card> getDeck()
        {
                return (this.deck);
        }

        public void addCard(Card card)
        {
                this.deck.add(card);
        }

        public FortuneWheel(int location)
        {
                super(location,"Roue de La Fortune");
                this.deck = new ArrayList<>();
                CSVReader moneyCardReader = new CSVReader((getClass().getResource("/csv/monopoly_classic/moneyCard.csv")).getPath());
                CSVReader moveCardReader = new CSVReader((getClass().getResource("/csv/monopoly_classic/moveCard.csv")).getPath());
                moneyCardReader.readData();
                moveCardReader.readData();
                for (int i = 1 ; i < moneyCardReader.getData().size();i++)
                {
                        this.addCard(new MoneyCard(moneyCardReader.getData().get(i)[1],Integer.parseInt(moneyCardReader.getData().get(i)[2])));
                }
                for (int j = 1 ; j < moveCardReader.getData().size();j++)
                {
                        this.addCard(new MoveCard(moveCardReader.getData().get(j)[1],Integer.parseInt(moveCardReader.getData().get(j)[2]),Boolean.parseBoolean(moveCardReader.getData().get(j)[3])));
                }
                this.addCard(new OutOfJailCard("Vous pouvez sortir de prison avec cette carte"));
}


public Card drawCard()
{
        int choice = (int)(Math.random() * this.deck.size());
        return (this.deck.get(choice));
}
}
