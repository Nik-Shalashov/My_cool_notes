package ru.android1.mycoolnotes.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.android1.mycoolnotes.NoteModel;
import ru.android1.mycoolnotes.R;

public class NotesAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final ArrayList<NoteModel> notes;
    private OnItemClickListener itemClickListener;
    private final Fragment fragment;
    private int menuPosition;

    public NotesAdapter(ArrayList<NoteModel> notes, Fragment fragment) {
        this.notes = notes;
        this.fragment = fragment;
    }

    public void setItems(List<NoteModel> noteModelList) {
        notes.clear();
        notes.addAll(noteModelList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    public int getMenuPosition() {
        return menuPosition;
    }
}

