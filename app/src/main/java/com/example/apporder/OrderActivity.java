package com.example.apporder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvWelcome,tvGia_order,tvSum,tvThanks;
    Button btnOrder;
    Item item,item1,item2,item3,item4,item5;
    AdapterItem adapterItem;
    List<Item> itemList, itemListChon;
    ListView lvOrder;
    float totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        imageView = findViewById(R.id.ivGioHang);
        tvWelcome = findViewById(R.id.tvWelcome_order);
        tvGia_order = findViewById(R.id.tvGia_order);
        tvSum = findViewById(R.id.tvSum_order);
        btnOrder = findViewById(R.id.btnOrder);
        tvThanks = findViewById(R.id.tvThanks);
        lvOrder = findViewById(R.id.lvOrder);

        Intent intent1 = getIntent();
        String name = intent1.getStringExtra("name");
        tvWelcome.setText(tvWelcome.getText() + " " + name);

        item = new Item("Hồng trà sữa", 2,0);
        item1 = new Item("Sữa tươi trân châu đường đen",2,0);
        item2 = new Item("Hồng trà",1.5,0);
        item3 = new Item("Trà đào",1.8,0);
        item4 = new Item("Trà đào cam xả",1.8,0);
        item5 = new Item("Trà bưởi",1.8,0);

        itemList = new ArrayList<>();
        itemList.add(item);
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
        itemList.add(item5);

        itemListChon = new ArrayList<>();
        itemListChon = itemList;

        adapterItem = new AdapterItem(itemList);
        lvOrder.setAdapter(adapterItem);

        lvOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item itemX = itemListChon.get(position);

                itemX.setQuantity(itemX.getQuantity()+1);   //tăng số lượt chọn trong listChon

                totalPrice += itemX.getPrice();             //Tính tổng tiền
                DecimalFormat df = new DecimalFormat("###.##");
                String x = df.format(totalPrice);
                tvGia_order.setText(x + "$");

                int sl = Integer.parseInt(tvSum.getText().toString());      //Đếm số lượng sp đã chọn
                sl++;
                tvSum.setText(sl+"");

            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvThanks.setText("Thank you! Your order is sent to store");
                totalPrice=0;
                tvGia_order.setText(totalPrice+"$");
                tvSum.setText("0");
                for (Item item:itemListChon)
                {
                    item.setQuantity(0);
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this,YourOrderActivity.class);
                ArrayList<String> ten = new ArrayList<>();
                ArrayList<String> gia = new ArrayList<>();
                ArrayList<Integer>  sl = new ArrayList<>();
                for (Item item:itemListChon) {                                      //Đổ dữ liệu vào mảng
                        ten.add(item.getItemName());
                        gia.add(item.getPrice()+"");
                        sl.add(item.getQuantity());
                }
                intent.putStringArrayListExtra("ten",ten);              //Đưa dữ liệu vào Extra
                intent.putStringArrayListExtra("gia",gia);
                intent.putIntegerArrayListExtra("sl",sl);
                startActivityForResult(intent,11);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
            if(requestCode==11)
            {
                tvThanks.setText("");
//                itemListChon.clear();
//                ArrayList<String> ten = new ArrayList<>();
//                ArrayList<String> gia = new ArrayList<>();
//                ArrayList<Integer> sl = new ArrayList<>();
//
//                ten = data.getStringArrayListExtra("ten");
//                gia = data.getStringArrayListExtra("gia");
//                sl = data.getIntegerArrayListExtra("sl");
//
//                for (int i = 0; i < ten.size(); i++) {
//                    double giaTien = Double.parseDouble(gia.get(i));
//                    Item itemTG = new Item(ten.get(i).toString(),giaTien,sl.get(i));
//                    itemListChon.add(itemTG);
//                }
            }
    }
}