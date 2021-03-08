package ru.android1.mycoolnotes.note_description;

import androidx.annotation.NonNull;

public interface NoteRepository {

    void setNote(@NonNull String id, @NonNull String title, @NonNull String description, @NonNull String date);

    void onDeleteClicked(@NonNull String id);
}
