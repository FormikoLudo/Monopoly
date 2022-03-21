package controller;


import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import java.io.IOException;
import game.Player;
import javafx.scene.control.Label;

/**
 * The following class represents the help controller on which you can find chance and community chest card explanations.
 */
public class ChanceHelpController
{

  /**
   * label containing the explanatory text of the chance and community chest cards.
   */
  @FXML private Label chanceLabel;


  /**
   * the following function allows us to initialise our variable linked to the fxml file
   *  in order to pass it the text passed in parameter.
   */
  @FXML
  public void initialize()
  {
    //a text is assigned to the label
    chanceLabel.setText("      Au Monopoly, il y a des cartes chance et des cartes de caisse de communauté. \n\n" +
                        "      Lorsque vous tomber sur une de ces cases, une carte est piochée et vous devez suivre les instructions indiquées sur la carte. Celles-ci peuvent vous aider mais aussi vous porter préjudice. \n\n" +
                        "      Vous pouvez percevoir une somme, mais aussi en être prélevé.   \n\n" +
                        "      Si vous tombez sur une carte ‘ vous êtes libéré de prison’, vous pouvez la préservez afin de vous en servir dans le cas où vous vous retrouveriez en prison. ");
  }



    /**
     * The following function allows an action to be associated with the 'back to help' button.
     * This will allow you to change the window.
     * @param  event       press on the button
     * @throws IOException
     */
  @FXML public void backHelp(ActionEvent event) throws IOException
  {
      //loads the view of the game
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/help/Help.fxml"));

      //instantiates the root node for all elements in the graph of the game scene
      Parent root = (Parent) loader.load();

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

}
