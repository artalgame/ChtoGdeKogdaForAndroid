package com.samples.myproject.BuisnessEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Question extends ID{
	
	private String question;
	private int rating;
	private String[] right_answers;
	private String[] wrong_answers;
	private long date_last_use;
	
	public Question(){
		
	}
	
	public String Get_question(){
		return question;
	}
	
	public void Set_question(String question){
		this.question = question;
	}
	
	public int Get_rating(){
		return rating;
	}
	
	public void Set_rating(int rating){
		this.rating = rating;
	}
	
	public String[] Get_right_answers(){
		return right_answers;
	}
	
	public void Set_right_answers(String[] right_answers){
		this.right_answers = right_answers;
	}
	
	public String[] Get_wrong_answers(){
		return wrong_answers;
	}
	
	public void Set_wrong_answers(String[] wrong_answers){
		this.wrong_answers = wrong_answers;
	}
	
	public long Get_date_last_use(){
		return date_last_use;
	}
	
	public void Set_date_last_use(long date){
		date_last_use = date;
	}

}
