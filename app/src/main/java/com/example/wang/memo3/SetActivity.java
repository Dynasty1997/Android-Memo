package com.example.wang.memo3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

public class SetActivity extends AppCompatActivity {

    private ImageButton back;
    private Button about;
    private Button font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_set);

        back=(ImageButton)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
            }
        });

        font = (Button)findViewById(R.id.font_size);
        font.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SetActivity.this,Font_sizeActivity.class);
                startActivity(intent);
            }
        });

        about=(Button)findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SetActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });
    }
}
