package controller;


import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import java.io.IOException;

import game.Player;
import game.BotPlayer;
import game.Mortgage;
import field.Domain;
import field.GoToJail;
import field.Jail;
import field.LuxuryTaxe;
import field.TrainStation;
import field.PublicService;
import field.Property;
import field.FortuneWheel;
import field.Card;
import game.InvestorSkill;
import game.FiscalParadiseSkill;

/**
 *  This class is the controller of the action menu
 */
public class FieldMenuController
{

    /**
     * The parent controller of this controller
     */
    private FieldController fieldController;

    /**
     * Ends the round
     */
    @FXML private Button endOfRound;

    /**
     * Rolls the dices
     */
    @FXML private Button rollTheDice;

    /**
     * Buys the current property
     */
    @FXML private Button buy;

    /**
     * Does not buy the current property
     */
    @FXML private Button dontBuy;

    /**
     * Pays an amount of money to the bank or to another player.
     */
    @FXML private Button pay;

    /**
     * Allows the player to mortgage one of their properties
     */
    @FXML private Button mortgage;

    /**
     * Pays the bank an amount of money to get out of jail.
     */
    @FXML private Button payTheFine;

    /**
     * Gets a loan from the bank.
     */
    @FXML private Button loan;

    /**
     * Makes the current player deal with any other player
     */
    @FXML private Button deal;

    /**
     * Switches to a new scene where the current player can
     * build houses and hotels
     */
    @FXML private Button build;



    /**
     * Displays the properties of each player
     */
    @FXML private Button properties;

    /**
     * Displays the skills scene
     */
    @FXML private Button skills;

    /**
     * Sets the parent controller to the given controller
     * @param main the new parent controller of this controller
     */
    public void transferController(FieldController main)
    {
	fieldController = main;
    }

    /**
     * This method initializes this controller
     */
    public void init() throws IOException
    {

	Player current = fieldController.getModel().getCurrentPlayer();
      loan.setDisable(false);

	//enable mortgage button if the current player owns properties
	//disable otherwise
	if(current.getProperties().size() > 0){
	    mortgage.setDisable(false);
	} else {
	    mortgage.setDisable(true);
	}

	//enables build button if the current player owns an entire district
	//disables it otherwise
	if(current.ownsAnEntireDistrict()){
	    build.setDisable(false);
	} else {
	    build.setDisable(true);
	}

  if(fieldController.getModel().getCurrentPlayer().canEquip(fieldController.getModel().getElectricityProvider().getOwner(),fieldController.getModel().getWaterProvider().getOwner()))
  {
   fieldController.getActualLocationController().getEquipButton().setDisable(false);
  }
  else
  {
   fieldController.getActualLocationController().getEquipButton().setDisable(true);
  }


	if(current.isInJail())
	    {
		if(current.getLeaveJailCard() > 0)
		    {
			fieldController.getActualLocationController().getOutOfJailCard().setVisible(true);
		    }
		else {
		    fieldController.getActualLocationController().getOutOfJailCard().setVisible(false);
		}

		if(current.getMoney() > ((Jail)fieldController.getModel().getActualLocation()).getFine())
		    {
			payTheFine.setVisible(true);
			payTheFine.setDisable(false);
		    }
	    }
	else
	    {
		fieldController.getActualLocationController().getOutOfJailCard().setVisible(false);
	    }

	if(fieldController.getModel().getDice().getRolled())
	    {
		rollTheDice.setDisable(true);
		action();
	    }
	else
	    {
		rollTheDice.setDisable(false);
		buy.setDisable(true);
		dontBuy.setDisable(true);
		pay.setDisable(true);
	    }
    }

