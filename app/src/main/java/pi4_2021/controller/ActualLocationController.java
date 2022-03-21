package controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

import field.FieldElement;
import field.TrainStation;
import field.LuxuryTaxe;
import field.PublicService;
import field.Domain;
import field.Jail;
import field.GoToJail;
import field.FreeParking;
import field.CommunityChest;
import field.Start;
import field.FortuneWheel;

/**
 * This class is the controller for the improved view
 * of the actual location of the current player
 */
public class ActualLocationController{
    
    /**
     *This VBox is the root pane of actualLocation
     */
    @FXML private VBox content;
    
    /**
     * This Group contains the content VBox
     */
    @FXML private Group fieldElementDisplay;
    
    /**
     * Rectangle representing the color of the card necesary for Domain
     */
    @FXML private Rectangle color;
    
    /**
     * Rectangle representing the entire card
     */
    @FXML private Rectangle card;
    
    /**
     * This label is the name of the card
     */
    @FXML private Label cardName;

    @FXML private Label lineZero;

    /**
     * This label shows the rent amount without constructions
     */
    @FXML private Label firstLine;

    /**
     * This label is used for PublicService cards, TrainStaion and Domain :
     * (price if two PublicService are owned byb the same palyer)
     * (price if one house is built in the domain)
     * (price if two TrainSations are owned byb the same player)
     */
    @FXML private Label secondLine;

    /**
     * This label is used for TrainStaion and Domain :
     * (price if two house is built in the domain)
     * (price if three TrainSations are owned byb the same player)
     */
    @FXML private Label thirdLine;

    /**
     * This label is used for TrainStaion and Domain :
     * (price if three houses are built in the domain)
     * (price if four TrainSations are owned byb the same player)
     */

    @FXML private Label fourthLine;

    /**
     * This label is used for TrainStaion and Domain :
     * (price if two house is built in the domain)
     * (price if three TrainSations are owned byb the same player)
     */
    @FXML private Label fifthLine;
    @FXML private Label sixthLine;
    @FXML private Label seventhLine;

    @FXML private StackPane cardType;
    @FXML private ImageView communityChestView;
    @FXML private ImageView trainStationView;
    @FXML private ImageView waterDistributionView;
    @FXML private ImageView electricityDistributionView;
    @FXML private ImageView luxuryTaxeView;
    @FXML private ImageView goToJailView;
    @FXML private ImageView jailView;
    @FXML private ImageView freeParkingView;
    @FXML private ImageView chanceView;
    @FXML private ImageView startView;
    
    /**
     * This StackPane is used to show the owner's pawn on the top righit han of the card if its owned by someone
     */
    @FXML private StackPane pawn;

    /**
     * This image is showed  on the top righit han of the card if its owned by the player who owns the hat pawn
     */
    @FXML private ImageView pawn1;

    /**
     * This image is showed  on the top righit han of the card if its owned by the player who owns the shoe pawn
     */
    @FXML private ImageView pawn2;

    /**
     * This image is showed  on the top righit han of the card if its owned by the player who owns the car pawn
     */
    @FXML private ImageView pawn3;

    /**
     * This image is showed  on the top righit han of the card if its owned by the player who owns the dog pawn
     */
    @FXML private ImageView pawn4;

    /**
     * This image is showed  on the top righit han of the card if its owned by the player who owns the boat pawn
     */
    @FXML private ImageView pawn5;

    /**
     * This image is showed  on the top righit han of the card if its owned by the player who owns the wheelbarrow pawn
     */
    @FXML private ImageView pawn6;

    @FXML private Button equipBtn;

    /**
     * The parent controller of this controller
     */
    private FieldController fieldController;

    @FXML private HBox buildings;
    @FXML private Rectangle firstHouse;
    @FXML private Rectangle secondHouse;
    @FXML private Rectangle thirdHouse;
    @FXML private Rectangle fourthHouse;
    @FXML private Button outOfJailCard;

    @FXML private ImageView electricityEquiped;
    @FXML private ImageView waterEquiped;
    
    /**
     * Sets the parent controller to the given controller
     * @param main the new parent controller of this controller
     */
    public void transferController(FieldController main){
	fieldController = main;
    }

