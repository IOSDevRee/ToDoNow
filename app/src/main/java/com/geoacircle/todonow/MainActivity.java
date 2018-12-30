package com.geoacircle.todonow;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener {

    EditText etask, edesc;
    FloatingActionButton fab;
    ToDoSQLiteHelper mydb;
    LinearLayout l1;
    TextView tv;
    ArrayAdapter<String> myAdapter;
    List<ToDoData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etask = findViewById(R.id.edtask);
        l1 = findViewById(R.id.maincontainer);
        edesc = findViewById(R.id.eddesc);
        fab = findViewById(R.id.fab);
        mydb = new ToDoSQLiteHelper(MainActivity.this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mydb.insertData(etask.getText().toString(),edesc.getText().toString());
                Toast.makeText(MainActivity.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, DisplayActivity.class);
                startActivity(i);


            }
        });

        list = mydb.getAllToDo();
        List<String> listTitle = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            listTitle.add(i, list.get(i).getTitle());
        }

        myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);

    }


    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){

        Intent intent = new Intent(this, ToDoActivity.class);
        intent.putExtra("todo", list.get(arg2).getId());

        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        list = mydb.getAllToDo();
        List<String> listTitle = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, list.get(i).getTitle());
        }

        myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
    }
}
