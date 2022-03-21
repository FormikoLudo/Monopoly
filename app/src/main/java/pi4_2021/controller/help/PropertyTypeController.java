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
 * The following class represents the help controller on which you can find the different type of properties
 */
public class PropertyTypeController
{

  /**
   * label containing the explanatory text of the property type
   */
  @FXML private Label propertyTypeLabel;


  /**
   * the following function allows us to initialise our variable linked to the fxml file
   *  in order to pass it the text passed in parameter.
   */
  @FXML
  public void initialize()
  {
    //a text is assigned to the label
    propertyTypeLabel.setText("    Le plateau du Monopoly est composé de différents type de case.   \n\n" +
                              "    Il y a les terrains regroupés par couleurs, sur lesquels les joueurs peuvent faire des acquisitions. Il y a aussi les gares et compagnie de service public. Vous pouvez acheter des propriétés en réglant la somme indiqué sur la description du terrain.  \n\n" +
                              "    Il est judicieux d'acheter les propriétés du même groupe de couleur car ceci permets d'augmenter le montant du loyer a payer par les autres joueurs.  \n\n");
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
