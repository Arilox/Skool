package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Matiere;
import model.Note;
import viewmodel.MatiereVM;
import viewmodel.NoteVM;

public class Controller {

    private MatiereVM mvm = new MatiereVM(new Matiere("Main"));

    @FXML
    private Spinner<Double> notespin;

    @FXML
    private ListView<NoteVM> matierelistv;

    @FXML
    private TextField notetitle;

    @FXML
    private Button btn;

    private boolean modif;


    @FXML
    private void initialize(){
        notespin.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory
                (0, 20, 0, 0.25));

        matierelistv.setCellFactory(__ -> new ListCell<>() {
            @Override
            protected void updateItem(NoteVM note, boolean empty) {
                super.updateItem(note, empty);

                if(!empty){
                    textProperty().bind(note.textProperty());
                }else{
                    textProperty().unbind();
                    setText("");
                }
            }
        });

        matierelistv.itemsProperty().bind(mvm.noteListProperty());

        matierelistv.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue != null){
                notespin.getValueFactory().valueProperty().unbindBidirectional(oldValue.noteProperty());
                notetitle.textProperty().unbindBidirectional(oldValue.titleProperty());
                notespin.getValueFactory().setValue(null);
                notetitle.textProperty().setValue(null);
            }
            if(newValue != null){
                notespin.getValueFactory().valueProperty().bindBidirectional(newValue.noteProperty());
                notetitle.textProperty().bindBidirectional(newValue.titleProperty());
            }
        });

        btn.disableProperty().bind(notespin.getValueFactory().valueProperty().isEqualTo(0).and(notetitle.textProperty().isEmpty()));
    }

    @FXML
    private void addNote() {
        if(!modif) {
            btn.textProperty().setValue("Unselect");
            mvm.addNote(new Note(notetitle.getText(), notespin.getValue()));
            matierelistv.getSelectionModel().selectLast();
        }
        else{
            btn.textProperty().setValue("Ajouter");
            matierelistv.getSelectionModel().select(null);
        }
        modif = !modif;
    }
}
