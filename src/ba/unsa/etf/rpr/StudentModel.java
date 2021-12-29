package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentModel {
    private ObservableList<Student> studenti = FXCollections.observableArrayList();

    public void napuni(){
        for(int i=0; i<5; i++){
            int broj=i+1;
            String novi="Student"+broj;
            studenti.add(new Student(novi));
        }
    }

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
