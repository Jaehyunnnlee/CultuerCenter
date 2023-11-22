package com.example.culturecenter;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ModifyRequest extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://software.hongik.ac.kr/a_team/a_team4/dbproject/Android/modifymember.php";
    private Map<String, String> map;


    public ModifyRequest( String userID,String userPass,String changePass,String changePassConfirm,String birth,String number, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPass", userPass);
        map.put("changePass", changePass);
        map.put("changePassConfirm", changePassConfirm);
        map.put("birth", birth);
        map.put("number", number);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
