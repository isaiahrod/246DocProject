package stuff;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FleschScorePane {

	private Scene scene;
	private VBox pane;
	private EditorDocument editor;
	
	public FleschScorePane(WindowView window){
		
		Stage stage = new Stage();
		Label countLbl = new Label("Flesch Score");
		TextField countField = new TextField();
		editor = new EditorDocument(window.getInputArea().getText());
		countField.setText(String.valueOf((editor.getFleschScore
				(editor.getNumberOfWords(), editor.getNumberOfSentences(),
						editor.getNumberOfSyllables()))));
		
		pane = new VBox();
		pane.getChildren().addAll(countLbl, countField);
		stage.setScene(new Scene(pane, 400, 400));
		stage.show();
	}
}
