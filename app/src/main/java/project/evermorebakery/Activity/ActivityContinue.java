//Activity Continue:
//Is the Bridge Between Activity Start and Activity Main
//Can Be Used to Store and Connect Data

package project.evermorebakery.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import project.evermorebakery.R;

public class ActivityContinue extends AppCompatActivity
{
    TextView vText_aContinue_Quote;
    Button uButton_aContinue_Continue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue);

        addControls();
        addQuote();
        addEvents();
    }

    void addControls()
    {
        vText_aContinue_Quote = findViewById(R.id.vText_aContinue_Quote);
        uButton_aContinue_Continue = findViewById(R.id.uButton_aContinue_Continue);
    }

    void addQuote()
    {
        vText_aContinue_Quote.setText("Life is better with cake\nOrder yours now and savor the sweetness!");
    }

    void addEvents() //Function: Add Events for User to Interact
    {
        uButton_aContinue_Continue.setOnClickListener(view ->
        {
            Intent intent = new Intent(ActivityContinue.this, ActivityMain.class);
            startActivity(intent);
        });
    }
}
