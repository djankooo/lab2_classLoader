import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Window;

public class LoginWindow extends Pane {
    public LoginWindow() {
        this.setBorder(new Border(new BorderStroke[]{new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)}));
        VBox VBox = this.createRegistrationFormPane();
        this.addUIControls(VBox);
        this.getChildren().add(VBox);
    }

    private VBox createRegistrationFormPane() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(40.0D, 40.0D, 40.0D, 40.0D));
        vBox.setSpacing(20.0D);
        return vBox;
    }

    private void addUIControls(VBox vBox) {
        Label headerLabel = new Label("Login");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24.0D));
        Label loginLabel = new Label("Login : ");
        TextField loginTextLabel = new TextField();
        Label passwordLabel = new Label("Hasło : ");
        PasswordField passwordPasswordField = new PasswordField();
        Button submitButton = new Button("Loguj!");
        HBox h1 = new HBox();
        h1.getChildren().addAll(new Node[]{headerLabel});
        HBox h2 = new HBox();
        h2.getChildren().addAll(new Node[]{loginLabel, loginTextLabel});
        HBox h3 = new HBox();
        h3.getChildren().addAll(new Node[]{passwordLabel, passwordPasswordField});
        HBox h4 = new HBox();
        h4.getChildren().addAll(new Node[]{submitButton});
        vBox.getChildren().addAll(new Node[]{h1, h2, h3, h4});
        submitButton.setOnAction((event) -> {
            if (loginTextLabel.getText().isEmpty()) {
                this.showAlert(AlertType.ERROR, vBox.getScene().getWindow(), "Błąd!", "Uzupełnij login!");
            } else if (passwordPasswordField.getText().isEmpty()) {
                this.showAlert(AlertType.ERROR, vBox.getScene().getWindow(), "Błąd!", "Uzupełnij hasło");
            }

        });
    }

    private void showAlert(AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText((String)null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