    /**
     * Rolls the dices and updates the model, also enables possible actions when
     * dices are rolled.
     * @param event the ActionEvent linked to the click of this button
     */
    public void rollTheDiceAction(ActionEvent event) throws IOException
    {
      fieldController.getLittleFieldController().unseeDices();
	    Player current = fieldController.getModel().getCurrentPlayer();
      if(!(current instanceof BotPlayer))
      {
        fieldController.getLittleFieldController().getDisplay().setText("");
      }
      fieldController.getModel().getDice().rollTheDices();
      fieldController.getLittleFieldController().drawTheDices(fieldController.getModel().getDice().getDiceRoll());

      fieldController.getActualLocationController().update();

      int doubles = fieldController.getModel().getDice().getCountDoubles();

      if(doubles == 0)
      {
        current.payLoan();
      }

      if(doubles == 3)
      {
	  //if the player has not the ("Fiscal Paradise" skill unlocked and at level 3), they go to Jail
	  if((current.hasTheSkillUnlocked(new Integer(3))
	       && ((FiscalParadiseSkill) current.getSkillByID(new Integer(3))).getDodgeJail())){
	      endOfRound.setDisable(false);
	  } else {

	      current.goToJail();
	      fieldController.getModel().getDice().restartDoublesCount();
	      fieldController.getLittleFieldController().putInJail();
	      fieldController.getActualLocationController().update();
	      fieldController.getPlayersListController().updatePlayer();
	      endOfRound.setDisable(false);
	      loan.setDisable(true);
	      deal.setDisable(true);
	      mortgage.setDisable(true);
	      rollTheDice.setDisable(true);
	  }
	  return;
      }
      //IF THE PLAYER IS IN JAIL
      if(current.isInJail())
      {
        //If the player has spend all his laps in jail or he's done a double he goes out of jail
        if(current.getNumberOfLaps() == 0 || doubles > 0)
        {

          //GOES OUT OF JAIL
          current.outOfJail();
          fieldController.getLittleFieldController().movePawn(fieldController.getModel().getCurrent(),fieldController.getModel().getActualLocation().getLocation());
          fieldController.getActualLocationController().update();
        }
        //We update the number of laps remaining to spend for the player on jail
        else
        {
          current.setNumberOfLaps(current.getNumberOfLaps() - 1);
          endOfRound.setDisable(false);
          rollTheDice.setDisable(true);
          payTheFine.setVisible(false);
          if(current instanceof BotPlayer)
          {
            playBot(event);
          }
          return;
        }

        fieldController.getPlayersListController().updatePlayer();
      }

      //We move the pawn of the player to the right place
      current.move(fieldController.getModel().getDice().result());
      fieldController.getLittleFieldController().movePawn(fieldController.getModel().getCurrent(),fieldController.getModel().getActualLocation().getLocation());
      fieldController.getActualLocationController().update();

      //If it's a Chance card we draw a chance quote and do the action associated to the quote
      //then we update the pawns position on the littleField
      if(fieldController.getModel().getActualLocation() instanceof FortuneWheel)
      {
        fieldController.getPlayersListController().updatePlayer();
        Card chosen = ((FortuneWheel)(fieldController.getModel().getActualLocation())).drawCard();
        if(current instanceof BotPlayer)
        {
          fieldController.getLittleFieldController().getDisplay().setText(fieldController.getLittleFieldController().getDisplay().getText() + "\n" + current.getName() + " a tiré : " + chosen.getContent());
          chosen.use(fieldController.getModel().getCurrentPlayer());
          fieldController.getPlayersListController().updatePlayer();
        }
        else
        {
          fieldController.displayDialog(chosen);
        }
        fieldController.getLittleFieldController().movePawn(fieldController.getModel().getCurrent(),fieldController.getModel().getActualLocation().getLocation());
        // endOfRound.setDisable(false);
        fieldController.getActualLocationController().update();
        fieldController.getPlayersListController().updatePlayer();
      }

      action();

      fieldController.getPlayersListController().updatePlayer();
      fieldController.getActualLocationController().update();
      //We disable the roll the dice button in the end
      rollTheDice.setDisable(true);

      //We unsee the pay the fine button if it's visible
      payTheFine.setVisible(false);

    }

