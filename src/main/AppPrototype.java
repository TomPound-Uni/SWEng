package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

public class AppPrototype extends Application{
	private int defaultXSize = 1280;
	private int defaultYSize = 720;
	
	private static Scene home;
	private static Scene main;
	private static Scene settings;
	private static Scene scene1;
	private static Scene scene2;
	
	private static Stage stage;
	
	private static int sceneNo = 1;
	
	private Document xmlDoc;
	
	private double yOffset = 0;
	private double xOffset = 0;
	
	private FileChooser fc = new FileChooser();
		
	public static void main(String[] args) {
		System.out.println("Application Started...");
		launch(args);
		System.out.println("Application Finished...");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//LOAD XML
		xmlDoc = XMLParser.getDocument("XML.xml");
		xmlDoc.getDocumentElement().normalize();
		//Start
		stage = primaryStage;
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setHeight(defaultYSize);
		stage.setWidth(defaultXSize);
		System.out.println("Stage Setup...");
		stage.show();
		
		setupMain();
		setupHome();
		settings = setupContentScreen("settings");
		scene1 = setupContentScreen("scene1");
		scene2 = setupContentScreen("scene2");
		
		File file = fc.showOpenDialog(stage);

		//COULD USE A FOR LOOP FOR ALL THE SCREEN TAGS HERE
		//AUTOMATICALLY CREATES THE CORRECT AMOUNT OF SCREENS
		
		stage.setScene(home);
		//Utils.pause(1000);
	}
	
	private void setupMain() throws FileNotFoundException {

		Button launch = new Button("Launch Presentation");
		SubScene hotBar = HotBar.createBar("Main Screen", defaultXSize, defaultYSize/20);
		hotBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
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
		bp.setTop(hotBar);
		bp.setCenter(imageGroup);
		
		//Finalise Scene
		main = new Scene(bp);
		main.getStylesheets().add("style/mainScreen.css");
		System.out.println("Main Screen Setup...");
	}
	
	private void setupHome(){
		Button launch = new Button("Launch Presentation");
		SubScene hotBar = HotBar.createBar("Home Screen", defaultXSize, defaultYSize/20);
		launch.setOnAction(e -> buttonPressed());
		
		BorderPane bp = new BorderPane();
		bp.setTop(hotBar);
		bp.setCenter(launch);
		
		//Finalise Scene
		home = new Scene(bp);
		home.getStylesheets().add("style/homeScreen.css");
		System.out.println("Home Screen Setup...");
	}
	
	private Scene setupContentScreen(String sceneName) throws FileNotFoundException {
		Text mainText = new Text();
		Text sceneTitle = new Text();
		System.out.println("ROOT NODE:" + xmlDoc.getDocumentElement().getNodeName());
		NodeList nList = xmlDoc.getElementsByTagName(sceneName);
		System.out.println("LIST" + nList.getLength());
		for(int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				System.out.println("Title : " + eElement.getElementsByTagName("title").item(0).getTextContent());
				System.out.println("Body : " + eElement.getElementsByTagName("body").item(0).getTextContent());
				mainText =  new Text(eElement.getElementsByTagName("body").item(0).getTextContent());
				sceneTitle = new Text(eElement.getElementsByTagName("title").item(0).getTextContent());
			}
		}
		
		SubScene hotBar = HotBar.createBar(sceneTitle.getText(), defaultXSize, defaultYSize/20);
		//mainText =  new Text(eElement.getElementsByTagName("body").item(0).getTextContent());
		mainText.setWrappingWidth(defaultXSize/2);
		mainText.setId("body");
		
		BorderPane bp = new BorderPane();
		
		Image car = new Image(new FileInputStream("src/images/car.jpg"));
		ImageView imageView = new ImageView(car);
		imageView.setFitWidth(defaultXSize/2);
		imageView.setPreserveRatio(true);
		Group imageGroup =  new Group();
		
		imageGroup.getChildren().add(imageView);
		//Add elements
		bp.setTop(hotBar);
		bp.setLeft(imageGroup);
		bp.setRight(mainText);
		
		//Finalise Scene
		Scene scene = new Scene(bp);
		scene.getStylesheets().add("style/contentScreen.css");
		System.out.println(sceneName + " Screen Setup...");
		return scene;
	}
	

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
