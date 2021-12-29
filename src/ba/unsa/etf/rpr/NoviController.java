package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class NoviController {
    public Button btnClose;
    public TextField fldIme;
    public ProgressBar progressBar;
    public Button btnOK;
    private double progress=0;

    private String pomocni="";
    private String pomocni2="";
    boolean start = true;
    boolean start2 = true;
    boolean usao = false;

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
                    if(fldIme.getText().length()<pomocni.length() || usao) {
                        progress -= 0.1;
                        usao = false;
                    }
                    else progress+=0.1;
                    double round=Math.round(progress*100);
                    progressBar.setProgress(round/100);
                    progressBar.getStyleClass().removeAll("zeleniProgress");
                    progressBar.getStyleClass().add("crveniProgress");
                    pomocni = fldIme.getText();
                }
                else if (fldIme.getText().length()==10){
                    progress=1.0;
                    progressBar.setProgress(progress);
                    progressBar.getStyleClass().removeAll("crveniProgress");
                    progressBar.getStyleClass().add("zeleniProgress");
                    usao = true;
                }
                else{
                    if(start2){
                        start2 = false;
                        pomocni2=fldIme.getText();
                    }
                    if(fldIme.getText().length()>pomocni.length()) progress+=0.1;
                    else progress-=0.1;
                    double round=Math.round(progress*100);
                    progress=round/100;
                    progressBar.getStyleClass().removeAll("crveniProgress");
                    progressBar.getStyleClass().add("zeleniProgress");
                    pomocni2= fldIme.getText();
                }
            }
        });
    }

    public void klikNaBtn(ActionEvent event) throws IOException {
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
            Stage stage = (Stage) btnOK.getScene().getWindow();
            stage.close();
        }
    }
}
