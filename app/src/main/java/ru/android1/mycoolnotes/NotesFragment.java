package ru.android1.mycoolnotes;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class NotesFragment extends Fragment {

    private final ArrayList<NoteModel> notes = new ArrayList<>();
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
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_lines);
        initRecyclerView(recyclerView, notes);
        return view;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initRecyclerView(RecyclerView recyclerView, ArrayList<NoteModel> notes) {
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        final NotesAdapter adapter = new NotesAdapter(notes);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator, null));
        recyclerView.addItemDecoration(itemDecoration);

        adapter.SetOnItemClickListener((view, position) -> checkOrientation(notes.get(position)));
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
        notes.add(new NoteModel("title", "desc", "21.02.2005"));
        notes.add(new NoteModel("title", "desc", "21.02.2006"));
        notes.add(new NoteModel("title", "desc", "21.02.2007"));
        notes.add(new NoteModel("title", "desc", "21.02.2008"));
        notes.add(new NoteModel("title", "desc", "21.02.2009"));
        notes.add(new NoteModel("title", "desc", "21.02.2010"));
        notes.add(new NoteModel("title", "desc", "21.02.2011"));
        notes.add(new NoteModel("title", "desc", "21.02.2012"));
        notes.add(new NoteModel("title", "desc", "21.02.2013"));
        notes.add(new NoteModel("title", "desc", "21.02.2014"));
        notes.add(new NoteModel("title", "desc", "21.02.2015"));
        notes.add(new NoteModel("title", "desc", "21.02.2016"));
        notes.add(new NoteModel("title", "desc", "21.02.2017"));
        notes.add(new NoteModel("title", "desc", "21.02.2018"));
        notes.add(new NoteModel("title", "desc", "21.02.2019"));
        notes.add(new NoteModel("title", "desc", "21.02.2020"));
        notes.add(new NoteModel("title", "desc", "21.02.2021"));
        notes.add(new NoteModel("title", "desc", "21.02.2022"));
        notes.add(new NoteModel("title", "desc", "21.02.2023"));
        notes.add(new NoteModel("title", "desc", "21.02.2024"));
        notes.add(new NoteModel("title", "desc", "21.02.2025"));
        notes.add(new NoteModel("title", "desc", "21.02.2026"));
        notes.add(new NoteModel("title", "desc", "21.02.2027"));
        notes.add(new NoteModel("title", "desc", "21.02.2028"));
        notes.add(new NoteModel("title", "desc", "21.02.2029"));
        notes.add(new NoteModel("title", "desc", "21.02.2030"));
        notes.add(new NoteModel("title", "desc", "21.02.2031"));
        notes.add(new NoteModel("title", "desc", "21.02.2032"));
        notes.add(new NoteModel("title", "desc", "21.02.2033"));
        notes.add(new NoteModel("title", "desc", "21.02.2034"));
        notes.add(new NoteModel("title", "desc", "21.02.2035"));
    }
}