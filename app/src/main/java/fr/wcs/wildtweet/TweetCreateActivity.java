package fr.wcs.wildtweet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class TweetCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_create);

        Button bLastTweet = findViewById(R.id.b_last_tweet);
        bLastTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference tweetsRef = database.getReference("tweets");
                tweetsRef.orderByChild("datetime").limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot tweetSnapshot : dataSnapshot.getChildren()) {
                            TweetModel tweetModel = tweetSnapshot.getValue(TweetModel.class);
                            Toast.makeText(TweetCreateActivity.this, String.format("%s : %s", tweetModel.getUsername(), tweetModel.getContent()), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        Button bAddTweet = findViewById(R.id.b_add_tweet);
        bAddTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etName = findViewById(R.id.et_username);
                EditText etContent = findViewById(R.id.et_content);

                String username = etName.getText().toString();
                String content = etContent.getText().toString();

                if (username.isEmpty() || content.isEmpty()) {
                    Toast.makeText(TweetCreateActivity.this, R.string.error_fill_form, Toast.LENGTH_LONG).show();
                } else {
                    FirebaseDatabase.getInstance();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference tweetsRef = database.getReference("tweets");

                    // écriture dans Firebase
                    TweetModel tweetModel = new TweetModel(username, content, -new Date().getTime());
                    final String key = tweetsRef.push().getKey();
                    tweetsRef.child(key).setValue(tweetModel);

                    Intent intent = new Intent(TweetCreateActivity.this, ListTweetActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