    /**
     * Updates the display of the actual location
     */
    public void update()
    {
	reset();
	FieldElement actualLocation = fieldController.getModel().getField().getFieldElements()[fieldController.getModel().getCurrentPlayer().getPawn().getLocation()];
	if (actualLocation instanceof Domain)
	    {
		drawBuyableActualLocation((Domain)actualLocation);
	    }
	else if (actualLocation instanceof TrainStation)
	    {
		drawBuyableActualLocation((TrainStation)actualLocation);
	    }
	else if (actualLocation instanceof PublicService)
	    {
		drawBuyableActualLocation((PublicService)actualLocation);
	    }
	else if (actualLocation instanceof LuxuryTaxe)
	    {
		drawActualLocation((LuxuryTaxe)actualLocation);
	    }
	else if (actualLocation instanceof GoToJail)
	    {
		drawActualLocation((GoToJail)actualLocation);
	    }
	else if (actualLocation instanceof Jail)
	    {
		drawActualLocation((Jail)actualLocation);
	    }
	else if (actualLocation instanceof FreeParking)
	    {
		drawActualLocation((FreeParking)actualLocation);
	    }
	else if (actualLocation instanceof Start)
	    {
		drawStart((Start)actualLocation);
	    }
	else if (actualLocation instanceof FortuneWheel)
	    {
		drawActualLocation((FortuneWheel)actualLocation);
	    }
    }

    /**
     * Resets the display of the Actual Location
     */
    private void reset()
    {
	content.setVisible(false);
	color.setVisible(false);
	Rectangle [] buildingsView = {firstHouse, secondHouse, thirdHouse , fourthHouse};
	for (int i = 0; i < buildingsView.length; i++)
	    {
		buildingsView [i].setVisible(false);
	    }
	buildings.setVisible(false);
	pawn.setVisible(false);
	cardType.setVisible(false);
	communityChestView.setVisible(false);
	trainStationView.setVisible(false);
	waterDistributionView.setVisible(false);
	electricityDistributionView.setVisible(false);
	luxuryTaxeView.setVisible(false);
	freeParkingView.setVisible(false);
	startView.setVisible(false);
	goToJailView.setVisible(false);
	jailView.setVisible(false);
	electricityEquiped.setVisible(false);
	waterEquiped.setVisible(false);
	card.setFill(Color.WHITE);
	card.setVisible(false);
	cardName.setText("");
	lineZero.setText("");
	firstLine.setText("");
	secondLine.setText("");
	thirdLine.setText("");
	thirdLine.setAlignment(Pos.CENTER_LEFT);
	fourthLine.setText("");
	fifthLine.setText("");
	sixthLine.setText("");
	seventhLine.setText("");
    }

    /**
     * This method generates a view for a LuxuryTaxe
     *  @param  model the model object
     *  @return       a Group representing the model in GUI
     */
    public void drawActualLocation(LuxuryTaxe model)
    {
	color.setVisible(false);
	content.setVisible(true);
	card.setVisible(true);
	cardType.setVisible(true);
	communityChestView.setVisible(false);
	trainStationView.setVisible(false);
	luxuryTaxeView.setVisible(true);
	cardName.setText(model.getName());
	lineZero.setText("");
	firstLine.setText("");
	secondLine.setText("");
	thirdLine.setText("Vous payez : " + model.getCost());
	thirdLine.setAlignment(Pos.CENTER);
	fourthLine.setText("");
	fifthLine.setText("");
	sixthLine.setText("");
	seventhLine.setText("");
	//   "Luxury Taxe"
    }

    /**
     * This method generates a view for a FortuneWheel
     *  @param  model the model object
     *  @return       a Group representing the model in GUI
     */
    public void drawActualLocation(FortuneWheel model)
    {
	color.setVisible(false);
	content.setVisible(true);
	card.setVisible(true);
	cardType.setVisible(true);
	communityChestView.setVisible(true);
	trainStationView.setVisible(false);
	cardName.setText(fieldController.getModel().getActualLocation().getName());
	lineZero.setText("");
	firstLine.setText("");
	secondLine.setText("");
	thirdLine.setText("");
	thirdLine.setAlignment(Pos.CENTER_LEFT);
	fourthLine.setText("");
	fifthLine.setText("");
	sixthLine.setText("");
	seventhLine.setText("");
    }


