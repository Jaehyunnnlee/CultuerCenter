package com.example.culturecenter;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


public class PerformListActivity extends AppCompatActivity {

    private TextView tv_pn,tv_pdate,tv_pt,tv_pf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform);
        tv_pn = findViewById(R.id.tv_pn);
        tv_pdate = findViewById(R.id.tv_pdate);
        tv_pt= findViewById(R.id.tv_pt);
        tv_pf= findViewById(R.id.tv_pf);
        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {
                        String performname = jsonObject.getString("performname");
                        String startdate  = jsonObject.getString("startdate");
                        String starttime  = jsonObject.getString("starttime");
                        String endtime  = jsonObject.getString("endtime");
                        String performfee  = jsonObject.getString("performfee");
                        String yoil  = jsonObject.getString("yoil");

                        if (performname.equals("null"))
                        {
                            tv_pn.setText("예약된 공연이 없습니다.");
                            tv_pdate.setText("예약된 공연이 없습니다.");
                            tv_pt.setText("예약된 공연이 없습니다.");
                            tv_pf.setText("예약된 공연이 없습니다.");
                        }
                        else {
                            tv_pn.setText(performname);
                            tv_pdate.setText(startdate + yoil);
                            tv_pt.setText(starttime + "~" + endtime);
                            tv_pf.setText(performfee);
                        }
                    }
                    else
                    {
                        //nothing to do
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        PerformListRequest performlistRequest = new PerformListRequest(userID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(PerformListActivity.this);
        queue.add(performlistRequest);

    }
}

