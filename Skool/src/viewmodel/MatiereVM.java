package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Matiere;
import model.Note;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MatiereVM implements PropertyChangeListener {
    private Matiere m;

    public MatiereVM(Matiere m){
        this.m = m;

        for (Note n: m.getNotes()) {
            noteObsList.add(new NoteVM(n));
        }
        m.addListener(this);
        setNoteList(noteObsList);

    }

    private ObservableList<NoteVM> noteObsList = FXCollections.observableArrayList();
    private ListProperty<NoteVM> noteList = new SimpleListProperty<>(noteObsList);
        public ObservableList<NoteVM> getNoteList() { return noteList.get(); }
        public ListProperty<NoteVM> noteListProperty() { return noteList; }
        public void setNoteList(ObservableList<NoteVM> noteList) { this.noteList.set(noteList); }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        System.out.println(String.format("PropertyChanged ! -> %s", evt.getPropertyName()));
        if(evt.getPropertyName().equals(Matiere.PROP_LIST_NOTES)){
            Note c = (Note) evt.getNewValue();
            noteObsList.add(new NoteVM((Note) evt.getNewValue()));
        }/*
        else if(evt.getPropertyName().equals(Matiere.PROP_LIST_TITRE)){
            String c = (String) evt.getNewValue();
            noteObsList.get(evt.getIndex()).setTitle(c);
        }*/
    }

    public void addNote(Note n){
        m.addNote(n);
    }
}
