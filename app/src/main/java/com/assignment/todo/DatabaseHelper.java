package com.assignment.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "todo_table";
    private static final String DB_NAME = "todoDB.db";
    private static final String COL_ID = "id";
    private static final String COL_TASK = "task";
    private static final String COL_DATE = "date";
    private static final int VERSION_NO = 1;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_TASK + " TEXT," +
            COL_DATE + " DATETIME" +
            ");";
    private static final String READ_DATA = "SELECT * FROM" + TABLE_NAME + ";";
    private static final String TAG = "DatabaseHelper";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION_NO);
    }

    public void addTask(Task task){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TASK, task.getTask());
        contentValues.put(COL_DATE, task.getDate());
        SQLiteDatabase sdb = getWritableDatabase();
        sdb.insert(TABLE_NAME, null, contentValues);
        sdb.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Task> getAllData() {
        ArrayList<Task> list = new ArrayList<>();
        SQLiteDatabase sdb = getReadableDatabase();
        Cursor cursor = sdb.query(TABLE_NAME, new String[]{COL_TASK, COL_DATE}, null, null,
                null, null, COL_DATE);
        while (cursor.moveToNext()){
            int id1 = cursor.getColumnIndex(COL_TASK);
            int id2 = cursor.getColumnIndex(COL_DATE);
            String todoTask = cursor.getString(id1);
            String date = cursor.getString(id2);
            list.add(new Task(todoTask, date));
        }
        cursor.close();
        sdb.close();
        return list;
    }
}
