package cherry.gui;


import java.io.IOException;

import cherry.main.Cherry;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Cherry using FXML.
 */
public class Main extends Application {

    private Cherry cherry = new Cherry("./data/Tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCherry(cherry);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
