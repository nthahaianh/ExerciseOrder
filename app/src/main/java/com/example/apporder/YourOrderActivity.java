package com.example.apporder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class YourOrderActivity extends AppCompatActivity {
    ListView lvYourOrder;
    AdapterItem adapterItem;
    Item item;
    List<Item> itemList;
    ImageView ivArrow;
    TextView tvTotal,tvThongBao;
    double tongTien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        lvYourOrder = findViewById(R.id.lvYourOrder);
        ivArrow = findViewById(R.id.ivArrow_yourorder);
        tvTotal = findViewById(R.id.tvGia_yourorder);

        Intent intent = getIntent();

        ArrayList<String> ten = intent.getStringArrayListExtra("ten");
        ArrayList<String> gia = intent.getStringArrayListExtra("gia");
        ArrayList<Integer> sl = intent.getIntegerArrayListExtra("sl");

        itemList = new ArrayList<>();
        int dem = 0;
        for (int i = 0; i < ten.size(); i++) {
            int soLuong = sl.get(i);
            if(soLuong>0){
                String show = ten.get(i) + " (" + soLuong + ") ";
                double giaTien = Double.parseDouble(gia.get(i));
                item = new Item(show,giaTien,soLuong);
                itemList.add(item);
                tongTien += soLuong*giaTien;
                dem++;
            }
        }

        if(dem == 0)
        {
            tvThongBao = findViewById(R.id.tvThongbao_yourorder);
            tvThongBao.setText("You have not selected any things yet");
        }

        DecimalFormat df = new DecimalFormat("###.##");
        String x = df.format(tongTien);
        tvTotal.setText(x+"$");
        adapterItem = new AdapterItem(itemList);
        lvYourOrder.setAdapter(adapterItem);

        ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getBaseContext(),OrderActivity.class);

                intent1.putStringArrayListExtra("ten",ten);
                intent1.putStringArrayListExtra("gia",gia);
                intent1.putIntegerArrayListExtra("sl",sl);

               setResult(RESULT_OK,intent1);
               finish();
            }
        });
    }
}