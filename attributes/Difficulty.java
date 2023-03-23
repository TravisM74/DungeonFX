package attributes;

public class Difficulty {
	private DifficultyEnum difficulty;
	private String difficultyDescription;
	private int difficultyValue;
	
	public Difficulty(DifficultyEnum difficulty) {
		this.difficulty = difficulty;
		
	}	
	public void setChallengeDifficulty(DifficultyEnum chal) {
		this.difficulty = chal;
		testDifficulty();
	}
	
	public void testDifficulty() {
		
		switch(this.difficulty) {
		case VERY_EASY:
			this.difficultyValue=5;
			this.difficultyDescription = " appears a very easy issue ";
			break;
		case EASY:
			this.difficultyValue=10;
			this.difficultyDescription = " appears an easy issue ";
			break;
		case MEDIUM:
			this.difficultyValue=15;
			this.difficultyDescription = " appears a medium issue ";
			break;
		case HARD:
			this.difficultyValue=20;
			this.difficultyDescription = " appears hard issue ";
			break;
		case VERY_HARD:
			this.difficultyValue=25;
			this.difficultyDescription = " appears a very hard issue ";
			break;
		case NEARLY_IMPOSSABLE:
			this.difficultyValue=30;
			this.difficultyDescription = " tis issue appearn near Impossable ";
			break;
			
		}
	}
	public int getDifficultyTarget() {
		return this.difficultyValue;
	}
	public String toString() {
		return this.difficultyDescription;
	}
	

}
