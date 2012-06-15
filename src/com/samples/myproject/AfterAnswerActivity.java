package com.samples.myproject;

import com.samples.myproject.BuisnessComponents.MusicBC;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.MultiAutoCompleteTextView;


public class AfterAnswerActivity extends Activity{
	
	MultiAutoCompleteTextView youAnswer;
	MultiAutoCompleteTextView rightAnswer;
	MultiAutoCompleteTextView score;
	SharedPreferences prefs;
	
	private int x1, x2;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MusicBC.play(this, R.raw.tema_4);
        setContentView(R.layout.after_answer);
        
        Context context = getApplicationContext();
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        
        
        youAnswer = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);
        rightAnswer = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView2);
        score = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView3);
        
        youAnswer.setText(getIntent().getExtras().getString("youAnswer"));
        String answers = "";
        for (String element : getIntent().getExtras().getStringArray("rightAnswers")) {
			answers = answers + element + ";";
		}
        rightAnswer.setText(answers);
        String answer = getIntent().getExtras().getString("youAnswer").toString();
        
        
        x1 = prefs.getInt("userScore", 0);
        x2 = prefs.getInt("audScore", 0);
        boolean fl = false;
        for (String element : getIntent().getExtras().getStringArray("rightAnswers")) {
			if (element.equals(answer) || answer.contains(element) || element.contains(answer))
				fl = true;
		}
        if (fl == true)
        	x1++;
        else
        	x2++;
        String score = String.valueOf(x1) + " : " + String.valueOf(x2);
        this.score.setText(score);
        
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("userScore", x1);
        editor.putInt("audScore", x2);
        editor.commit();
    }
    
    public void NextQuestionClick(View v){
    	MusicBC.stop(this);
    	MusicBC.play(this, R.raw.volchok);
    	finish();
    }


}
