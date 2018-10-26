package com.rui.guang.infs3634groupassignment;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PolymorphismVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final String TAG = "YoutubeActivity";
    static final String GOOGLE_API_KEY = "AIzaSyC0dZcPvr_gMz-x3qxWYXp6v4YCpunCKgk ";
    static final String YOUTUBE_VIDEO_ID = "Jh_K0lggAIc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.polymorphism_video_activity_);
        YouTubePlayerView playerView = (YouTubePlayerView) findViewById(R.id.polymorphismYoutubeVideo);
        playerView.initialize(GOOGLE_API_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Toast.makeText(this, "Playing polymorphism video", Toast.LENGTH_SHORT).show();
        if(!wasRestored){
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        }
        else{
            String errorMessage = String.format("There was an error initializing the YoutubePlayer (%1$s)",youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage,Toast.LENGTH_LONG).show();
        }
    }


}