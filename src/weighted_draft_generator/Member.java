package weighted_draft_generator;

public class Member {
	private String name;
	private double chance;
	private int start_position;
	public Member(String name, double chance, int start_position) {
		this.name = name;
		this.chance = chance;
		this.start_position = start_position;
	}
	public Member(double chance, int start_position) {
		this.name = null;
		this.chance = chance;
		this.start_position = start_position;
	}
	public String getName() {
		return name;
	}
	// chance is chance to get next pick
	public double getChance() {
		return chance;
	}
	public void setChance(double chance) {
		this.chance = chance;
	}
	
	public int getStartPosition() {
		return start_position;
	}
}
