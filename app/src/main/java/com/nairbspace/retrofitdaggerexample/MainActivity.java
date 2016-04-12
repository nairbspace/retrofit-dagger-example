package com.nairbspace.retrofitdaggerexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nairbspace.retrofitdaggerexample.retrofit.ExampleInterceptor;
import com.nairbspace.retrofitdaggerexample.retrofit.ExampleInterface;
import com.nairbspace.retrofitdaggerexample.retrofit.model.ExampleModel;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Inject ExampleInterceptor mInterceptor;
    @Inject ExampleInterface mInterface;

    private EditText mApiUrlEditText;
    private Button mRunButton;
    private TextView mResponseHeader;
    private TextView mResponse;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetupApplication.get(this).getAppComponent().inject(this); // Inject dependencies to Activity

        mApiUrlEditText = (EditText) findViewById(R.id.api_url);
        mRunButton = (Button) findViewById(R.id.run);
        mResponseHeader = (TextView) findViewById(R.id.response_header_title);
        mResponse = (TextView) findViewById(R.id.response_header);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mRunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar();

                /** Will return null if http/https url is not properly formatted **/
                HttpUrl url = HttpUrl.parse(mApiUrlEditText.getText().toString());

                if (url == null) {
                    showNothingAndToast("API URL is empty or improperly formatted.");
                } else {
                    runRetrofit(url.toString());
                }
            }
        });
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mResponseHeader.setVisibility(View.GONE);
        mResponse.setVisibility(View.GONE);
    }

    private void showResponse() {
        mProgressBar.setVisibility(View.GONE);
        mResponseHeader.setVisibility(View.VISIBLE);
        mResponse.setVisibility(View.VISIBLE);
    }

    private void showNothingAndToast(String toastText) {
        mProgressBar.setVisibility(View.GONE);
        mResponseHeader.setVisibility(View.GONE);
        mResponse.setVisibility(View.GONE);

        Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_LONG).show();
    }

    private void runRetrofit(String apiUrl) {
        mInterceptor.setInterceptor(apiUrl); /** This is where we change the Base URL! **/

        Call<ExampleModel> call = mInterface.get();
        call.enqueue(new Callback<ExampleModel>() {
            @Override
            public void onResponse(Call<ExampleModel> call, Response<ExampleModel> response) {
                String header = response.headers().toString();
                if (TextUtils.isEmpty(header)) {
                    String blank = "(Header is blank.)";
                    mResponse.setText(blank);
                } else {
                    mResponse.setText(header);
                }

                showResponse();
            }

            @Override
            public void onFailure(Call<ExampleModel> call, Throwable t) {
                showNothingAndToast("Retrofit failed.");
            }
        });
    }
}
