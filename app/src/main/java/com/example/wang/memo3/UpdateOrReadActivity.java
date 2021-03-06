package com.example.wang.memo3;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dynasty on 2019/3/18.
 */
public class UpdateOrReadActivity extends Activity implements RadioGroup.OnCheckedChangeListener{
    private Button mComplete;
    private Button mBackBtn;
    private EditText mContent;
    private LinearLayout mScreen;
    private TextView tv_date;
    private int noteId;
    private String noteTime;
    private String noteContent;
    private String originData;

    private NoteDB mNoteDB;
    private static Note note;

    LinearLayout myLayout;
    RadioGroup tagRadio;
    RadioButton rdButton;
    int tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.write_note);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("note_id",0);
        Log.d("Anonymous", "传递后的备忘录ID:" + noteId);

        initView();
        tv_date = (TextView) findViewById(R.id.tv_date);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = sdf.format(date);
        tv_date.setText(dateString);

        new LoadAsyncTask().execute();
        initEvent();
    }

    private void initView() {
        mComplete = (Button) findViewById(R.id.complete_btn);
        mBackBtn = (Button) findViewById(R.id.back_btn);
        mContent = (EditText) findViewById(R.id.note_content);
        mScreen = (LinearLayout) findViewById(R.id.whole);
//      下划线
//        mContent.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        mNoteDB = NoteDB.getInstance(this);

//      新添加内容
        myLayout = (LinearLayout)findViewById(R.id.whole);
        myLayout.setBackgroundResource(R.drawable.bg);

        tagRadio=(RadioGroup) findViewById(R.id.tagRadio);
        tagRadio.setOnCheckedChangeListener(this);

        setRadioButtonCheckedAccordingToTag(tag);
        rdButton.setChecked(true);
    }

    private void initEvent() {

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataOrNot();
            }
        });

        mComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mContent.getText().toString().trim().equals("")){
//          Log.d("Dynasty","进入判断为空函数");
                    new DeleteAsyncTask(mNoteDB).execute(noteId);
                    finish();
                } else if (mContent.getText().toString().equals(originData)) {
                    finish();
                } else {
//          Log.d("Dynasty","进入判断不为空函数");
                    new UpdateAsyncTask().execute();
                    Toast.makeText(UpdateOrReadActivity.this, "修改成功!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }

    class LoadAsyncTask extends AsyncTask<Void,Void,Note>{

        @Override
        protected Note doInBackground(Void... voids) {
            note = mNoteDB.loadById(noteId);
            return note;
        }

        @Override
        protected void onPostExecute(Note note) {
            super.onPostExecute(note);

            originData = note.getContent();
            mContent.setText(note.getContent());
            mContent.setSelection(mContent.getText().toString().length());
        }
    }
    class UpdateAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
            Date date = new Date(System.currentTimeMillis());
            noteTime = sdf.format(date);
            noteContent = mContent.getText().toString();
            note.setTime(noteTime);
            note.setContent(noteContent);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mNoteDB.updateById(noteTime, noteContent, noteId);
            return null;
        }
    }

    private void updateDataOrNot() {
        if (!mContent.getText().toString().equals(originData)) {
            new AlertDialog.Builder(UpdateOrReadActivity.this)
                    .setTitle("提示")
                    .setMessage("需要保存您编辑的内容吗？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new UpdateAsyncTask().execute();
                            finish();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        } else {
            finish();
        }
    }
    @Override
    public void onBackPressed() {
        updateDataOrNot();
    }

    private void setRadioButtonCheckedAccordingToTag(int tag) {
        switch (tag) {
            case 0:
                rdButton=(RadioButton) findViewById(R.id.bg);
                break;
            case 1:
                rdButton=(RadioButton) findViewById(R.id.yellow);
                break;
            case 2:
                rdButton=(RadioButton) findViewById(R.id.blue);
                break;
            case 3:
                rdButton=(RadioButton) findViewById(R.id.green);
                break;
            case 4:
                rdButton=(RadioButton) findViewById(R.id.red);
                break;
            case 5:
                rdButton=(RadioButton) findViewById(R.id.white);
                break;
            default:
                break;
        }
    }

    public void onCheckedChanged (RadioGroup group, int checkedId) {
        switch (tagRadio.getCheckedRadioButtonId()) {
            case R.id.bg:
                tag=0;
                //edt.setBackgroundColor(color[tag]);
                myLayout.setBackgroundResource(R.drawable.bg);
                break;
            case R.id.yellow:
                tag=1;
                myLayout.setBackgroundResource(R.drawable.yellow);
                break;
            case R.id.blue:
                tag=2;
                myLayout.setBackgroundResource(R.drawable.blue);
                break;
            case R.id.green:
                tag=3;
                myLayout.setBackgroundResource(R.drawable.green);
                break;
            case R.id.red:
                tag=4;
                myLayout.setBackgroundResource(R.drawable.pink);
                break;
            case R.id.white:
                tag=5;
                myLayout.setBackgroundResource(R.drawable.white0);
                break;
            default:
                break;
        }
    }
}
