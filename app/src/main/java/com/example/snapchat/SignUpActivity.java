package com.example.snapchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Preferences;
import com.example.maja.snapchat.R;
import com.example.snapchat.api.Api;
import com.example.snapchat.api.dto.UserDto;
import com.example.snapchat.database.DatabaseFacade;
import com.example.snapchat.database.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText nickEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signUpButton;

    private Preferences preferences;
    private SignUpActivity thisInstance;
    private DatabaseFacade databaseFacade = null;

    public SignUpActivity() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = Preferences.getInstance(this);
        thisInstance = this;

        setContentView(R.layout.fragment_sign_up);
        nameEditText = (EditText) findViewById(R.id.user_name_et);
        surnameEditText = (EditText) findViewById(R.id.user_surname_et);
        nickEditText = (EditText) findViewById(R.id.user_nick_et);
        emailEditText = (EditText) findViewById(R.id.user_email_et);
        passwordEditText = (EditText) findViewById(R.id.user_password_et);
        signUpButton = (Button) findViewById(R.id.sign_up_btn);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

   public void signUp(){
       final String name = nameEditText.getText().toString();
       final String surname = surnameEditText.getText().toString();
       final String nick = nickEditText.getText().toString();
       final String email = emailEditText.getText().toString();
       final String password = passwordEditText.getText().toString();
       if (email.isEmpty()) {
           Toast.makeText(this, "Missing email", Toast.LENGTH_SHORT).show();
       } else if (password.isEmpty()) {
           Toast.makeText(this, "Missing password", Toast.LENGTH_SHORT).show();
       } else if (nick.isEmpty()) {
           Toast.makeText(this, "Missing nick", Toast.LENGTH_SHORT).show();
       }else if (name.isEmpty()) {
           Toast.makeText(this, "Missing name", Toast.LENGTH_SHORT).show();
       } else if (surname.isEmpty()) {
           Toast.makeText(this, "Missing surname", Toast.LENGTH_SHORT).show();
       } else {
           UserDto dto = new UserDto(name, surname, nick, email,password);
           Api.getInstance().signUp(dto)
                   .enqueue(new Callback<UserDto>() {
                       @Override
                       public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                           Toast.makeText(SignUpActivity.this, "OK on response!", Toast.LENGTH_SHORT).show();
                           try {
                               if (response.code() != 200) {
                                   Toast.makeText(thisInstance, "Invalid registration data!", Toast.LENGTH_SHORT).show();
                                   this.onFailure(call, new Throwable("Invalid registration data"));
                               } else {
                                   preferences.setEmail(email);
                                   preferences.setPassword(password);
                                   User user = new User();
                                   user.setId(response.body().getId());
                                   user.setName(name);
                                   user.setSurname(surname);
                                   user.setNick(nick);
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
                           Toast.makeText(SignUpActivity.this, "on failure " + call.request().url().toString() + t.toString(), Toast.LENGTH_SHORT).show();
                            Log.d(SignUpActivity.class.getSimpleName(), "Registration in login(): " + t.getLocalizedMessage());
                       }
                   });

        }
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
