package weighted_draft_generator;

import java.util.Arrays;

public class Draft {
	private Member[] members;
	private int size;
	// constructors
	public Draft(int size, Member[] members) {
		this.size = size;
		this.members = members;
	}
	public Draft(int size) {
		this.size = size;
		members = new Member[size];
	}
	public Draft (Member[] members) {
		this.members = members;
		size = members.length;
	}
	
	public Member[] evaluateDraft() {
		Member[] clone = getMembers().clone(); // clone to allow multiple repetitions
		Member[] answer = new Member[clone.length]; // answer array where the order of the array equals the draft order
		double total = Math.round(getTotal(clone) * 100000) / 100000; // java is weird
		if (total != 1.0) {
			throw new IllegalArgumentException("Total chance doesn't add up to 1: " + total);
		}
		for (int i = 0; i < answer.length; i++) {
			int x = condition_one(clone, i);
			if (x == -1) {
				x = condition_two(clone);
			}
			// x represents the index in "clone" of the next thing in the answer array.
			answer[i] = clone[x];
			clone[x].setChance(0.0);
			clone = calcChance(clone);
		}
		return answer;
	}
	// check if any have to be next to not drop 2
	private int condition_one(Member[] arr, int j) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].getStartPosition() + 1 == j && arr[i].getChance() != 0) { // second half to avoid duplicates
				return i;
			}
		}
		return -1;
	}
	
	// randomly choose one - duplicates avoided because 0.0 chance will never be selected
	private int condition_two(Member[] arr) {
		double weightchooser = 0.0;
		double random_choice = Math.random();
		for (int i = 0; i < arr.length; i++) {
			weightchooser+= arr[i].getChance();
			if (weightchooser >= random_choice) {
				return i;
			}
		}
		return -1;
	}
	
	private Member[] calcChance(Member[] arr) {;
		double currtotal = getTotal(arr);
		// currtotal should be less than 1
		double multiplier = 1 / currtotal;
		for (int i = 0; i < arr.length; i++) {
			arr[i].setChance(arr[i].getChance() * multiplier);
		}
		return arr; // returns the same array with adjusted chances. 
	}
	
	private double getTotal(Member[] arr) {
		double total = 0;
		for (int i = 0; i < arr.length; i++) {
			total+= arr[i].getChance();
		}
		return total;
	}
	
	public Member[] getMembers() {
		return members.clone();
	}
}
