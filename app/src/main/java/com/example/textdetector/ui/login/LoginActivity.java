package com.example.textdetector.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.textdetector.R;
import com.example.textdetector.ui.forgetpassword.ForgetPassword;
import com.example.textdetector.ui.home.HomeActivity;
import com.example.textdetector.ui.signup.CreateAccount;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    private ProgressBar progressBar;
    TextView signUpBtn;
    TextView forgetPasswordBtn;
    Button loginBtn;
    EditText inputEmail, inputPassword;
    private FirebaseAuth mAuth;
    public static String username;
    ImageView fbBtn;
    CallbackManager callbackManager;
    AccessToken accessToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        signUpBtn = findViewById(R.id.SignUp);
        forgetPasswordBtn = findViewById(R.id.forget_password);
        loginBtn = findViewById(R.id.login_Button);
        inputEmail = (EditText) findViewById(R.id.et_email_login);
        inputPassword = (EditText) findViewById(R.id.et_password_login);
        /*fbBtn = findViewById(R.id.facebook_logo);
        fbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));

            }
        });*/

        /*facebookSignIn();*/
        mAuth = FirebaseAuth.getInstance();
        // Hide password toggle icon by default
        TextInputLayout passwordInput = findViewById(R.id.Password_Input);
        passwordInput.setEndIconVisible(false);

        // Show password toggle icon when password text is entered
        inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    passwordInput.setEndIconVisible(true);
                } else {
                    passwordInput.setEndIconVisible(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if (checkCredentials()) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        LoginToast();

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                        startActivity(i);

                                    } else {

                                        UnSuccessfulLoginToast();

                                    }
                                }
                            });
                }
            }
        });

        forgetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgetPassword.class));
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CreateAccount.class));
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Onseccess();
        }
    }

    /*void facebookSignIn() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        accessToken = AccessToken.getCurrentAccessToken();
                        GraphRequest request = GraphRequest.newMeRequest(
                                accessToken,
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject object,
                                            GraphResponse response) {
//                                            personFullName = object.getString("name");
//                                            personId = object.getString("id");
//                                            personFirstName = object.getString("first_name");
//                                            personLastName = object.getString("last_name");
//                                            personEmail = object.getString("email");
//                                            personPhoto = object.getJSONObject("picture").getJSONObject("data").getString("url");
                                        Onseccess();
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,first_name,last_name,email,picture.type(large)");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        UnSuccessfulLoginToast();
                    }
                });
    }*/

    private void Onseccess() {
        LoginToast();
        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(i);
    }

    private Boolean checkCredentials() {
        Boolean isVaild = true;
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (email.isEmpty() || !email.contains("@") || !email.contains(".com")) {
            showError(inputEmail, "Email is not valid");
            isVaild = false;
        } else if (password.isEmpty() || password.length() < 7) {
            showError(inputPassword, "Password is not valid");
            isVaild = false;
        } else {
            isVaild = true;


        }
        return isVaild;
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

    private void LoginToast() {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.login_successful_toast, this.findViewById(R.id.Toast_login));
        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    private void UnSuccessfulLoginToast() {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.login_unsuccessful_toast, this.findViewById(R.id.Toast_login));
        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }



}