package controller;

import game.Game;
import game.Player;
import field.Property;



import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;


/**
 * This class is the controller of the general view of the auction choice.
 */
public class AuctionChoiceController
{
  /**
   * The game that is actually played.
   */
    private Game model;

    /**
     * The title of the pane.
     */
    @FXML private Label title;

    /**
     * The labels of the players
     */
    @FXML private Label firstPlayerLabel = new Label();
    @FXML private Label secondPlayerLabel = new Label();
    @FXML private Label thirdPlayerLabel = new Label();
    @FXML private Label fourthPlayerLabel = new Label();
    @FXML private Label fifthPlayerLabel = new Label();
    @FXML private Label sixthPlayerLabel = new Label();

    /**
     * The yes and no ToggleButtons to get the players answers.
     */
    @FXML private ToggleButton yesPlayer1;
    @FXML private ToggleButton noPlayer1;
    @FXML private ToggleButton yesPlayer2;
    @FXML private ToggleButton noPlayer2;
    @FXML private ToggleButton yesPlayer3;
    @FXML private ToggleButton noPlayer3;
    @FXML private ToggleButton yesPlayer4;
    @FXML private ToggleButton noPlayer4;
    @FXML private ToggleButton yesPlayer5;
    @FXML private ToggleButton noPlayer5;
    @FXML private ToggleButton yesPlayer6;
    @FXML private ToggleButton noPlayer6;

    /**
     * An avertissement label.
     */
    @FXML private Label avertissement;

    /**
     * This method initializes the model of this class.
     * @param model The game that is played.
     */
    public void initModel(Game model)
    {
      this.model = model;
      title.setText("Voulez-vous participer à l'enchère pour l'achat de la carte '" + model.getActualLocation().getName() + "' ?" );
      initNicknames();
    }

    /**
     * Initializes the labels and toggle buttons of the players
     */
    public void initNicknames()
    {
      firstPlayerLabel.setText(model.getPlayers()[0].getName() + " : ");
      if(!model.getPlayers()[0].canAuction(((Property)model.getActualLocation()).getPrice()) || 0 == model.getCurrent())
      {
        yesPlayer1.setDisable(true);
        noPlayer1.setDisable(true);
      }

      secondPlayerLabel.setText(model.getPlayers()[1].getName() + " : ");
      if(!model.getPlayers()[1].canAuction(((Property)model.getActualLocation()).getPrice()) || 1 == model.getCurrent())
      {
        yesPlayer2.setDisable(true);
        noPlayer2.setDisable(true);
      }

      thirdPlayerLabel.setText(model.getPlayers()[2].getName() + " : ");
      if(!model.getPlayers()[2].canAuction(((Property)model.getActualLocation()).getPrice()) || 2 == model.getCurrent())
      {
        yesPlayer3.setDisable(true);
        noPlayer3.setDisable(true);
      }

      if(!model.getPlayers()[3].canAuction(((Property)model.getActualLocation()).getPrice()) || 3 == model.getCurrent())
      {
        yesPlayer4.setDisable(true);
        noPlayer4.setDisable(true);
      }
      fourthPlayerLabel.setText(model.getPlayers()[3].getName() + " : ");
      if(model.getPlayers().length > 4)
      {
        fifthPlayerLabel.setVisible(true);
        yesPlayer5.setVisible(true);
        noPlayer5.setVisible(true);
        fifthPlayerLabel.setText(model.getPlayers()[4].getName() + " : ");
        if(!model.getPlayers()[4].canAuction(((Property)model.getActualLocation()).getPrice()) || 4 == model.getCurrent())
        {
          yesPlayer5.setDisable(true);
          noPlayer5.setDisable(true);

        }

        if(model.getPlayers().length > 5)
        {
          sixthPlayerLabel.setVisible(true);
          yesPlayer6.setVisible(true);
          noPlayer6.setVisible(true);

          sixthPlayerLabel.setText(model.getPlayers()[5].getName() + " : ");
          if(!model.getPlayers()[5].canAuction(((Property)model.getActualLocation()).getPrice()) || 5 == model.getCurrent())
          {
            yesPlayer6.setDisable(true);
            noPlayer6.setDisable(true);
          }
        }
      }

    }

