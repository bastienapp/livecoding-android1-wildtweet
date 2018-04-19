package fr.wcs.wildtweet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

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

        final ArrayList<TweetModel> tweetList = new ArrayList<>();


        final TweetAdapter adapter = new TweetAdapter(this, tweetList);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tweetsRef = database.getReference("tweets");
        tweetsRef.orderByChild("datetime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tweetList.clear();
                for (DataSnapshot tweetSnapshot : dataSnapshot.getChildren()) {
                    TweetModel tweetModel = tweetSnapshot.getValue(TweetModel.class);
                    tweetList.add(tweetModel);
                }
                Collections.reverse(tweetList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ListView listTweet = findViewById(R.id.list_tweet);
        listTweet.setAdapter(adapter);
    }
}
