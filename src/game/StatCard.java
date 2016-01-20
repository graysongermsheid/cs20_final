package game;

public class StatCard {

	public String name;
	public int playerHealth;
	public int score;
	public int goldCollected;
	public int healthLost;
	public int healthHealed;
	public int areasExplored;
	public int monstersKilled;
	public double distanceTraveled;
	
	public StatCard(String name){
		
		this.name = name;
		
		this.playerHealth = ScoreTracker.playerHealth;
		this.score = ScoreTracker.score;
		this.goldCollected = ScoreTracker.goldCollected;
		this.healthLost = ScoreTracker.healthLost;
		this.healthHealed = ScoreTracker.healthHealed;
		this.areasExplored = ScoreTracker.areasExplored;
		this.monstersKilled = ScoreTracker.monstersKilled;
		this.distanceTraveled = ScoreTracker.distanceTraveled;
	}
	
	public void saveCard(){
		
		//write card to file
		
	}
}
