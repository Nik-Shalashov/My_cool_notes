package ru.android1.mycoolnotes.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import ru.android1.mycoolnotes.NoteModel;
import ru.android1.mycoolnotes.R;

public class NotesViewHolder extends RecyclerView.ViewHolder {
    private final TextView name;
    private final TextView date;
    private NotesAdapter.OnItemClickListener itemClickListener;
    private Fragment fragment;
    private int menuPosition;

    public NotesViewHolder(@NonNull final View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_item_note_name);
        date = itemView.findViewById(R.id.tv_item_note_date);
        LinearLayout note = itemView.findViewById(R.id.ll_item_note_review);
        registerContextMenu(itemView);        
        note.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, NotesViewHolder.this.getAdapterPosition());
            }
        });

        note.setOnLongClickListener(v -> {
            menuPosition = getLayoutPosition();
            itemView.showContextMenu(10,10);
            return true;
        });
    }

    private void registerContextMenu(@NonNull View itemView) {
        if (fragment != null){
            itemView.setOnLongClickListener(v -> {
                menuPosition = getLayoutPosition();
                return false;
            });
            fragment.registerForContextMenu(itemView);
        }
    }

    public void onBind (NoteModel noteModel) {
        name.setText(noteModel.getName());
        date.setText(noteModel.getDate());
    }
}
