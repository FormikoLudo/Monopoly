package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import java.io.IOException;

import game.Player;
import game.BotPlayer;
import game.Game;
/**
   This class is the controller for the view at the end of the game
 */
public class EndOfGameController{

  /*
  A label where the winner's name is displayed on the window
   */
  @FXML Label winner;

  /*
  The game that has ended
   */
  Game model;

  /*
  Initializes the model for this controller
   */
  public void setModel(Game model)
  {
    this.model = model;
    winner.setText(model.getGameWinner().getName() + " a gagné la partie"); // TO SEE IN GAME CLASS WINNER OF THE GAME
  }

  /*
  The action done when we click on quit button
   */
  @FXML
  public void quitAction()
  {
    System.exit(0);
  }

  /*
  The action done when clicked on back to menu
   */
  @FXML
  public void backToMenuAction(ActionEvent event) throws IOException
  {
    //loads the view of the menu
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu.fxml"));

    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    //sets the scene of the stage to the menu scene
    window.setScene(loader.load());

    //shows the stage
    window.show();
  }

  /*
  The action done when clicked on replay
   */
  @FXML
  public void replayAction(ActionEvent event) throws IOException
  {
    for(int i = 0; i < model.getPlayers().length; i++)
    {
      if(model.getPlayers()[i] instanceof BotPlayer)
      {
        backToMenuAction(event);
	return;
      }
    }
    //loads the view of replay
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Replay.fxml"));

    //instantiates the root node for all elements in the graph of the game scene
    Parent root = (Parent) loader.load();

    //initializes an array with players names
    String[] names = new String[model.getPlayers().length];
    for(int i = 0; i < model.getPlayers().length; i++)
    {
      names[i] = model.getPlayers()[i].getName();
    }

    //VERY IMPORTANT: load the loader then access its controller
    ReplayController rpc = loader.getController();
    rpc.init(names, model.getDice().getDiceRoll().length);

    //instantiates the replay scene with the root node
    Scene replayScene = new Scene(root);
    String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
    replayScene.getStylesheets().clear();
    replayScene.getStylesheets().add(path);

    //recovers stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();


    //sets the scene of the stage to the game scene
    window.setScene(replayScene);

    //shows the stage
    window.show();
  }
}
