package com.example.culturecenter;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://software.hongik.ac.kr/a_team/a_team4/dbproject/Android/register.php";
    private Map<String, String> map;


    public RegisterRequest(String userID, String userPass,String userPassConfirm,String userName,String sex,String birth,String number, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID",userID);
        map.put("userPass", userPass);
        map.put("userPassConfirm", userPassConfirm);
        map.put("userName", userName);
        map.put("sex", sex);
        map.put("birth", birth);
        map.put("number", number);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
