package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;

import javafx.event.ActionEvent;

import javafx.stage.Stage;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.io.IOException;

import field.Property;
import game.Game;
import game.Player;
import game.Deal;

/**
 * Controller of the deal negotiation view
 */
public class DealNegotiationController
{
  /**
   * The name of the buyer.
   */
  @FXML private Label buyerName;

  /**
   * The name of the seller.
   */
  @FXML private Label sellerName;

  /**
   * The name of the property to buy
   */
  @FXML private Label propertyName;

  /**
   * A label where we display a message
   */
  @FXML private Label display;

  /**
   * Back button.
   */
  @FXML private Button back;

  /**
   * Next button.
   */
  @FXML private Button next;

  /**
   * Accept button.
   */
  @FXML private Button accept;

  /**
   * Ask for more button.
   */
  @FXML private Button askForMore;

  /**
   * Reject Button.
   */
  @FXML private Button reject;

  /**
   * The text field where the buyer puts his price
   */
  @FXML private TextField proposition;

  /**
   * The game being played.
   */
  private Game model;

  /**
   * The deal being made.
   */
  private Deal deal;

  /**
   * A combo box containing the properties of the buyer that he wants to exchange with the other player.
   */
  @FXML private ComboBox properties;

  /**
   * initializes the controller.
   * @param model The game.
   * @param deal  The deal.
   */
  public void init(Game model, Deal deal)
  {
    this.model = model;
    this.deal = deal;
    buyerName.setText(deal.getBuyer().getName());
    sellerName.setText(deal.getSeller().getName());
    propertyName.setText(deal.getProperty().getName());

    //Adding the properties to the ComboBox.
    if(deal.getBuyer().getProperties().size() > 0)
    {
      ObservableList<Property> list = FXCollections.observableArrayList(deal.getBuyer().getProperties());
      properties.setItems(list);
    }
    else
    {
      properties.setEditable(false);
    }
  }


  /**
  * The button associated to the click on the next button.
   */
  public void nextAction()
  {
    int temp = 0;
    //We verify that the proposition is an int
    try
    {
      temp = Integer.parseInt(proposition.getText());
    }
    catch(NumberFormatException e)
    {
      if(((Property)properties.getValue()) == null)
      {
        display.setText("Veuillez proposer quelque chose");
        return;
      }
    }

    if (temp > deal.getBuyer().getMoney())
    {
      display.setText("Vous n'avez pas assez d'argent");
    }
    //else we update the view.
    else
    {
      deal.setLastPrice(temp);
      deal.setBuyerProperty((Property)properties.getValue());
      proposition.setDisable(true);
      back.setDisable(true);
      next.setDisable(true);
      accept.setDisable(false);
      askForMore.setDisable(false);
      reject.setDisable(false);
      display.setText("");
    }
  }

  /**
  * The button associated to the click on the ask for more button.
   */
  public void askForMoreAction()
  {
    proposition.setDisable(false);
    back.setDisable(false);
    next.setDisable(false);
    accept.setDisable(true);
    askForMore.setDisable(true);
    reject.setDisable(true);
  }

  /**
   * The button associated to the click on the accept button.
   * @param  event       Event.
   * @throws IOException in case the fxml isn't found.
   */
  public void acceptAction(ActionEvent event) throws IOException
  {
    //the buyer buys the property from the seller
    deal.action();

    //then we go back to the field pane
    backAction(event);
  }

  /**
   * The button associated to the click on the back button.
   * @param  event       Event.
   * @throws IOException in case the fxml isn't found.
   */
  public void backAction(ActionEvent event) throws IOException
  {
    // loads the view of the loan window
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Field.fxml"));

    //instantiates the root node for all elements in the graph of the game scene
    Parent root = (Parent) loader.load();

    //VERY IMPORTANT: load the loader then access its controller
    //recovers the controller of the loan fxml and initializes its data
    FieldController fc = loader.getController();
    fc.initModel(model);
    fc.getPlayersListController().updatePlayer();
    fc.getActualLocationController().update();
    //instantiates the loan scene with the root node
    Scene fieldScene = new Scene(root);
    String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
    fieldScene.getStylesheets().clear();
    fieldScene.getStylesheets().add(path);
    //recovers stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    //sets the scene of the stage to the loan scene
    window.setScene(fieldScene);

    //shows the stage
    window.show();
  }

}
