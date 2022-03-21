package controller;

import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;

/**
   This class is the controller of the list of players in the current
   game
 */
public class PlayersListController{

    /**
       The parent controller of this controller
     */
    @FXML private FieldController fieldController;

    /**
     * The different attributes of the players
     */
    @FXML Label namePlayer1;

    @FXML Label namePlayer2;

    @FXML Label namePlayer3;

    @FXML Label namePlayer4;

    @FXML Label namePlayer5;

    @FXML Label namePlayer6;

    @FXML Label moneyPlayer1;

    @FXML Label moneyPlayer2;

    @FXML Label moneyPlayer3;

    @FXML Label moneyPlayer4;

    @FXML Label moneyPlayer5;

    @FXML Label moneyPlayer6;

    @FXML Label locationPlayer1;

    @FXML Label locationPlayer2;

    @FXML Label locationPlayer3;

    @FXML Label locationPlayer4;

    @FXML Label locationPlayer5;

    @FXML Label locationPlayer6;

    @FXML Label pretPlayer1;

    @FXML Label pretPlayer2;

    @FXML Label pretPlayer3;

    @FXML Label pretPlayer4;

    @FXML Label pretPlayer5;

    @FXML Label pretPlayer6;

    @FXML StackPane panePlayer5;

    @FXML StackPane panePlayer6;

    @FXML ImageView inJailPlayer1;

    @FXML ImageView inJailPlayer2;

    @FXML ImageView inJailPlayer3;

    @FXML ImageView inJailPlayer4;

    @FXML ImageView inJailPlayer5;

    @FXML ImageView inJailPlayer6;

    @FXML ImageView endPlayer1;

    @FXML ImageView endPlayer2;

    @FXML ImageView endPlayer3;

    @FXML ImageView endPlayer4;

    @FXML ImageView endPlayer5;

    @FXML ImageView endPlayer6;

    @FXML ImageView player1round;

    @FXML ImageView player2round;

    @FXML ImageView player3round;

    @FXML ImageView player4round;

    @FXML ImageView player5round;

    @FXML ImageView player6round;

    @FXML ImageView pawnPlayer5;

    @FXML ImageView pawnPlayer6;


    /**
     * The following function initializes the player's attributes at the beginning
     * of the game and displays his money, location and name. If there are players 5 and 6,
     * their attributes are also initialized.
     */
     public void init()
     {
        namePlayer1.setText(fieldController.getModel().getPlayers()[0].getName());
        namePlayer2.setText(fieldController.getModel().getPlayers()[1].getName());
        namePlayer3.setText(fieldController.getModel().getPlayers()[2].getName());
        namePlayer4.setText(fieldController.getModel().getPlayers()[3].getName());

        moneyPlayer1.setText("Argent : " + String.valueOf(fieldController.getModel().getPlayers()[0].getMoney()));
        moneyPlayer2.setText("Argent : " + String.valueOf(fieldController.getModel().getPlayers()[1].getMoney()));
        moneyPlayer3.setText("Argent : " + String.valueOf(fieldController.getModel().getPlayers()[2].getMoney()));
        moneyPlayer4.setText("Argent : " + String.valueOf(fieldController.getModel().getPlayers()[3].getMoney()));

        locationPlayer1.setText("Position : " + String.valueOf(fieldController.getModel().getPlayers()[0].getPawn().getLocation()));
        locationPlayer2.setText("Position : " + String.valueOf(fieldController.getModel().getPlayers()[1].getPawn().getLocation()));
        locationPlayer3.setText("Position : " + String.valueOf(fieldController.getModel().getPlayers()[2].getPawn().getLocation()));
        locationPlayer4.setText("Position : " + String.valueOf(fieldController.getModel().getPlayers()[3].getPawn().getLocation()));

        //the attributes of player 5 are made visible if they exist
        if(fieldController.getModel().getPlayers().length > 4)
        {
         pawnPlayer5.setVisible(true);
         namePlayer5.setVisible(true);
         moneyPlayer5.setVisible(true);
         locationPlayer5.setVisible(true);
         panePlayer5.setVisible(true);
         namePlayer5.setText(fieldController.getModel().getPlayers()[4].getName());
         moneyPlayer5.setText("Argent : " + String.valueOf(fieldController.getModel().getPlayers()[4].getMoney()));
         locationPlayer5.setText("Position : " + String.valueOf(fieldController.getModel().getPlayers()[4].getPawn().getLocation()));

         // the attributes of player 6 are updated if they exist
         if(fieldController.getModel().getPlayers().length > 5)
         {
           pawnPlayer6.setVisible(true);
           namePlayer6.setVisible(true);
           moneyPlayer6.setVisible(true);
           locationPlayer6.setVisible(true);
           panePlayer6.setVisible(true);
           namePlayer6.setText(fieldController.getModel().getPlayers()[5].getName());
           moneyPlayer6.setText("Argent : " + String.valueOf(fieldController.getModel().getPlayers()[5].getMoney()));
           locationPlayer6.setText("Position : " + String.valueOf(fieldController.getModel().getPlayers()[5].getPawn().getLocation()));
         }
       }
     }


