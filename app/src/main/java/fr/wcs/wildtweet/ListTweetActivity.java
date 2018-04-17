package fr.wcs.wildtweet;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListTweetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tweet);

        FloatingActionButton bAddTweet = findViewById(R.id.b_add_tweet);
        bAddTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListTweetActivity.this, TweetCreateActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<TweetModel> tweetList =
                new ArrayList<>();
        tweetList.add(new TweetModel("Brandon",
                "ce live coding est génial !"));
        tweetList.add(new TweetModel("Kevin",
                "tacos"));
        tweetList.add(new TweetModel("Monika",
                "Git je comprends trop ce que c'est lol"));

        TweetAdapter adapter = new TweetAdapter(this, tweetList);
        ListView listTweet = findViewById(R.id.list_tweet);
        listTweet.setAdapter(adapter);
    }
}
