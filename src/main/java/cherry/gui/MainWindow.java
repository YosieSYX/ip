package cherry.gui;

import cherry.main.Cherry;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Label chatboxName;

    private Cherry cherry;

    private Image cherryImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Cherry.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        chatboxName.setText("Cherry");
    }

    private void showWelcomeMessage() {
        dialogContainer.getChildren().add(DialogBox.getCherryDialog(cherry.displayWelcomeMessage(), cherryImage));
    }


    /**
     * Injects the Cherry instance
     */
    public void setCherry(Cherry d) {
        cherry = d;
        showWelcomeMessage();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = cherry.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCherryDialog(response, cherryImage)
        );
        userInput.clear();
    }
}
