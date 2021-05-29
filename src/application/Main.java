package application;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 800;
    private static final String WINDOW_TITLE = "Text2Speech";
    private static final String FXML_FILENAME = "main.fxml";

    @Override
    public void start(Stage primaryStage)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_FILENAME));
            Parent root = loader.load();
            primaryStage.setTitle(WINDOW_TITLE);
            primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
            primaryStage.initStyle(StageStyle.UNDECORATED);

            MainController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);

            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
