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
 *  The following class represents the mortgage-related aid controller. It explains what this action is about.
 */
public class MorgageHelpController
{


/**
 * label containing the explanatory text of the mortgage
 */
  @FXML private Label morgageLabel;

  /**
   * the following function allows us to initialise our variable linked to the fxml file
   *  in order to pass it the text passed in parameter.
   */
  @FXML
  public void initialize()
  {
    //a text is assigned to the label
    morgageLabel.setText("       Si un joueur n’a plus les moyens, celui-ci peut hypothéquer ses propriétés à la banque en commençant par les propriétés appartenant au même groupe de couleur. \n\n" +
                          "       Le joueur pourra récupérer ses propriétés plus tard en ajoutant un certain taux d’intérêt.  \n\n" +
                          "       Lorsqu’une propriété est hypothéquée, le joueur ne perçoit plus le loyer de la part des autres joueurs.  \n\n");
  }




  /**
   * The following function allows an action to be associated with the 'back to help' button. This will allow you to change the window.
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
