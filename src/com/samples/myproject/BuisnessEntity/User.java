package com.samples.myproject.BuisnessEntity;

import java.util.Set;


public class User extends ID{
	
	private Game last_game;
	private String name;
	private String level;
	
	
	public User(){
		
	}
	
	public Game GetLastGame(){
		return last_game;
	}
	
	public void SetLastGame(Game value){
		last_game = value;
	}
	
	public String GetName(){
		return name;
	}
	
	public void SetName(String value){
		name = value;
	}
	
	public String GetLevel(){
		return level;
	}
	
	public void SetLevel(String value){
		level = value;
	}

}
