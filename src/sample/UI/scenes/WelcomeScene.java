package sample.UI.scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class WelcomeScene {
    private final Scene scene;

    public WelcomeScene() {

        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(20);

        Text fullNameTxt = new Text("Welcome \t");
        fullNameTxt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Text TextRole = new Text("Your Role Is : ");
        TextRole.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL,
                FormatStyle.MEDIUM);

        Text TextDate = new Text("Today  is  : " +LocalDateTime.now().format(dateTimeFormatter));
        TextDate.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));

        layout.getChildren().addAll(fullNameTxt, TextRole, TextDate);
        scene = new Scene(layout, 500, 500, Color.BLUE);
    }

    public Scene getScene() {
        return scene;
    }
}
