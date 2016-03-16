package stuff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class WindowView {

	private MenuBar menuBar;
	private BorderPane rootPane;
	private TextArea inputArea;
	
	private FileChooser fileChooser;
	private HBox bottomPane;
	private Label wordCountLbl;
	private TextField wordCountField;
	private Label sentenceCountLbl;
	private TextField sentenceCountField;
	private Label fleschLbl;
	private TextField fleschField;
	
	private WordCountPane wordPane;
	private SentenceCountPane sentencePane;
	private FleschScorePane fleschPane;
	
	public WindowView(Stage stage){
		
		buildMenuBar(stage);
		buildBorderPane();
		buildBottomPane();
		
		setInputArea(new TextArea());
		
		rootPane.setTop(menuBar);
		rootPane.setCenter(getInputArea());
		rootPane.setBottom(bottomPane);

		stage.setScene(new Scene(rootPane, 1200, 900));
		stage.show();
	}

	public void buildMenuBar(Stage stage){
		menuBar = new MenuBar();
		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("TXT", "*.txt"));
		fileChooser.setTitle("Select A Text File");
		Menu fileMenu = new Menu("File");
		MenuItem openMenuItem = new MenuItem("Open");
		MenuItem saveMenuItem = new MenuItem("Save");
		MenuItem closeMenuItem = new MenuItem("Close");
		MenuItem exitMenuItem = new MenuItem("Exit");
		
		fileMenu.getItems().addAll(openMenuItem, saveMenuItem, closeMenuItem,
				exitMenuItem);
		
		exitMenuItem.setOnAction(action -> {
			Platform.exit();
		});
		
		openMenuItem.setOnAction(action -> {
			File file = fileChooser.showOpenDialog(stage);
			
			BufferedReader reader = null;
			try {
				reader = new BufferedReader( new FileReader (file));
			} catch (Exception e) {
				e.printStackTrace();
			}
		    String         line = null;
		    StringBuilder  stringBuilder = new StringBuilder();
		    String         ls = System.getProperty("line.separator");

		    try {
		        try {
					while( ( line = reader.readLine() ) != null ) {
					    stringBuilder.append( line );
					    stringBuilder.append( ls );
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

		        getInputArea().setText(stringBuilder.toString());
		    } finally {
		        try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		});
		
		saveMenuItem.setOnAction(action ->{
			String s = getInputArea().getText();
			
		});
		
		Menu editMenu = new Menu("Edit");
		MenuItem wordCountMenuItem = new MenuItem("Word Count");
		MenuItem sentenceCountMenuItem = new MenuItem("Sentence Count");
		MenuItem fleschScoreMenuItem = new MenuItem("Flesch Score");
		
		editMenu.getItems().addAll(wordCountMenuItem, sentenceCountMenuItem, 
				fleschScoreMenuItem	);
		
		wordCountMenuItem.setOnAction(action -> {
			wordPane = new WordCountPane(this);
		});
		sentenceCountMenuItem.setOnAction(action -> {
			sentencePane = new SentenceCountPane(this);
		});
		fleschScoreMenuItem.setOnAction(action -> {
			fleschPane = new FleschScorePane(this);
		});
		//need panes betch
		
		menuBar.getMenus().addAll(fileMenu, editMenu);
	}
	
	public void buildBorderPane(){
		rootPane = new BorderPane();
	}
	
	public void buildBottomPane(){
		bottomPane = new HBox();
		
		VBox leftBox = new VBox(50);
		VBox centerBox = new VBox(50);
		VBox rightBox = new VBox(50);
		
		wordCountLbl = new Label("Word Count");
		wordCountField = new TextField();
		sentenceCountLbl = new Label("Sentence Count");
		sentenceCountField = new TextField();
		fleschLbl = new Label("Flesch Score");
		fleschField = new TextField();
		
		leftBox.getChildren().addAll(wordCountLbl, wordCountField);
		centerBox.getChildren().addAll(sentenceCountLbl, sentenceCountField);
		rightBox.getChildren().addAll(fleschLbl, fleschField);
		bottomPane.getChildren().addAll(leftBox, centerBox, rightBox);
	}

	public TextArea getInputArea() {
		return inputArea;
	}

	public void setInputArea(TextArea inputArea) {
		this.inputArea = inputArea;
	}
}
