package com.geoacircle.todonow;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

   // ListView lv;
    ToDoSQLiteHelper mydb;
    Cursor c;
    LinearLayout ll;

    private ListView lv;
    private ArrayList<ToDoData> al;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        ll = findViewById(R.id.maincontainer);

        FirstFragment firstFragment = new FirstFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.maincontainer, firstFragment);
        fragmentTransaction.commit();

        lv = findViewById(R.id.lvdata);
        al = new ArrayList<>();

        mydb = new ToDoSQLiteHelper(DisplayActivity.this);

        c = mydb.getData();

        if(c.getCount()>0){

            if(c.moveToFirst()){

                do{
                    int id = Integer.parseInt(c.getString(0));
                    String title = c.getString(1);
                    String descript = c.getString(2);


                        ToDoData gl = new ToDoData(id, title, descript);


                    al.add(new ToDoData(id, title, descript));

                }while(c.moveToNext());
            }
        }
        //ListAdapter adapter = new ArrayAdapter(DisplayActivity.this,android.R.layout.simple_list_item_1, al );
        lv.setAdapter(new CustomListAdapter(this, al));
       // lv.setAdapter(adapter);

        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                        Toast.makeText(DisplayActivity.this, al.get(position).getTitle() + " "+ al.get(position).getDesc(), Toast.LENGTH_SHORT).show();


                        Intent i = new Intent(DisplayActivity.this, FirstFragment.class);
                        //i.putExtra("Keyname",al.toString() );
                        i.putExtra("Keyname",al.get(position).getTitle() + " " + al.get(position).getDesc());
                        startActivity(i);

                    }
                });


    }
}

