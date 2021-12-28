package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NoviController {
    public Button btnClose;
    public TextField fldIme;
    public ProgressBar fldProgress;
    private double progress;
    private ZadacaController z3;
    private String pomocni="";
    boolean start=true;

    public void actionEnd(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void initialize(){

        fldIme.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(fldIme.getText().length()<10){
                    if(start){
                        start = false;
                        pomocni=fldIme.getText();
                    }
                    if(fldIme.getText().length()<pomocni.length()) progress-=0.1;
                    else progress+=0.1;
                    fldProgress.setProgress(progress);
                    fldProgress.getStyleClass().removeAll("-fx-accent: red");
                    fldProgress.setStyle("-fx-accent: red");
                    pomocni=fldIme.getText();
                }
                else{
                    progress+=0.1;
                    fldProgress.setProgress(progress);
                    fldProgress.getStyleClass().removeAll("-fx-accent: red");
                    fldProgress.setStyle("-fx-accent: blue");
                }
            }
        });
    }

    public void klikNaBtn(ActionEvent event) {
        if(fldIme.getText().length()<10){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Neispravno ime");
            alert.setContentText("Ime studenta treba biti najmanje 10 karaktera dugacko");
            alert.setHeaderText("Neispravno ime studenta");
            alert.showAndWait();
        }
        else{
            String ime = fldIme.getText();
            Student s = new Student(ime);
        }
    }
}
