package com.samples.myproject.BuisnessEntity;


public class Results {

	private Result[] results;
	
	public Results(){
		
	}
	
	public Result[] GetResults(){
		return results;
	}
	
	public void Addresult(Result value){
		Result[] myResult = new Result[results.length + 1];
		for (int i = 0; i < results.length; i++){
			myResult[i] = results[i];
		}
		myResult[myResult.length - 1] = value;
		results = new Result[myResult.length];
		results = myResult;
	}
}
