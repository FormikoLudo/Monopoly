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
 * The following class represents the help controller on which you can find round rule
 */
public class RoundRuleController
{


  /**
   * label containing the explanatory text of the round rule
   */
  @FXML private Label roundRuleLabel;



  /**
   * the following function allows us to initialise our variable linked to the fxml file
   *  in order to pass it the text passed in parameter.
   */
  @FXML
  public void initialize()
  {
    roundRuleLabel.setText("       Au Monopoly, chaque joueur joue l’un après l’autre. \n\n" +
                          "       Lorsque c’est votre tour de jouer, vous tirez les dés. Votre pion est ensuite déplacé selon la valeur obtenue. La case sur laquelle le pion se pose déterminera l’action que vous pourrez effectuer pendant votre tour. Vous pourrez choisir d'acheter ou non une propriété, mais aussi choisir de rester un tour sur la case parking par exemple  \n\n" +
                          "       Si vous réalisez un double avec vos dés, vous pouvez refaire un tour. \n\n" +
                          "       Si votre pion passe par la case départ, vous recevez 200$. \n\n" +
                          "       Lorsque vous avez fini votre action, c’est au tour du joueur suivant de jouer. \n\n");
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
