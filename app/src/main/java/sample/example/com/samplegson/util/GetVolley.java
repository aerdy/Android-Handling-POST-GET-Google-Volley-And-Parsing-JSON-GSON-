package sample.example.com.samplegson.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

/**
 * Created by jarod on 7/4/15.
 */
public class GetVolley extends AsyncTask<String, Void, Boolean> {
    Context context;
    public GetVolley(Context context){
        this.context = context;
    }
    @Override
    protected Boolean doInBackground(String... strings) {
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "http://privydoc.com/v2/api/form/form-apartemen-step-1";
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub
                JsonParser jsonParser = new JsonParser();
                JsonObject jo = (JsonObject) jsonParser.parse(response.toString());
                JsonArray jsonArr = jo.getAsJsonArray("form");
                //jsonArr.
                for (int a = 0; a < jsonArr.size(); a++) {
                    JsonObject jsonObject = jsonArr.get(a).getAsJsonObject();
                    String text = jsonObject.get("type").getAsString();
                    String name = jsonObject.get("name").getAsString();
                    Log.d("text", text);
                    Log.d("name", name);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == HttpStatus.SC_UNAUTHORIZED) {
                    // HTTP Status Code: 401 Unauthorized
                    Log.d("response code", "" + networkResponse.statusCode);
                }
            }
        });
        //timeout
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                1000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsObjRequest);
        queue.start();
        Log.d("cache", "" + queue.getCache());
        return null;
    }
}