    /**
     * This method is associated to the next button.
     * @param  event       Event.
     * @throws IOException If "Field.fxml" or "Auction.fxml" can'b be loaded.
     */
    @FXML public void nextBtnAction(ActionEvent event) throws IOException
    {
      //We check if all the players that can auction answered the question by checking if he selected any of the yes or no.
      if(model.getPlayers()[0].canAuction(((Property)model.getActualLocation()).getPrice()) && !yesPlayer1.isSelected() && !noPlayer1.isSelected() && model.getCurrent() != 0)
      {
        avertissement.setVisible(true);
      }
      else if(model.getPlayers()[1].canAuction(((Property)model.getActualLocation()).getPrice()) && !yesPlayer2.isSelected() && !noPlayer2.isSelected() && model.getCurrent() != 1)
      {
        avertissement.setVisible(true);
      }
      else if(model.getPlayers()[2].canAuction(((Property)model.getActualLocation()).getPrice()) && !yesPlayer3.isSelected() && !noPlayer3.isSelected() && model.getCurrent() != 2)
      {
        avertissement.setVisible(true);
      }
      else if(model.getPlayers()[3].canAuction(((Property)model.getActualLocation()).getPrice()) && !yesPlayer4.isSelected() && !noPlayer4.isSelected() && model.getCurrent() != 3)
      {
        avertissement.setVisible(true);
      }
      else if(model.getPlayers().length > 4 && model.getPlayers()[4].canAuction(((Property)model.getActualLocation()).getPrice()) && !yesPlayer5.isSelected() && !noPlayer5.isSelected() && model.getCurrent() != 4)
      {
        avertissement.setVisible(true);
      }
      else if(model.getPlayers().length > 5 && model.getPlayers()[5].canAuction(((Property)model.getActualLocation()).getPrice()) && !yesPlayer6.isSelected() && !noPlayer6.isSelected() && model.getCurrent() != 5)
      {
        avertissement.setVisible(true);
      }
      //If all the players auctioning answered.
      else
      {
        ArrayList<Player> auctioners = new ArrayList<Player>();
        //We add all the players that answered yes to the auctioners ArrayList.
        if(yesPlayer1.isSelected())
        {
          auctioners.add(model.getPlayers()[0]);
        }
        if(yesPlayer2.isSelected())
        {
          auctioners.add(model.getPlayers()[1]);
        }
        if(yesPlayer3.isSelected())
        {
          auctioners.add(model.getPlayers()[2]);
        }
        if(yesPlayer4.isSelected())
        {
          auctioners.add(model.getPlayers()[3]);
        }
        if(model.getPlayers().length > 4)
        {
          if(yesPlayer5.isSelected())
          {
            auctioners.add(model.getPlayers()[4]);
          }
          if (model.getPlayers().length == 6)
          {
            if(yesPlayer6.isSelected())
            {
              auctioners.add(model.getPlayers()[5]);
            }
          }
        }

        //If none of the players chose to auction we go back to the field.
        if(auctioners.size() == 0)
        {
	    
          //loads the view of the field window
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Field.fxml"));

          //instantiates the root node for all elements in the graph of the game scene
          Parent root = (Parent) loader.load();

          //VERY IMPORTANT: load the loader then access its controller
          //recovers the controller of the Field Controller fxml and initializes its data
          FieldController flc = loader.getController();
          flc.initModel(model);

	  //disable and enables this buttons when going back to screen
	  flc.getFieldMenuController().getEndOfRoundButton().setDisable(false);
	  flc.getFieldMenuController().getPayButton().setDisable(true);
	  flc.getFieldMenuController().getBuyButton().setDisable(true);
	  flc.getFieldMenuController().getDontBuyButton().setDisable(true);

          //instantiates the fieldController scene with the root node
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
        else if (auctioners.size() == 1)
        {
          ((Property)model.getActualLocation()).buy(auctioners.get(0));
          //loads the view of the winning window
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Field.fxml"));

          //instantiates the root node for all elements in the graph of the game scene
          Parent root = (Parent) loader.load();

          //VERY IMPORTANT: load the loader then access its controller
          //recovers the controller of the Field fxml and initializes its data
          FieldController flc = loader.getController();
          flc.initModel(model);

	  //disable and enable this buttons when going back to the game
	  flc.getFieldMenuController().getEndOfRoundButton().setDisable(false);
	  flc.getFieldMenuController().getPayButton().setDisable(true);
	  
          //instantiates the field scene with the root node
          Scene endOfGameScene = new Scene(root);
          String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
          endOfGameScene.getStylesheets().clear();
          endOfGameScene.getStylesheets().add(path);

          //recovers stage information
          Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

          //sets the scene of the stage to the field scene
          window.setScene(endOfGameScene);

          //shows the stage
          window.show();
        }
        else
        {
          //loads the view of the winning window
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Auction.fxml"));

          //instantiates the root node for all elements in the graph of the game scene
          Parent root = (Parent) loader.load();

          //VERY IMPORTANT: load the loader then access its controller
          //recovers the controller of the Auction fxml and initializes its data
          AuctionController ac = loader.getController();
          ac.init(model,auctioners);

          //instantiates the auction scene with the root node
          Scene endOfGameScene = new Scene(root);
          String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
          endOfGameScene.getStylesheets().clear();
          endOfGameScene.getStylesheets().add(path);
          //recovers stage information
          Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

          //sets the scene of the stage to the end of the game scene
          window.setScene(endOfGameScene);

          //shows the stage
          window.show();
        }

      }
    }
}