    /**
     * The following function allows you to update the information of each player in the player list bar.
     * It mentions which player is currently playing, the counter associated with the player, his money,
     *  his rental, and his current loan. It also indicates if the player is in jail or has lost the game.
     */
    public void updatePlayer()
    {
      moneyPlayer1.setText("Argent : " + String.valueOf(fieldController.getModel().getPlayers()[0].getMoney()));
      moneyPlayer2.setText("Argent : " + String.valueOf(fieldController.getModel().getPlayers()[1].getMoney()));
      moneyPlayer3.setText("Argent : " + String.valueOf(fieldController.getModel().getPlayers()[2].getMoney()));
      moneyPlayer4.setText("Argent : " + String.valueOf(fieldController.getModel().getPlayers()[3].getMoney()));

      locationPlayer1.setText("Position : " + String.valueOf(fieldController.getModel().getPlayers()[0].getPawn().getLocation()));
      locationPlayer2.setText("Position : " + String.valueOf(fieldController.getModel().getPlayers()[1].getPawn().getLocation()));
      locationPlayer3.setText("Position : " + String.valueOf(fieldController.getModel().getPlayers()[2].getPawn().getLocation()));
      locationPlayer4.setText("Position : " + String.valueOf(fieldController.getModel().getPlayers()[3].getPawn().getLocation()));

      //We update the bank loan information for each player
      if(fieldController.getModel().getPlayers()[0].getLoan() == null){
        pretPlayer1.setText("Pas de prêt en cours");
      } else {
        pretPlayer1.setText("Prêt : ( " + String.valueOf(fieldController.getModel().getPlayers()[0].getLoan().getNumberOfTurns() + 1) + " tours restants ) " + String.valueOf(fieldController.getModel().getPlayers()[0].getLoan().getValue()));
      }

      if(fieldController.getModel().getPlayers()[1].getLoan() == null){
        pretPlayer2.setText("Pas de prêt en cours");
      } else {
        pretPlayer2.setText("Prêt : ( " + String.valueOf(fieldController.getModel().getPlayers()[1].getLoan().getNumberOfTurns() + 1) + " tours restants ) " + String.valueOf(fieldController.getModel().getPlayers()[1].getLoan().getValue()));
      }

      if(fieldController.getModel().getPlayers()[2].getLoan() == null){
        pretPlayer3.setText("Pas de prêt en cours");
      } else {
        pretPlayer3.setText("Prêt : ( " + String.valueOf(fieldController.getModel().getPlayers()[2].getLoan().getNumberOfTurns() + 1) + " tours restants ) " + String.valueOf(fieldController.getModel().getPlayers()[2].getLoan().getValue()));
      }

      if(fieldController.getModel().getPlayers()[3].getLoan() == null){
        pretPlayer4.setText("Pas de prêt en cours");
      } else {
        pretPlayer4.setText("Prêt : ( " + String.valueOf(fieldController.getModel().getPlayers()[3].getLoan().getNumberOfTurns() + 1) + " tours restants ) " + String.valueOf(fieldController.getModel().getPlayers()[3].getLoan().getValue()));
      }

      // If there is a fifth player, we update his information
      if(fieldController.getModel().getPlayers().length > 4)
      {
        moneyPlayer5.setText("Argent : " + String.valueOf(fieldController.getModel().getPlayers()[4].getMoney()));
        locationPlayer5.setText("Position : " + String.valueOf(fieldController.getModel().getPlayers()[4].getPawn().getLocation()));
        // If the player does not have a current bank loan, the following message is displayed
        if(fieldController.getModel().getPlayers()[4].getLoan() == null){
          pretPlayer5.setText("Pas de prêt en cours");
        } else {
          //otherwise, the amount of his loan is displayed and in how many turns he has to pay it back
          pretPlayer5.setText("Prêt : ( " + String.valueOf(fieldController.getModel().getPlayers()[4].getLoan().getNumberOfTurns() + 1) + " tours restants ) " + String.valueOf(fieldController.getModel().getPlayers()[4].getLoan().getValue()));
        }

        //If player 5 is in prison, bars are displayed.
        if(fieldController.getModel().getPlayers()[4].isInJail())
        {
          inJailPlayer5.setVisible(true);
        } else {
          inJailPlayer5.setVisible(false);
        }

        //If it is player 5's turn, they are notified
        if(fieldController.getModel().getCurrent()==4)
        {
          player5round.setVisible(true);
        } else {
          player5round.setVisible(false);
        }


        //We check if the player is still playing or if he has lost. If they are, they are notified
        if(!fieldController.getModel().getPlayers()[4].getStillPlaying())
        {
          endPlayer5.setVisible(true);
          inJailPlayer5.setVisible(false);
        }

        //If there is a sixth player, their information is updated.
        if(fieldController.getModel().getPlayers().length > 5)
        {
          moneyPlayer6.setText("Argent : " + String.valueOf(fieldController.getModel().getPlayers()[5].getMoney()));
          locationPlayer6.setText("Position : " + String.valueOf(fieldController.getModel().getPlayers()[5].getPawn().getLocation()));
          if(fieldController.getModel().getPlayers()[5].getLoan() == null){
            pretPlayer6.setText("Pas de prêt en cours");
          } else {
            pretPlayer6.setText("Prêt : ( " + String.valueOf(fieldController.getModel().getPlayers()[5].getLoan().getNumberOfTurns() + 1) + " tours restants ) " + String.valueOf(fieldController.getModel().getPlayers()[5].getLoan().getValue()));
          }

          //If player 6 is in prison, bars are displayed.
          if(fieldController.getModel().getPlayers()[5].isInJail())
          {
            inJailPlayer6.setVisible(true);
          } else {
            inJailPlayer6.setVisible(false);
          }

          //If it is player 5's turn, they are notified
          if(fieldController.getModel().getCurrent()==5)
          {
            player6round.setVisible(true);

          } else {
            player6round.setVisible(false);

            inJailPlayer6.setVisible(false);

          }

          //A check is made to see if the player is still playing or has lost. If so, they are notified
          if(!fieldController.getModel().getPlayers()[5].getStillPlaying())
          {
            endPlayer6.setVisible(true);
            inJailPlayer6.setVisible(false);
          }
        }
      }

      //We see if the players are in jail or not, if so, we display bars
      if(fieldController.getModel().getPlayers()[0].isInJail())
      {
        inJailPlayer1.setVisible(true);
      } else {
        inJailPlayer1.setVisible(false);
      }

      if(fieldController.getModel().getPlayers()[1].isInJail())
      {
        inJailPlayer2.setVisible(true);
      } else {
        inJailPlayer2.setVisible(false);
      }

      if(fieldController.getModel().getPlayers()[2].isInJail())
      {
        inJailPlayer3.setVisible(true);
      } else {
        inJailPlayer3.setVisible(false);
      }

      if(fieldController.getModel().getPlayers()[3].isInJail())
      {
        inJailPlayer4.setVisible(true);
      } else {
        inJailPlayer4.setVisible(false);
      }



      //Here you can see if the players have lost or not. If so, a cross is displayed.
      if(!fieldController.getModel().getPlayers()[0].getStillPlaying())
      {
        endPlayer1.setVisible(true);
        inJailPlayer1.setVisible(false);
      }

      if(!fieldController.getModel().getPlayers()[1].getStillPlaying())
      {
        endPlayer2.setVisible(true);
        inJailPlayer2.setVisible(false);
      }

      if(!fieldController.getModel().getPlayers()[2].getStillPlaying())
      {
        endPlayer3.setVisible(true);
        inJailPlayer3.setVisible(false);
      }

      if(!fieldController.getModel().getPlayers()[3].getStillPlaying())
      {
        endPlayer4.setVisible(true);
        inJailPlayer4.setVisible(false);
      }


      //If it is a player's turn to play, they are mentioned
      if(fieldController.getModel().getCurrent()==0)
      {
        player1round.setVisible(true);
      } else {
        player1round.setVisible(false);
      }

      if(fieldController.getModel().getCurrent()==1)
      {
        player2round.setVisible(true);
      } else {
        player2round.setVisible(false);
      }

      if(fieldController.getModel().getCurrent()==2)
      {
        player3round.setVisible(true);
      } else {
        player3round.setVisible(false);
      }

      if(fieldController.getModel().getCurrent()==3)
      {
        player4round.setVisible(true);
      } else {
        player4round.setVisible(false);
      }


    }

   /**
       Sets the parent controller to the given controller
       @param main the new parent controller of this controller
     */
  public void transferController(FieldController main){
	fieldController = main;
  }


}
