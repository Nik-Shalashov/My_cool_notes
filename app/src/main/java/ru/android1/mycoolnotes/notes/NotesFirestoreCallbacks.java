package ru.android1.mycoolnotes.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import ru.android1.mycoolnotes.NoteModel;

public interface NotesFirestoreCallbacks {

    void onSuccessNotes(@NonNull List<NoteModel> items);
    void onErrorNotes(@Nullable String message);
}
