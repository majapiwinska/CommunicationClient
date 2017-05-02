package com.example.snapchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maja.snapchat.R;
import com.example.snapchat.api.Api;
import com.example.snapchat.api.dto.UserDto;
import com.example.snapchat.database.DatabaseFacade;
import com.example.snapchat.database.model.User;
import com.example.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by maja on 10.04.17.
 */

public class WelcomeActivity extends AppCompatActivity {

    private Preferences preferences;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signUpButton;

    private WelcomeActivity thisInstance;
    private DatabaseFacade databaseFacade = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = Preferences.getInstance(this);
        thisInstance = this;

        if (preferences.isLogged()) {
            startMainActivity();
            return;
        }

        setContentView(R.layout.activiti_login);
        emailEditText = (EditText) findViewById(R.id.login_user_email_et);
        passwordEditText = (EditText) findViewById(R.id.login_user_password_et);
        loginButton = (Button) findViewById(R.id.log_in_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        signUpButton = (Button) findViewById(R.id.sign_up_login_btn);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void login() {
        final String email = emailEditText.getText().toString();
        final String password = passwordEditText.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(this, "Missing email", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Missing password", Toast.LENGTH_SHORT).show();
        } else {
            UserDto userDto = new UserDto("", "", "", email, password);
            Api.getInstance().login(userDto)
                    .enqueue(new Callback<UserDto>() {
                        @Override
                        public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                            try {
                                if (response.code() != 200) {
                                    Toast.makeText(thisInstance, "Niepoprawne dane logowania!", Toast.LENGTH_SHORT).show();
                                    this.onFailure(call, new Throwable("Niepoprawne dane logowania"));
                                } else {
                                    preferences.setEmail(email);
                                    preferences.setPassword(password);
                                    User user = new User();
                                    user.setEmail(email);
                                    user.setPassword(password);
                                    getHelper().createOrUpdateUser(user);
                                    startMainActivity(user);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserDto> call, Throwable t) {
                            Log.d(WelcomeActivity.class.getSimpleName(), "Error in login(): " + t.getLocalizedMessage());
                        }
                    });

        }
    }

    private void signUp(){
      Intent myIntent = new Intent(WelcomeActivity.this, SignUpActivity.class);
      WelcomeActivity.this.startActivity(myIntent);
    };

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void startMainActivity(User user) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public DatabaseFacade getHelper() {
        if (databaseFacade == null) {
            databaseFacade=new DatabaseFacade(this);
        }
        return databaseFacade;
    }

}