    /**
     * This method generates a view for a PublicService
     * @param  model the model object
     * @return       a Group representing the model in GUI
     */
    public void drawBuyableActualLocation(PublicService model)
    {
	if(model.getIsMortgaged())
	    {
		card.setFill(Color.rgb(255,0,0,0.5));
	    }
	if(model.getOwner() != null)
	    setPawn(model.getOwner().getPawn().getId());
	color.setVisible(false);
	content.setVisible(true);
	card.setVisible(true);
	cardType.setVisible(true);
	communityChestView.setVisible(false);
	trainStationView.setVisible(false);
	switch (model.getName())
	    {
	    case "COMPAGNIE DE DISTRIBUTION DES EAUX":
		cardName.setText("COMPAGNIE DES EAUX");
		waterDistributionView.setVisible(true);
		electricityDistributionView.setVisible(false);
		break;
	    case "COMPAGNIE DE DISTRIBUTION D'ÉLECTRICITÉ":
		cardName.setText("COMPAGNIE D'ÉLECTRICITÉ");
		electricityDistributionView.setVisible(true);
		waterDistributionView.setVisible(false);
		break;
	    }
	lineZero.setText("Eau OU Electricité:");

	firstLine.setText("4 fois le résultat des dès");
	secondLine.setText("Eau ET Electricité :");
	thirdLine.setText("10 fois le  résultat des dès");
	thirdLine.setAlignment(Pos.CENTER_LEFT);
	fourthLine.setText("");
	fifthLine.setText("");
	sixthLine.setText("");
	seventhLine.setText("Prix : "  +  model.getPrice());
    }

    /**
     * This method generates a view for a TrainStation
     * @param  model the model object
     * @return       a Group representing the model in GUI
     */
    public void drawBuyableActualLocation(TrainStation model)
    {

	if(model.getIsMortgaged())
	    {
		card.setFill(Color.rgb(255,0,0,0.5));
	    }
	content.setVisible(true);
	if(model.getOwner() != null)
	    setPawn(model.getOwner().getPawn().getId());
	color.setVisible(false);
	card.setVisible(true);
	cardType.setVisible(true);
	trainStationView.setVisible(true);
	jailView.setVisible(false);
	cardName.setText(model.getName());
	lineZero.setText("Loyer actuel : " + model.getRent());
	firstLine.setText("Loyer de base : " + model.getBASERENT());
	secondLine.setText("Une gare : " + model.getBASERENT());
	thirdLine.setText("Deux gares : " + model.getBASERENT() * (int)(Math.pow(2,(1))));
	thirdLine.setAlignment(Pos.CENTER_LEFT);
	fourthLine.setText("Trois gares : " + model.getBASERENT() * (int)(Math.pow(2,(2))));
	fifthLine.setText("Quatre gares : " + model.getBASERENT() * (int)(Math.pow(2,(3))));
	sixthLine.setText("");
	seventhLine.setText("Prix : " + model.getPrice());
    }

    /**
     * This method generates a view for GoToJail
     * @param  model the model object
     * @return       a Group representing the model in GUI
     */
    public void drawActualLocation(GoToJail model)
    {

	content.setVisible(true);
	color.setVisible(false);
	card.setVisible(true);
	cardType.setVisible(true);
	goToJailView.setVisible(true);
	cardName.setText(model.getName());
	lineZero.setText("");
	firstLine.setText("");
	secondLine.setText("");
	thirdLine.setText("Allez en prison !");
	thirdLine.setAlignment(Pos.CENTER);
	fourthLine.setText("");
	fifthLine.setText("");
	sixthLine.setText("");
	seventhLine.setText("");
    }

