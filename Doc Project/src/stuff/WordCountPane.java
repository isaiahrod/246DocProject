package stuff;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WordCountPane {

	private Scene scene;
	private VBox pane;
	private EditorDocument editor;
	
	public WordCountPane(WindowView window){
		
		Stage stage = new Stage();
		Label countLbl = new Label("Word Count");
		TextField countField = new TextField();
		editor = new EditorDocument(window.getInputArea().getText());
		countField.setText(String.valueOf((editor.getNumberOfWords())));
		
		pane = new VBox();
		pane.getChildren().addAll(countLbl, countField);
		stage.setScene(new Scene(pane, 400, 400));
		stage.show();
	}
}
