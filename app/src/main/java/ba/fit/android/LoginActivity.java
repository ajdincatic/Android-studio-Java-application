package ba.fit.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import ba.fit.android.data.KorisnikVM;
import ba.fit.android.data.Storage;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnLoginClick();
            }
        });
    }

    private void do_btnLoginClick() {
        KorisnikVM x = Storage.LoginCheck(txtUsername.getText().toString(),
                txtPassword.getText().toString());

        if(x==null){
            // parnet view
            View v = findViewById(android.R.id.content);
            Snackbar.make(v,"Pogresan unos",Snackbar.LENGTH_LONG).show();
        }
        else{
            startActivity(new Intent(this,GlavniActivity.class));
        }
    }
}
