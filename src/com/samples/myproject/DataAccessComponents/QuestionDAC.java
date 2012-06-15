package com.samples.myproject.DataAccessComponents;

import java.security.acl.LastOwnerException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import com.samples.myproject.BuisnessEntity.Question;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

public class QuestionDAC{
	private String rating;
	private Question question;
	
	private static final String DB_NAME = "mydb.sqlite3";
	private static final String TABLE_NAME = "Questions";
	private static final String QuestionID = "_id";
	private static final String questionTEXT = "question";
	private static final String rightAnswersName = "rightAnswers";
	private static final String wrongAnswersName = "wrongAnswers";
	private static final String ratingName = "rating";
	private static final String dataText = "lastDate";
	private static final String PREF_DATE = "PREF_DATE";
	
	private SQLiteDatabase database;
	
	SharedPreferences prefs;
	Context context;
	private Cursor myCursor;
	
	public QuestionDAC(String rating, Context context){
		this.rating = rating;
		this.context = context;
		question = new Question();
	}
	
	public String GetRating(){
		return rating;
	}
	
	public void SetRating(String value){
		rating = value;
	}
	
	public Question GetQuestion(){
		return question;
	}
	
	public void SetQuestion(Question value){
		question = value;
	}
	
	public Question Read(){
		//Наш ключевой хелпер
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context, DB_NAME);
        database = dbOpenHelper.openDataBase();
		//тут происходит считывание с базы данных
		int minRating, maxRating;
		if (rating.equals("easy")){
			minRating = 1;
			maxRating = 4;
		}
		else{
			if (rating.equals("medium")){
				minRating = 5;
				maxRating = 7;
			}
			else{
				minRating = 8;
				maxRating = 10;
			}
		}
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
		myCursor = database.query(TABLE_NAME, new String[]{ratingName, dataText, QuestionID, questionTEXT, rightAnswersName, wrongAnswersName}, null, null, null, null, null);
		myCursor.moveToFirst();
		if(!myCursor.isAfterLast()) {
			boolean flag = true;
			do {
				int ratingCoIndex = myCursor.getColumnIndex(ratingName);
				int dateCoIndex = myCursor.getColumnIndex(dataText);
				int idgCoIndex = myCursor.getColumnIndex(QuestionID);
				int rightCoIndex = myCursor.getColumnIndex(rightAnswersName);
				int wrongCoIndex = myCursor.getColumnIndex(wrongAnswersName);
				if ((myCursor.getInt(ratingCoIndex) >= minRating) && (myCursor.getInt(ratingCoIndex) <= maxRating)){
					long nowDate = prefs.getLong(PREF_DATE, 0);
					long date = myCursor.getLong(dateCoIndex);
					if (date > nowDate){
						getItem(myCursor.getPosition());
						ContentValues updateValues = new ContentValues();
						updateValues.put(ratingName, question.Get_rating());
						updateValues.put(dataText, nowDate);
						updateValues.put(questionTEXT, question.Get_question());
						updateValues.put(rightAnswersName, myCursor.getString(rightCoIndex));
						updateValues.put(wrongAnswersName, myCursor.getString(wrongCoIndex));
						updateValues.put(QuestionID, myCursor.getInt(idgCoIndex));
						String where = QuestionID + "=" + myCursor.getInt(idgCoIndex);
						database.update(TABLE_NAME, updateValues, where, null);
						flag = false;
					}
				}
			} while ((myCursor.moveToNext()) && (flag));
		}
		myCursor.close();
		database.close();
		return this.question;
	}
	
	public void getItem(int position) {
		if (myCursor.moveToPosition(position)){
			int ratingCoIndex = myCursor.getColumnIndex(ratingName);
			int dateCoIndex = myCursor.getColumnIndex(dataText);
			int questionCoIndex = myCursor.getColumnIndex(questionTEXT);
			int rightCoIndex = myCursor.getColumnIndex(rightAnswersName);
			int wrongCoIndex = myCursor.getColumnIndex(wrongAnswersName);
			this.question.Set_question(myCursor.getString(questionCoIndex));
			this.question.Set_rating(myCursor.getInt(ratingCoIndex));
			this.question.Set_date_last_use(myCursor.getLong(dateCoIndex));
			String[] right = myCursor.getString(rightCoIndex).split(";");
			String[] wrong = myCursor.getString(wrongCoIndex).split(";");
			this.question.Set_right_answers(right);
			this.question.Set_wrong_answers(wrong);
		}
	}
}
