package fr.wcs.wildtweet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static fr.wcs.wildtweet.MainActivity.EXTRA_FIRSTNAME;
import static fr.wcs.wildtweet.MainActivity.EXTRA_LASTNAME;

public class ListTweetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tweet);

        Intent intent = getIntent();
        String firstnameTweet = intent.getStringExtra(EXTRA_FIRSTNAME);
        String lastnameTweet = intent.getStringExtra(EXTRA_LASTNAME);

        Toast.makeText(this, firstnameTweet + " " + lastnameTweet, Toast.LENGTH_SHORT).show();
    }
}
