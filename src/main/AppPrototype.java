package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tools.Utils;

public class AppPrototype extends Application{
	private int defaultXSize = 1280;
	private int defaultYSize = 720;
	
	private Scene home;
	private Scene main;
	private Scene settings;
	private Scene scene1;
	private Scene scene2;
	private SubScene hotBar;
	
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
		//stage.setTitle("Engineering Application"); //CHANGE WHEN NEEDED
		stage.setHeight(defaultYSize);
		stage.setWidth(defaultXSize);
		System.out.println("Stage Setup...");
		stage.show();
		
		createBar();
		setupHome();
		stage.setScene(home);
		Utils.pause(1000);
	}

	private void setupHome(){
		Text title = new Text("Engineering Application!");
		title.setId("title");
		GridPane gridPane = new GridPane();
		
		gridPane.setPadding(new Insets(10,10,10,10));
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		gridPane.setAlignment(Pos.CENTER);
		
		//Add elements
		gridPane.add(title,1,1);
		
		gridPane.add(hotBar, 0, 0);
		gridPane.setColumnSpan(hotBar,3);
		gridPane.setGridLinesVisible(true);
		//Finalise Scene
		home = new Scene(gridPane);
		//home.getStylesheets().add("style/homeScreen.css");
		System.out.println("Home Screen Setup...");
	}
	
	public void createBar() {
		Button close = new Button("X");
		Button max = new Button("MAXIMISE");
		Button min = new Button("MINIMISE");
		Text title = new Text("Slide X");
		GridPane gp = new GridPane();
		gp.add(title,1,0);
		gp.add(close,2,0);
		gp.add(max,3,0);
		gp.add(min,4,0);
		hotBar = new SubScene(gp, defaultXSize, defaultYSize);
	}
}