        /**
         * Buys the current property and enables the possible actions
         * when a property is bought.
         * @param event the ActionEvent linked to the click of this button
         */
        public void buyAction(ActionEvent event)
        {
                ((Property)fieldController.getModel().getActualLocation()).buy(fieldController.getModel().getCurrentPlayer());
                if(fieldController.getModel().getCurrentPlayer() instanceof BotPlayer)
                {
                  fieldController.getLittleFieldController().getDisplay().setText(fieldController.getLittleFieldController().getDisplay().getText() + "\n" + fieldController.getModel().getCurrentPlayer().getName() + " a acheté " + ((Property)fieldController.getModel().getActualLocation()).getName());
                }
                //if the player buys the property we try to equip it (if the player owns at least one ServiceProvider)
                fieldController.getModel().getCurrentPlayer().autoEquip();
                if(fieldController.getModel().getCurrentPlayer().canEquip(fieldController.getModel().getElectricityProvider().getOwner(),fieldController.getModel().getWaterProvider().getOwner()))
                {
                  fieldController.getActualLocationController().getEquipButton().setDisable(false);
                }
                fieldController.getPlayersListController().updatePlayer();
                buy.setDisable(true);
                dontBuy.setDisable(true);
                endOfRound.setDisable(false);
        }

        /**
         * Does not buy the current property and enables the possible actions
         * when a property remains untouched
         * @param event the ActionEvent linked to the click of this button
         */
        public void dontBuyAction(ActionEvent event) throws IOException
        {
                fieldController.getPlayersListController().updatePlayer();
                //loads the view of the winning window
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AuctionChoice.fxml"));

                //instantiates the root node for all elements in the graph of the game scene
                Parent root = (Parent) loader.load();

                //VERY IMPORTANT: load the loader then access its controller
                //recovers the controller of the Auction fxml and initializes its data
                AuctionChoiceController acc = loader.getController();
                acc.initModel(fieldController.getModel());

                // instantiates the auction scene with the root node
                Scene auctionScene = new Scene(root);
                String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
                auctionScene.getStylesheets().clear();
                auctionScene.getStylesheets().add(path);
                //recovers stage information
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                //sets the scene of the stage to the end of the game scene
                window.setScene(auctionScene);

                // shows the stage
                window.show();

                buy.setDisable(true);
                dontBuy.setDisable(true);
                endOfRound.setDisable(false);
        }

        /**
         * The player pays whatever they have to pay and enables the possible
         * actions when a player has to pay something
         * @param event the ActionEvent linked to the click of this button
         */
        public void payAction(ActionEvent event)
        {
	    Player current = fieldController.getModel().getCurrentPlayer();
	    if(fieldController.getModel().getActualLocation() instanceof LuxuryTaxe)
                {

		    int taxCost = ((LuxuryTaxe)fieldController.getModel().getActualLocation()).getCost();

		    //if the current player has the "Fiscal Paradise" skill unlocked, the tax to pay changes
		    if(current.hasTheSkillUnlocked(new Integer(3))){
			int newPrice = ((FiscalParadiseSkill)current.getSkillByID(new Integer(3))).computeNewTax(taxCost);
			current.pay(newPrice);
		    } else {
			//Must pay the fine for a luxury taxe
			current.pay(taxCost);
		    }
		    if(current instanceof BotPlayer){
			fieldController.getLittleFieldController().getDisplay()
			    .setText(fieldController.getLittleFieldController().getDisplay().getText() +
				     "\n" + current.getName() + " a payé " +taxCost);
		    }
                }
                else
                {
                        Player owner = ((Property)fieldController.getModel().getActualLocation()).getOwner();
                        int rent = ((Property)fieldController.getModel().getActualLocation()).getRent();
                        if(current instanceof BotPlayer)
                        {
                          fieldController.getLittleFieldController().getDisplay().setText(fieldController.getLittleFieldController().getDisplay().getText() + "\n" + current.getName() + " a payé " + rent + " à " + owner.getName());
                        }
			//if the player cannot pay they loose
                        if(!current.pay(owner,rent)){

			    //the current player looses
			    current.setStillPlaying(false);
			}
                }
                fieldController.getPlayersListController().updatePlayer();
                pay.setDisable(true);
                loan.setDisable(true);
                deal.setDisable(true);
                mortgage.setDisable(true);
                build.setDisable(true);
                endOfRound.setDisable(false);
		properties.setDisable(true);
		skills.setDisable(true);
        }

