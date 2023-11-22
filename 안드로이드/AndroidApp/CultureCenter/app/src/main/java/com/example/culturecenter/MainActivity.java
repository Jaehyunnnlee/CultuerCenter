package com.example.culturecenter;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_id,tv_name,tv_sex,tv_birth,tv_number;
    private Button btn_delete, btn_perform,btn_modify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_id = findViewById(R.id.tv_id);
        tv_name = findViewById(R.id.tv_name);
        tv_sex = findViewById(R.id.tv_sex);
        tv_birth = findViewById(R.id.tv_birth);
        tv_number = findViewById(R.id.tv_number);
        btn_perform=findViewById(R.id.btn_perform);
        btn_delete=findViewById(R.id.btn_delete);
        btn_modify=findViewById(R.id.btn_modify);


        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String UserName = intent.getStringExtra("UserName");
        String UserSex = intent.getStringExtra("UserSex");
        String UserBirth = intent.getStringExtra("UserBirth");
        String UserPhoneNumber = intent.getStringExtra("UserPhoneNumber");


        btn_perform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PerformListActivity.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });
        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ModifyActivity.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });


        tv_id.setText(userID);
        tv_name.setText(UserName);
        tv_sex.setText(UserSex);
        tv_birth.setText(UserBirth);
        tv_number.setText(UserPhoneNumber);

    }
}
