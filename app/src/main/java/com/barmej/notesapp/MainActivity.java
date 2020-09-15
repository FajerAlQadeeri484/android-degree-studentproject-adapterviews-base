package com.barmej.notesapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Toast;

import com.barmej.notesapp.adapter.PhotoNoteAdapter;
import com.barmej.notesapp.data.CheckNote;
import com.barmej.notesapp.data.Note;
import com.barmej.notesapp.data.PhotoNote;
import com.barmej.notesapp.listener.ItemClickListener;
import com.barmej.notesapp.listener.ItemLongClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Note> mItems;
    private PhotoNoteAdapter mAdapter;

    RecyclerView.LayoutManager mGridLayoutManager;

    private static final int ADD_NOTE = 145;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view_photos);
        mGridLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mItems = new ArrayList<Note>();
        mAdapter = new PhotoNoteAdapter(mItems,
                new ItemClickListener() {
                    @Override
                    public void onClickItem(int position) {
                        edit(position);
                    }
            },
                new ItemLongClickListener() {
                    @Override
                    public void onLongClickItem(int position) {
                        deleteItem(position);
                    }
        });
        mRecyclerView.setAdapter(mAdapter);

        findViewById(R.id.floating_button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddNewPhotoActivity();
            }
        });
    }

    private void startAddNewPhotoActivity(){
        Intent intent = new Intent(this, AddNewNoteActivity.class);
        startActivityForResult(intent, ADD_NOTE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE){
            if (data != null){
                Uri photoUri = data.getParcelableExtra(Constants.EXTRA_PHOTO_URI);
                String nnote = data.getStringExtra(Constants.EXTRA_NOTE);
                int color = data.getIntExtra(Constants.EXTRA_COLOR,1);
                System.out.println("ppp"+nnote);
                if (photoUri!=null){
                    PhotoNote photoNote = new PhotoNote(nnote,color,photoUri);
                    addItem(photoNote);
                }else{
                    Note note = new Note(nnote,color);
                    addItem(note);
                }

            }else{
                Toast.makeText(this,R.string.didnt_add_note, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void addItem(Note note){
        mItems.add(note);
        mAdapter.notifyItemInserted(mItems.size()-1);
    }

    private void edit(int position){
        Note note = mItems.get(position);
        if (note instanceof PhotoNote){
            Intent intentToNotePhotoDetails = new Intent(MainActivity.this,NotePhotoDetails.class);
            intentToNotePhotoDetails.putExtra("PHOTO",((PhotoNote) note).getImage());
            intentToNotePhotoDetails.putExtra("TEXT",note.getNote());
            intentToNotePhotoDetails.putExtra("COLOR",note.getColor());
            startActivity(intentToNotePhotoDetails);

        }else if (note instanceof CheckNote){
            Intent intentToNoteCheckDetails = new Intent(MainActivity.this,NoteCheckDetails.class);
            intentToNoteCheckDetails.putExtra("TEXT",note.getNote());
            intentToNoteCheckDetails.putExtra("COLOR",note.getColor());
            startActivity(intentToNoteCheckDetails);
        }else {
            Intent intentToNoteDetails = new Intent(MainActivity.this,NoteDetails.class);
            intentToNoteDetails.putExtra("TEXT",note.getNote());
            intentToNoteDetails.putExtra("COLOR",note.getColor());
            startActivity(intentToNoteDetails);
        }

    }

    private void deleteItem(final int position){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage(R.string.delete_confirmation)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mItems.remove(position);
                        mAdapter.notifyItemRemoved(position);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        alertDialog.show();
    }
}
