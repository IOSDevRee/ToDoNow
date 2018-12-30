package com.geoacircle.todonow;


import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    private ListView lv;

    Cursor c;
    private ArrayList al;


    public FirstFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.first_fragment, container, false);

        lv = v.findViewById(R.id.lvdata);

        al = new ArrayList<>();

        ToDoSQLiteHelper mydb = new ToDoSQLiteHelper(getActivity());

        c = mydb.getData();

        if(c.getCount()>0){

            if(c.moveToFirst()){

                do{
                    String id = c.getString(0);
                    String title = c.getString(1);
                    String descript = c.getString(2);


                  //  GetterSetter gl = new GetterSetter(id, email, descript);

                  //  al.add(id);
                    al.add(title);
                  //  al.add(descript);
                   // al.add(new GetterSetter(id, email, descript));

                }while(c.moveToNext());
            }
        }

       // lv.setAdapter(new CustomListAdapter(this, al));
        ListAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, al);

        lv.setAdapter(adapter);


        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                        //Toast.makeText(FirstFragment.this, al.get(position).getTitle() + " "+ al.get(position).getDesc(), Toast.LENGTH_SHORT).show();
                        String item = String.valueOf(adapterView.getItemAtPosition(position));

                        Intent i = new Intent(getActivity(), MainActivity.class);
                        //i.putExtra("Keyname", al.get(position).getTitle());
                         i.putExtra("Keyname", item); /*+ " " + al.get(position).getDesc())*/
                        startActivity(i);

                    }
                });

           // Inflate the layout for this fragment
        return v;

    }


}
