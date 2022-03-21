package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Parent;

import game.Mortgage;

public class MortgagePaneController {

    /**
     * The mortgage object
     */
    private Mortgage model;

    /**
     * The button that returns to the game
     */
    @FXML private Button retour;

    /**
     * The parent controller of this controller
     */
    @FXML private FieldMenuController fieldMenuController;

    /**
     * The MortgageTab controller
     */
    @FXML private MortgageTabController mortgageTabController;

    /**
     * The UnmortgageTab controller
     */
    @FXML private UnmortgageTabController unmortgageTabController;

    /**
     * Initializes this controller and its sub controllers
     */
    @FXML private void initialize(){
	mortgageTabController.transferParentController(this);
	unmortgageTabController.transferParentController(this);
    }

    /**
     * Transfers the parent controller to this controller
     * @param parent the parent controller
     */
    public void transferParentController(FieldMenuController parent){
	fieldMenuController = parent;
    }

    @FXML public void retourAction(ActionEvent event) throws IOException {
	//loads the field gui and goes back to it
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Field.fxml"));
	Parent root = (Parent) loader.load();
	FieldController flc = loader.getController();
	flc.initModel(fieldMenuController.getParentController().getModel());
	flc.getPlayersListController().updatePlayer();
	Scene newScene = new Scene(root);
  String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
  newScene.getStylesheets().clear();
  newScene.getStylesheets().add(path);
	Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
	window.setScene(newScene);
	window.show();
        flc.getActualLocationController().update();
    }

    /**
     * Initializes this controller model with the given model
     * @param model the model of this controller
     */
    public void initModel(Mortgage mortgage){
	model = mortgage;
	mortgageTabController.initModel();
	unmortgageTabController.initModel();
    }

    /**
     * Gets the model of this controller
     * @return the player model
     */
    public Mortgage getModel(){
	return model;
    }

    /**
     * Gets the mortgage tab controller
     * @return the field mortgageTabController
     */
    public MortgageTabController getMortgageTabController(){
	return mortgageTabController;
    }

    /**
     * Gets the unmortgage tab controller
     * @return the field unmortgageTabController
     */
    public UnmortgageTabController getUnmortgageTabController(){
	return unmortgageTabController;
    }
}
