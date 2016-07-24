package com.org.fresscodemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/7/19.
 */
public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView clickTv;
    private EditText testEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        clickTv = (TextView) findViewById(R.id.tv_click);
        clickTv.setOnClickListener(this);
        testEt = (EditText) findViewById(R.id.et_test);
    }

    @Override
    public void onClick(View v) {
        clickTv.setVisibility(View.GONE);
        testEt.requestFocus();
        InputMethodManager imm = (InputMethodManager) testEt.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0,InputMethodManager.SHOW_FORCED);
    }
}
