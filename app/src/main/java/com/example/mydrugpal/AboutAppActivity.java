package com.example.mydrugpal;

import android.os.Bundle;
import android.webkit.WebView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * activity with info about the app and the dev team
 * @author Alicia Wong, Richard Purcell
 */

public class AboutAppActivity extends YouTubeBaseActivity {

    /**
     * method that creates the about page displayed and initializes video
     * @param savedInstanceState the state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        WebView wView = findViewById(R.id.gifView);
        wView.loadUrl("file:///android_asset/enjoy.gif");
        wView.getSettings().setUseWideViewPort(true);
        wView.getSettings().setLoadWithOverviewMode(true);
        final YouTubePlayerView youtubePlayerView = findViewById(R.id.youtubePlayerView);
        playVideo(youtubePlayerView);

    }

    private void playVideo(final YouTubePlayerView youTubePlayerView) {
        youTubePlayerView.initialize("AIzaSyBY05aHQ8mzE6aEdNxVEMdmIALTQYckf4Y",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        final YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.cueVideo("nzCyyT7j5Do");

                        setPlayerStateChangeListener(youTubePlayer);

                        youTubePlayer.setPlayerStateChangeListener(setPlayerStateChangeListener(youTubePlayer));

                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }

    private YouTubePlayer.PlayerStateChangeListener setPlayerStateChangeListener(final YouTubePlayer youTubePlayer){
        YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener(){
            @Override
            public void onLoading() {
            }

            @Override
            public void onLoaded(String s) {

            }

            @Override
            public void onAdStarted() {

            }

            @Override
            public void onVideoStarted() {

            }
            @Override
            public void onVideoEnded() {
                youTubePlayer.cueVideo("nzCyyT7j5Do");
                youTubePlayer.play();
            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {

            }
        };
        return playerStateChangeListener;
    }

}
