package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class App extends Application {
	
	public static Application app;
	
	@Override
	public void start(Stage primaryStage) {
		
		this.app = (Application) this;
		try {
			FXMLLoader app = new FXMLLoader (getClass().getResource("/application/Application.fxml"));
			BorderPane root = (BorderPane) app.load();
			Scene scene = new Scene(root,800,400);
			
			primaryStage.getIcons().add(new Image("/icon/Avatar.png"));
			primaryStage.setTitle("TNTVillage | NoNameoN");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void connection(String URL) {
		app.getHostServices().showDocument(URL);
	}
	
	public static void inviaEmail() {
		app.getHostServices().showDocument("mailto:" + "nonameon@protonmail.ch");
	}
	
}

/*--module-path "C:\Users\NoNameoN\Desktop\Computer\javafx\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml*/