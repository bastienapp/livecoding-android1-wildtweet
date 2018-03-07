package fr.wcs.wildtweet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String EXTRA_FIRSTNAME = "EXTRA_FIRSTNAME";
    public static String EXTRA_LASTNAME = "EXTRA_LASTNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editFirstname = findViewById(R.id.edit_firstname);
                EditText editLastname = findViewById(R.id.edit_lastname);
                String firstnameValue = editFirstname.getText().toString();
                String lastnameValue = editLastname.getText().toString();
                if (firstnameValue.isEmpty() || lastnameValue.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.error_empty, Toast.LENGTH_SHORT).show();
                } else {
                    Intent goToTweetList = new Intent(MainActivity.this,
                            ListTweetActivity.class);
                    goToTweetList.putExtra(EXTRA_FIRSTNAME, firstnameValue);
                    goToTweetList.putExtra(EXTRA_LASTNAME, lastnameValue);
                    MainActivity.this.startActivity(goToTweetList);
                }
            }
        });

        CheckBox checkCGU = findViewById(R.id.check_cgu);
        checkCGU.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                EditText editFirstname = findViewById(R.id.edit_firstname);
                EditText editLastname = findViewById(R.id.edit_lastname);

                editFirstname.setEnabled(isChecked);
                editLastname.setEnabled(isChecked);
                buttonLogin.setEnabled(isChecked);
            }
        });
    }
}
