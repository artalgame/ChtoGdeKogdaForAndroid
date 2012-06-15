package com.samples.myproject.BuisnessEntity;

import java.util.*;

public abstract class ID {
	public UUID id;
	
	public ID(){
		id = UUID.randomUUID();
	}
}
