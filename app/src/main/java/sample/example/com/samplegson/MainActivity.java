package sample.example.com.samplegson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sample.example.com.samplegson.util.GetVolley;
import sample.example.com.samplegson.util.PostVolley;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetVolley(getApplicationContext()).execute();
        new PostVolley(getApplicationContext()).execute();
    }



}
