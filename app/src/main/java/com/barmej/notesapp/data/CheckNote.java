package com.barmej.notesapp.data;

public class CheckNote extends Note {

    private boolean checkBox;


    public CheckNote(String note, int color, boolean checkBox) {
        super(note, color);
        this.checkBox = checkBox;
    }

    public boolean isCheckBox() {
        return checkBox;
    }
}
