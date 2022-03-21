package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;

import game.Game;

/**
 * The controller of the loan view
 */
public class LoanChoiceController
{

  /**
    * The game being played.
    */
  Game model;

  /**
    * Initializes this controller.
    * @param model The model.
    */
  public void init(Game model)
  {
    this.model = model;
  }

  /**
  * The action associated to the bank loan button.
  * @param  event       event.
  * @throws IOException In case BankLoan.fxml isn't found.
  */
  public void bankLoan(ActionEvent event) throws IOException
  {
    // loads the view of the loan window
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BankLoan.fxml"));

    //instantiates the root node for all elements in the graph of the game scene
    Parent root = (Parent) loader.load();

    //VERY IMPORTANT: load the loader then access its controller
    //recovers the controller of the loan fxml and initializes its data
    BankLoanController blc = loader.getController();
    blc.init(model);

    //instantiates the loan scene with the root node
    Scene loanScene = new Scene(root);
    String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
    loanScene.getStylesheets().clear();
    loanScene.getStylesheets().add(path);
    //recovers stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    //sets the scene of the stage to the loan scene
    window.setScene(loanScene);

    //shows the stage
    window.show();
  }

  /**
  * The action associated to the player loan button.
  * @param  event       event.
  * @throws IOException In case LoanPlayerChoice.fxml isn't found.
  */
  public void playerLoan(ActionEvent event) throws IOException
  {
    // loads the view of the loan window
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoanPlayerChoice.fxml"));

    //instantiates the root node for all elements in the graph of the game scene
    Parent root = (Parent) loader.load();

    //VERY IMPORTANT: load the loader then access its controller
    //recovers the controller of the loan fxml and initializes its data
    LoanPlayerChoiceController lpcc = loader.getController();
    lpcc.init(model);

    //instantiates the loan scene with the root node
    Scene loanScene = new Scene(root);

    String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
    loanScene.getStylesheets().clear();
    loanScene.getStylesheets().add(path);

    //recovers stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    //sets the scene of the stage to the loan scene
    window.setScene(loanScene);

    //shows the stage
    window.show();
  }

  public void backAction(ActionEvent event) throws IOException
  {

    //loads the view of the field window
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Field.fxml"));


    //instantiates the root node for all elements in the graph of the game scene
    Parent root = (Parent) loader.load();

    //VERY IMPORTANT: load the loader then access its controller
    //recovers the controller of the Field fxml and initializes its data
    FieldController flc = loader.getController();
    flc.initModel(model);
    flc.getPlayersListController().updatePlayer();
    flc.getActualLocationController().update();
    flc.getFieldMenuController().init();


    //instantiates the field scene with the root node
    Scene fieldScene = new Scene(root);
    String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
    fieldScene.getStylesheets().clear();
    fieldScene.getStylesheets().add(path);

    //recovers stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    //sets the scene of the stage to the end of the game scene
    window.setScene(fieldScene);
    flc.getActualLocationController().update();
    //shows the stage
    window.show();
  }

}
