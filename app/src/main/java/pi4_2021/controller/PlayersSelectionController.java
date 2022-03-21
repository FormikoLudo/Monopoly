package controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;

import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;

import game.Player;
import game.BotPlayer;

/**
 * This class is used to update the playerSelection view represented by PlayersSelection.fxml file
 */
public class PlayersSelectionController
{
  /**
   * This label is used to show an error message
   * @return the label which contains the message
   */
  @FXML
  private Label required;

  /**
   * This slider is used to know the expected number of players
   * @return the Slider object linked to the fxml file
   */
  @FXML
  private Slider playerSelector;  /**
   * This TextField is used to ask the firstplayer nickname
   * @return the TextField object linked to the first TextField on the fxml file
   */
  @FXML
  private TextField player1;

  /**
   * This TextField is used to ask the secondplayer nickname
   * @return the TextField object linked to the secondPlayerLabel TextField on the fxml file
   */
  @FXML
  private TextField player2;

  /**
   * This TextField is used to ask the thirdplayer nickname
   * @return the TextField object linked to the thirdPlayerLabel TextField on the fxml file
   */
  @FXML
  private TextField player3;

  /**
   * This TextField is used to ask the fourthplayer nickname
   * @return the TextField object linked to the fourth TextField on the fxml file
   */
  @FXML
  private TextField player4;

  /**
   * This TextField is used to ask the fifthplayer nickname
   * @return the TextField object linked to the fifth TextField on the fxml file
   */
  @FXML
  private TextField player5;

  /**
   * This TextField is used to ask the firstplayer nickname
   * @return the TextField object linked to the first TextField on the fxml file
   */
  @FXML
  private TextField player6;

  /**
   * This Label is used to show the TextField used to ask the firstplayer nickname
   * @return the Label object linked to the firstplayer Label on the fxml file
   */
  @FXML
  private Label firstPlayerLabel;

  /**
   * This Label is used to show the TextField used to ask the secondplayer nickname
   * @return the Label object linked to the secondplayer Label on the fxml file
   */
  @FXML
  private Label secondPlayerLabel;

  /**
   * This Label is used to show the TextField used to ask the thirdplayer nickname
   * @return the Label object linked to the thirdplayer Label on the fxml file
   */
  @FXML
  private Label thirdPlayerLabel;

  /**
   * This Label is used to show the TextField used to ask the fourthplayer nickname
   * @return the Label object linked to the fourthplayer Label on the fxml file
   */
  @FXML
  private Label fourthPlayerLabel;

  /**
   * This Label is used to show the TextField used to ask the fifthplayer nickname
   * @return the Label object linked to the fifthplayer Label on the fxml file
   */
  @FXML
  private Label fifthPlayerLabel;

  /**
   * This Label is used to show the TextField used to ask the sixthplayer nickname
   * @return the Label object linked to the sixthplayer Label on the fxml file
   */
  @FXML
  private Label sixthPlayerLabel;

  /**
   * This Button brings to the next view
   * @return the Button object linked to the nextBtn on the fxml file
   */
  @FXML
  private Button  nextBtn;

  /**
   * This Button brings to the previous view
   * @return the Button object linked to the backBtn on the fxml file
   */
  @FXML
  private Button  backBtn;

  /**
   * The number of dices.
   */
   private int nb_dices;

   @FXML private MenuButton themeMenuButton;

   private String themePath = "";


  /**
   * Checkboxes for choosing to play with a bot.
   */
  @FXML private CheckBox bot2;
  @FXML private CheckBox bot3;
  @FXML private CheckBox bot4;
  @FXML private CheckBox bot5;
  @FXML private CheckBox bot6;

  /**
   * Initializes the number of dices.
   * @param nb_dices The number of dices.
   */
  public void init(int nb_dices)
  {
    this.nb_dices = nb_dices;
  }

  /**
   * This is the method called when the user clicks on the nextBtn.
   * It verifies that all visible TextField are not empty and that there are not to same nicknames
   * @param  event     this parameter represent the click on the button
   * @throws Exception IoExeption if "../gui/Game.fxml" does not exist
   */
  @FXML
  public void nextBtnAction(ActionEvent event) throws IOException
  {

    TextField [] playersNames = new TextField[6];
    playersNames[0] = player1;
    playersNames[1] = player2;
    playersNames[2] = player3;
    playersNames[3] = player4;
    playersNames[4] = player5;
    playersNames[5] = player6;

    int value = (int)playerSelector.getValue();
    for (int i = 0; i < value;i++)
    {
      if (playersNames[i].getText().equals(""))
      {
        required.setText("Veuillez tous entrer un pseudo.");
        required.setVisible(true);
        return ;
      }
    }
    //Players can't choose the name bot
    if(playersNames[0].getText().equals("Bot") || ((playersNames[1].getText().equals("Bot")) && !bot2.isSelected()) || ((playersNames[2].getText().equals("Bot")) && !bot3.isSelected()) ||((playersNames[3].getText().equals("Bot")) && !bot4.isSelected()) ||((playersNames[4].getText().equals("Bot")) && !bot5.isSelected()) ||((playersNames[5].getText().equals("Bot")) && !bot6.isSelected()) )
    {
      required.setText("Veuillez choisir un nom autre que bot");
      required.setVisible(true);
      return;
    }
    for (int i = 0; i < value - 1;i++)
    {
      for (int j = i + 1; j < value; j++)
      {
        if (!playersNames[i].getText().equals("Bot") && playersNames[i].getText().equals(playersNames[j].getText()))
        {
          required.setText("Chaque pseudo doit être unique.");
          required.setVisible(true);
          return ;
        }
      }
    }

    //We Initialize the players array.
    Player[] players = new Player[value];
    for(int i = 0; i < value; i++)
    {
      if(playersNames[i].getText().equals("Bot"))
      {
        players[i] = new BotPlayer(("Bot " + (i+1) ),i);
      }
      else
      {
        players[i] = new Player(playersNames[i].getText(),i);
      }
    }
    //loads the view of the game
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Field.fxml"));
    //instantiates the root node for all elements in the graph of the game scene
    Parent root = (Parent) loader.load();

    //VERY IMPORTANT: load the loader then access its controller
    //recovers the controller of the Field fxml and initializes its data
    FieldController flc = loader.getController();
    flc.initModel(players, nb_dices);
    flc.getActualLocationController().update();

    //instantiates the game scene with the root node
    Scene gameScene = new Scene(root);
    String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
    gameScene.getStylesheets().clear();
    gameScene.getStylesheets().add(path);
    //recovers stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    //allow window to be bigger as game gui is
    // window.setResizable(true);

    //sets the scene of the stage to the game scene
    window.setScene(gameScene);

    //shows the stage
    window.show();
  }

