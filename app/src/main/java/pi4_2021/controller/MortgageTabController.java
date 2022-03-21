package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.HashMap;

import field.Property;

/**
 * This class is the controller of the mortgage tab that allows
 * a player during their turn to mortgage their properties in
 * order to earn money.
 */
public class MortgageTabController {

    /**
     * The parent controller of this controller
     */
    @FXML private MortgagePaneController parentController;

    /**
     * The list of not mortgaged properties to display
     */
    @FXML private ListView<String> notMortgagedProperties;

    /**
     * The button that mortgages the selected properties
     */
    @FXML private Button mortgageButton;

    /**
     * A hashmap of properties with their string representation
     */
    private HashMap<String,Property> mapProperties;

    /**
     * The list of data to display on the gui
     */
    private ObservableList<String> dataToDisplay;

    /**
     * The array list of String representing the properties displayed
     */
    private ArrayList<String> dataRep;

    /**
     * Transfers the parent controller to this controller
     * @param parent the parent controller
     */
    public void transferParentController(MortgagePaneController parent){
	parentController = parent;
    }

    /**
     * Initializes the list of properties for this controller
     */
    public void initModel(){

	//create a map compiling string representation and property associated
	mapProperties = new HashMap<>();

	//recover unmortgaged properties from parent model
	ArrayList<Property> unmortgagedProperties =
	    parentController.getModel().getUnmortgagedProperties();

	//instantiation of the array list of string reps
	dataRep = new ArrayList<>();
	
	String representation = new String();
	
	//for each property in the list
	for(Property p : unmortgagedProperties){

	    //get the reprensetation of the property
	    representation =
		parentController.getModel().propertyMortgageDescription(p);

	    //add it to the map with key string representation and value property
	    mapProperties.put(representation,p);

	    //add it to the list of string reps
	    dataRep.add(representation);
	}
	
	//add the list of properties represented by their string to the
	//display list
	dataToDisplay =
	    FXCollections.observableArrayList(dataRep);

	//add data content to the list view
	notMortgagedProperties.setItems(dataToDisplay);
    }

    /**
     * Action of the button mortgage.
     * Gets the selected items (properties) and mortgages them
     */
    @FXML public void mortgageButtonAction() {

	//recover selected item from the list
	String selectedItem = 
	    notMortgagedProperties.getSelectionModel().getSelectedItem();
	
	//mortgage it
	if(mapProperties.get(selectedItem).mortgage()){
	    
	    //remove string rep of the selected item from the dataRep
	    dataRep.remove(selectedItem);

	    //update observable list and display
	    dataToDisplay = FXCollections.observableArrayList(dataRep);
	    notMortgagedProperties.setItems(dataToDisplay);

	    //update UnmortgageTab display
	    parentController.getUnmortgageTabController().initModel();
	}
    }
}
