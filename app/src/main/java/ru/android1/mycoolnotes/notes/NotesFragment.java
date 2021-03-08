package ru.android1.mycoolnotes.notes;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import ru.android1.mycoolnotes.NoteModel;
import ru.android1.mycoolnotes.adapter.NotesAdapter;
import ru.android1.mycoolnotes.R;
import ru.android1.mycoolnotes.note_description.NoteDescriptionFragment;

public class NotesFragment extends Fragment implements NotesFirestoreCallbacks {

    private RecyclerView recyclerView;
    private final ArrayList<NoteModel> notes = new ArrayList<>();
    private final NotesRepository repository = new NotesRepositoryImpl(this);
    private final NotesAdapter adapter = new NotesAdapter(notes, this);
    private boolean isLandscapeOrientation;

    public static NotesFragment newInstance() {
        Bundle args = new Bundle();
        NotesFragment fragment = new NotesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        recyclerView = view.findViewById(R.id.recycler_view_lines);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView(recyclerView, notes);
        repository.requestNotes();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initRecyclerView(RecyclerView recyclerView, List<NoteModel> notes) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator, null));
        recyclerView.addItemDecoration(itemDecoration);

        adapter.SetOnItemClickListener((view, position) -> checkOrientation(notes.get(position)));
    }


    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = adapter.getMenuPosition();
        if (item.getItemId() == R.id.action_delete) {
            NoteModel note = notes.get(position);
            repository.onDeleteClicked(note.getId());
            adapter.notifyItemRemoved(position);
            return true;
        }
        return super.onContextItemSelected(item);
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

    @Override
    public void onSuccessNotes(@NonNull List<NoteModel> items) {
        adapter.setItems(items);
    }

    @Override
    public void onErrorNotes(@Nullable String message) {
        showToast(message);
    }

    private void showToast(@Nullable String message) {
        if (message != null) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
}