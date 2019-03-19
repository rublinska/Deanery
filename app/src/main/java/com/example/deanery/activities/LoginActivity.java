package com.example.deanery.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.dataModels.login.AuthTokenFromLogin;
import com.example.deanery.dataModels.login.BodyForLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    final DeaneryAPI client = ServiceGenerator.createService(DeaneryAPI.class);

    @Override
    protected void onResume() {
        super.onResume();
/*
        final String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjgwMDAvYXBpL2F1dGgvbG9naW4iLCJpYXQiOjE1NTI1MDQwODcsImV4cCI6MTU1MjUwNzY4NywibmJmIjoxNTUyNTA0MDg3LCJqdGkiOiJDT2RpaXdJZzRPV3lBT3NCIiwic3ViIjoxLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.O-yZKKxEC4Mt5QPIqxHLTJVT_Sx1Zwzy0w2AIixE8to";

        Call<GetAllLecturers> getLecturersData = client.getAllLecturers(token);
        final List<Lecturer> lecturers = new ArrayList<>();


        getLecturersData.enqueue(new Callback<GetAllLecturers>() {
            @Override
            public void onResponse(Call<GetAllLecturers> call, Response<GetAllLecturers> response) {

                Log.i("LizaTest", client.getAllLecturers(token).request().toString());
                if (response.isSuccessful()) {
                    Log.i("LizaTest", response.body().getData().get(0).getFullName());
                }
            }

            @Override
            public void onFailure(Call<GetAllLecturers> call, Throwable t) {

            }
        });


        Call<User> getUser = client.getUser("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjgwMDAvYXBpL2F1dGgvbG9naW4iLCJpYXQiOjE1NTI0NzE3MzUsImV4cCI6MTU1MjQ3NTMzNSwibmJmIjoxNTUyNDcxNzM1LCJqdGkiOiJHTTh3U0lZV1Z3MUUzakRKIiwic3ViIjoxLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.62T5KgXHZJu8DqY4AsK6_HTRsKq-lihnFSdLragloTQ");

        getUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    Log.i("VladTest", user.getName());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("VladTest", String.valueOf(call.isExecuted()));
                Log.i("VladTest", String.valueOf(call.request().url()));
                Log.i("VladTest", String.valueOf(t.getMessage()));
            }
        });
*/




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);


        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {

                    return true;
                }
                return false;
            }
        });

        OnClickListener checkLoginCredentials = new OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<AuthTokenFromLogin> call = client.loginPost(new BodyForLogin(mEmailView.getText().toString(), mPasswordView.getText().toString()));

                call.enqueue(new Callback<AuthTokenFromLogin>() {
                    @Override
                    public void onResponse(Call<AuthTokenFromLogin> call, Response<AuthTokenFromLogin> response) {

                        if (response.isSuccessful() && response.body().getAccessToken()!=null) {
                            Log.i("token", response.body().getAccessToken().toString());
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.putExtra("token", response.body().getAccessToken());
                            getApplicationContext().startActivity(i);

                        } else if (response.message() == "Unauthorized") {
                            //todo: add message about failed authorization
                            Toast.makeText(getApplicationContext(), "Check your credentials",
                                    Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<AuthTokenFromLogin> call, Throwable t) {
                        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (!(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)) {
                            Toast.makeText(getApplicationContext(), "Check your internet connection and try again",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        };

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        ((Button) findViewById(R.id.email_sign_in_button)).setOnClickListener(checkLoginCredentials);

    }

}