    /**
     * Ends the round of the current player
     * @param event the ActionEvent linked to the click of this button
     */
     public void endOfRoundAction(ActionEvent event) throws IOException
     {
	 Player current = fieldController.getModel().getCurrentPlayer();
	 //gives the current player its bonus for the Investor skill if it is unlocked
	 if(current.hasTheSkillUnlocked(new Integer(2))){
	     current.receive(((InvestorSkill) current.getSkillByID(new Integer(2))).bonus(current));
	 }

       if(!(current instanceof BotPlayer))
       {
         fieldController.getLittleFieldController().unseeDices();
       }
 	fieldController.getModel().endOfRound();
  current = fieldController.getModel().getCurrentPlayer();
 	fieldController.getPlayersListController().updatePlayer();
 	fieldController.getActualLocationController().update();
 	fieldController.getLittleFieldController().unseeUnplayingPawns();

	//update current player (as model changed)
	current = fieldController.getModel().getCurrentPlayer();

	//initializes the buttons display
	init();

 	if(fieldController.getModel().isFinished())
 	    {
 		winningWindow(event);
    return;
 	    }


 	//If the player has no other loan
 	if(current.getLoan() == null)
 	    {
 		loan.setDisable(false);
 	    }

 	buy.setDisable(true);
 	dontBuy.setDisable(true);
 	endOfRound.setDisable(true);

 	//enable "mortgage" button if currentPlayer owns at least one
 	//property, disable it otherwise
 	if(current.getProperties().size() > 0) {
 	    mortgage.setDisable(false);
 	} else {
 	    mortgage.setDisable(true);
 	}

 	//checks if this player owns an entire district and if so enables the
 	//"build" button
 	if(current.ownsAnEntireDistrict()){
 	    build.setDisable(false);
 	}

     //if the player coming is in jail we propose to pay the fine by activating a button
     if(current.isInJail())
     {
       if(current.getLeaveJailCard() > 0)
       {
         fieldController.getActualLocationController().getOutOfJailCard().setVisible(true);
       }else {
         fieldController.getActualLocationController().getOutOfJailCard().setVisible(false);
       }

       if(current.getMoney() > ((Jail)fieldController.getModel().getActualLocation()).getFine())
       {
         payTheFine.setVisible(true);
         payTheFine.setDisable(false);
       }
       //we also disable the loan button because the player can't take a loan while being in jail
       loan.setDisable(true);
       deal.setDisable(true);
       mortgage.setDisable(true);
     }
     else
     {
       fieldController.getActualLocationController().getOutOfJailCard().setVisible(false);
       payTheFine.setVisible(false);
       deal.setDisable(false);
     }

     rollTheDice.setDisable(false);
     properties.setDisable(false);
     skills.setDisable(false);
     if (fieldController.getModel().getCurrentPlayer().canEquip(fieldController.getModel().getElectricityProvider().getOwner(),fieldController.getModel().getWaterProvider().getOwner()))
     {
       fieldController.getActualLocationController().getEquipButton().setDisable(false);
     }
     else
     {
       fieldController.getActualLocationController().getEquipButton().setDisable(true);
     }

     //If the next player is a bot
     if (current instanceof BotPlayer)
     {
       deal.setDisable(true);
       mortgage.setDisable(true);
       loan.setDisable(true);
       build.setDisable(true);
       payTheFine.setDisable(true);
       fieldController.getActualLocationController().getOutOfJailCard().setDisable(true);
       // rollTheDice.setText("BOT");

       playBot(event);
     }

   }


        /**
         * Gets the parent controller of this controller
         * @return the fieldController attribute (the parent conbtroller)
         */
        public FieldController getParentController(){
                return fieldController;
        }

        /**
         * Getter of loan button.
         * @return loan button.
         */
        public Button getLoan()
        {
                return this.loan;
        }

