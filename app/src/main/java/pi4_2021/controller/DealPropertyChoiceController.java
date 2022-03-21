package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import game.Deal;
import game.Player;

/**
 * This class is the controller of the general view of the deal property choice.
 */
public class DealPropertyChoiceController
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
   * The seller.
   */
  private Player seller;

  /**
   * A combo box containing the properties of the seller.
   */
  @FXML private ComboBox properties;

  /**
   * The actual game.
   */
  private Game model;

  /**
   * The deal being made.
   */
  private Deal deal;

  /**
   * Initializes the controller.
   * @param model  The model of the game.
   * @param seller The seller of the card.
   */
  public void init(Game model, Deal deal)
  {
    this.model = model;
    this.seller = deal.getSeller();
    buyerName.setText(deal.getBuyer().getName());
    sellerName.setText(deal.getSeller().getName());
    this.deal = deal;

    //Adding the properties to the ComboBox.
    ObservableList<Property> list = FXCollections.observableArrayList(seller.getProperties());
    properties.setItems(list);
  }

  /**
   * The button associated to the click on the next button.
   * @param  event       Event.
   * @throws IOException in case the fxml isn't found.
   */
  public void nextAction(ActionEvent event) throws IOException
  {

    if(properties.getValue() != null)
    {
      // loads the view of the loan window
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DealNegotiation.fxml"));

      //instantiates the root node for all elements in the graph of the game scene
      Parent root = (Parent) loader.load();

      //VERY IMPORTANT: load the loader then access its controller
      //recovers the controller of the loan fxml and initializes its data
      DealNegotiationController dnc = loader.getController();
      deal.setProperty((Property)properties.getValue());
      dnc.init(model,deal);

      //instantiates the loan scene with the root node
      Scene dealScene = new Scene(root);
      String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
      dealScene.getStylesheets().clear();
      dealScene.getStylesheets().add(path);

      //recovers stage information
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

      //sets the scene of the stage to the loan scene
      window.setScene(dealScene);

      //shows the stage
      window.show();
    }
  }

  /**
   * The button associated to the click on the back button.
   * @param  event       Event.
   * @throws IOException in case the fxml isn't found.
   */
  public void backAction(ActionEvent event) throws IOException
  {
    // loads the view of the deal player choice window
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DealPlayerChoice.fxml"));

    //instantiates the root node for all elements in the graph of the game scene
    Parent root = (Parent) loader.load();

    //VERY IMPORTANT: load the loader then access its controller
    //recovers the controller of the deal player choice fxml and initializes its data
    DealPlayerChoiceController dpcc = loader.getController();
    dpcc.init(model);

    //instantiates the loan scene with the root node
    Scene dealScene = new Scene(root);
    String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
    dealScene.getStylesheets().clear();
    dealScene.getStylesheets().add(path);

    //recovers stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    //sets the scene of the stage to the loan scene
    window.setScene(dealScene);

    //shows the stage
    window.show();
  }

}