    /**
     * This method generates a view for Jail
     * @param  model the model object
     * @return       a Group representing the model in GUI
     */
    public void drawActualLocation(Jail model)
    {

	content.setVisible(true);
	color.setVisible(false);
	card.setVisible(true);
	cardType.setVisible(true);
	jailView.setVisible(true);
	cardName.setText("Prison");
	if (fieldController.getModel().getPlayers()[fieldController.getModel().getCurrent()].isInJail())
	    {
                firstLine.setText("Vous êtes en prison!");
                secondLine.setText("Pour sortir vous pouvez :");
                thirdLine.setText("-attendre 3 tours");
                thirdLine.setAlignment(Pos.CENTER_LEFT);
                fourthLine.setText("-payer " + model.getFine());
                fifthLine.setText("-utiliser une carte ");
                sixthLine.setText("\"SORTIE DE PRISON\"");
	    }
	else
	    {
                thirdLine.setText("SIMPLE VISITE !");
                thirdLine.setAlignment(Pos.CENTER_LEFT);
                lineZero.setText("");
                firstLine.setText("");
                secondLine.setText("");
                fourthLine.setText("");
                fifthLine.setText("");
                sixthLine.setText("");
                seventhLine.setText("");
	    }
    }

    public void drawActualLocation(FreeParking model)
    {

	content.setVisible(true);
	color.setVisible(false);
	card.setVisible(true);
	cardType.setVisible(true);
	freeParkingView.setVisible(true);
	cardName.setText("Parc gratuit");
	lineZero.setText("");
	firstLine.setText("");
	secondLine.setText("");
	thirdLine.setText("");//NO IT HAS BEEN REMOVED
	thirdLine.setAlignment(Pos.CENTER_LEFT);
	fourthLine.setText("");
	fifthLine.setText("");
	sixthLine.setText("");
	seventhLine.setText("");
    }

    public void drawStart(Start model)
    {

	content.setVisible(true);
	color.setVisible(false);
	card.setVisible(true);
	cardType.setVisible(true);
	startView.setVisible(true);
	cardName.setText("CASE DÉPART");
	lineZero.setText("");
	firstLine.setText("");
	secondLine.setText("");
	thirdLine.setText("Recevez " + model.getReward());
	thirdLine.setAlignment(Pos.CENTER);
	fourthLine.setText("");
	fifthLine.setText("");
	sixthLine.setText("");
	seventhLine.setText("");
    }

    /**
     * This method generates a view for a Domain
     * @param  model the model object
     * @return       a Group representing the model in GUI
     */
    public void drawBuyableActualLocation(Domain model)
    {
	if(model.getIsMortgaged())
	    {
		card.setFill(Color.rgb(255,0,0,0.5));
	    }
	if(model.electricicyEquiped())
	    {
		electricityEquiped.setVisible(true);
	    }
	if(model.waterEquiped())
	    {
		waterEquiped.setVisible(true);
	    }
	content.setVisible(true);
	color.setVisible(true);
	card.setVisible(true);
	switch(model.getDistrict().getColor())
	    {
	    case 1:// brown
		color.setFill(Color.web("#91462a"));
		break;
	    case 2:// light blue
		color.setFill(Color.web("#bae4fa"));
		break;
	    case 3:// magenta
		color.setFill(Color.web("#d72f86"));
		break;
	    case 4:// orange
		color.setFill(Color.web("#f39000"));
		break;
	    case 5:// red
		color.setFill(Color.web("#e2010f"));
		break;
	    case 6:// yellow
		color.setFill(Color.web("#fded00"));
		break;
	    case 7:// green
		color.setFill(Color.web("#1fa449"));
		break;
	    case 8:// dark blue
		color.setFill(Color.web("#0067b2"));
		break;

	    }

	if(model.getOwner() != null)
	    setPawn(model.getOwner().getPawn().getId());
	cardName.setText(model.getName());

	lineZero.setText("Loyer actuel : " + model.getRent());
	firstLine.setText("Loyer de base : " + model.getBASERENT());
	secondLine.setText("Une maison : " + model.getBASERENT() * 5);
	thirdLine.setText("Deux maisons : " + model.getBASERENT() * 15);
	thirdLine.setAlignment(Pos.CENTER_LEFT);
	fourthLine.setText("Trois maisons : " + model.getBASERENT() * 45);
	fifthLine.setText("Quatre maisons : " + model.getBASERENT() * 65);
	sixthLine.setText("Hôtel : " + model.getBASERENT() * 90);
	seventhLine.setText("Prix : " + model.getPrice());
	showBuildings(model);
    }
    /**
     * This method shows the owner's pawn if the actualLocation is baught
     */
    public void setPawn(int whichPawn)
    {
	pawn.setVisible(true);
	switch (whichPawn)
	    {
	    case 0:
		pawn1.setVisible(true);
		pawn2.setVisible(false);
		pawn3.setVisible(false);
		pawn4.setVisible(false);
		pawn5.setVisible(false);
		pawn6.setVisible(false);
		break;
	    case 1:
		pawn1.setVisible(false);
		pawn2.setVisible(true);
		pawn3.setVisible(false);
		pawn4.setVisible(false);
		pawn5.setVisible(false);
		pawn6.setVisible(false);
		break;
	    case 2:
		pawn1.setVisible(false);
		pawn2.setVisible(false);
		pawn3.setVisible(true);
		pawn4.setVisible(false);
		pawn5.setVisible(false);
		pawn6.setVisible(false);
		break;

	    case 3:
		pawn1.setVisible(false);
		pawn2.setVisible(false);
		pawn3.setVisible(false);
		pawn4.setVisible(true);
		pawn5.setVisible(false);
		pawn6.setVisible(false);
		break;

	    case 4:
		pawn1.setVisible(false);
		pawn2.setVisible(false);
		pawn3.setVisible(false);
		pawn4.setVisible(false);
		pawn5.setVisible(true);
		pawn6.setVisible(false);
		break;

	    case 5:
		pawn1.setVisible(false);
		pawn2.setVisible(false);
		pawn3.setVisible(false);
		pawn4.setVisible(false);
		pawn5.setVisible(false);
		pawn6.setVisible(true);
		break;
	    }
    }

