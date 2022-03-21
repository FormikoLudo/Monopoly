package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;

import game.Game;
import game.Player;
import field.Property;
import field.Domain;

public class PropertiesController {

    /**
     * The model for this controller
     */
    private Game model;

    /**
     * The selected player
     */
    private Player selectedPlayer;

    /**
     * The return button
     */
    @FXML private Button returnButton;

    /**
     * The menu button for player selection
     */
    @FXML private MenuButton playerChoiceMenuButton;

    /**
     * The name label of the selected player
     */
    @FXML private Label nameLabel;

    /**
     * The list view of the properties to display
     */
    @FXML private ListView<String> propertiesListView;

    /**
     * Initializes the model for this controller
     * @param model the game model for this controller
     */
    public void initModel(Game model){
	this.model = model;
	initPlayerSelectionChoice();
    }

    /**
     * Returns to the field gui
     * @param event the ActionEvent triggerd by the click
     */
    @FXML private void returnButtonAction(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Field.fxml"));
	Parent root = (Parent) loader.load();
	FieldController flc = loader.getController();
	flc.initModel(model);
	Scene scene = new Scene(root);
  String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
  scene.getStylesheets().clear();
  scene.getStylesheets().add(path);
	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	window.setScene(scene);
	window.show();
    }

    /**
     * Displays the selected player properties
     */
    private void displayProperties(){
	nameLabel.setText("" + selectedPlayer.getName());
	propertiesListView.getItems().clear();
	if(!selectedPlayer.getProperties().isEmpty()){
	    for(Property p : selectedPlayer.getProperties()){
		if(p instanceof Domain){
		    propertiesListView.getItems().add(p.toString() + " (in District " + ((Domain) p).getDistrict().getColor() + ")");
		} else {
		    propertiesListView.getItems().add(p.toString());
		}
	    }
	}
    }

    /**
     * Initializes the player selection choice
     */
    private void initPlayerSelectionChoice(){
	Player[] players = model.getPlayers();
	for(int i = 0; i < players.length; i++){
	    if(players[i].getStillPlaying()){
		playerChoiceMenuButton.getItems().addAll(new PlayerMenuItem(players[i]));
	    }
	}
    }

    /**
     * Represents a menu item associated to player model
     */
    private class PlayerMenuItem extends MenuItem {

	/**
	 * The player associated to this menu item
	 */
	Player model;

	/**
	 * Instantiates a player menu item
	 * @param model the player model
	 */
	PlayerMenuItem(Player model){
	    super(model.getName());
	    this.model = model;

	    setOnAction(e -> {
		    selectedPlayer = model;
		    displayProperties();
		});
	}
    }
}
