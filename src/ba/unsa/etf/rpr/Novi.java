package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Novi {
    public Button btnClose;
    public TextField fldIme;

    public void actionEnd(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }


}
