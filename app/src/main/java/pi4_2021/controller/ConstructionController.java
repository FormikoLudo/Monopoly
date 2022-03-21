package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.HashMap;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import game.Game;
import game.Player;
import field.Domain;
import field.District;

/**
 * This class controlls the Construction functionality
 */
public class ConstructionController {

    /**
     * The game instance
     */
    private Game game;

    /**
     * The player model for this controller
     */
    private Player model;

    /**
     * Stores the district owned by the model
     */
    private ArrayList<District> ownedDistricts;

    /**
     * Maps the domains in the selected district to
     * their name
     */
    private HashMap<String,Domain> mapDomains;

    /**
     * The domain on which the player wants to build
     */
    private Domain buildOnThisDomain;

    /**
     * The district where all buildings will be destroyed
     */
    private District destroyOnThisDistrict;

    /**
     * The button that switches the scene to game gui
     */
    @FXML private Button returnButton;

    /**
     * The menubutton for choosing which of the districts owned by
     * this controller player model to display
     */
    @FXML private MenuButton districtChoiceMenuButton;

    /**
     * The money of the model
     */
    @FXML private Label playerMoneyLabel;

    /**
     * The list of the domains in the district chosen by the player
     */
    @FXML private ListView<String> domainsList;

    /**
     * The name of the selected domain
     */
    @FXML private Label nameLabel;

    /**
     * The construction price of the selected domain
     */
    @FXML private Label constructionPriceLabel;

    /**
     * The number of houses in the selected domain
     */
    @FXML private Label numberHousesLabel;

    /**
     * The hotel of the selected domain
     */
    @FXML private Label hotelLabel;

    /**
     * The rent of the selected domain
     */
    @FXML private Label rentLabel;

    /**
     * The button for building a house or a hotel on the selected domain
     */
    @FXML private Button buildButton;

    /**
     * Allows the current player to destroy ALL of the buildings in the ENTIRE district
     */
    @FXML private Button destroyButton;

    /**
     * Initialize the model data for this controller
     */
    public void initModel(Game game){

	//initialize model data to use
	this.game = game;
	model = game.getCurrentPlayer();
	ownedDistricts = model.getOwnedDistricts();
	mapDomains = new HashMap<>();

	//reset the display on initialization
	resetDisplay();
	updateMoney();

	//add owned districts by this player to the menubutton
	initDistrictChoice();
    }

    /**
     * Returns to the game scene
     */
    @FXML public void returnButtonAction(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Field.fxml"));
	Parent root = (Parent) loader.load();
	FieldController flc = loader.getController();
	flc.initModel(game);
	Scene scene = new Scene(root);
  String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
  scene.getStylesheets().clear();
  scene.getStylesheets().add(path);
	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	window.setScene(scene);
	window.show();
    }

    /**
     * Builds a house or a hotel
     */
    @FXML public void buildButtonAction() {

	//check if the user selected a domain
	if(buildOnThisDomain == null){
	    return;
	}

	//depending on this result, show a dialog box
	buildOnThisDomain.getConstructionFactory().build();

	//either way update the display
	updateSelectedDomain(buildOnThisDomain);
	updateMoney();
    }

    /**
     * Updates the display of the selected domain
     * and sets the value of the domain to build on
     */
    private void updateSelectedDomain(Domain domain){
	resetDisplay();
	buildOnThisDomain = domain;
	nameLabel.setText(domain.getName());
	constructionPriceLabel.setText("" + domain.getConstructionFactory().getBuildPrice());
	numberHousesLabel.setText("" + domain.getConstructionFactory().getNumberOfHouses());
	hotelLabel.setText((domain.getConstructionFactory().getHotel() != null)?
			   domain.getConstructionFactory().getHotel().toString() : "Pas d'hôtel");
	rentLabel.setText("" + domain.getRent());
    }

    /**
     * Updates the money label showing the money of the model player
     */
    private void updateMoney(){
	playerMoneyLabel.setText("" + model.getMoney());
    }

    /**
     * Resets the display of the selected domain
     */
    private void resetDisplay(){
	buildOnThisDomain = null;
	nameLabel.setText("No domain selected...");
	constructionPriceLabel.setText("No domain selected...");
	numberHousesLabel.setText("No domain selected...");
	hotelLabel.setText("No domain selected...");
	rentLabel.setText("No domain selected...");
    }

    /**
     * Initializes the items that can be selected by the player in the
     * districtChoice MenuButton
     */
    private void initDistrictChoice(){
	for(District d : ownedDistricts){
	    districtChoiceMenuButton.getItems().addAll(new DistrictMenuItem(d));
	}
    }

    /**
     * Updates the list of domains display based on the selected districted
     * @param district the selected district
     */
    private void domainsListDisplay(District district){

	ArrayList<String> stringRep = new ArrayList<>();

	for(Domain domain : district.getDomains()){
	    stringRep.add(domain.toString());
	    mapDomains.put(domain.toString(),domain);
	}

	ObservableList<String> domainsListObs = FXCollections.observableArrayList(stringRep);
	domainsList.setItems(domainsListObs);
    }

    /**
     * Recovers selected domain and displays its specifications on the side
     * @param event the mouse event triggered by the click
     */
    @FXML private void domainsListSelectionAction(MouseEvent event){

	//check if the user did not click on one of the displayed domains
	if(domainsList.getSelectionModel().isEmpty()){
	    return;
	}

	//recover selected domain
	String selectedDomain = domainsList.getSelectionModel().getSelectedItems().get(0);
	updateSelectedDomain(mapDomains.get(selectedDomain));
    }

    @FXML private void destroyButtonAction(){

	//check if a district is selected
	if(destroyOnThisDistrict == null){
	    return;
	}

	//otherwise a district is selected
	int repay = destroyOnThisDistrict.destroyBuildings();

	//return money to the current player
	model.receive(repay);

	//update selected domain display and money
	updateSelectedDomain(buildOnThisDomain);
	updateMoney();

    }

    /**
     * This inner class represents a District MenuItem.
     * It is a MenuItem with a district model associated to it.
     * It is used for the update of list of domains list displayed
     * according to the selected District
     */
    private class DistrictMenuItem extends MenuItem {

	/**
	 * The model for this district item
	 */
	District model;

	/**
	 * Instantiates a new DistrictItem and the model associated to it
	 * @param model the model of this district item
	 */
	DistrictMenuItem(District model){
	    super("District " + model.getColor());
	    this.model = model;

	    //when this district is selected
	    setOnAction(event -> {

		    //update the model of the list display
		    domainsListDisplay(this.model);

		    //update the display according to the new model
		    resetDisplay();

		    //initialize the district in which all buildings
		    //will be deleted if the player desires to do so
		    destroyOnThisDistrict = model;
		});
	}
    }
}
