package com.example.mydrugpal;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * activity with info about the app and the dev team
 * @author Alicia Wong, Richard Purcell
 */

public class AboutAppActivity extends YouTubeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        final YouTubePlayerView youtubePlayerView = findViewById(R.id.youtubePlayerView);
        playVideo(youtubePlayerView);

    }

    public void playVideo(YouTubePlayerView youTubePlayerView) {
        //initialize youtube player view
        youTubePlayerView.initialize("AIzaSyBY05aHQ8mzE6aEdNxVEMdmIALTQYckf4Y",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.cueVideo("nzCyyT7j5Do");
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }

}
