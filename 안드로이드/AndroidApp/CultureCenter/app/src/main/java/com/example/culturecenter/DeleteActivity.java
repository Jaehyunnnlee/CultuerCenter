package com.example.culturecenter;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class DeleteActivity extends AppCompatActivity {
    private TextView tv_id;
    private EditText et_pass,et_passcon;
    private Button btn_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        et_pass = findViewById(R.id.et_pass);
        et_passcon = findViewById(R.id.et_passcon);
        btn_delete = findViewById(R.id.btn_delete);
        tv_id = findViewById(R.id.tv_id);
        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        tv_id.setText(userID);


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userPass = et_pass.getText().toString();
                String userPassConfirm = et_passcon.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success)
                            {
                                Toast.makeText(getApplicationContext(),"회원 탈퇴가 완료되었습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DeleteActivity.this, LoginActivity.class);
                                startActivity(intent);

                            }
                            else { //회원가입 실패 case1
                                Toast.makeText(getApplicationContext(),"비밀번호가 다릅니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                if(userPass.equals(userPassConfirm)) {
                    DeleteRequest deleteRequest = new DeleteRequest(userID,userPass,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(DeleteActivity.this);
                    queue.add(deleteRequest);
                }
                else
                {//회원가입 실패 case2
                    Toast.makeText(getApplicationContext(),"비밀번호와 그 확인값이 다릅니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
