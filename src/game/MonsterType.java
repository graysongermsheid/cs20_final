package game;

public enum MonsterType {

	SNAKE ("Snake"),
	RAT ("Rat"),
	ETC ("Some other thing, I guess...");
	
	String name;
	
	MonsterType(String a){
		
		name = a;
		
	}
	
	public String getName(){
		
		return name;
		
	}
}
