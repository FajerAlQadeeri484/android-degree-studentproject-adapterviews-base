package com.barmej.notesapp.data;

public class Note {

    private String note;
    private int color;

    public Note(String note, int color) {
        this.note = note;
        this.color = color;
    }

    public String getNote() {
        return note;
    }

    public int getColor() {
        return color;
    }
}
