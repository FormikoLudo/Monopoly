package controller;

import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.event.ActionEvent;
import java.io.IOException;
import game.Player;

/**
 * The controller of the menu view.
 */
public class MenuController
{

  /**
     The QUIT button.
   */
  @FXML private Button quitBtn;


  @FXML private MenuButton themeMenuButton;

  private String themePath = "";


  /**
     Quits the application.
     @param event The event of clicking on the button/
   */
  @FXML public void quitBtnAction(ActionEvent event)
  {
    System.exit(0);
  }

  /**
     On click, changes the scene to be the game scene and starts a game.
     @param event The event of clicking on the button.
     @throws an IOException Whenever the fxml file to load is missing.
   */
   @FXML public void playBtnAction(ActionEvent event) throws IOException
   {

      //loads the view of the game choice
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameChoice.fxml"));

       //instantiates the root node for all elements in the graph of the game choice scene
       Parent root = (Parent) loader.load();

       //instantiates the game choice scene with the root node
       Scene gameChoiceScene = new Scene(root);
       String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
       gameChoiceScene.getStylesheets().clear();
       gameChoiceScene.getStylesheets().add(path);

       GameChoiceController gcc =  loader.getController();

       //recovers stage information
       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

       //sets the scene of the stage to the game choice scene
       window.setScene(gameChoiceScene);

       //shows the stage
       window.show();
   }


   /**
      On click, changes the scene to be the game scene and starts a game.
      @param event The event of clicking on the button.
      @throws an IOException Whenever the fxml file to load is missing.
    */
    @FXML public void helpBtnAction(ActionEvent event) throws IOException
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

    /**
     * Changes the theme path into the dark one.
     */
    @FXML public void setDark()
    {
      themeMenuButton.setText("Sombre");
      this.themePath = "style/dark.css";
    }

    /**
     * Changes the theme path into the classic one.
     */
    @FXML public void setClassic()
    {
      themeMenuButton.setText("Classique");
      this.themePath = "style/classic.css";
    }

    /**
     * Changes the theme path into the pikachu one.
     */
    @FXML public void setPikachu()
    {
      themeMenuButton.setText("Pikachu");
      this.themePath = "style/pikachu.css";
    }

    /**
     * Changes the theme path into the princess one.
     */
    @FXML public void setPrincess()
    {
      themeMenuButton.setText("Princesse");
      this.themePath = "style/princess.css";
    }

    /**
     * Changes the theme of the actual view into the choice of the player.
     * @param  event       The click on the change button.
     * @throws IOException in case the Menu fxml isn't found.
     */
    public void changeTheme(ActionEvent event) throws IOException
    {
      if(themePath == "")
      {
        return;
      }
      //loads the view of the game
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu.fxml"));
      //instantiates the game scene with the root node
      Scene gameScene = (Scene)loader.load();
      gameScene.getStylesheets().clear();
      gameScene.getStylesheets().add(themePath);
      //recovers stage information
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
      //sets the scene of the stage to the game scene
      window.setScene(gameScene);
    }

}
