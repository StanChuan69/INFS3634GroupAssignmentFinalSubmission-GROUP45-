package com.rui.guang.infs3634groupassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScorePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_page);

        //Intent intent = getIntent();
        //String message = intent.getStringExtra();
        //TextView textView = findViewById(R.id.scorePageTextView);
        //textView.setText(message);

        Button button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScorePage.this, AppMainPage.class);
                startActivity(intent);
            }
        });
    }
}