    /**
     * This function changes the scene at the end of the game.
     * @param event the ActionEvent linked to the click of this button
     * @throws IOException in case the EndOfGame fxml file isn't found.
     */
    public void winningWindow(ActionEvent event) throws IOException
    {
      // loads the view of the winning window
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EndOfGame.fxml"));

      //instantiates the root node for all elements in the graph of the game scene
      Parent root = (Parent) loader.load();

      //VERY IMPORTANT: load the loader then access its controller
      //recovers the controller of the EndOfGame fxml and initializes its data
      EndOfGameController eogc = loader.getController();
      eogc.setModel(fieldController.getModel());

      //instantiates the endOfGame scene with the root node
      Scene endOfGameScene = new Scene(root);
      String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
      endOfGameScene.getStylesheets().clear();
      endOfGameScene.getStylesheets().add(path);

      //recovers stage information
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

      //sets the scene of the stage to the end of the game scene
      window.setScene(endOfGameScene);

      //shows the stage
      window.show();
    }

    /**
     * The action associated to the click on the "pay the fine" button.
     */
    @FXML public void payTheFineAction() throws IOException
    {
      fieldController.getModel().getCurrentPlayer().pay(((Jail)fieldController.getModel().getActualLocation()).getFine());;
      fieldController.getModel().getCurrentPlayer().outOfJail();
      fieldController.getPlayersListController().updatePlayer();
      //sets to visible each button that has to be set visible
      init();
      payTheFine.setVisible(false);
    }

    /**
     * The action associated to the click on the "mortgage" button
     */
    @FXML public void mortgageAction(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MortgagePane.fxml"));
	Parent root = (Parent) loader.load();
	MortgagePaneController controller = loader.getController();
	controller.initModel(new Mortgage(fieldController.getModel().getCurrentPlayer()));
	controller.transferParentController(this);
	Scene newScene = new Scene(root);
  String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
  newScene.getStylesheets().clear();
  newScene.getStylesheets().add(path);
	Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
	window.setScene(newScene);
	window.show();
    }

    /**
     * The action associated to the click on the loan button.
     * @param event the ActionEvent linked to the click of this button
     * @throws IOException in case the fxml file isn't found.
     */
    public void loanAction(ActionEvent event) throws IOException
    {
      // loads the view of the loan window
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoanChoice.fxml"));

      //instantiates the root node for all elements in the graph of the game scene
      Parent root = (Parent) loader.load();

      //VERY IMPORTANT: load the loader then access its controller
      //recovers the controller of the loan fxml and initializes its data
      LoanChoiceController lcc = loader.getController();
      lcc.init(fieldController.getModel());

      //instantiates the loan scene with the root node
      Scene loanScene = new Scene(root);
      String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
      loanScene.getStylesheets().clear();
      loanScene.getStylesheets().add(path);
      //recovers stage information
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

      //sets the scene of the stage to the loan scene
      window.setScene(loanScene);

      //shows the stage
      window.show();
    }

    /**
     * The button associated to the click on the deal button.
     * @param  event       Event.
     * @throws IOException in case the fxml isn't found.
     */
    public void dealAction(ActionEvent event) throws IOException
    {
      // loads the view of the deal player choice window
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DealPlayerChoice.fxml"));

      //instantiates the root node for all elements in the graph of the game scene
      Parent root = (Parent) loader.load();

      //VERY IMPORTANT: load the loader then access its controller
      //recovers the controller of the deal player choice fxml and initializes its data
      DealPlayerChoiceController dpcc = loader.getController();
      dpcc.init(fieldController.getModel());

      //instantiates the loan scene with the root node
      Scene dealScene = new Scene(root);
      String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
      dealScene.getStylesheets().clear();
      dealScene.getStylesheets().add(path);
      //recovers stage information
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

      //sets the scene of the stage to the loan scene
      window.setScene(dealScene);

      //shows the stage
      window.show();
    }

    /**
     * Displays the construction gui
     */
    @FXML public void buildButtonAction(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Construction.fxml"));
	Parent root = (Parent) loader.load();
	ConstructionController cc = loader.getController();
	cc.initModel(fieldController.getModel());
	Scene scene = new Scene(root);
  String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
  scene.getStylesheets().clear();
  scene.getStylesheets().add(path);
	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	window.setScene(scene);
	window.show();
    }

