package model;

import javafx.collections.FXCollections;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Matiere {
    private String titre;
    private List<Note> notes;
    public static final String PROP_LIST_TITRE = "titre";
    public static final String PROP_LIST_NOTES = "notes";
    private transient PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Matiere(String titre){
        this.titre = titre;
        notes = new ArrayList<>();
    }

    public List<Note> getNotes(){
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(notes));
    }

    public void addListener(PropertyChangeListener pcl){
        pcs.addPropertyChangeListener(pcl);
    }

    public void removeListener(PropertyChangeListener pcl){
        pcs.removePropertyChangeListener(pcl);
    }

    public void addNote(Note n){
        notes.add(n);
        pcs.firePropertyChange(PROP_LIST_NOTES, null, n);
    }
}
