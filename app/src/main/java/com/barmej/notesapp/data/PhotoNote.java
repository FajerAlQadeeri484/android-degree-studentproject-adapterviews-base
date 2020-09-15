package com.barmej.notesapp.data;

import android.net.Uri;

public class PhotoNote extends Note {
    private Uri image;


    public PhotoNote(String note, int color, Uri image) {
        super(note, color);
        this.image = image;
    }

    public Uri getImage() {
        return image;
    }
}
