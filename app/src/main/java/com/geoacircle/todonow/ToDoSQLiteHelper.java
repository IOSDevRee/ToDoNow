package com.geoacircle.todonow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class ToDoSQLiteHelper extends SQLiteOpenHelper {


    private static final int database_VERSION = 1;
    private static final String database_NAME = "ToDoDB";
    private static final String table_TODO = "todo";
    private static final String todo_ID = "id";
    private static final String todo_TITLE = "title";
    private static final String todo_DESC = "desc";

    private static final String[] COLUMNS = {todo_ID, todo_TITLE, todo_DESC};

    public ToDoSQLiteHelper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String CREATE_TODO_TABLE = "CREATE TABLE todo ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "title TEXT, " + "desc TEXT)";
        db.execSQL(CREATE_TODO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS todo");
        this.onCreate(db);

    }

    public Cursor getData(){

        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("select * from todo",null);
        return c;
    }

    public void insertData(String title, String description){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(todo_TITLE, title);
        values.put(todo_DESC, description);

        db.insert(table_TODO, null, values);
        db.close();
    }

    public void createToDo(ToDoData todo){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(todo_TITLE, todo.getTitle());
        values.put(todo_DESC, todo.getDesc());

        db.insert(table_TODO, null, values);
        db.close();
    }

    public ToDoData readToDo(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(table_TODO, COLUMNS, "id = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        ToDoData todoD = new ToDoData();
        if(cursor != null && cursor.moveToFirst()) {
            todoD.setId(Integer.parseInt(cursor.getString(0)));
            todoD.setTitle(cursor.getString(1));
            todoD.setDesc(cursor.getString(2));
        }
        return todoD;

    }

    public List<ToDoData> getAllToDo(){

        List<ToDoData> todo = new LinkedList<ToDoData>();

        String query = "SELECT * FROM " + table_TODO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ToDoData todoitem = null;
        if(cursor.moveToFirst()){

            do{

                todoitem = new ToDoData();
                todoitem.setId(Integer.parseInt(cursor.getString(0)));
                todoitem.setTitle(cursor.getString(1));
                todoitem.setDesc(cursor.getString(2));

                todo.add(todoitem);

            } while (cursor.moveToNext());

        }

        return todo;

    }

    public int updateToDo(ToDoData todoitem){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", todoitem.getTitle());
        values.put("desc", todoitem.getDesc());

        int i = db.update(table_TODO, values, todo_ID + " = ?", new String[]{String.valueOf(todoitem.getId())});
        db.close();
        return i;
    }

    public void deleteToDo(ToDoData todoitem){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(table_TODO, todo_ID + " = ?", new String[]{ String.valueOf(todoitem.getId())});
        db.close();
    }

}
