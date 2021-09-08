package com.example.apporder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterItem extends BaseAdapter {
    List<Item> itemList;

    public AdapterItem(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_act_order,parent,false);

        Item item = itemList.get(position);
        String name = item.getItemName();
        double price = item.getPrice();

        TextView tvName = view.findViewById(R.id.tvName_order);
        TextView tvPrice = view.findViewById(R.id.tvGiaItem_order);

        tvName.setText(name);
        tvPrice.setText(price+"$");

        return view;
    }
}
