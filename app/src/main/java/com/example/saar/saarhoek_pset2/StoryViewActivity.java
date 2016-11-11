package com.example.saar.saarhoek_pset2;

/**
 * Created by Saar on 11-11-2016.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StoryViewActivity extends AppCompatActivity {
    private String story;
    private TextView storyview;

    // get the data
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_view);

        // show
        Bundle extras = getIntent().getExtras();
        story = extras.getString("story_time");
        storyview = (TextView)findViewById(R.id.storyview);
        storyview.setText(story);
    }

    public void tryAgain(View view) {
        Intent retryActivity = new Intent(this, MainActivity.class);
        startActivity(retryActivity);
        finish();
    }
}
