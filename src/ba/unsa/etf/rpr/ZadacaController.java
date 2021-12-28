package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class ZadacaController implements Initializable {
    public ChoiceBox<String> choiceColor;
    public GridPane fldTabla;
    public TextField fldText;


    public ListView<String> lvStudents;
    public Slider sliderStudents;
    //private ObservableList l= FXCollections.observableArrayList();
    //private boolean prvi = true;
    private String[] studenti={"Student1", "Student2", "Student3"};
    //private StudentModel model;
    //int brojStudenata;

    private String[] boje={"Plava", "Crvena", "Zelena", "Default"};
    private boolean start = true;

    public void getBoje(ActionEvent event){
        String myBoje = choiceColor.getValue();
        if (myBoje.equals("Plava")) {
            fldTabla.getChildren().forEach((btn)->btn.getStyleClass().removeAll("redPolje", "greenPolje", "defaultPolje"));
            fldTabla.getChildren().forEach((btn)-> btn.getStyleClass().add("bluePolje"));
            //fldTabla.getStyleClass().removeAll("redPolje", "greenPolje", "defaultPolje");
            //fldTabla.getStyleClass().add("bluePolje");
        }
        else if (myBoje.equals("Crvena")) {
            fldTabla.getChildren().forEach((btn)->btn.getStyleClass().removeAll("bluePolje", "greenPolje", "defaultPolje"));
            fldTabla.getChildren().forEach((btn)->btn.getStyleClass().add("redPolje"));
            //fldTabla.getStyleClass().removeAll("bluePolje", "greenPolje", "defaultPolje");
            //fldTabla.getStyleClass().add("redPolje");
        }
        else if (myBoje.equals("Zelena")) {
            fldTabla.getChildren().forEach((btn)->btn.getStyleClass().removeAll("redPolje", "bluePolje", "defaultPolje"));
            fldTabla.getChildren().forEach((btn)->btn.getStyleClass().add("greenPolje"));
            //fldTabla.getStyleClass().removeAll("redPolje", "bluePolje", "defaultPolje");
            //fldTabla.getStyleClass().add("greenPolje");
        }
        else if (myBoje.equals("Default")) {
            fldTabla.getChildren().forEach((btn)->btn.getStyleClass().removeAll("redPolje", "bluePolje", "greenPolje"));
            fldTabla.getChildren().forEach((btn)->btn.getStyleClass().add("defaultPolje"));
            //fldTabla.getStyleClass().removeAll("redPolje", "bluePolje", "greenPolje");
            //fldTabla.getStyleClass().add("defaultPolje");
        }
    }

    public void actionNewWindow(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/novi.fxml"));
        stage.setTitle("Unos studenta");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(true);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceColor.getItems().addAll(boje);
        choiceColor.setOnAction(this::getBoje);
        lvStudents.getItems().addAll(studenti);
    }

    public void processNumbers(ActionEvent event) {
        if(start){
            fldText.setText("");
            start=false;
        }
        String value = ((Button) event.getSource()).getText();
        fldText.setText(fldText.getText()+value);
    }

    public void izlistajStudente(MouseEvent mouseEvent) {


    }
}
