package fr.wcs.wildtweet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String EXTRA_LOGIN = "EXTRA_LOGIN";
    public static String EXTRA_PASSWORD = "EXTRA_PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editLogin = findViewById(R.id.edit_login);
        // initialiser les sharedPreferences
        final SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        // récupérer le username du cache s'il existe
        String usernameCache = sharedPref.getString("username", "");
        editLogin.setText(usernameCache);

        final Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editPassword = findViewById(R.id.edit_password);
                String passwordValue = editPassword.getText().toString();
                String loginValue = editLogin.getText().toString();
                if (loginValue.isEmpty() || passwordValue.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.error_empty, Toast.LENGTH_SHORT).show();
                } else {
                    // enregistrer dans le cache de l'application
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("username", loginValue);
                    editor.commit();

                    // TODO : initialiser l'utilisateur
                    WilderModel wilder = new WilderModel(loginValue, passwordValue);

                    Intent goToTweetList = new Intent(MainActivity.this,
                            ListTweetActivity.class);
                    goToTweetList.putExtra(EXTRA_LOGIN, wilder.getUsername());
                    MainActivity.this.startActivity(goToTweetList);
                }
            }
        });

        CheckBox checkCGU = findViewById(R.id.check_cgu);
        checkCGU.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                buttonLogin.setEnabled(isChecked);
            }
        });
    }
}
