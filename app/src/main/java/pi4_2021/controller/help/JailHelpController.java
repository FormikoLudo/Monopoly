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
 * The following class represents the help controller on which you can find jail case explanations.
 */
public class JailHelpController
{

  /**
   * label containing the explanatory text of the auction.
   */
  @FXML private Label jailLabel;


  /**
   * the following function allows us to initialise our variable linked to the fxml file
   *  in order to pass it the text passed in parameter.
   */
  @FXML
  public void initialize()
  {
    //a text is assigned to the label
    jailLabel.setText("      La case prison peut être atteinte de trois manière. Vous tomber sur la case 'allez en prison',vous réalisez 3 doubles de suite ou alors vous piochez une carte chance ou caisse de communauté qui vous envois en prison. \n\n" +
                      "      Lorsque vous êtes en prison, vous devez y rester trois tours, à moins que vous ne remplissiez une des conditions suivantes: \n\n" +
                      "-Payer une amende \n" +
                      "-Utilisez une carte qui permets de quitter la prison \n" +
                      "-Réalisez un doublon avec les dés et vous serez libéré ");
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
