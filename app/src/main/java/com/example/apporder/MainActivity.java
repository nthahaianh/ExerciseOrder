package com.example.apporder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apporder.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    TextView user;
    TextView pass;
    TextView tBao;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        user = findViewById(R.id.etUsername);
        pass = findViewById(R.id.etPassword);
        tBao = findViewById(R.id.tvThongbao);
        btn = findViewById(R.id.btnLogin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getText().toString().isEmpty() || pass.getText().toString().isEmpty())
                {
                    tBao.setText("Không được để trống");
                }
                else {
                    Intent intent = new Intent(MainActivity.this,OrderActivity.class);
                    intent.putExtra("name",user.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }
}