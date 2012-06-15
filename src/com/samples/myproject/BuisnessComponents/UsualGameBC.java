package com.samples.myproject.BuisnessComponents;
import android.content.Context;

import com.samples.myproject.BuisnessEntity.*;
import com.samples.myproject.DataAccessComponents.QuestionDAC;



public class UsualGameBC {
	private UsualGame usualGame;
	private String rating;
	Context context;
	
	public UsualGameBC(Context context, UsualGame value, String Rating){
		usualGame = value;
		rating = Rating;
		this.context = context;
	}
	
	public Question AddNewQuestion(){
		Question question = new Question();
		QuestionDAC qDAC = new QuestionDAC(rating, context);
		question = qDAC.Read();
		usualGame.SetLastQuestion(question);
		return question;
	}
	
	private UsualGame GetUsualGame(){
		return usualGame;
	}
}
