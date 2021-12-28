package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleStringProperty;

public class Student {
    private SimpleStringProperty ime;

    public Student(String s) {
        this.ime=new SimpleStringProperty(s);
    }

    public String getIme() {
        return ime.get();
    }

    public SimpleStringProperty imeProperty() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime.set(ime);
    }

    @Override
    public String toString() {
        return ime.get();
    }
}
