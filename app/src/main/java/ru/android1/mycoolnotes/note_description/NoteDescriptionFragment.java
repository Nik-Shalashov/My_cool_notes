package ru.android1.mycoolnotes.note_description;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.UUID;

import ru.android1.mycoolnotes.NoteModel;
import ru.android1.mycoolnotes.R;

public class NoteDescriptionFragment extends Fragment implements NoteFirestoreCallbacks {

    private static final String ARG_MODEL_KEY = "arg_model_key";
    private final NoteRepository repository = new NoteRepositoryImpl(this);


    public static NoteDescriptionFragment newInstance(@Nullable NoteModel note) {
        NoteDescriptionFragment fragment = new NoteDescriptionFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MODEL_KEY, note);
        fragment.setArguments(args);
        return fragment;
    }

    private EditText noteName;
    private EditText noteDescription;
    private EditText noteDate;
    private MaterialButton updateButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        noteName = view.findViewById(R.id.note_name);
        noteDescription = view.findViewById(R.id.note_description);
        noteDate = view.findViewById(R.id.note_date);
        updateButton = view.findViewById(R.id.update_button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateButton.setOnClickListener(v -> {
            final String name = noteName.getText().toString();
            final String description = noteDescription.getText().toString();
            final String date = noteDate.getText().toString();
            update(name, description, date);
        });

        if (getArguments() != null) {
            NoteModel noteModel = (NoteModel) getArguments().getSerializable(ARG_MODEL_KEY);
            if (noteModel != null) {
                updateButton.setText(getString(R.string.add_note_button));
                noteName.setText(noteModel.getName());
                noteDescription.setText(noteModel.getDescription());
                noteDate.setText(noteModel.getDate());
            }
        }
    }

    private void update(@Nullable String name, @Nullable String description, @Nullable String date) {
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(description) && !TextUtils.isEmpty(date)) {
            if (getArguments() != null) {
                NoteModel noteModel = (NoteModel) getArguments().getSerializable(ARG_MODEL_KEY);
                if (noteModel != null) {
                    repository.setNote(noteModel.getId(), name, description, date);
                } else {
                    String id = UUID.randomUUID().toString();
                    repository.setNote(id, name, description, date);
                }
            }
        } else showToastMessage("Поля не могут быть пустыми!");
    }

    private void showToastMessage(String s) {
        if (s != null) Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(@Nullable String message) {
        showToastMessage(message);
    }

    @Override
    public void onError(@Nullable String message) {
        showToastMessage(message);
    }


}