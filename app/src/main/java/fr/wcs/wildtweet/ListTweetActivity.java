package fr.wcs.wildtweet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static fr.wcs.wildtweet.MainActivity.EXTRA_PASSWORD;
import static fr.wcs.wildtweet.MainActivity.EXTRA_LOGIN;

public class ListTweetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tweet);

        Intent intent = getIntent();
        String loginTweet = intent.getStringExtra(EXTRA_LOGIN);

        Toast.makeText(this, loginTweet, Toast.LENGTH_SHORT).show();

        ArrayList<TweetModel> tweetList =
                new ArrayList<>();
        tweetList.add(new TweetModel("pascal",
                "ce live coding est génial !"));
        tweetList.add(new TweetModel("pablo",
                "tacos"));
        tweetList.add(new TweetModel("claire",
                "Git je comprends trop ce que c'est lol"));

        TweetAdapter adapter = new TweetAdapter(this, tweetList);
        ListView listTweet = findViewById(R.id.list_tweet);
        listTweet.setAdapter(adapter);
    }
}
