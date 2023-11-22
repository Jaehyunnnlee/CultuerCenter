package com.example.culturecenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ModifyActivity extends AppCompatActivity {
    private TextView tv_id,et_pass,et_chpass,et_chpassconfirm,et_birth,et_number;
    private Button btn_modify;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 액티비티 시작시 처음으로 실행되는 생명주기!
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        // 아이디 값 찾아주기
        tv_id=findViewById(R.id.tv_id);
        et_pass = findViewById(R.id.et_pass);
        et_chpass = findViewById(R.id.et_chpass);
        et_chpassconfirm = findViewById(R.id.et_chpassconfirm);
        et_birth = findViewById(R.id.et_birth);
        et_number = findViewById(R.id.et_p_number);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        tv_id.setText(userID);

        // 회원수정 버튼 클릭 시 수행
        btn_modify = findViewById(R.id.btn_modify);
        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userPass = et_pass.getText().toString();
                String changePass = et_chpass.getText().toString();
                String changePassConfirm = et_chpassconfirm.getText().toString();
                String birth = et_birth.getText().toString();
                String number = et_number.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { //가입 성공
                                Toast.makeText(getApplicationContext(), "회원 정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent4 = new Intent(ModifyActivity.this, LoginActivity.class);
                                startActivity(intent4);
                            } else { // 회원등록에 실패한 경우
                                Toast.makeText(getApplicationContext(),"회원 정보 수정에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                // 서버로 Volley를 이용해서 요청을 함.
                ModifyRequest modifyRequest = new ModifyRequest(userID,userPass,changePass,changePassConfirm,birth,number,responseListener);
                RequestQueue queue = Volley.newRequestQueue(ModifyActivity.this);
                queue.add(modifyRequest);
            }
        });
    }
}