  /**
   * This is the method called when the user clicks on the backBtn
   * @param  event     this parameter represents the click on the button
   * @throws Exception IoExeption if "../gui/Menu.fxml" does not exist
   */
  @FXML
  public void backBtnAction(ActionEvent event) throws Exception
  {
      //loads the view of the game
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameChoice.fxml"));

      //instantiates the root node for all elements in the graph of the game scene
      Parent root = (Parent) loader.load();

      //instantiates the game scene with the root node
      Scene gameChoiceScene = new Scene(root);
      GameChoiceController gcc = loader.getController();
      String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
      gameChoiceScene.getStylesheets().clear();
      gameChoiceScene.getStylesheets().add(path);
      //recovers stage information
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

      //sets the scene of the stage to the game scene
      window.setScene(gameChoiceScene);

      //shows the stage
      window.show();
}

  @FXML public void onSliderChanged()
  {
    int value = (int)playerSelector.getValue();
    if (value == 4)
    {
      //firstplayer
      player1.setVisible(true);
      firstPlayerLabel.setVisible(true);

      //secondplayer
      player2.setVisible(true);
      secondPlayerLabel.setVisible(true);

      // thirdplayer
      player3.setVisible(true);
      thirdPlayerLabel.setVisible(true);

      // fourthplayer
      player4.setVisible(true);
      fourthPlayerLabel.setVisible(true);

      // firfthplayer
      player5.setVisible(false);
      fifthPlayerLabel.setVisible(false);
      bot5.setVisible(false);

      // sixthplayer
      player6.setVisible(false);
      sixthPlayerLabel.setVisible(false);
      bot6.setVisible(false);

    }
    else if (value == 5)
    {
      // firstplayer
      player1.setVisible(true);
      firstPlayerLabel.setVisible(true);

      // secondplayer
      player2.setVisible(true);
      secondPlayerLabel.setVisible(true);

      // thirdplayer
      player3.setVisible(true);
      thirdPlayerLabel.setVisible(true);

      // fourthplayer
      player4.setVisible(true);
      fourthPlayerLabel.setVisible(true);

      // firfthplayer
      fifthPlayerLabel.setVisible(true);
      player5.setVisible(true);
      bot5.setVisible(true);

      // sixthplayer
      player6.setVisible(false);
      sixthPlayerLabel.setVisible(false);
      bot6.setVisible(false);
    }
    else if (value == 6)
    {
      // firstplayer
      player1.setVisible(true);
      firstPlayerLabel.setVisible(true);

      // secondplayer
      player2.setVisible(true);
      secondPlayerLabel.setVisible(true);

      // thirdplayer
      player3.setVisible(true);
      thirdPlayerLabel.setVisible(true);

      // fourthplayer
      player4.setVisible(true);
      fourthPlayerLabel.setVisible(true);

      // firfthplayer
      player5.setVisible(true);
      fifthPlayerLabel.setVisible(true);
      bot5.setVisible(true);

      // sixthplayer
      player6.setVisible(true);
      sixthPlayerLabel.setVisible(true);
      bot6.setVisible(true);
    }
  }

  /**
   * The action associated to selecting bot2 CheckBox.
   */
  @FXML public void bot2Action()
  {
    player2.setDisable(!player2.isDisable());
    if(bot2.isSelected())
    {
      player2.setText("Bot");
    }
    else
    {
      player2.setText("");
    }
  }

  /**
   * The action associated to selecting bot3 CheckBox.
   */
  @FXML public void bot3Action()
  {
    player3.setDisable(!player3.isDisable());
    if(bot3.isSelected())
    {
      player3.setText("Bot");
    }
    else
    {
      player3.setText("");
    }
  }

  /**
   * The action associated to selecting bot4 CheckBox.
   */
  @FXML public void bot4Action()
  {
    player4.setDisable(!player4.isDisable());
    if(bot4.isSelected())
    {
      player4.setText("Bot");
    }
    else
    {
      player4.setText("");
    }
  }

  /**
   * The action associated to selecting bot5 CheckBox.
   */
  @FXML public void bot5Action()
  {
    player5.setDisable(!player5.isDisable());
    if(bot5.isSelected())
    {
      player5.setText("Bot");
    }
    else
    {
      player5.setText("");
    }
  }

  /**
   * The action associated to selecting bot6 CheckBox.
   */
  @FXML public void bot6Action()
  {
    player6.setDisable(!player6.isDisable());
    if(bot6.isSelected())
    {
      player6.setText("Bot");
    }
    else
    {
      player6.setText("");
    }
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
    //loads the view of the game
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayersSelection.fxml"));
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
