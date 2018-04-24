package fr.wcs.wildtweet;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by bastienwcs on 24/04/18.
 */

public class SingletonExample {

    public static SingletonExample sInstance = null;
    public String mTextSaved = "";
    public ArrayList<TweetModel> mTweetList = new ArrayList<>();
    public LoadTweetListener mListener = null;

    public SingletonExample() {

        loadTweetList();
    }

    public static SingletonExample getInstance() {
        if (sInstance == null) {
            // créer l'instance
            sInstance = new SingletonExample();
        }
        return sInstance;
    }

    public String getText() {
        return mTextSaved;
    }

    public void setText(String texte) {
        mTextSaved = texte;
    }

    public ArrayList<TweetModel> getTweetList() {
        return mTweetList;
    }

    public void loadTweetList() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tweetsRef = database.getReference("tweets");
        tweetsRef.orderByChild("datetime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTweetList.clear();
                for (DataSnapshot tweetSnapshot : dataSnapshot.getChildren()) {
                    TweetModel tweetModel = tweetSnapshot.getValue(TweetModel.class);
                    mTweetList.add(tweetModel);
                }
                if (mListener != null) {
                    mListener.onListUpdate(mTweetList);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setListener(LoadTweetListener listener) {
        mListener = listener;
    }

    public interface LoadTweetListener {

        void onListUpdate(ArrayList<TweetModel> tweets);
    }
}
