package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import java.io.IOException;
import game.Player;

/**
 * This class is the controller of the replay view.
 */
public class ReplayController
{
    /**
     * The players names in the previous game
     */
    private String[] names;

  private int nb_dices;

  /**
   * Actions done when clicked on new players button.
   * @param  event       the event of clicking on the button.
   * @throws IOException whenever the FXML file is missing.
   */
  @FXML public void newPlayersAction(ActionEvent event) throws IOException
  {
    // loads the view of the game
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayersSelection.fxml"));
    //
    // instantiates the root node for all elements in the graph of the game scene
    Parent root = (Parent) loader.load();

    // instantiates the game scene with the root node
    Scene gameScene = new Scene(root);
    String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
    gameScene.getStylesheets().clear();
    gameScene.getStylesheets().add(path);

    // recovers stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    // sets the scene of the stage to the game scene
    window.setScene(gameScene);

    // shows the stage
    window.show();

  }

  @FXML public void samePlayersAction(ActionEvent event) throws IOException
  {
    //loads the view of the game
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Field.fxml"));

    //instantiates the root node for all elements in the graph of the game scene
    Parent root = (Parent) loader.load();

    //VERY IMPORTANT: load the loader then access its controller
    //recovers the controller of the Field fxml and initializes its data
    FieldController flc = loader.getController();
    Player[] players = new Player[names.length];
    for(int i = 0; i < names.length; i++)
    {
      players[i] = new Player(names[i],i);
    }

    flc.initModel(players, nb_dices);


    //instantiates the game scene with the root node
    Scene gameScene = new Scene(root);
    String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
    gameScene.getStylesheets().clear();
    gameScene.getStylesheets().add(path);

    //recovers stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    //sets the scene of the stage to the game scene
    window.setScene(gameScene);

    //shows the stage
    window.show();
  }

  /**
   * Sets the players names.
   * @param names The names of the players.
   */
  public void init(String[] names, int nb_dices)
  {
      this.names = names;
      this.nb_dices = nb_dices;
  }
}
