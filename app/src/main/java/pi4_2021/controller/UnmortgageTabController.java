package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.HashMap;

import field.Property;

public class UnmortgageTabController {

    /**
     * The parent controller of this controller
     */
    @FXML private MortgagePaneController parentController;

    /**
     * The list vie of mortgaged properties to display
     */
    @FXML private ListView<String> mortgagedProperties;

    /**
     * Allows the player to unmortgage their properties
     */
    @FXML private Button unmortgageButton;
    
    /**
     * Map of properties as value and their string rep as key
     */
    private HashMap<String, Property> mapProperties;

    /**
     * Observalbe list of the data to display on gui
     */
    private ObservableList<String> dataToDisplay;

    /**
     * Array containing the string rep of each property in the map
     */
    private ArrayList<String> dataRep;

    /**
     * Transfer the parent controller to this controller
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
	ArrayList<Property> mortgagedPropertiesModel =
	    parentController.getModel().getMortgagedProperties();

	//instantiation of the array list of string reps
	dataRep = new ArrayList<>();
	
	String representation = new String();
	
	//for each property in the list
	for(Property p : mortgagedPropertiesModel){

	    //get the reprensetation of the property
	    representation =
		parentController.getModel().propertyUnmortgageDescription(p);

	    //add it to the map with key string representation and value property
	    mapProperties.put(representation,p);

	    //add it to the list of string reps
	    dataRep.add(representation);
	}

	//add the list of properties represented by their string to the
	//display list if not null
	dataToDisplay =
	    FXCollections.observableArrayList(dataRep);

	//add data content to the list view
	mortgagedProperties.setItems(dataToDisplay);
    }

    /**
     * On click, unmortgages the selected property, removes it from
     * the list and updates the mortgage tab
     */
    @FXML public void unmortgageButtonAction(){
	
	//recover selected item from the list
	String selectedItem = 
	    mortgagedProperties.getSelectionModel().getSelectedItem();
	
	//unmortgage it
	mapProperties.get(selectedItem).unMortgage();

	//remove string rep of the selected item from the dataRep
	dataRep.remove(selectedItem);

	//update observable list and display
	dataToDisplay = FXCollections.observableArrayList(dataRep);
	mortgagedProperties.setItems(dataToDisplay);

	//update MortgageTab display
	parentController.getMortgageTabController().initModel();

    }
}
