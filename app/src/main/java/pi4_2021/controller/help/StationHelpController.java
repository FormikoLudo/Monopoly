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
 *  The following table represents the aid controller related to stations and water companies.
 */
public class StationHelpController
{


  /**
   * label containing the explanatory text of the station help
   */
  @FXML private Label stationLabel;



  /**
   * the following function allows us to initialise our variable linked to the fxml file
   *  in order to pass it the text passed in parameter.
   */
  @FXML
  public void initialize()
  {
    //a text is assigned to the label
    stationLabel.setText("       Lorsque l'on se retrouve sur une case gare, le loyer de la gare dépend du nombre de gares que le joueur possède. \n\n" +
                          "       Les cases de compagnie de service public ont un loyer qui dépend d'un tir de dés. En effet, vous tirez les dés et vous multipliez le résultat. Si le joueur possède toutes les compagnies de service public, alors le résultat sera multiplié par 10.  \n\n");
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
