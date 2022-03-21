package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import java.io.IOException;


import game.Auction;
import game.Game;
import game.Player;
import field.Property;


/**
 * This class is the controller of the general view of the auction choice.
 */
public class AuctionController
{

  /**
   * Label of the actual auctioner.
   */
  @FXML Label actualAuctioner;

  /**
   * Label of the title of the view.
   */
  @FXML Label title;

  /**
   * Label of proposition.
   */
  @FXML Label prop;

  /**
   * Label of the actual card.
   */
  @FXML Label actualCard;

  /**
   * Label of the highest auction.
   */
  @FXML Label highestAuction;

  /**
   * Label where an avertissement is displayed.
   */
  @FXML Label avertissement;

  /**
   * TextField that gets the proposition of the auction from the player.
   */
  @FXML TextField proposition;

  /**
   * Label where we display the highest auctioner.
   */
  @FXML Label highestAuctioner;


  @FXML Button nextBtn;


  @FXML Button quitAuction;

  /**
   * The game that is actually played.
   */
  Game model;

  /**
   * The actual auction.
   */
  Auction auction;

  /**
   * When clicked the last time on the quit auction button.
   */
  boolean clicked;

  /**
   * Initializing the model of this controller.
   * @param model      The game being played.
   * @param auctioners The ArrayList of the auctioners.
   */
  public void init(Game model, ArrayList<Player> auctioners)
  {
      this.model = model;
      auction = new Auction(auctioners);
      auction.setHighestAuction(((Property)model.getActualLocation()).getPrice());
      actualAuctioner.setText("Tour de " + auctioners.get(0).getName());
      actualCard.setText(model.getActualLocation().getName());
      highestAuction.setText("Enchère la plus haute :      " + ((Property)model.getActualLocation()).getPrice());
      clicked = false;
  }

  /**
   * The button associated to the click on the next button.
   * @param  event Event.
   * @throws IOException if the Field.fxml isn't load correctly.
   */
  @FXML public void nextBtnAction(ActionEvent event) throws IOException
  {
    if(!clicked)
    {
      //We verify that the proposition is an int
      try
      {
        int value = Integer.parseInt(proposition.getText());
      }
      catch(NumberFormatException e)
      {
        avertissement.setText("Veuillez entrer un nombre");
        return;
      }
      int auctionValue = Integer.parseInt(proposition.getText());
      //If the auction proposed by the player is less than the highest auction we display a message.
      if(auctionValue <= auction.getHighestAuction() )
      {
        avertissement.setText("Offre trop basse");
      }
      //If the player proposes more than what he has we tell him that he can't and hasn't enough money.
      else if (auctionValue >= auction.getAuctioners().get(auction.getActualAuctioner()).getMoney())
      {
        avertissement.setText("Vous n'avez pas assez d'argent");
      }
      else
      {
        auction.setHighestAuctioner(auction.getAuctioners().get(auction.getActualAuctioner()));
        auction.setHighestAuction(auctionValue);
        auction.setHighestAuctioner(auction.getAuctioners().get(auction.getActualAuctioner()));
        auction.nextAuctioner(true);
        update();
      }
    }
    else
    {
      //loads the view of the field window
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Field.fxml"));


      //instantiates the root node for all elements in the graph of the game scene
      Parent root = (Parent) loader.load();

      //VERY IMPORTANT: load the loader then access its controller
      //recovers the controller of the Field fxml and initializes its data
      FieldController flc = loader.getController();
      flc.initModel(model);
      flc.getFieldMenuController().endOfRoundAction(event);

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

  /**
   * This method updates the view of the auction after changing a player.
   */
  public void update()
  {
    actualAuctioner.setText("Tour de " + auction.getAuctioners().get(auction.getActualAuctioner()).getName());
    highestAuction.setText("Enchère la plus haute : " + auction.getHighestAuction());
    if(auction.getHighestAuctioner() != null)
    {
      highestAuctioner.setText("De :     " + auction.getHighestAuctioner().getName());
    }
    avertissement.setText("");
    proposition.setText("");
  }

  /**
   * The action associated to the quit auction button.
   * @param  event       Event.
   */
  @FXML public void quitAuctionBtnAction(ActionEvent event)
  {
    //We remove the player from the auctioners.
    auction.getAuctioners().remove(auction.getActualAuctioner());
    //If there's still only one player left he wins the auction and buys the actual location.
    if(auction.getAuctioners().size() == 1)
    {
      ((Property)model.getActualLocation()).buy(auction.getAuctioners().get(0), auction.getHighestAuction());
      auction.getAuctioners().get(0).autoEquip();

      clicked = true;

      quitAuction.setDisable(true);

      nextBtn.setText("SUIVANT");

      actualAuctioner.setVisible(false);

      title.setVisible(false);

      // prop.setDisable(true);

      proposition.setText(auction.getHighestAuction() + "");
      proposition.setEditable(false);

      highestAuction.setText("ENCHERE GAGNEE PAR :          " + auction.getAuctioners().get(0).getName());
      return;
    }
    auction.nextAuctioner(false);
    update();
  }

}
