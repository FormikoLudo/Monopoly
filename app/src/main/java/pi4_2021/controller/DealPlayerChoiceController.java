package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;

import javafx.event.ActionEvent;

import javafx.stage.Stage;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.util.ArrayList;

import java.io.IOException;

import game.Game;
import game.Player;
import game.BotPlayer;
import game.Deal;


/**
 * This class is the controller of the general view of the deal.
 */
public class DealPlayerChoiceController
{
  /**
   * A combo box that'll contain the players.
   */
  @FXML private ComboBox players;

  /**
   * A label where we display a message.
   */
  @FXML private Label display;

  /**
   * The buyer's name.
   */
  @FXML private Label buyer;

  /**
   * The game being played
   */
  private Game model;


  /**
   * Initializes the controller.
   * @param model The game being played.
   */
  public void init(Game model)
  {
    this.model = model;
    buyer.setText(model.getPlayers()[model.getCurrent()].getName());

    //Creating an ArrayList of the players.
    ArrayList<Player> choices = new ArrayList<>();
    for(int i = 0; i < model.getPlayers().length; i++)
    {
      if(model.getPlayers()[i].getStillPlaying() && i != model.getCurrent() && model.getPlayers()[i].getNumberOfProperties() > 0 && !(model.getPlayers()[i] instanceof BotPlayer))
      {
        choices.add(model.getPlayers()[i]);
      }
    }

    //Adding the ArrayList of players to the combo box as an ObservableList.
    ObservableList<Player> list = FXCollections.observableArrayList(choices);
    players.setItems(list);
  }

  /**
   * The button associated to the click on the back button.
   * @param  event       Event.
   * @throws IOException in case the fxml isn't found.
   */
  public void backAction(ActionEvent event) throws IOException
  {
    //loads the view of the field window
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Field.fxml"));


    //instantiates the root node for all elements in the graph of the game scene
    Parent root = (Parent) loader.load();

    //VERY IMPORTANT: load the loader then access its controller
    //recovers the controller of the Field fxml and initializes its data
    FieldController flc = loader.getController();
    flc.initModel(model);
    flc.getPlayersListController().updatePlayer();

    //instantiates the field scene with the root node
    Scene fieldScene = new Scene(root);
    String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
    fieldScene.getStylesheets().clear();
    fieldScene.getStylesheets().add(path);

    //recovers stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    //sets the scene of the stage to the end of the game scene
    window.setScene(fieldScene);

    //shows the stage
    window.show();
  }

  /**
   * The button associated to the click on the next button.
   * @param  event       Event.
   * @throws IOException in case the fxml isn't found.
   */
  public void nextAction(ActionEvent event) throws IOException
  {
    //If there is no answer in the combo box.
    if(players.getValue() == null)
    {
      display.setText("Veuillez choisir un joueur");
    }
    else
    {
      //Changing the scene
      // loads the view of the loan window
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DealPropertyChoice.fxml"));

      //instantiates the root node for all elements in the graph of the game scene
      Parent root = (Parent) loader.load();

      //VERY IMPORTANT: load the loader then access its controller
      //recovers the controller of the loan fxml and initializes its data
      DealPropertyChoiceController dpcc = loader.getController();
      dpcc.init(model,new Deal(model.getPlayers()[model.getCurrent()],(Player)(players.getValue())));

      //instantiates the loan scene with the root node
      Scene dealPropertyChoiceScene = new Scene(root);
      String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
      dealPropertyChoiceScene.getStylesheets().clear();
      dealPropertyChoiceScene.getStylesheets().add(path);
      //recovers stage information
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

      //sets the scene of the stage to the loan scene
      window.setScene(dealPropertyChoiceScene);

      //shows the stage
      window.show();
    }
  }

}