    @FXML public void outOfJailCardAction()
    {
	fieldController.getModel().getPlayers()[fieldController.getModel().getCurrent()].setLeaveJailCard(fieldController.getModel().getPlayers()[fieldController.getModel().getCurrent()].getLeaveJailCard() - 1);
	fieldController.getModel().getPlayers()[fieldController.getModel().getCurrent()].outOfJail();
	outOfJailCard.setVisible(false);
	fieldController.getFieldMenuController().getPayTheFine().setDisable(true);
	fieldController.getFieldMenuController().getPayTheFine().setVisible(false);
	fieldController.getPlayersListController().updatePlayer();
	update();
    }

    public Button getOutOfJailCard()
    {
	return this.outOfJailCard;
    }
    private void showBuildings(Domain model)
    {
	Rectangle [] buildingsView = {firstHouse, secondHouse, thirdHouse , fourthHouse};
	int toShow = model.getConstructionFactory().getNumberOfHouses();
	if (toShow > 0)
	    {
		buildings.setVisible(true);
	    }
	if (model.getConstructionFactory().isHotelBuilt())
	    {
		buildingsView[1].setVisible(true);
	    }
	else
	    {
		for(int i = 0; i < toShow; i++)
		    {
			buildingsView[i].setVisible(true);
			buildingsView[i].setFill(Color.GREEN);
		    }
	    }
    }

    @FXML public void equipBtnAction(ActionEvent event) throws IOException
    {
	// loads the view of the equipement  window
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Equipement.fxml"));

	//instantiates the root node for all elements in the graph of the game scene
	Parent root = (Parent) loader.load();

	//VERY IMPORTANT: load the loader then access its controller
	//recovers the controller of the deal player choice fxml and initializes its data
	EquipementController ec = loader.getController();
	ec.init(fieldController.getModel());

	//instantiates the loan scene with the root node
	Scene newScene = new Scene(root);
	String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
	newScene.getStylesheets().clear();
	newScene.getStylesheets().add(path);

	//recovers stage information
	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

	//sets the scene of the stage to the loan scene
	window.setScene(newScene);

	//shows the stage
	window.show();
    }

    public Button getEquipButton()
    {
	return (this.equipBtn);
    }


}
