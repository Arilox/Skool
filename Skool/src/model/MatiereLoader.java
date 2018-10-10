package model;

public class MatiereLoader {
    public static Matiere loadNotes(){
        Matiere m = new Matiere("Fake");
        m.addNote(new Note("Controle 1", 1));
        m.addNote(new Note("Exam Flash 1", 5));
        m.addNote(new Note("Controle 2", 10));
        m.addNote(new Note("Exam Flash 2", 11));
        m.addNote(new Note("Controle 3", 15));
        m.addNote(new Note("Exam Final", 12));
        return m;
    }
}
