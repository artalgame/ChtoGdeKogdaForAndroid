package com.samples.myproject.BuisnessEntity;


public class Game extends ID{
	
	private Question lastQuestion;
	private String winner;
	
	public Game(){
		
	}
	
	public Question GetLastQuestion(){
		return lastQuestion;
	}
	
	public void SetLastQuestion(Question value){
		lastQuestion = value;
	}

	public String GetWinner(){
		return winner;
	}
	
	public void SetWinner(String value){
		winner = value;
	}
}
