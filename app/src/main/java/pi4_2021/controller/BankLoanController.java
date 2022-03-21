package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;

import game.Player;
import game.Loan;
import game.Game;

/**
 * The controller of the bank loan view
 */
public class BankLoanController
{

  /**
   * The textField where the player writes the amount of money he wants.
   */
  @FXML private TextField request;

  /**
   * The slider so the player chooses for how many turns he wants the loan.
   */
  @FXML private Slider nb_turns;

  /**
   * The label where we display the value that the player must pay.
   */
  @FXML private Label loanValue;

  /**
   * The label where we tell the player if the loan was done correctly or not.
   */
  @FXML private Label done;

  /**
   * The button that makes the player take the loan.
   */
  @FXML private Button takeLoan;

  /**
   * The button that makes the player cancel the loan.
   */
  @FXML private Button back;

  /**
   * The button that make the operation to calculate the loan value.
   */
  @FXML private Button calculate;

  /**
   * The game being played.
   */
  private Game model;

  /**
   * The player looking to get a loan.
   */
  private Player player;

  /**
   * The value of the loan.
   */
  private int value;

  /**
   * Tells us if the player has accepted the loan or not.
   */
  private boolean clicked;

  /**
   * Initializing the model of this controller.
   * @param model The game.
   */
  public void init(Game model)
  {
    this.model = model;
    this.player = model.getCurrentPlayer();
    this.clicked = false;
  }

  /**
   * The action associated to the back button.
   * It cancels the loan and get back to the loan choice view.
   * @param  event       event.
   * @throws IOException In case LoanChoice.fxml isn't found.
   */
  public void backAction(ActionEvent event) throws IOException
  {
    //loads the view of the field window
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoanChoice.fxml"));


    //instantiates the root node for all elements in the graph of the game scene
    Parent root = (Parent) loader.load();

    //VERY IMPORTANT: load the loader then access its controller
    //recovers the controller of the LoanChoice fxml and initializes its data
    LoanChoiceController lc = loader.getController();
    lc.init(model);

    //instantiates the field scene with the root node
    Scene fieldScene = new Scene(root);
    String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
    fieldScene.getStylesheets().clear();
    fieldScene.getStylesheets().add(path);

    //recovers stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    //sets the scene of the stage to the loan choice scene
    window.setScene(fieldScene);
    //shows the stage
    window.show();
  }

  /**
   * The action associated to the takeLoan button.
   * It takes the scene back to the field after giving the player a loan.
   * @param  event       event.
   * @throws IOException In case Field.fxml isn't found.
   */
  public void takeLoanAction(ActionEvent event) throws IOException
  {
    if(!clicked)
    {
      player.setLoan(new Loan(value,((int)nb_turns.getValue())));
      takeLoan.setText("Suivant");
      back.setDisable(true);
      calculate.setDisable(true);
      done.setVisible(true);
      clicked = true;
      return;
    }

    //loads the view of the field window
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Field.fxml"));


    //instantiates the root node for all elements in the graph of the game scene
    Parent root = (Parent) loader.load();

    //VERY IMPORTANT: load the loader then access its controller
    //recovers the controller of the Field fxml and initializes its data
    FieldController flc = loader.getController();
    flc.initModel(model);
    flc.getPlayersListController().updatePlayer();
    flc.getFieldMenuController().getLoan().setDisable(true);
    flc.getActualLocationController().update();

    //instantiates the field scene with the root node
    Scene fieldScene = new Scene(root);
    String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
    fieldScene.getStylesheets().clear();
    fieldScene.getStylesheets().add(path);
    //recovers stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    //sets the scene of the stage to the end of the game scene
    window.setScene(fieldScene);

    //shows the stage
    window.show();
  }

  /**
   * The action associated to calculate button.
   * It calculates the amount of money the player must pay after.
   */
  public void calculateAction()
  {
    int temp = 0;
    //We verify that the proposition is an int
    try
    {
      temp = Integer.parseInt(request.getText());
    }
    catch(NumberFormatException e)
    {
      request.setText("");
      request.setPromptText("Entrez un nombre");
      takeLoan.setDisable(true);
      return;
    }
    value = (int)(temp + temp * (nb_turns.getValue() * 0.1));
    loanValue.setText(String.valueOf(value));
    takeLoan.setDisable(false);
  }
}
