/**
 * 
 */
package com.samples.myproject;

import com.samples.myproject.BuisnessComponents.MusicBC;
import com.samples.myproject.BuisnessComponents.UsualGameBC;
import com.samples.myproject.BuisnessEntity.Question;
import com.samples.myproject.BuisnessEntity.UsualGame;

import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

/**
 * @author apostalic
 *
 */
public class GameActivity extends Activity {
	
	Chronometer chronometer1;
	MultiAutoCompleteTextView text1;
	Button btn;
	Button btn2; //кнопка, отвечающая за доп минуты

	
    private Question question;
    private UsualGame ug;
    private String rating;
    
    SharedPreferences prefs;
    public static final String PREF_USER = "userScore";
	private static final String PREF_AUDIENCE = "audScore";
	private static final String PREF_ADD = "addScore";
	private static final String PREF_NAME = "PREF_NAME";
	
	private boolean flagOn20Sec; 
	private boolean flagOnAddMin = false;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_questions);
        MusicBC.stop(this);
        btn2 = (Button) findViewById(R.id.button2);
        flagOn20Sec = true;
        Context context = getApplicationContext();
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (prefs.getInt(PREF_ADD, 0) > 0){
        	btn2.setEnabled(true);
        }
        else {
			btn2.setEnabled(false);
		}
        text1 = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);
        chronometer1 = (Chronometer) findViewById(R.id.chronometer1);
        rating = getIntent().getExtras().getString("rating");
        ug = new UsualGame();
        UsualGameBC ugBC = new UsualGameBC(getApplicationContext() ,ug, rating);
        question = new Question();
        //тут начинается цикл while
        question = ugBC.AddNewQuestion();
        String s;
        if (question.Get_question() != null){
        	s = question.Get_question().toString();
        }
        else
        	s = "null";
	    text1.setText(s); //и вконце(по происшествии 60 сек или после закрытия активности ответа), запускаем активность для ввода результата
        //chronometer1 = (Chronometer) findViewById(R.id.chronometer1);
        chronometer1.start(); 	       
        btn = (Button) findViewById(R.id.button1);
        OnClickListener oKbtn = new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				chronometer1.setBase(SystemClock.elapsedRealtime());
				Intent i = new Intent(getApplicationContext(), AnswerActivity.class);
				i.putExtra("rightAnswers", question.Get_right_answers());
				chronometer1.setBase(SystemClock.elapsedRealtime());
				startActivity(i);
			}
		};
        btn.setOnClickListener(oKbtn);
        
        chronometer1.setOnChronometerTickListener(
        		new Chronometer.OnChronometerTickListener() {
			@Override
			public void onChronometerTick(Chronometer chronometer) {
				// TODO Auto-generated method stub
				long myElapsedMillis = SystemClock.elapsedRealtime() - chronometer1.getBase();
					if (myElapsedMillis > 60000) {
					Intent i = new Intent(getApplicationContext(), AfterAnswerActivity.class);
					i.putExtra("youAnswer", "null");
					i.putExtra("rightAnswers", question.Get_right_answers());
					startActivity(i);
					chronometer1.setBase(SystemClock.elapsedRealtime());
				}
			}
		});    
    }
    
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	MusicBC.stop(this);
    	int addScore = prefs.getInt(PREF_ADD, 0);
    	if (addScore > 0){
        	btn2.setEnabled(true);
        }
        else {
			btn2.setEnabled(false);
		}
    	flagOn20Sec = true;
    	int user = prefs.getInt(PREF_USER, 0);
    	int aud = prefs.getInt(PREF_AUDIENCE, 0);
    	if ((user < 6) && (aud < 6)){
    		rating = getIntent().getExtras().getString("rating");
            ug = new UsualGame();
            UsualGameBC ugBC = new UsualGameBC(getApplicationContext() ,ug, rating);
            question = new Question();
            //тут начинается цикл while
            question = ugBC.AddNewQuestion();
            String s;
            chronometer1.setBase(SystemClock.elapsedRealtime());
            if (question.Get_question() != null){
            	s = question.Get_question().toString();
            }
            else
            	s = "null";
    	    text1.setText(s); //и вконце(по происшествии 60 сек или после закрытия активности ответа), запускаем активность для ввода результата
            //chronometer1.setBase(SystemClock.elapsedRealtime()); 	  
	    	OnClickListener oKbtn = new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					chronometer1.setBase(SystemClock.elapsedRealtime());
					Intent i = new Intent(getApplicationContext(), AnswerActivity.class);
					i.putExtra("rightAnswers", question.Get_right_answers());
					chronometer1.setBase(SystemClock.elapsedRealtime());
					startActivity(i);
				}
			};
	        btn.setOnClickListener(oKbtn);
	        chronometer1
			.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
				public void onChronometerTick(Chronometer chronometer) {
					// TODO Auto-generated method stub
					long myElapsedMillis = SystemClock.elapsedRealtime()
							- chronometer1.getBase();
						if (myElapsedMillis > 60000) {
						Intent i = new Intent(getApplicationContext(), AfterAnswerActivity.class);
						i.putExtra("youAnswer", "null");
						i.putExtra("rightAnswers", question.Get_right_answers());
						startActivity(i);
						chronometer1.setBase(SystemClock.elapsedRealtime());
					}
				}
			});    
    	}
    	else{
    		if (user == 6){
    			String s = prefs.getString(PREF_NAME, "") + ", you win. My Congratulations";
				int duration = Toast.LENGTH_SHORT;
    			Toast toast = Toast.makeText(getApplicationContext(), s, duration);
    			toast.show();
    			finish();
    			//тут вывод инфы о победе 
    		}
    		else{
    			if (aud == 6){
    				MusicBC.play(this, R.raw.na_konec);
    				String s = prefs.getString(PREF_NAME, "") + ", you lose.";
    				int duration = Toast.LENGTH_SHORT;
        			Toast toast = Toast.makeText(getApplicationContext(), s, duration);
        			toast.show();
        			finish();
        			//тут вывод инфы о проигрыше
    			}
    		}
    	}
    }
    
}
