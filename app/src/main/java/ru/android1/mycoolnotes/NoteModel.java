package ru.android1.mycoolnotes;

import android.os.Parcel;
import android.os.Parcelable;

public class NoteModel implements Parcelable {

    private String name;
    private String description;
    private String date;

    public NoteModel(String name, String description, String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.date);
    }

    public void readFromParcel(Parcel source) {
        this.name = source.readString();
        this.description = source.readString();
        this.date = source.readString();
    }

    protected NoteModel(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.date = in.readString();
    }

    public static final Parcelable.Creator<NoteModel> CREATOR = new Parcelable.Creator<NoteModel>() {
        @Override
        public NoteModel createFromParcel(Parcel source) {
            return new NoteModel(source);
        }

        @Override
        public NoteModel[] newArray(int size) {
            return new NoteModel[size];
        }
    };
}
