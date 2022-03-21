package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Collection;

import game.Game;
import game.Player;
import game.Skill;

public class SkillsController {

    /**
     * The current game
     */
    private Game game;

    /**
     * The current player, model of this controller
     */
    private Player model;

    /**
     * The skill to upgrade
     */
    private Skill upgradeThisSkill;

    /**
     * Returns to the game
     */
    @FXML private Button returnButton;

    /**
     * Selects a skill
     */
    @FXML private MenuButton skillChoiceMenuButton;

    /**
     * Displays the money of the model player
     */
    @FXML private Label playerMoneyLabel;

    /**
     * Displays the name of the selected skill
     */
    @FXML private Label nameLabel;

    /**
     * Displays the current level of the selected skill
     */
    @FXML private Label currentLevelLabel;

    /**
     * Displays the maximum level the selected skill can be
     */
    @FXML private Label maxLevelLabel;

    /**
     * Displays the level cost of the selected skill
     */
    @FXML private Label levelCostLabel;

    /**
     * Initializes the model for this controller
     */
    public void initModel(Game game){
	this.game = game;
	model = game.getCurrentPlayer();
	resetDisplay();
	updateMoney();
	initSkillChoice();
    }

    /**
     * Updates the money label showing the money of the model player
     */
    private void updateMoney(){
	playerMoneyLabel.setText("" + model.getMoney());
    }

    /**
     * Resets the display of the selected skill
     */
    private void resetDisplay(){
	nameLabel.setText("# selectionnez une compétence");
	currentLevelLabel.setText("# selectionnez une compétence");
	maxLevelLabel.setText("# selectionnez une compétence");
	levelCostLabel.setText("# selectionnez une compétence");
    }

    /**
     * Initialize the skill choice
     */
    private void initSkillChoice(){
	Collection<Skill> skillCollection = model.getSkillsMap().values();
	for(Skill skill : skillCollection){
	    skillChoiceMenuButton.getItems().addAll(new SkillMenuItem(skill));
	}
    }

    /**
     * Displays the selected skill information
     */
    private void displaySkill(Skill skill){
	resetDisplay();
	updateMoney();
	upgradeThisSkill = skill;
	nameLabel.setText(skill.toString());
	currentLevelLabel.setText("" + skill.getCurrentLevel());
	maxLevelLabel.setText("" + skill.getTOTALLEVELS());
	levelCostLabel.setText("" + skill.getLevelCost());
    }

    /**
     * On click, upgrades the selected skill if possible
     */
    @FXML private void upgradeButtonAction(){
	if(upgradeThisSkill == null){
	    return;
	}

	upgradeThisSkill.increaseLevel(model);
	displaySkill(upgradeThisSkill);
    }

    /**
     * Returns to the game scene
     */
    @FXML private void returnButtonAction(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Field.fxml"));
	Parent root = (Parent) loader.load();
	FieldController flc = loader.getController();
	flc.initModel(game);
	Scene scene = new Scene(root);
  String path = ((Scene)((Node)event.getSource()).getScene()).getStylesheets().get(0);
  scene.getStylesheets().clear();
  scene.getStylesheets().add(path);
	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	window.setScene(scene);
	window.show();
    }

    /**
     * Represents the skill menu item that the player selects to upgrade it (or not)
     */
    private class SkillMenuItem extends MenuItem {

	/**
	 * The model of the skill menu item
	 */
	Skill model;

	/**
	 * Instantiates the skill menu item
	 * @param model the skill model for this skill menu item
	 */
	SkillMenuItem(Skill model){
	    super(model.toString());
	    this.model = model;

	    //on selection, displays the selected skill
	    setOnAction( e -> {
		    displaySkill(model);
		});
	}
    }
}
