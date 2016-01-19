package game;

public enum ItemType {

	COINS ("Coins"),
	ROPE ("A Rope Ladder"),
	STAIRS ("Some Stairs"), //If I can implement dungeons
	IDK ("...");
	
	String name;
	
	ItemType(String val){
		
		name = val;
		
	}
	
	@Override
	public String toString(){
		
		return name;
		
	}
}
