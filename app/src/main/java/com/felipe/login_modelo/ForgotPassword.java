package com.felipe.login_modelo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText forEmail;
    private Button forgot;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forEmail = findViewById(R.id.forEmail);
        forgot = findViewById(R.id.forgot);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = forEmail.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(ForgotPassword.this, "Campo Vazio",Toast.LENGTH_SHORT).show();
                } else {
                    forgotPassword();
                }
            }
        });

    }


private void forgotPassword(){

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()) {
                            Toast.makeText(ForgotPassword.this,"Verifique seu email",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPassword.this,TelaLogin.class));
                            finish();
                         } else {

             Toast.makeText(ForgotPassword.this,"Error : " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }

                    }
                });

}


}
