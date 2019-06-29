import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class RegistrationWindow extends Pane {



    public String getLoginTextLabel() {
        return loginTextLabel.getText();
    }

    public void setLoginTextLabel(TextField loginTextLabel) {
        this.loginTextLabel = loginTextLabel;
    }

    public String getFirstNameTextLabel() {
        return firstNameTextLabel.getText();
    }

    public void setFirstNameTextLabel(TextField firstNameTextLabel) {
        this.firstNameTextLabel = firstNameTextLabel;
    }

    public String getLastNameTextLabel() {
        return lastNameTextLabel.getText();
    }

    public void setLastNameTextLabel(TextField lastNameTextLabel) {
        this.lastNameTextLabel = lastNameTextLabel;
    }

    public String getPasswordPasswordField() {
        return passwordPasswordField.getText();
    }

    public void setPasswordPasswordField(PasswordField passwordPasswordField) {
        this.passwordPasswordField = passwordPasswordField;
    }

    private Label headerLabel;
    private Label loginLabel;
    private Label firstNameLabel;
    private Label lastNameLabel;
    private Label passwordLabel;
    private TextField loginTextLabel, firstNameTextLabel,lastNameTextLabel;
    private PasswordField passwordPasswordField;
    private Button submitButton;

    public RegistrationWindow() {
        this.setBorder(new Border(new BorderStroke[]{new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)}));
        VBox vBox = this.createRegistrationFormPane();
        this.addUIControls(vBox);
        this.getChildren().add(vBox);
    }

    private VBox createRegistrationFormPane() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(40.0D, 40.0D, 40.0D, 40.0D));
        vBox.setSpacing(20.0D);
        return vBox;
    }

    private void addUIControls(VBox vBox) {

        headerLabel = new Label("Rejestracja");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24.0D));
        loginLabel = new Label("Login : ");
        loginTextLabel = new TextField();
        firstNameLabel = new Label("Imię : ");
        firstNameTextLabel = new TextField();
        lastNameLabel = new Label("Nazwisko: ");
        lastNameTextLabel = new TextField();
        passwordLabel = new Label("Hasło : ");
        passwordPasswordField = new PasswordField();
        submitButton = new Button("Rejestruj!");

        HBox h1 = new HBox();
        h1.getChildren().addAll(headerLabel);
        HBox h2 = new HBox();
        h2.getChildren().addAll( loginLabel, loginTextLabel);
        HBox h3 = new HBox();
        h3.getChildren().addAll( firstNameLabel, firstNameTextLabel);
        HBox h4 = new HBox();
        h4.getChildren().addAll( lastNameLabel, lastNameTextLabel);
        HBox h5 = new HBox();
        h5.getChildren().addAll( passwordLabel, passwordPasswordField);
        HBox h6 = new HBox();
        h6.getChildren().addAll( submitButton);
        vBox.getChildren().addAll( h1, h2, h3, h4, h5, h6);

        h1.setAlignment(Pos.BOTTOM_CENTER);
        h2.setAlignment(Pos.BOTTOM_RIGHT);
        h3.setAlignment(Pos.BOTTOM_RIGHT);
        h4.setAlignment(Pos.BOTTOM_RIGHT);
        h5.setAlignment(Pos.BOTTOM_RIGHT);
        h6.setAlignment(Pos.BOTTOM_RIGHT);

        submitButton.setOnAction((event) -> {
            System.out.println("wywolane z obiektu klasy : " + getLoginTextLabel());
            System.out.println("wywolane z obiektu klasy : " + getFirstNameTextLabel());
            System.out.println("wywolane z obiektu klasy : " + getLastNameTextLabel());
            System.out.println("wywolane z obiektu klasy : " + getPasswordPasswordField());
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
