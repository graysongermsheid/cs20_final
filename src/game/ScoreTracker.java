package game;

public class ScoreTracker {

	public static int playerHealth;
	public static int score;
	public static int goldCollected;
	public static int healthLost;
	public static int healthHealed;
	public static int areasExplored = 1;
	public static String killedBy;
	
	public static void resetStats(){
		

		score = 0;
		goldCollected = 0;
		healthLost = 0;
		healthHealed = 0;
		areasExplored = 1;
		
	}
}
