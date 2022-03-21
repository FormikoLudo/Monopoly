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
 * The following class represents the help controller on which you can find build explanations.
 */
public class BuildHelpController
{

  /**
   * label containing the explanatory text of the auction.
   */
  @FXML private Label buildLabel;


  /**
   * the following function allows us to initialise our variable linked to the fxml file
   *  in order to pass it the text passed in parameter.
   */
  @FXML
  public void initialize()
  {
    //a text is assigned to the label
    buildLabel.setText("      Au Monopoly, de nombreux terrains sont disponible afin de pouvoir y réaliser des constructions. \n\n" +
                       "      Vous pouvez acheter des maisons dont le prix est indiqué sur le titre de propriété. Vous pouvez acheter jusqu’à 4 maisons sur le même terrain au maximum. \n\n" +
                        "       Après avoir acheté les 4 maisons sur un terrain, vous pouvez réaliser un échange en y construisant un hôtel. Un montant à payer est requis afin de pouvoir acheter l’hôtel et celui-ci est indiqué sur le titre de propriété.   \n\n" +
                        "      Si vous implantez un hôtel sur un terrain, il ne sera plus possible d’acheter de maison sur celui-ci. ");
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
