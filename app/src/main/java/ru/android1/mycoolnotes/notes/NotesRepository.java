package ru.android1.mycoolnotes.notes;

import androidx.annotation.NonNull;

public interface NotesRepository {

    void requestNotes();
    void onDeleteClicked(@NonNull String id);
}
