package sample.example.com.samplegson.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jarod on 7/4/15.
 */
public class PostVolley extends AsyncTask<String, Void, Boolean> {
    Context context;
    public PostVolley(Context context){
        this.context = context;
    }
    @Override
    protected Boolean doInBackground(String... strings) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.POST, "http://privydoc.com/v2/api/sendCanvas", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response post", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("judulPerjanjian", "mbohh");
                params.put("pihakSatu", "15RU50766");
                params.put("pihakDua", "15AN33995");
                params.put("isiPerjanjian", "ada");
                params.put("pengirim", "pihak 1 pengirim");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
        return null;
    }
}
