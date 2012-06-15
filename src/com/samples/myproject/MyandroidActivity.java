package com.samples.myproject;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.samples.myproject.BuisnessComponents.MusicBC;
import com.samples.myproject.BuisnessComponents.UsualGameBC;
import com.samples.myproject.BuisnessEntity.Question;
import com.samples.myproject.BuisnessEntity.UsualGame;

import android.app.Activity;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyandroidActivity extends Activity{

	Button loadGame;
	Button newGame;
	Button result;
	Button help;
	Button settings;
	Button about;
	SharedPreferences msettings;
	
	private static final String PREF_LANG = "PREF_LANG";
	private static final String PREF_LEVEL = "PREF_LEVEL";
	private static final String PREF_TYPE = "PREF_TYPE";
	private static final String PREF_VOLUME = "PREF_VOLUME";
	private static final String PREF_DATE = "PREF_DATE";
	private static final int SHOW_PREFERENCES = 1;
	
	Button button1;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getApplicationContext();
        msettings = PreferenceManager.getDefaultSharedPreferences(context);
        //msettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        setContentView(R.layout.main);
        button1 = (Button) findViewById(R.id.button1);
        button1.setEnabled(false);
        MusicBC.play(this, R.raw.nachalo);
    }

    public void button1_click(View v){
    	
    }
	
    public void button2_click(View v){
    	MusicBC.stop(this);
    	MusicBC.play(this, R.raw.volchok);
    	SimpleDateFormat formatter2 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    	String date = formatter2.format(new Date());
    	Date nowDate = new Date();
    	long numberDate = nowDate.getTime();
    	SharedPreferences.Editor editor = msettings.edit();
        editor.putInt("userScore", 0);
        editor.putInt("audScore", 0);
        editor.putInt("addScore", 0);
        editor.putLong(PREF_DATE, numberDate);
        editor.commit();
    	Intent i = new Intent(this, GameActivity.class);
    	String mess = "";
    	if (msettings.getInt(PREF_LEVEL, 0) == 0){
    		mess = "easy";
    	}
    	else{
    		if(msettings.getInt(PREF_LEVEL, 1) == 1){
    			mess = "medium";
    		}
    		else{
    			mess = "high";
    		}
    	}
    	i.putExtra("rating", mess);//тут сделать чтение с preference
    	startActivity(i);
    }
    
    public void button3_click(View v){
    	
    }
    
    public void button4_click(View v){
    	switch (v.getId()) 
    	{
    	    case R.id.button4:
    	        Intent i = new Intent(this, HelpActivity.class);
    	        startActivity(i);
    	        break;
        }
    }
    
    public void button5_click(View v){
    	switch (v.getId()) 
    	{
    	    case R.id.button5:
    	        Intent i = new Intent(this, Preferences.class);
    	        startActivityForResult(i, SHOW_PREFERENCES);
    	        break;
        }    	
    }
    
    public void button6_click(View v){
    	switch (v.getId()) {
		case R.id.button6:
			Intent i = new Intent(this, AboutActivity.class);
			startActivity(i);
			break;
    	}
    }
}