package com.barmej.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class NotePhotoDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_photo_details);

        ImageView photoImageView = findViewById(R.id.photoImageView);
        EditText photoNoteEditText = findViewById(R.id.photoNoteEditText);
        RelativeLayout background = findViewById(R.id.background);

        Bundle b = getIntent().getExtras();

        assert b != null;
        photoImageView.setImageURI((Uri) b.get("PHOTO"));
        photoNoteEditText.setText(b.getString("TEXT"));
        background.setBackgroundColor(b.getInt("COLOR"));

    }
}