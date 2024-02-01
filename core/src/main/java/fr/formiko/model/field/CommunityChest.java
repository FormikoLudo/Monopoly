package field;

/**
* The community box is an element of the board that gives rhythm to the monopoly
* game. Indeed, when you win the right to draw a card, you then have an action
* associated with the card that will be carried out. This can be a gain of money,
* but also a gain of the right to leave the prison.
*/
public class CommunityChest extends FieldElement{

    /**
     * This table shows the different cards available in the community 
     * box.
     */
    private String[] cards;

    /**
     * This is the constructor of the communityChest.
     * @param location location of the Community Chest.
     */
    public CommunityChest(int location)
    {
	super(location,"Community Chest");
	this.cards= new String[16];
	
	//The following lines indicate the text associated with each of the cards of the community fund.
	this.cards[0] = "remboursement des impôts sur le revenu, recevez 20$";
	this.cards[1] = "erreur de la banque, recevez 200$";
	this.cards[2] = "avancez jusqu'à la case départ(recevez 200$)";
	this.cards[3] = "votre assurance vie vous rapporte. Recevez 100$.";
	this.cards[4] = "c'est votre anniversaire. Recevez 10$ de chaque joueur.";
	this.cards[5] = "vous êtes évalué pour de travaux de voirie: payez 40$ par maison et 115$ par hotel que vous possédez.";
	this.cards[6] = "recevez 25$ d'honoraires de consultation.";
	this.cards[7] = "vous avez gagné le deuxième prix du concours de beauté. recevez 10$";
	this.cards[8] = "frais d'hôpital. payez 100.";
	this.cards[9] = "votre caisse de vacances vous rapporte. recevez 100$.";
	this.cards[10] = "vous héritez de 120$";
	this.cards[11] = "frais de scolarité. payez 50$";
	this.cards[12] = "la vente de vos actions vous rapporte 50$";
	this.cards[13] = "honoraires du médecin. payez 50$.";
	this.cards[14] = "vous êtes libérez de prison. Cette carte peut être conservée jusqu'à ce qu'elle soit utilisée ou échangée";
	this.cards[15] = "allez en prison. allez tout droit en prison. ne passez pas par la case départ, ne recevez pas 200$.";
    }


    /**
     * Gets the cards in this community chest
     * @return the attribute cards
     * */
    public String[] getCards()
    {
	return this.cards;
    }

    /**
     * Sets the cards in this community chest
     * @param cards the new cards in this community chest
     */
    public void setCards( String[] cards)
    {
	this.cards = cards;
    }
}
