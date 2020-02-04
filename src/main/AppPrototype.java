package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
		SubScene hotBar = createBar();
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
		SubScene hotBar = createBar();
		launch.setOnAction(e -> buttonPressed());
		
		BorderPane bp = new BorderPane();
		bp.setTop(hotBar);
		bp.setCenter(launch);
		
		//Finalise Scene
		home = new Scene(bp);
		home.getStylesheets().add("style/homeScreen.css");
		System.out.println("Home Screen Setup...");
	}
	
	public SubScene createBar() {
		Button settings = new Button("Settings");
		Button open = new Button("Open");
		Button newFile = new Button("New");
		Button close = new Button("X");
		Button max = new Button("MAX");
		Button min = new Button("MIN");
		Text title = new Text("INTERACTIVE LEARNING APPLICATION");
		
		
		
		settings.setOnAction(e -> settingsButtonPressed());
		open.setOnAction(e -> openFileButtonPressed());
		newFile.setOnAction(e -> newFileButtonPressed());
		max.setOnAction(e -> maxButtonPressed());
		min.setOnAction(e -> minButtonPressed());		
		
		close.setOnAction(e -> closeButtonPressed());
		
		title.setId("title");
		
		title.setTextAlignment(TextAlignment.CENTER);
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(10,10,10,10));
		gp.setHgap(0);
		gp.setAlignment(Pos.CENTER);
		gp.setHalignment(title, HPos.CENTER);
		gp.add(settings,0,0);
		gp.add(open,1,0);
		gp.add(newFile,2,0);
		gp.add(title,3,0);
		gp.add(min,4,0);
		gp.add(max,5,0);
		gp.add(close,6,0);
		
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(5);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(5);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(5);
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setPercentWidth(70);
		ColumnConstraints column5 = new ColumnConstraints();
		column5.setPercentWidth(5);
		ColumnConstraints column6 = new ColumnConstraints();
		column6.setPercentWidth(5);
		ColumnConstraints column7 = new ColumnConstraints();
		column7.setPercentWidth(5);
		
		gp.getColumnConstraints().addAll(column1,column2,column3
				,column4,column5,column6,column7);
		//gp.setGridLinesVisible(true);
		SubScene hotBar = new SubScene(gp, defaultXSize, defaultYSize/20);
		//hotBar.setUserAgentStylesheet("style/homeScreen.css");
		return hotBar;
	}
	public void buttonPressed() {
		stage.setScene(main);
	}
	public void maxButtonPressed() {
		stage.setMaximized(true);
		defaultXSize = 1920;
	}
	public void minButtonPressed() {
		System.out.println("Minimized");
	}
	public void openFileButtonPressed() {
		System.out.println("Open File Button Pressed...");
	}
	public void newFileButtonPressed() {
		System.out.println("New File Button Pressed...");
	}
	public void settingsButtonPressed() {
		System.out.println("Settings Button Pressed...");
	}
	public void closeButtonPressed() {
		System.exit(0);
	}
	
}
