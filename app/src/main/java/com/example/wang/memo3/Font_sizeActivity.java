package com.example.wang.memo3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Font_sizeActivity extends AppCompatActivity {

    private Button mSmall;
    private Button mMedium;
    private Button mLarge;
    private int mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            mode=getIntent().getIntExtra("textsize", 1);
        }catch(NullPointerException e){
            e.printStackTrace();
        }catch (Exception e) {
        }
        if(mode==-1||mode==1){
            this.setTheme(R.style.Theme_Small);
        }else if(mode==2){
            this.setTheme(R.style.Theme_Medium);
        }else {
            this.setTheme(R.style.Theme_Large);
        }

        setContentView(R.layout.activity_font_size);

        mSmall = (Button)findViewById(R.id.small);
        mSmall.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

            }
        });
        mMedium = (Button)findViewById(R.id.medium);
        mMedium.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                setTheme(R.style.Theme_Medium);
            }
        });
        mLarge = (Button)findViewById(R.id.large);
        mLarge.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                setTheme(R.style.Theme_Large);
            }
        });
    }
}
