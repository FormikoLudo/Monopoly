package controller;

import gui.MainGui;
import game.Game;
import javafx.fxml.FXML;

/**
   Main controller class that links the view of the game and its data
 */
public class MainController {

    /**
       The main gui of the application
     */
    @FXML private MainGui view;

    /**
       The game model
     */
    private Game model;

    /**
       The controller of the menu scene
     */
    @FXML private MenuController menuController;

    /**
     * The controller of the game
     */
    @FXML private FieldController fieldController;

    /**
       Sets the view of this controller
       @param view the new view of this controller
     */
    public void setView(MainGui view){
	this.view = view;
    }

    /**
       Sets the model of this controller
       @param model the new model of this controller
     */
    public void setModel(Game model){
	this.model = model;
    }
}
