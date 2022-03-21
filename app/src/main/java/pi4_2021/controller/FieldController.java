package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
import java.io.IOException;

import game.Game;
import game.Player;
import field.Field;
import field.FortuneWheel;
import field.Card;


/**
 * This controller class controls the game part of the program,
 * it links all sub controller classes and manages everything game-wise
 */
public class FieldController
{

        /**
         * Controller of the action menu
         */
        @FXML private FieldMenuController fieldMenuController;

        /**
         * Controller of the improved view of the actual location
         */
        @FXML private ActualLocationController actualLocationController;

        /**
         * Controller of the general view of the monopoly board
         */
        @FXML private LittleFieldController littleFieldController;

        /**
         * Controller of the players list view
         */
        @FXML private PlayersListController playersListController;

        /**
         * The game model of this controller
         */
        private Game model;

        @FXML private void initialize(){

        //initializes the fieldController attribute of each
        //sub controller class with this controller
        fieldMenuController.transferController(this);
        actualLocationController.transferController(this);
        littleFieldController.transferController(this);
        playersListController.transferController(this);
        }

        /**
         * Gets the game model of this controller
         * @return the game model of this controller
         */
        public Game getModel(){
                return model;
        }

        /**
         * Initializes the model for this controller
         */
        public void initModel(Player[] players, int nb_dices) throws IOException
        {
                model = new Game(players, nb_dices);
                littleFieldController.init();
                playersListController.init();
                fieldMenuController.init();
                playersListController.updatePlayer();
        }

        public void initModel() throws IOException
        {
                model = new Game();
                littleFieldController.init();
                playersListController.init();
                fieldMenuController.init();
                playersListController.updatePlayer();
        }

        /**
         * Initializes the model for this controller
         */
        public void initModel(Game game) throws IOException
        {
                model = game;

                littleFieldController.init();

                playersListController.init();

		//this updates the players list, so when this controller
		//is loaded, the display of the players list is made
		//according the game state ( such as current money or
		//players un jail)
		playersListController.updatePlayer();

                fieldMenuController.init();

		//updates the display of the actual location
		actualLocationController.update();
        }

        /**
         * Gets the controller of the board
         * @return the attribute littleFieldController
         */
        public LittleFieldController getLittleFieldController()
        {
                return this.littleFieldController;
        }

        public ActualLocationController getActualLocationController()
        {
                return this.actualLocationController;
        }

        public FieldMenuController getFieldMenuController()
        {
                return this.fieldMenuController;
        }

        public PlayersListController getPlayersListController(){
                return playersListController;
        }

        public void displayDialog(Card card)
        {
                Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                dialog.setTitle("Information");
                dialog.setHeaderText("Vous êtes sur Roue de la Fortune");
                dialog.setContentText(card.getContent());
                dialog.initStyle(StageStyle.UNDECORATED);
                Optional<ButtonType> result = dialog.showAndWait();
                this.getPlayersListController().updatePlayer();
                if (result.get() == ButtonType.OK)
                {
                        card.use(this.model.getCurrentPlayer());
                        this.getPlayersListController().updatePlayer();
                }
        }
}
