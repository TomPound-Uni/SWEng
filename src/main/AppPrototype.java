package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//XML COMPONENTS
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//JAVAFX COMPONENTS
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tools.*;

public class AppPrototype extends Application{ //EXTENDS JAVAFX APPLICATION
	private int defaultXSize = 1280;
	private int defaultYSize = 720;
	
	//Global Scene Names
	private static Scene home;
	private static Scene main;
	private static Scene settings;
	private static Scene scene1;
	private static Scene scene2;
	
	//Stage for the application
	private static Stage stage;
	//Screen No. Tracker
	private static int sceneNo = 1;
	
	//Document to Load XML in memory
	private Document xmlDoc;

	//Current screen offset
	private double yOffset = 0;
	private double xOffset = 0;
	
	//Inbuilt file viewer, dependent on OS
	private FileChooser fc = new FileChooser();
		
	public static void main(String[] args) {
		System.out.println("Application Started...");
		launch(args);	//Launch application
		System.out.println("Application Finished...");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//LOAD XML
		xmlDoc = XMLParser.getDocument("XML.xml");
		xmlDoc.getDocumentElement().normalize();
		//Start
		stage = primaryStage;	//Create stage (Frame)
		stage.initStyle(StageStyle.TRANSPARENT); //Remove bar at the top if window
		//Set dimensions
		stage.setHeight(defaultYSize);
		stage.setWidth(defaultXSize);
		System.out.println("Stage Setup...");
		stage.show(); //Show stage
		
		//Setup Scenes
		setupMain();
		setupHome();
		settings = setupContentScreen("settings");
		scene1 = setupContentScreen("scene1");
		scene2 = setupContentScreen("scene2");
		
		//Open file viewer and save selected file
		//File file = fc.showOpenDialog(stage);

		//COULD USE A FOR LOOP FOR ALL THE SCREEN TAGS HERE
		//AUTOMATICALLY CREATES THE CORRECT AMOUNT OF SCREENS
		
		stage.setScene(home); //Set initial scene in frame
		//Utils.pause(1000);
	}
	

	//Setup main screen
	private void setupMain() throws FileNotFoundException {
		Button launch = new Button("Launch Presentation");
		SubScene hotBar = HotBar.createBar("Main Screen", defaultXSize, defaultYSize/20);
		//Get mouse press coords and offset
		//USE GETTERS AND SETTERS FOR SCREEN OFFSET
		hotBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		//Move window with mouse
		hotBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX() - xOffset);
				stage.setY(event.getScreenY() - yOffset);	
			}
		});

		BorderPane bp = new BorderPane();
		Image car = new Image(new FileInputStream("src/images/car.jpg"));
		ImageView imageView = new ImageView(car);
		imageView.setFitWidth(1200);
		imageView.setFitHeight(700);
		imageView.setPreserveRatio(true);
		Group imageGroup =  new Group();
		
		imageGroup.getChildren().add(imageView);
		//Add elements
		bp.setTop(hotBar); //Apply layout (Set hotbar at the top of the screen)
		bp.setCenter(imageGroup);
		
		//Finalise Scene
		main = new Scene(bp); //Create new scene with elements from above
		main.getStylesheets().add("style/mainScreen.css"); //Use style from css sheet
		System.out.println("Main Screen Setup...");
	}
	
	//Setup Home screen
	private void setupHome(){
		Button launch = new Button("Launch Presentation");
		SubScene hotBar = HotBar.createBar("Home Screen", defaultXSize, defaultYSize/20); //Create hotbar for this scene
		hotBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		//Move window with mouse
		hotBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX() - xOffset);
				stage.setY(event.getScreenY() - yOffset);	
			}
		});
		launch.setOnAction(e -> buttonPressed()); //Event Listener for launch button
		
		BorderPane bp = new BorderPane(); //New layout
		bp.setTop(hotBar);
		bp.setCenter(launch);
		
		//Finalise Scene
		home = new Scene(bp);
		home.getStylesheets().add("style/homeScreen.css"); //Get styling from css sheet
		System.out.println("Home Screen Setup...");
	}
	
	//Setup universal content screen
	private Scene setupContentScreen(String sceneName) throws FileNotFoundException {
		Text mainText = new Text();
		Text sceneTitle = new Text();

		//Cycle through the XML file for specific tags
		System.out.println("ROOT NODE:" + xmlDoc.getDocumentElement().getNodeName());
		NodeList nList = xmlDoc.getElementsByTagName(sceneName); //Creates list of nodes of tags
		System.out.println("LIST" + nList.getLength()); //Finds amount of tags

		//Cycle through the tags to find specific attributes
		//This was found online for practice
		for(int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp); //Update node
			System.out.println("\nCurrent Element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode; //Split into elements
				System.out.println("Title : " + eElement.getElementsByTagName("title").item(0).getTextContent());
				System.out.println("Body : " + eElement.getElementsByTagName("body").item(0).getTextContent());
				mainText =  new Text(eElement.getElementsByTagName("body").item(0).getTextContent());
				sceneTitle = new Text(eElement.getElementsByTagName("title").item(0).getTextContent());
			}
		}
		
		SubScene hotBar = HotBar.createBar(sceneTitle.getText(), defaultXSize, defaultYSize/20); //Create hot bad for this specific scene
		//mainText =  new Text(eElement.getElementsByTagName("body").item(0).getTextContent());
		mainText.setWrappingWidth(defaultXSize/2); //Wraps text at correct width
		mainText.setId("body"); //Set CSS ID TAG for styling of just this element
		
		BorderPane bp = new BorderPane();
		
		Image car = new Image(new FileInputStream("src/images/car.jpg")); //Adding an image from a file
		ImageView imageView = new ImageView(car);
		imageView.setFitWidth(defaultXSize/2);
		imageView.setPreserveRatio(true);
		Group imageGroup =  new Group(); //Add to a group of elements
		
		imageGroup.getChildren().add(imageView); //Add image to group
		//Add elements
		bp.setTop(hotBar);
		bp.setLeft(imageGroup);
		bp.setRight(mainText);
		
		//Finalise Scene
		Scene scene = new Scene(bp); //Create scene
		scene.getStylesheets().add("style/contentScreen.css");
		System.out.println(sceneName + " Screen Setup...");
		return scene;
	}
	
	//Example button listener which cycles through the scenes
	public static void buttonPressed() {
		if(sceneNo == 0) {
			stage.setScene(home);
			sceneNo++;
		}else if(sceneNo == 1){
			stage.setScene(main);
			sceneNo++;
			
		}else if(sceneNo == 2){
			stage.setScene(scene1);
			sceneNo++;
		}else if(sceneNo == 3){
			stage.setScene(scene2);
			sceneNo++;
		
		}else if(sceneNo == 4){
			stage.setScene(settings);
			sceneNo=0;
		}

	}
	
}

//TODO
/*Cursor image change
Resize Window (Area in the bottom corner)
Create button behaviour file
Create 3D model viewer

*/