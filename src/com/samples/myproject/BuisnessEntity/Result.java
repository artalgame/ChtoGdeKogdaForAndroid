package com.samples.myproject.BuisnessEntity;


public class Result extends ID{

	private String game_type;
	private String level;
	private String winner;
	
	public Result(){
		
	}
	
	public void SetGameType(String value){
		game_type = value;
	}
	
	public String GetGameType(){
		return game_type;
	}
	
	public void SetLevel(String value){
		level = value;
	}
	
	public String GetLevel(){
		return level;
	}
	
	public void SetWinner(String value){
		winner = value;
	}
	
	public String GetWinner(){
		return winner;
	}
}
