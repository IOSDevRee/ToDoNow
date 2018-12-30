package com.geoacircle.todonow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter{


    private DisplayActivity context;
    private ArrayList<ToDoData> list;



    public CustomListAdapter(DisplayActivity context, ArrayList<ToDoData> list) {
        this.context = context;
        this.list = list;
    }


    @Override


    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{

        //private TextView id;
        private TextView title;
        private TextView desc;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){

            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_list, null);
            holder.title = (TextView) convertView.findViewById(R.id.tv);
            holder.desc = (TextView) convertView.findViewById(R.id.tv2);
            convertView.setTag(holder);

        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(list.get(position).getTitle());
        holder.desc.setText(list.get(position).getDesc());
        //holder.id.setText(list.get(position).getId());

        return convertView;
    }
}
