package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentModel {
    private ObservableList<Student> studenti = FXCollections.observableArrayList();

    public void dodaj(Student s){
        this.studenti.add(s);
    }

    public ObservableList<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(ObservableList<Student> studenti) {
        this.studenti = studenti;
    }
}
