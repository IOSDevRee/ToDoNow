package com.geoacircle.todonow;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ToDoActivity extends Activity {
    TextView todoTitle;
    TextView descName;
    ToDoData selectedToDo;
    ToDoSQLiteHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_todo);

        todoTitle = (TextView) findViewById(R.id.title);
        descName = (TextView) findViewById(R.id.desc);

        Intent intent = getIntent();
        int id = intent.getIntExtra("todo", -1);

        // open the database of the application context
        db = new ToDoSQLiteHelper(getApplicationContext());

        selectedToDo = db.readToDo(id);

        initializeViews();
    }

    public void initializeViews() {
        todoTitle.setText(selectedToDo.getTitle());
        descName.setText(selectedToDo.getDesc());
    }

    public void update(View v) {
        Toast.makeText(getApplicationContext(), "This todo is updated.", Toast.LENGTH_SHORT).show();
        selectedToDo.setTitle(((EditText) findViewById(R.id.titleEdit)).getText().toString());
        selectedToDo.setDesc(((EditText) findViewById(R.id.descEdit)).getText().toString());


        db.updateToDo(selectedToDo);
        finish();
    }

    public void delete(View v) {
        Toast.makeText(getApplicationContext(), "This todo is deleted.", Toast.LENGTH_SHORT).show();

        db.deleteToDo(selectedToDo);
        finish();
    }
}