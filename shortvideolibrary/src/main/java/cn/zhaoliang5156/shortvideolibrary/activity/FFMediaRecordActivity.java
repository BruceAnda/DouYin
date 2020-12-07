package cn.zhaoliang5156.shortvideolibrary.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import cn.zhaoliang5156.shortvideolibrary.fragment.FFMediaRecordFragment;

import cn.zhaoliang5156.shortvideolibrary.R;

public class FFMediaRecordActivity extends AppCompatActivity {

    private static final String FRAGMENT_FFMEDIA_RECORD = "FRAGMENT_FFMEDIA_RECORD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ffmedia_record);
        if (null == savedInstanceState) {
            FFMediaRecordFragment fragment = new FFMediaRecordFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment, FRAGMENT_FFMEDIA_RECORD)
                    .commit();
        }
    }

}
