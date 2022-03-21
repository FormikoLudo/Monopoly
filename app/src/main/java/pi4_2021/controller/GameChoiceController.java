package controller;

import javafx.scene.control.Button;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.MenuButton;

import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * The controller of the game choice view.
 */
public class GameChoiceController
{
  @FXML private MenuButton themeMenuButton;

  private String themePath = "";

  /**
   * The action associated to the classic game button.
   * @param  event       The action event linked to the click.
   * @throws IOException in case the fxml file isn't found.
   */
  public void classicAction(ActionEvent event) throws IOException
  {
    loadSelection(event,2);
  }

  /**
   * The action associated to the three dices game button.
   * @param  event       The action event linked to the click.
   * @throws IOException in case the fxml file isn't found.
   */
  public void threeDicesAction(ActionEvent event) throws IOException
  {
    loadSelection(event,3);
  }

  /**
   * The action associated to the four dices game button.
   * @param  event       The action event linked to the click.
   * @throws IOException in case the fxml file isn't found.
   */
  public void fourDicesAction(ActionEvent event) throws IOException
  {
    loadSelection(event,4);
  }

  /**
   * Changes the view and initializes the next controller according to the button.
   * @param  event       The action event linked to the click.
   * @param  nb_dices    The number of dices of the game.
   * @throws IOException In case the fxml file isn't found.
   */
  public void loadSelection(ActionEvent event,int nb_dices) throws IOException
  {
    //loads the view of the game
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayersSelection.fxml"));

    //instantiates the root node for all elements in the graph of the game scene
    Parent root = (Parent) loader.load();

    //VERY IMPORTANT: load the loader then access its controller
    //recovers the controller of the PLayer selection fxml and initializes its data
    PlayersSelectionController psc = loader.getController();
    psc.init(nb_dices);

    //instantiates the game scene with the root node
    Scene playersSelectionScene = new Scene(root);
    String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
    playersSelectionScene.getStylesheets().clear();
    playersSelectionScene.getStylesheets().add(path);

    // playersSelectionScene.getStylesheets().clear();
    // playersSelectionScene.getStylesheets().add(themePath);

    //recovers stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    //sets the scene of the stage to the game scene
    window.setScene(playersSelectionScene);

    //shows the stage
    window.show();
  }

  /**
   * The action associated to the back button.
   * @param  event       The action event linked to the click.
   * @throws IOException in case the fxml file isn't found.
   */
  public void backAction(ActionEvent event) throws IOException
  {
    Scene newScene = (Scene)FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
    String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
    newScene.getStylesheets().clear();
    newScene.getStylesheets().add(path);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(newScene);
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
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameChoice.fxml"));
    //instantiates the game scene with the root node
    Parent root = (Parent) loader.load();
    Scene newScene = new Scene(root);

    newScene.getStylesheets().clear();
    newScene.getStylesheets().add(themePath);
    //recovers stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    //sets the scene of the stage to the game scene
    window.setScene(newScene);
  }

}
