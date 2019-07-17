package com.example.mydrugpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.example.mydrugpal.model.CurrentUser;
import com.google.android.material.tabs.TabLayout;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * activity with info about the app
 * @author Alicia Wong, Richard Purcell
 */

public class AboutAppActivity extends YouTubeBaseActivity {
    /**
     * TabLayout that holds three tabs
     */
    public TabLayout layout;

    /**
     * Tab for substance list page
     */
    public TabLayout.Tab list;

    /**
     * Tab for about page
     */
    public TabLayout.Tab about;

    /**
     * Tab for diary (substance summary) page
     */
    public TabLayout.Tab diary;

    /**
     * method that creates the about page displayed and initializes video
     * @param savedInstanceState the state of the application
     * @see <a href= "https://media.giphy.com/media/26DnYbbcv2EupNje8/giphy.gif"/> for the moira gif
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

        initializeMenu();
    }

    private void initializeMenu()
    {
        layout = findViewById(R.id.menuTabLayout);
        list = layout.getTabAt(0);
        diary = layout.getTabAt(1);
        about = layout.getTabAt(2);

        if (CurrentUser.getInstance() == null ||
                CurrentUser.getInstance().GetEmail() == null || CurrentUser.getInstance().GetEmail().equals(""))
        {
            layout.setVisibility(View.INVISIBLE);
        }

        else {
            about.select();

            layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    changeTab(tab);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    // no action here, needs override definition
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    // no action here, needs override definition
                }
            });
        }
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
            /**
             *
             */
            @Override
            public void onLoading() {
            }

            /**
             * default onLoaded method
             * @param s default input string
             */
            @Override
            public void onLoaded(String s) {

            }

            /**
             * default method for when an ad starts on the video
             */
            @Override
            public void onAdStarted() {

            }

            @Override
            public void onVideoStarted() {
                youTubePlayer.setFullscreen(true);
            }

            /**
             * default method for when the video ends
             * @see <a href= "https://www.youtube.com/watch?v=nzCyyT7j5Do"/> for the drugs owl vine
             */
            @Override
            public void onVideoEnded() {
                youTubePlayer.cueVideo("nzCyyT7j5Do");
                youTubePlayer.play();
            }

            /**
             * default method for when an error occurs with the youtube video
             */
            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {

            }
        };
        return playerStateChangeListener;
    }

    /**
     * Called when a menu tab is pressed. Changes the activity to
     * the one matching the tab.
     * @param tab A menu tab. Should be list, summary, or about.
     */
    private void changeTab(TabLayout.Tab tab)
    {
        if (tab.getPosition() == 0)
        {
            Intent intent = new Intent(this, SubstanceListActivity.class);
            startActivity(intent);
        }

        else if (tab.getPosition() == 1)
        {
            Intent intent = new Intent(this, SubstanceSummaryActivity.class);
            startActivity(intent);

        }

        else if (tab.getPosition() == 2)
        {
            Intent intent = new Intent(this, AboutAppActivity.class);
            startActivity(intent);
        }
    }
}
