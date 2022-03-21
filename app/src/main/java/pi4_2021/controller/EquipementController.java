package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;

import javafx.event.ActionEvent;

import javafx.stage.Stage;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.util.ArrayList;

import java.io.IOException;

import field.Domain;
import field.Property;
import field.PublicService;
import field.WaterProvider;
import field.ElectricityProvider;
import field.WaterContract;
import field.ElectricityContract;
import game.Game;
import game.Player;

/**
 * This class controlls the Equipement functionality
 */
public class EquipementController {
  /**
   * A menu button that'll contain the domains.
   */
  @FXML private MenuButton domains;
  /**
   * A combo box that'll contain the available contracts
   */
  @FXML private ComboBox contracts;
  /**
   * A label where we display a message.
   */
  @FXML private Label display;
/**
 * The domain to be equiped
 */
  private Domain selectedDomain;

  /**
   * The game being played
   */
  private Game model;

  /**
   * Initializes the controller.
   * @param model The game being played.
   */
  public void init(Game model)
  {
    this.model = model;
    this.selectedDomain = null;

    for(Property d : model.getCurrentPlayer().getProperties())
    {
      if(d instanceof Domain)
      {
        if(!(((Domain)d).waterEquiped()) || !(((Domain)d).electricicyEquiped()))
        {
          domains.getItems().addAll(new DomainMenuItem((Domain)d));
        }
      }
    }

  }
/**
 * This method adds the chosen contrant to the given domain
 * @param domain the domain to equip
 */
  public void fillContracts(Domain domain)
  {

    ArrayList<PublicService> pss = new ArrayList<>();
    if(model.getWaterProvider().getOwner() != null && !domain.waterEquiped())
    {
      pss.add(model.getWaterProvider());
    }
    if(model.getElectricityProvider().getOwner() != null && !domain.waterEquiped())
    {
      pss.add(model.getElectricityProvider());
    }
    if(pss.size() > 0)
    {
      ObservableList<PublicService> list = FXCollections.observableArrayList(pss);
      contracts.setItems(list);
    }
    else
    {
      contracts.setEditable(true);
    }
  }

  /**
   * This inner class represents a Domain MenuItem.
   * It is a MenuItem with a domain model associated to it.
   * It is used for the update of list of available contracts
   * according to the selected Domain
   */
  private class DomainMenuItem extends MenuItem {

/**
 * The model for this domain item
 */
 Domain domain;

/**
 * Instantiates a new DomainItem and the model associated to it
 * @param model the model of this Domain item
 */
 DomainMenuItem(Domain domain){
    super(domain.getName());
    this.domain = domain;

    //when this district is selected
    setOnAction(event -> {
      selectedDomain = domain;
      domains.setText(domain.getName());
      fillContracts(domain);
  });
}
  }

/**
 * This method is acociated to the next button. It verifies that all the elements are selected equipes the given doamin
 * and changes the scence to the current game
 * @param  event       the mouse clik
 * @throws IOException thrown if the current game view file is not found
 */
  public void nextAction(ActionEvent event) throws IOException
  {
    if(selectedDomain == null)
    {
      display.setText("Choisissez un domaine");
      return;
    }
    if(contracts.getValue() == null)
    {
      display.setText("Choisissez un contrat");
      return;
    }
    else if(contracts.getValue() instanceof WaterProvider)
    {
      WaterContract wc = new WaterContract((WaterProvider)(contracts.getValue()));
      wc.install(selectedDomain);
    }
    else if(contracts.getValue() instanceof ElectricityProvider)
    {
      ElectricityContract ec = new ElectricityContract((ElectricityProvider)(contracts.getValue()));
      ec.install(selectedDomain);
    }

    backAction(event);
  }

  /**
   * The button associated to the click on the back button.
   * @param  event       Event.
   * @throws IOException in case the fxml isn't found.
   */
  public void backAction(ActionEvent event) throws IOException
  {
    //loads the view of the field window
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Field.fxml"));


    //instantiates the root node for all elements in the graph of the game scene
    Parent root = (Parent) loader.load();

    //VERY IMPORTANT: load the loader then access its controller
    //recovers the controller of the Field fxml and initializes its data
    FieldController flc = loader.getController();
    flc.initModel(model);
    flc.getPlayersListController().updatePlayer();

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
