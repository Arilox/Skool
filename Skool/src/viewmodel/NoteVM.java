package viewmodel;

import javafx.beans.property.*;
import model.Note;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NoteVM implements PropertyChangeListener {
    private Note n;

    public NoteVM(Note n){
        this.n = n;

        setNote(n.getNote20());
        setTitle(n.getTitle());
        setText(n.getText());

        n.addListener(this);

        title.addListener((observable, oldValue, newValue) -> {
            n.setTitle(newValue);
        });
        note.addListener((observable, oldValue, newValue) -> n.setNote(newValue));
        text.addListener(o -> n.setText());
    }

    private StringProperty title = new SimpleStringProperty();
        public String getTitle() { return title.get(); }
        public StringProperty titleProperty() { return title; }
        public void setTitle(String title) { this.title.set(title); }

    private Property<Double> note = new SimpleObjectProperty<>();
        public double getNote() { return note.getValue(); }
        public Property<Double> noteProperty() { return note; }
        public void setNote(double note) { this.note.setValue(note); }

    private StringProperty text = new SimpleStringProperty();
        public String getText() { return text.get(); }
        public StringProperty textProperty() { return text; }
        public void setText(String text) { this.text.set(text); }

    public Note getN(){
            return n;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(String.format("PropertyChanged ! -> %s", evt.getPropertyName()));
        if(evt.getPropertyName().equals(Note.PROP_TITLE)){
            setTitle((String) evt.getNewValue());
        }
        if(evt.getPropertyName().equals(Note.PROP_NOTE)){
            setNote((double) evt.getNewValue());
        }
        if(evt.getPropertyName().equals(Note.PROP_TEXT)){
            setText((String) evt.getNewValue());
        }
    }
}
