package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Note {
    private String title;
    private double note;
    private double max;
    private String text = "";
    public final static String PROP_TITLE = "title";
    public final static String PROP_NOTE = "note";
    public final static String PROP_TEXT = "text";

    private transient PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Note(String title, double note){
        max = 20;
        setTitle(title);
        setNote(note);
        setText();
    }

    public Note(String title, double note, double max){
        this(title, note);
        this.max = max;
    }
    
    public void addListener(PropertyChangeListener pcl){
        pcs.addPropertyChangeListener(pcl);
    }

    public void removeListener(PropertyChangeListener pcl){
        pcs.removePropertyChangeListener(pcl);
    }

    public double getNote20(){ return note/max * 20; }

    public void setNote(double n){
        if (n < 0 || n > max){
            throw new IllegalArgumentException(String.format("%f est < 0 ou > %f", n, max));
        }
        double old = getNote20();
        String oldS = toString();
        note = n;
        pcs.firePropertyChange(Note.PROP_NOTE, old, note);
        setText();
    }
    public String getTitle(){
        return title;
    }

    public void setTitle(String t){
        String old = getTitle();
        String oldS = toString();
        title = t;
        pcs.firePropertyChange(Note.PROP_TITLE, old, title);

        System.out.println(text);
        setText();
        System.out.println(text);
    }

    public String getText(){ return text; }

    public void setText(){
        String old = text;
        if((int)getNote20() == getNote20()){
            text = String.format("%s : %d/20", getTitle(), (int)getNote20());

        }
        else{
            text = String.format("%s : %.2f/20", getTitle(), getNote20());
        }
        pcs.firePropertyChange(Note.PROP_TEXT, old, text);
    }

    @Override
    public String toString(){
        return text;
    }
}
