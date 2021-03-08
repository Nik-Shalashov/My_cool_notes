package ru.android1.mycoolnotes.note_description;

import androidx.annotation.Nullable;

public interface NoteFirestoreCallbacks {

    void onSuccess(@Nullable String message);
    void onError(@Nullable String message);
}
