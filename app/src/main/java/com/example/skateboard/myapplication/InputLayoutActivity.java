package com.example.skateboard.myapplication;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

public class InputLayoutActivity extends AppCompatActivity {

    private TextInputLayout inputLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_layout);
        inputLayout= (TextInputLayout) findViewById(R.id.text_input);
        inputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                 if((s.toString()).equals("aaa"))
                 {
                     inputLayout.setErrorEnabled(true);
                     inputLayout.setError("error");
                 }
                else
                 {
                     inputLayout.setErrorEnabled(false);
                 }
            }
        });
    }
}
