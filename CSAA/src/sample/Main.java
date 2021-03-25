package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.FXML_URL));
        Parent root = loader.load();
        primaryStage.setTitle(Constants.Application_Title);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(Constants.ICO_URL)));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
