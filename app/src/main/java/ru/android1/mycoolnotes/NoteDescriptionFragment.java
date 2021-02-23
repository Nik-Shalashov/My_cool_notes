package ru.android1.mycoolnotes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NoteDescriptionFragment extends Fragment {

    public static String DESCRIPTION;


    private String mParam1;
    private String mParam2;

    public NoteDescriptionFragment() {
        // Required empty public constructor
    }

    public static NoteDescriptionFragment newInstance(NoteModel note) {
        NoteDescriptionFragment fragment = new NoteDescriptionFragment();
        Bundle args = new Bundle();
        args.putParcelable(DESCRIPTION, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_description, container, false);
    }
}