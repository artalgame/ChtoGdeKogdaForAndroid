package com.samples.myproject.BuisnessEntity;


public class UsualGame extends Game{
	
	private int questions_user;
	private int question_audience;
	
	public UsualGame(){
		question_audience = 0;
		questions_user = 0;
	}
	
	public void AddQuestionsUser(){
		questions_user++;
	}
	
	public void AddQuestionsAudience(){
		question_audience++;
	}
	
	public int GetQuestionsUser(){
		return questions_user;
	}
	
	public int GetQuestionsAudience(){
		return question_audience;
	}

}
