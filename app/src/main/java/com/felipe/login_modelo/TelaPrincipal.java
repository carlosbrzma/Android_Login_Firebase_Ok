package com.felipe.login_modelo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TelaPrincipal extends AppCompatActivity {

    private TextView name, email;
    private Button logout;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        name = findViewById(R.id.userName);
        email = findViewById(R.id.userEmail);
        logout = findViewById(R.id.userLogout);

        if (user != null ) {
            name.setText(user.getDisplayName());
            email.setText(user.getEmail());
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(TelaPrincipal.this,TelaLogin.class));
                finish();
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        if (user == null) {
            startActivity(new Intent(this,TelaLogin.class));
            finish();
        }
    }

}
