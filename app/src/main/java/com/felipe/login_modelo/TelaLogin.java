package com.felipe.login_modelo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TelaLogin extends AppCompatActivity {

    private EditText logEmail, logPass;
    Button login;

    private Button register;
    private TextView forgotPass;
    //private TextView register;

    private String email, pass;

    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        logEmail = findViewById(R.id.LogEmail);
        logPass = findViewById(R.id.LogPass);
        login = findViewById(R.id.userLogin);
        register = findViewById(R.id.openRegister);
        forgotPass = findViewById(R.id.forgotPassword);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    validateUser();
            }
        });
/*
forgotPass.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(TelaLogin.this,ForgotPassword.class));
        finish();
    }
}); */


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(this,TelaPrincipal.class));
            finish();
        }
    }


    public void telaregistro(View view) {

  //      Intent it = new Intent(this,RegistroLogin.class);
      //  startActivity(it);

        startActivity( new Intent(TelaLogin.this,RegistroLogin.class));
        finish();

    }


    public void forgotpassword(View view) {



        startActivity( new Intent(TelaLogin.this,ForgotPassword.class));
        //finish();

    }

    private void validateUser() {
        email = logEmail.getText().toString();
        pass = logPass.getText().toString();
        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Preencha os Campos", Toast.LENGTH_SHORT).show();
        } else {
            loginUser();
        }

    }
    private void loginUser() {
            auth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            Toast.makeText(TelaLogin.this, "Login Sucess", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(TelaLogin.this, TelaPrincipal.class));
                            finish();
                        } else {

                            Toast.makeText(TelaLogin.this,"Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }


}
