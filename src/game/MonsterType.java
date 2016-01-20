package game;

import java.util.Random;

public enum MonsterType {

	SNAKE ("Snake", 0),
	RAT ("Rat", 1),
	GUARD ("Guard", 2);
	
	String name;
	int value;
	
	MonsterType(String a, int value){
		
		name = a;
		this.value = value;
		
	}
	
	@Override
	public String toString(){
		
		return name;
		
	}
	
	private int val(){
		
		return value;
		
	}
	
	public static MonsterType random(){
		
		Random r = new Random();
		int x = r.nextInt(values().length);
		
		for (MonsterType m : values()){
			
			if (m.val() == x){
				
				return m;
				
			}
		}
		
		return SNAKE;
	}
}
