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

/**
*The following class represents the help controller on which you can find several topics related to the game.
*/
public class HelpController
{

  /**
   * The following function allows an action to be associated with the 'return' button.
   * This will allow you to change the window into main menu.
   * @param  event       press on the button
   * @throws IOException
   */
  @FXML public void menuPrincipal(ActionEvent event) throws IOException
  {
      //loads the view of the game
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu.fxml"));

      //instantiates the root node for all elements in the graph of the game scene
      Scene root = (Scene) loader.load();
      String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
      root.getStylesheets().clear();
      root.getStylesheets().add(path);

      //recovers stage information
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

      //sets the scene of the stage to the game scene
      window.setScene(root);

      //shows the stage
      window.show();
  }



  /**
   * The following function allows an action to be associated with the 'jail help' button.
   * This will allow you to change the window to bring us to the explanations.
   * @param  event       press on the button
   * @throws IOException
   */
  @FXML public void jailHelp(ActionEvent event) throws IOException
  {
      //loads the view of the game
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/help/JailHelp.fxml"));

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
   * The following function allows an action to be associated with the 'chance help' button.
   * This will allow you to change the window to bring us to the explanations.
   * @param  event       press on the button
   * @throws IOException
   */
  @FXML public void chanceHelp(ActionEvent event) throws IOException
  {
      //loads the view of the game
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/help/ChanceHelp.fxml"));

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
   * The following function allows an action to be associated with the 'luxury taxe help' button.
   * This will allow you to change the window to bring us to the explanations.
   * @param  event       press on the button
   * @throws IOException
   */
  @FXML public void luxuryTaxeHelp(ActionEvent event) throws IOException
  {
      //loads the view of the game
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/help/LuxuryTaxeHelp.fxml"));

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
   * The following function allows an action to be associated with the 'build help' button.
   * This will allow you to change the window to bring us to the explanations.
   * @param  event       press on the button
   * @throws IOException
   */
  @FXML public void buildHelp(ActionEvent event) throws IOException
  {
      //loads the view of the game
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/help/BuildHelp.fxml"));

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
   * The following function allows an action to be associated with the 'property type' button.
   * This will allow you to change the window to bring us to the explanations.
   * @param  event       press on the button
   * @throws IOException
   */
  @FXML public void propertyType(ActionEvent event) throws IOException
  {
      //loads the view of the game
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/help/PropertyType.fxml"));

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
   * The following function allows an action to be associated with the 'round rule' button.
   * This will allow you to change the window to bring us to the explanations.
   * @param  event       press on the button
   * @throws IOException
   */
    @FXML public void roundRule(ActionEvent event) throws IOException
    {
        //loads the view of the game
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/help/RoundRule.fxml"));

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
     * The following function allows an action to be associated with the 'morgage help' button.
     * This will allow you to change the window to bring us to the explanations.
     * @param  event       press on the button
     * @throws IOException
     */
  @FXML public void morgageHelp(ActionEvent event) throws IOException
  {
      //loads the view of the game
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/help/MorgageHelp.fxml"));

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
   * The following function allows an action to be associated with the 'auction help' button.
   * This will allow you to change the window to bring us to the explanations.
   * @param  event       press on the button
   * @throws IOException
   */
  @FXML public void auctionHelp(ActionEvent event) throws IOException
  {
      //loads the view of the game
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/help/AuctionHelp.fxml"));

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
   * The following function allows an action to be associated with the 'free parking help' button.
   * This will allow you to change the window to bring us to the explanations.
   * @param  event       press on the button
   * @throws IOException
   */
  @FXML public void freeParkingHelp(ActionEvent event) throws IOException
  {
      //loads the view of the game
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/help/FreeParkingHelp.fxml"));

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
   * The following function allows an action to be associated with the 'station help' button.
   * This will allow you to change the window to bring us to the explanations.
   * @param  event       press on the button
   * @throws IOException
   */
  @FXML public void stationHelp(ActionEvent event) throws IOException
  {
      //loads the view of the game
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/help/StationHelp.fxml"));

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