      public Button getPayTheFine()
      {
        return this.payTheFine;
      }

      /**
        * Executes the actions after the roll of the dice
        **/
      public void action()
      {
        Player current = fieldController.getModel().getCurrentPlayer();

        //If the player's position is on case GoToJail
        if(fieldController.getModel().getActualLocation() instanceof GoToJail)
        {
	    //if the player has not ( the "Fiscal Paradise" skill unlocked and at max level), they go to Jail
	    if(!(current.hasTheSkillUnlocked(new Integer(3))
		&& ((FiscalParadiseSkill)current.getSkillByID(new Integer(3))).getDodgeJail())){
		// We send the player to jail (in the model)
		current.goToJail();

		// We put the pawn on the jail case
		fieldController.getLittleFieldController().putInJail();

	    }

	    properties.setDisable(false);
	    skills.setDisable(false);

	    //We enable the endOfRound button
	    endOfRound.setDisable(false);
        }

        //If the player's position is a buyable card
        else if(fieldController.getModel().getActualLocation() instanceof Property)
        {
          //If no one owns the card he can't either choose to buy the card or not
          if(((Property)fieldController.getModel().getActualLocation()).getOwner() == null)
          {
            if (current.getMoney() < ((Property)fieldController.getModel().getActualLocation()).getPrice())
            {
              buy.setDisable(true);
            }
            else {
              buy.setDisable(false);
            }
            dontBuy.setDisable(false);
          }

    // if there is a player who owns the domain that isn't mortgaged he must pay so we enable the pay button
    else if (fieldController.getModel().someoneOwnsTheProperty() && !((Property)fieldController.getModel().getActualLocation()).getIsMortgaged())
        {
      pay.setDisable(false);
        }
    //We enable the end of round button
    else
        {
      endOfRound.setDisable(false);
        }
        }

        //If the player is located on a LuxuryTaxe he must pay the taxe
        else if(fieldController.getModel().getActualLocation() instanceof LuxuryTaxe)
      {
          pay.setDisable(false);
      }
        //If the player is located on start, FreeParking, Jail for a simple visit we don't do anything
        else
                  {
          endOfRound.setDisable(false);
                  }
      }

    /**
     * On click, displays the properties owned by each player
     */
    @FXML public void propertiesAction(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Properties.fxml"));
	Parent root = (Parent) loader.load();
	PropertiesController pc = loader.getController();
	pc.initModel(fieldController.getModel());
	Scene scene = new Scene(root);
  String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
  scene.getStylesheets().clear();
  scene.getStylesheets().add(path);
	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	window.setScene(scene);
	window.show();
    }

    /**
     * On click, displays the skills of the current player
     */
    @FXML public void skillsAction(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Skills.fxml"));
	Parent root = (Parent) loader.load();
	SkillsController sc = loader.getController();
	sc.initModel(fieldController.getModel());
	Scene scene = new Scene(root);
  String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
  scene.getStylesheets().clear();
  scene.getStylesheets().add(path);
	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	window.setScene(scene);
	window.show();
    }

      /**
        * Makes play a bot
        * @param event The event.
        * @throws IOException in case the fxml isn't found.
        **/
      public void playBot(ActionEvent event) throws IOException
      {
        rollTheDiceAction(event);

        if(!pay.isDisable())
        {
         payAction(event);
         endOfRoundAction(event);
        }
        else if (!buy.isDisable() && fieldController.getModel().getCurrentPlayer().getMoney() > 1.25*((Property)fieldController.getModel().getActualLocation()).getPrice())
        {
          buyAction(event);
          endOfRoundAction(event);
        }
        else if(!dontBuy.isDisable())
        {
          dontBuyAction(event);
        }
        else
        {
          endOfRoundAction(event);
        }
      }

    public Button getEndOfRoundButton(){
	return endOfRound;
    }

    public Button getPayButton(){
	return pay;
    }

    public Button getBuyButton(){
	return buy;
    }

    public Button getDontBuyButton(){
	return dontBuy;
    }
}
