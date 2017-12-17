package at.main.ghwt.terminverwaltung_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Martin on 08.12.2017.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.btnLogin);
        final EditText txtPassword   = (EditText) findViewById(R.id.txtPassword);
        final EditText txtUsername = (EditText) findViewById(R.id.txtUsername);

        txtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        loginButton.setOnClickListener( new View.OnClickListener()
        {
            public void onClick (View v){
                if(txtUsername.getText().toString().equals("martin") & txtPassword.getText().toString().equals("tomii"))
                {
                    next_page(v);
                }
                else
                {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.btnLogin), txtUsername.getText().toString().length() + ";" + txtPassword.getText().toString().length(), Snackbar.LENGTH_LONG);

                    snackbar.show();
                }
            }
        });
    }

    public void next_page(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}



