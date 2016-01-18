package game;

public enum EntityType {

	//Entity types so the game knows what to cast to on collision
	
	PLAYER ("Player"),
	MONSTER ("A Monster!"),
	ITEM ("A Thing!");
	
	String info;
	
	EntityType(String a){
		
		info = a;
		
	}
	
	public String getInfo(){
		
		return info;
		
	}
}
