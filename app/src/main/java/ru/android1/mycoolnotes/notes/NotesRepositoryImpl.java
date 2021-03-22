package ru.android1.mycoolnotes.notes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.List;
import ru.android1.mycoolnotes.Constants;
import ru.android1.mycoolnotes.NoteModel;

public class NotesRepositoryImpl implements NotesRepository {

    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private final NotesFirestoreCallbacks callbacks;
    private static final String TAG = "[NotesRepositoryImpl]";

    public NotesRepositoryImpl(NotesFirestoreCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public void requestNotes() {
        firestore.collection(Constants.TABLE_NAME_NOTES).get().addOnCompleteListener(task -> {
            if (task.getResult() != null) {
                List<NoteModel> items = task.getResult().toObjects(NoteModel.class);
                callbacks.onSuccessNotes(items);
            }
        }).addOnFailureListener(e -> callbacks.onErrorNotes(e.getMessage()));
    }

    @Override
    public void onDeleteClicked(@NonNull String id) {
        firestore
                .collection(Constants.TABLE_NAME_NOTES)
                .document(id)
                .delete()
                .addOnSuccessListener(aVoid -> requestNotes())
                .addOnFailureListener(e -> Log.d(TAG, "get failed with", e));
    }
}
