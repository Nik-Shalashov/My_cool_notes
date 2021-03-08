package ru.android1.mycoolnotes.note_description;

import androidx.annotation.NonNull;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import ru.android1.mycoolnotes.Constants;

public class NoteRepositoryImpl implements NoteRepository {

    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private final NoteFirestoreCallbacks callbacks;

    public NoteRepositoryImpl(NoteFirestoreCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public void setNote(@NonNull String id, @NonNull String name, @NonNull String description, @NonNull String date) {
        final Map<String, Object> note = new HashMap<>();
        note.put("id", id);
        note.put("name", name);
        note.put("description", description);
        note.put("date", date);

        firebaseFirestore.collection(Constants.TABLE_NAME_NOTES).document(id).set(note).addOnSuccessListener(aVoid -> callbacks.onSuccess("Заметка успешно обновлена!")).addOnFailureListener(e -> callbacks.onError(e.getMessage()));
    }

    @Override
    public void onDeleteClicked(@NonNull String id) {
        firebaseFirestore.collection(Constants.TABLE_NAME_NOTES).document(id).delete();
    }
}
