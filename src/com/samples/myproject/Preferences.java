package com.samples.myproject;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

public class Preferences extends Activity{
	
	Spinner spinner1; // spinner for languages
	Spinner spinner2; // spinner for levels
	Spinner spinner3; //spinner for types
	SeekBar seekbar1; //seekbar for volume
	EditText editText1; //editText for name
	
	private static final String PREF_LANG = "PREF_LANG";
	private static final String PREF_LEVEL = "PREF_LEVEL";
	private static final String PREF_TYPE = "PREF_TYPE";
	private static final String PREF_VOLUME = "PREF_VOLUME";
	private static final String PREF_NAME = "PREF_NAME";
	
	SharedPreferences prefs;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);
        
        // Получаем экземпляр элемента Spinner
        spinner1 = (Spinner)findViewById(R.id.spinner1);
        
        // Получаем экземпляр элемента Spinner
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        
        // Получаем экземпляр элемента Spinner
        spinner3 = (Spinner)findViewById(R.id.spinner3);
        
        seekbar1 = (SeekBar) findViewById(R.id.seekBar1);
        
        editText1 = (EditText) findViewById(R.id.editText1);
        
        populateSpinners();
        
        Context context = getApplicationContext();
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        updateUIFromPreferences();
        
        Button okButton = (Button) findViewById(R.id.button1);
        okButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				savePreferences();
				Preferences.this.setResult(RESULT_OK);
				finish();
			}
		});
        
        Button cancelButton = (Button) findViewById(R.id.button2);
        cancelButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Preferences.this.setResult(RESULT_CANCELED);
				finish();
			}
		});
	}
	
	private void populateSpinners(){
		// Настраиваем адаптер
        ArrayAdapter<CharSequence> adapter1 = 
        	ArrayAdapter.createFromResource(this, R.array.languages_options, android.R.layout.simple_spinner_item);
        int spinner_dd_item = android.R.layout.simple_spinner_dropdown_item;
        adapter1.setDropDownViewResource(spinner_dd_item);
        
        // Вызываем адапетр
        spinner1.setAdapter(adapter1);
        
        
        // Настраиваем адаптер
        ArrayAdapter<CharSequence> adapter2 = 
        	ArrayAdapter.createFromResource(this, R.array.levels_options, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(spinner_dd_item);
        
        // Вызываем адапетр
        spinner2.setAdapter(adapter2);
        
        // Настраиваем адаптер
        ArrayAdapter<CharSequence> adapter3 = 
        	ArrayAdapter.createFromResource(this, R.array.Types_options, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(spinner_dd_item);
        
        // Вызываем адапетр
        spinner3.setAdapter(adapter3);
	}
	
	private void updateUIFromPreferences(){
		int langIndex = prefs.getInt(PREF_LANG, 0);
		int levelIndex = prefs.getInt(PREF_LEVEL, 0);
		int typeIndex = prefs.getInt(PREF_TYPE, 0);
		String myName = prefs.getString(PREF_NAME, "");
		
		spinner1.setSelection(langIndex);
		spinner2.setSelection(levelIndex);
		spinner3.setSelection(typeIndex);
		editText1.setText(myName);
	}
	
	private void savePreferences(){
		int langIndex = spinner1.getSelectedItemPosition();
		int levelIndex = spinner2.getSelectedItemPosition();
		int typeIndex = spinner3.getSelectedItemPosition();
		int volumeIndex = seekbar1.getProgress();
		String myName = editText1.getText().toString();
		
		Editor editor = prefs.edit();
		editor.putInt(PREF_LANG, langIndex);
		editor.putInt(PREF_LEVEL, levelIndex);
		editor.putInt(PREF_TYPE, typeIndex);
		editor.putInt(PREF_VOLUME, volumeIndex);
		editor.putString(PREF_NAME, myName);
		editor.commit();
	}
}
