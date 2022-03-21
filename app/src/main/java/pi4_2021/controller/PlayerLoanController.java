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
 * The controller of the player loan view
 */
public class PlayerLoanController
{
  /**
   * The name of the taker.
   */
  @FXML private Label takerName;

  /**
   * The name of the giver.
   */
  @FXML private Label giverName;

  /**
   * The textField where the player writes the amount of money he wants.
   */
  @FXML TextField request;

  /**
   * The slider so the player chooses for how many turns he wants the loan.
   */
  @FXML Slider nb_turns;

  /**
   * The label where we display the value that the player must pay.
   */
  @FXML Label loanValue;

  /**
   * Back button.
   */
  @FXML private Button cancel;

  /**
   * Next button.
   */
  @FXML private Button next;

  /**
   * Accept button.
   */
  @FXML private Button accept;

  /**
    * Reject button.
    */
  @FXML private Button reject;

  /**
   * The button that make the operation to calculate the loan value.
   */
  @FXML private Button calculate;

  /**
   * The game being played.
   */
   private Game model;

   private Player giver;

   private int value;

  /**
   * Initializing the model of this controller.
   * @param model The game.
   */
  public void init(Game model, Player giver)
  {
    this.model = model;
    this.giver = giver;
    takerName.setText(model.getCurrentPlayer().getName());
    giverName.setText(giver.getName());
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
      next.setDisable(true);
      return;
    }
    value = (int)(temp + temp * (nb_turns.getValue() * 0.1));
    loanValue.setText(String.valueOf(value));
    next.setDisable(false);
    request.setEditable(false);
  }

  /**
  * The action associated to the next button.
  * @param  event       event.
  * @throws IOException In case Field.fxml isn't found.
  */
  public void nextAction(ActionEvent event) throws IOException
  {
      // player.setLoan(new Loan(value,((int)nb_turns.getValue())));
      next.setDisable(true);
      cancel.setDisable(true);
      calculate.setDisable(true);
      if(giver.getMoney() > Integer.parseInt(request.getText()))
      {
        accept.setDisable(false);
      }
      reject.setDisable(false);
  }

  /**
  * The action associated to the accept button.
  * @param  event       event.
  * @throws IOException In case Field.fxml isn't found.
  */
  public void acceptAction(ActionEvent event) throws IOException
  {
    model.getCurrentPlayer().setLoan(new Loan(value,((int)nb_turns.getValue()),giver));
    giver.setMoney(giver.getMoney() - Integer.parseInt(request.getText()));
    goToField(event,true);
  }

  /**
  * The action associated to the reject button.
  * @param  event       event.
  * @throws IOException In case Field.fxml isn't found.
  */
  public void rejectAction(ActionEvent event) throws IOException
  {
    goToField(event, false);
  }

  /**
  * The action associated to the cancek button.
  * @param  event       event.
  * @throws IOException In case Field.fxml isn't found.
  */
  public void cancelAction(ActionEvent event) throws IOException
  {
    goToField(event, false);
  }

  /**
   * Takes the view back to the field.
   * @param  event       event.
   * @param taken If the player has taken a loan or not.
   * @throws IOException In case LoanChoice.fxml isn't found.
   */
  public void goToField(ActionEvent event, boolean taken) throws IOException
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
    if(taken)
    {
      flc.getFieldMenuController().getLoan().setDisable(true);
    }
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
}
