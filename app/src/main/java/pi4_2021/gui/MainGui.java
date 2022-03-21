package gui;

import field.*;
import game.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.stage.StageStyle;
import controller.*;
import utils.CSVReader;
import java.io.File;

//MAin class that launch the graphical interface
public class MainGui extends Application
{

    /**
       The main controller of this application
     */
    @FXML
    private MainController controller;

    @FXML
    private FieldMenuController fmc;

    public void start(Stage primaryStage) throws Exception {

	//defines the title of the stage
	primaryStage.setTitle("Monopoly");

	//FIX THIS : causes the app to be sometimes the size of the
	//player's selection
	//sets the app as non resizable
	primaryStage.setResizable(false);

	//initialization of the main controller
	controller = new MainController();

	//initialization of the view of the controller
	controller.setView(this);


	setSceneToShow("/fxml/Menu.fxml", primaryStage);

	//shows the primary stage
	primaryStage.show();
    }

    /**
       Sets the scene to show on the given stage with its given path
       @param path the path to the FXML resources
       @param primaryStage the stage the scene is put on
     */
    public void setSceneToShow(String path, Stage primaryStage) throws Exception {
      Scene scene = FXMLLoader.load(getClass().getResource(path));

      // scene.getStylesheets().clear();
      // scene.getStylesheets().add("style/dark.css");
    	primaryStage.setScene(scene);
    }

    public static void main(String[] args) throws Exception{

	launch(args);
    }
}
