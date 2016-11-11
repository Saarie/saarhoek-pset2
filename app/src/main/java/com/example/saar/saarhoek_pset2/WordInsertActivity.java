package com.example.saar.saarhoek_pset2;

/**
 * Created by Saar on 11-11-2016.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class WordInsertActivity extends AppCompatActivity {

    private Story story_time;
    private InputStream stream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_insert);

        // set university as story (for starters)
        stream = this.getResources().openRawResource(R.raw.madlib2_university);

        // create the new story
        story_time = new Story(stream);

        // initialise countdown
        int words_left = story_time.getPlaceholderRemainingCount();
        TextView countdown = (TextView) findViewById(R.id.countdown);
        countdown.setText("Just " + Integer.toString(words_left) + " more to go!");

        // wordtype to fill in
        String wordtype = story_time.getNextPlaceholder();
        EditText filledWord = (EditText) findViewById(R.id.filler);
        filledWord.setHint(wordtype);
        TextView go_type = (TextView) findViewById(R.id.wordtype);
        go_type.setText("I need a(n): " + wordtype + "___");
    }

    // safeguarding in case of rotation
    public void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);

        int words_left = story_time.getPlaceholderRemainingCount();
        String wordtype = story_time.getNextPlaceholder();

        outState.putSerializable("story_time", story_time);
        outState.putInt("words_left", words_left);
        outState.putString("wordtype", wordtype);
    }

    // and then putting back
    public void onRestoreInstanceState (Bundle inState){
        super.onRestoreInstanceState(inState);

        // retrieving saved data
        story_time = (Story)inState.getSerializable("story_time");

        // restoring wordtype
        String wordtype = inState.getString("wordtype");
        EditText filledWord = (EditText) findViewById(R.id.filler);
        filledWord.setHint(wordtype);
        TextView go_type = (TextView) findViewById(R.id.wordtype);
        go_type.setText("I need a(n): " + wordtype + "___");

        // and countdown
        int words_left = inState.getInt("remaining_words");
        TextView countdown = (TextView)findViewById(R.id.countdown);
        countdown.setText("Just " + Integer.toString(words_left) + " more to go!");
    }

    public void theInsertion(View view) {
        // conversion of user input
        EditText inputWord = (EditText)findViewById(R.id.filler);
        String wordstring = inputWord.getText().toString();

        // check for actual input
        if (!(wordstring.length() == 0)) {
            // fill word in text
            story_time.fillInPlaceholder(wordstring);
        }
        else {
            Toast toast = Toast.makeText(this, "I need a word, please.", Toast.LENGTH_SHORT);
            toast.show();
        }

        if (story_time.isFilledIn())
        {
            Intent goToStory = new Intent(this, StoryViewActivity.class);
            goToStory.putExtra("story_time", story_time.toString());
            startActivity(goToStory);
            finish();
        }

        int words_left = story_time.getPlaceholderRemainingCount();

        // if-statement to prevent Toast messages etc if the user filled in the last word
        if (words_left > 0) {

            TextView countdown = (TextView) findViewById(R.id.countdown);
            countdown.setText("Just " + Integer.toString(words_left) + " more to go!");

            // clear the editText bar
            inputWord.setText("");

            // put the new suggestion into editText bar
            String wordtype = story_time.getNextPlaceholder();
            EditText fillWord = (EditText) findViewById(R.id.filler);
            fillWord.setHint(wordtype);
            TextView go_type = (TextView) findViewById(R.id.wordtype);
            go_type.setText("I need a(n): " + wordtype + "___");

            Toast toast = Toast.makeText(this, "Good. Feed me more.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
