package com.example.wang.memo3;

import android.os.AsyncTask;

import com.example.wang.memo3.NoteDB;

/**
 * Created by Dynasty on 2019/3/25.
 */
public class DeleteAsyncTask extends AsyncTask<Integer,Void,Void> {

    private NoteDB noteDB;

    public DeleteAsyncTask(NoteDB noteDB) {
        this.noteDB = noteDB;
    }

    @Override
    protected Void doInBackground(Integer... params) {
        noteDB.deleteById(params[0]);
        return null;
    }

}