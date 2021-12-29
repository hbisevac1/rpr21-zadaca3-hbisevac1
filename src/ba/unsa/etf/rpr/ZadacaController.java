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
import javafx.scene.input.DragEvent;
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
    private boolean prvihPet = true;
    public ListView<String> lvStudents;
    public Slider sliderStudents;
    private StudentModel model=new StudentModel();
    private int brojStudenata;
    public NoviController noviController;

    private String[] boje={"Plava", "Crvena", "Zelena", "Default"};
    private boolean start = true;

    public ZadacaController(){
        model.napuni();
    }

    public void getBoje(ActionEvent event){
        String myBoje = choiceColor.getValue();
        if (myBoje.equals("Plava")) {
            fldTabla.getChildren().forEach((btn)->btn.getStyleClass().removeAll("redPolje", "greenPolje", "defaultPolje"));
            fldTabla.getChildren().forEach((btn)-> btn.getStyleClass().add("bluePolje"));
        }
        else if (myBoje.equals("Crvena")) {
            fldTabla.getChildren().forEach((btn)->btn.getStyleClass().removeAll("bluePolje", "greenPolje", "defaultPolje"));
            fldTabla.getChildren().forEach((btn)->btn.getStyleClass().add("redPolje"));
        }
        else if (myBoje.equals("Zelena")) {
            fldTabla.getChildren().forEach((btn)->btn.getStyleClass().removeAll("redPolje", "bluePolje", "defaultPolje"));
            fldTabla.getChildren().forEach((btn)->btn.getStyleClass().add("greenPolje"));
        }
        else if (myBoje.equals("Default")) {
            fldTabla.getChildren().forEach((btn)->btn.getStyleClass().removeAll("redPolje", "bluePolje", "greenPolje"));
            fldTabla.getChildren().forEach((btn)->btn.getStyleClass().add("defaultPolje"));
        }
    }

    public void actionNewWindow(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/novi.fxml"));
        loader.load();
        noviController = loader.getController();

        myStage.setOnHiding((event) -> {
            model.dodaj(new Student(noviController.fldIme.getText()));
            lvStudents.getItems().clear();
            for (int i=0; i<model.getStudenti().size(); i++) lvStudents.getItems().add(String.valueOf(model.getStudenti().get(i)));
        });


        myStage.setTitle("Unos studenta");
        myStage.setScene(new Scene(loader.getRoot(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(true);
        myStage.show();
/*
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/novi.fxml"));
        stage.setTitle("Unos studenta");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(true);
        stage.show();
*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceColor.getItems().addAll(boje);
        choiceColor.setOnAction(this::getBoje);
        brojStudenata = (int) sliderStudents.getValue();
        lvStudents.getItems().clear();
        for (int i=0; i<brojStudenata; i++) lvStudents.getItems().add(String.valueOf(model.getStudenti().get(i)));
        sliderStudents.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                brojStudenata = (int) sliderStudents.getValue();
                lvStudents.getItems().clear();
                boolean usao = false;
                if(brojStudenata==15) {
                    brojStudenata -= 1;
                    usao=true;
                }
                for(int i=model.getStudenti().size(); i<brojStudenata; i++) {
                    int broj=i+1;
                    model.dodaj(new Student("Student"+broj));
                }

                for (int i=0; i<brojStudenata; i++) lvStudents.getItems().add(String.valueOf(model.getStudenti().get(i)));
                if(usao){
                    if(fldText.getText().isEmpty()) lvStudents.getItems().add("Student");
                    else {
                        model.dodaj(new Student("Student"+fldText.getText()));
                        lvStudents.getItems().add(String.valueOf(model.getStudenti().get(brojStudenata)));
                    }
                    brojStudenata=15;
                }
            }
        });
    }

    public void processNumbers(ActionEvent event) {
        if(start){
            fldText.setText("");
            start=false;
        }
        String value = ((Button) event.getSource()).getText();
        fldText.setText(fldText.getText()+value);
    }

    public void dodajStudenta(Student s){
        model.dodaj(s);
    }



}
