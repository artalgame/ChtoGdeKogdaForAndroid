package com.samples.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;

public class AnswerActivity extends Activity{
	
	Button btnOK;
	EditText mytext;
	Chronometer chronometer1;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.icon_for_answer_question);
        btnOK = (Button) findViewById(R.id.button1); 
        mytext = (EditText) findViewById(R.id.editText1);
        chronometer1 = (Chronometer) findViewById(R.id.chronometer1);
        chronometer1.start();

        chronometer1.setOnChronometerTickListener(
        		new Chronometer.OnChronometerTickListener() {
    		@Override
    		public void onChronometerTick(Chronometer chronometer) {
    			// TODO Auto-generated method stub
	    		long myElapsedMillis = SystemClock.elapsedRealtime() - chronometer1.getBase();
	    		if (myElapsedMillis > 30000) {
	    			Intent g = new Intent(getApplicationContext(), AfterAnswerActivity.class);
	    		   	g.putExtra("youAnswer", "null");
	    		   	g.putExtra("rightAnswers", getIntent().getExtras().getStringArray("rightAnswers"));
	    		   	startActivity(g);
	    		   	finish();
	    		}
    		}
    	}); 
    }

    public void button1Click(View v){
    	Intent g = new Intent(this, AfterAnswerActivity.class);
    	g.putExtra("youAnswer", mytext.getText().toString());
    	g.putExtra("rightAnswers", getIntent().getExtras().getStringArray("rightAnswers"));
    	startActivity(g);
    	finish();
    }
    
}
