package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.w3c.dom.Document;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tools.*;

public class AppPrototype extends Application{
	private int defaultXSize = 1280;
	private int defaultYSize = 720;
	
	private static Scene home;
	private static Scene main;
	private Scene settings;
	private static Scene scene1;
	private static Scene scene2;
	
	private static Stage stage;
	
	private static int sceneNo = 0;
	
	private Document xmlDoc;
		
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
		scene1 = setupContentScreen("scene1");
		scene2 = setupContentScreen("scene2");
		
		stage.setScene(home);
		Utils.pause(1000);
	}
	
	private void setupMain() throws FileNotFoundException {
		Button launch = new Button("Launch Presentation");
		SubScene hotBar = HotBar.createBar("Main Screen", defaultXSize, defaultYSize/20);
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
		//main.getStylesheets().add("style/mainScreen.css");
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
		//home.getStylesheets().add("style/homeScreen.css");
		System.out.println("Home Screen Setup...");
	}
	
	private Scene setupContentScreen(String title) throws FileNotFoundException {
		SubScene hotBar = HotBar.createBar(title, defaultXSize, defaultYSize/20);
		Text mainText =  new Text("NVJKNRSKJVNEJKNAVJKNEKJANVJLKNELJKANVJKENAJVNEJNVKENANEVKNEKV");
		mainText.setWrappingWidth(defaultXSize/2);
		
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
		System.out.println(title + " Screen Setup...");
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
			sceneNo=0;
		}

	}
	
}
