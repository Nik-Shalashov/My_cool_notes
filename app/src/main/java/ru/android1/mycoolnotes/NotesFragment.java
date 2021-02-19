package ru.android1.mycoolnotes;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesFragment extends Fragment {

    private ArrayList<NoteModel> notes = new ArrayList<>();
    private boolean isLandscapeOrientation;

    public NotesFragment() {

    }

    public static NotesFragment newInstance() {

        Bundle args = new Bundle();

        NotesFragment fragment = new NotesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArrayList();
        isLandscapeOrientation = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @SuppressLint("SetTextI18n")
    private void initView(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        for (int i = 0; i < notes.size(); i++) {
            String name = notes.get(i).getName();
            String date = notes.get(i).getDate();
            String description = notes.get(i).getDescription();
            TextView textView = new TextView(linearLayout.getContext());
            textView.setText(name + " (" + date + ")");
            textView.setPadding(12, 0, 12, 0);
            textView.setTextSize(30f);
            int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkOrientation(notes.get(finalI));
                }
            });
            linearLayout.addView(textView);
        }
    }

    private void checkOrientation(NoteModel note) {
        if (isLandscapeOrientation) {
            openNoteDescriptionFragment(note);
        } else {
            startNoteDescriptionFragment(note);
        }
    }

    private void openNoteDescriptionFragment(NoteModel note) {
        NoteDescriptionFragment fragment = NoteDescriptionFragment.newInstance(note);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_land, fragment)
                .commit();
    }

    private void startNoteDescriptionFragment(NoteModel note) {
        NoteDescriptionFragment fragment = NoteDescriptionFragment.newInstance(note);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void initArrayList() {
        notes.add(new NoteModel("title", "desc", "21.02.2004"));
        notes.add(new NoteModel("title", "desc", "21.02.2004"));
        notes.add(new NoteModel("title", "desc", "21.02.2004"));
        notes.add(new NoteModel("title", "desc", "21.02.2004"));
    }
}