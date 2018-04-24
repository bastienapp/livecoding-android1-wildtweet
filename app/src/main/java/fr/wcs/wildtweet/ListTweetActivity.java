package fr.wcs.wildtweet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListTweetActivity extends AppCompatActivity {

    ArrayList<TweetModel> mTweetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tweet);

        SingletonExample singletonExample = SingletonExample.getInstance();
        TextView test = findViewById(R.id.tacos_test);
        test.setText(singletonExample.getText());


        FloatingActionButton bAddTweet = findViewById(R.id.b_add_tweet);
        bAddTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListTweetActivity.this, TweetCreateActivity.class);
                startActivity(intent);
            }
        });
        mTweetList = singletonExample.getTweetList();
        final TweetAdapter adapter = new TweetAdapter(this, mTweetList);
        ListView listTweet = findViewById(R.id.list_tweet);
        listTweet.setAdapter(adapter);

        singletonExample.setListener(new SingletonExample.LoadTweetListener() {
            @Override
            public void onListUpdate(ArrayList<TweetModel> tweets) {
                mTweetList = tweets;
                adapter.notifyDataSetChanged();
            }
        });
    }
}
