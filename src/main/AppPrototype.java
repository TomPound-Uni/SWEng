package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppPrototype extends Application{
	private int defaultXSize = 1280;
	private int defaultYSize = 720;
	
	private Scene home;
	private Scene main;
	private Scene settings;
	private Scene scene1;
	private Scene scene2;
	
	private Stage stage;
		
	public static void main(String[] args) {
		System.out.println("Application Started...");
		launch(args);
		System.out.println("Application Finished...");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Start
		stage = primaryStage;
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setHeight(defaultYSize);
		stage.setWidth(defaultXSize);
		System.out.println("Stage Setup...");
		stage.show();
		
		setupMain();
		setupHome();

		stage.setScene(home);
		//Utils.pause(1000);
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
	

	public void buttonPressed() {
		stage.setScene(main);
	}
	
}
