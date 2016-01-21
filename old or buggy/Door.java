package level;

public class Door extends Tile {
	
	public String connectedRoom;
	public int partnerID;
	public int id;

	public Door(int spriteID, int id, String connectedRoom, int partnerID){

		super(spriteID);

		this.id = id;
		this.connectedRoom = connectedRoom;
		this.partnerID = partnerID;

	}

}