package main;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class HotBar {
	
	public static SubScene createBar(String slideTitle, int width, int height) {
		Button settings = new Button("Settings");
		Button open = new Button("Open");
		Button newFile = new Button("New");
		Button close = new Button("X");
		Button max = new Button("MAX");
		Button min = new Button("MIN");
		Text title = new Text(slideTitle);
		
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
		SubScene hotBar = new SubScene(gp, width, height);
		hotBar.setUserAgentStylesheet("style/hotBar.css");
		return hotBar;
	}
	public static void maxButtonPressed() {
		//stage.setMaximized(true);
		//defaultXSize = 1920;
		System.out.println("Maximized...");
	}
	public static void minButtonPressed() {
		System.out.println("Minimized...");
	}
	public static void openFileButtonPressed() {
		System.out.println("Open File Button Pressed...");
		AppPrototype.buttonPressed();
	}
	public static void newFileButtonPressed() {
		System.out.println("New File Button Pressed...");
	}
	public static void settingsButtonPressed() {
		System.out.println("Settings Button Pressed...");
	}
	public static void closeButtonPressed() {
		System.exit(0);
	}
	
}
