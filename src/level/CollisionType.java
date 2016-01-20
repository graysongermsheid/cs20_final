package level;

public enum CollisionType {
	
	NONE (0),
	WALL (1);

	int val;

	CollisionType(int value){

		this.val = value;

	}
	
	public int value(){
		
		return val;
		
	}
	
	public static CollisionType fromValue(int value){
		
		for (CollisionType c : values()){
			
			if (c.value() == value){
				
				return c;
				
			}
			
		}
		
		return CollisionType.NONE;
	}
}