package game;

public interface Monster { //Enemies with EntityType:Monster inherit this so the player can check what type of monster he hits

	public MonsterType getMonsterType();
	
}
