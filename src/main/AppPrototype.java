package main;

import javax.swing.GroupLayout.Alignment;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
		gridPane.setAlignment(Pos.CENTER);
		//gridPane.setVgap(660);
		
		//Add elements
		gridPane.add(title,1,1);
		
		gridPane.add(hotBar, 0, 0);
		gridPane.setColumnSpan(hotBar,2);
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(3);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(97);
		gridPane.getRowConstraints().addAll(row1, row2);

		//gridPane.setGridLinesVisible(true);
		
		//Finalise Scene
		home = new Scene(gridPane);
		//home.getStylesheets().add("style/homeScreen.css");
		System.out.println("Home Screen Setup...");
	}
	
	public void createBar() {
		Button settings = new Button("Settings");
		Button open = new Button("Open");
		Button newFile = new Button("New");
		Button close = new Button("X");
		Button max = new Button("MAX");
		Button min = new Button("MIN");
		Text title = new Text("BINGO BONGO!!!!!!");
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
		hotBar = new SubScene(gp, defaultXSize, defaultYSize/20);
	}
}
