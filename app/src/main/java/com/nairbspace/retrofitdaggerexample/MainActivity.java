package com.nairbspace.retrofitdaggerexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mApiUrlEditText;
    private Button mRunButton;
    private TextView mResponseHeader;
    private TextView mResponse;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiUrlEditText = (EditText) findViewById(R.id.api_url);
        mRunButton = (Button) findViewById(R.id.run);
        mResponseHeader = (TextView) findViewById(R.id.response_header_title);
        mResponse = (TextView) findViewById(R.id.response_header);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mRunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Run Retrofit
            }
        });
    }
}
