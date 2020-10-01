package com.barmej.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.barmej.notesapp.data.CheckNote;

public class NoteCheckDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_check_details);

        final EditText checkNoteEditText = findViewById(R.id.checkNoteEditText);
        CheckBox checkNoteCheckBox = findViewById(R.id.checkNoteCheckBox);
        ConstraintLayout background2 = findViewById(R.id.background2);
        Button update2 = findViewById(R.id.update2);

        CheckNote c = (CheckNote) getIntent().getSerializableExtra("NOTE");
        checkNoteEditText.setText(c.getNote());
        checkNoteCheckBox.setChecked(c.isChecked());
        background2.setBackgroundColor(c.getColor());

        Bundle b = getIntent().getExtras();
        final int itemPosition = b.getInt("POSITION");

        update2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textUpdated = checkNoteEditText.getText().toString();
                Intent data = new Intent();
                data.putExtra("TEXT_UPDATED",textUpdated);
                data.putExtra("P",itemPosition);
                setResult(RESULT_OK,data);
                finish();
            }
        });


    }
}