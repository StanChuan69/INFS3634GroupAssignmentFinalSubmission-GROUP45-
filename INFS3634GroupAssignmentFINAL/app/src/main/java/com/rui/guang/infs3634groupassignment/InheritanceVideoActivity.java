package com.rui.guang.infs3634groupassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class InheritanceVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private YouTubePlayer YPlayer;
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    private static final String TAG = "YoutubeActivity";
    static final String GOOGLE_API_KEY = "AIzaSyC0dZcPvr_gMz-x3qxWYXp6v4YCpunCKgk ";
    static final String YOUTUBE_VIDEO_ID = "mPNBIgq7cg0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inheritance_video_activity);
        YouTubePlayerView playerView = (YouTubePlayerView) findViewById(R.id.inheritanceYoutubeVideo);
        playerView.initialize(GOOGLE_API_KEY, this);
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Toast.makeText(this, "Playing inheritance video", Toast.LENGTH_SHORT).show();
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
