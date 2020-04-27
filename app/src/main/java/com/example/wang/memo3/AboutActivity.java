package com.example.wang.memo3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Dynasty on 2019/3/24.
 */
public class AboutActivity extends AppCompatActivity {

    private TextView title;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about);

        back=(ImageButton)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
            }
        });

    }

}
