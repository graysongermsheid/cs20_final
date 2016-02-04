package game;

public enum AreaType{
	
	DUNGEONS ("Dungeons", 0),
	CAVES ("Caves", 1);
	
	String name;
	int id;
	
	AreaType(String val, int num){
		
		name = val;
		id = num;
		
	}
	
	@Override
	public String toString(){
		
		return name;
		
	}
	
	public int getValue(){
		
		return id;
		
	}
}
