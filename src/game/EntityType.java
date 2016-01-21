package game;

public enum EntityType {

	//Entity types so the game knows what to cast to on collision
	
	PLAYER ("Player"),
	MONSTER ("THIS SHOULDNT BE HERE"),
	COIN ("Coins"),
	DOOR ("Door"),
	HEAL ("Health Boost");
	
	String info;
	
	EntityType(String a){
		
		info = a;
		
	}
	
	public String getInfo(){
		
		return info;
		
	}
}
