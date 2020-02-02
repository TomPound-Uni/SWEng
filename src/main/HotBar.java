package main;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class HotBar {
	private int width;
	Scene hotBar;
	
	public HotBar(int Width) {
		this.width = width;
		createBar();
	}
	
	public Scene createBar() {
		
		Button close = new Button("X");
		Button max = new Button("MAXIMISE");
		Button min = new Button("MINIMISE");
		Text title = new Text("Slide X");
		GridPane gp = new GridPane();
		gp.add(title,1,0);
		gp.add(close,2,0);
		gp.add(max,3,0);
		gp.add(min,4,0);
		return hotBar;
	}
}
