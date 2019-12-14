package weighted_draft_generator;

public class Weighted_Draft_Playground {

	public static void main(String[] args) {
		// Test Single runs a test on a 10 person draft and prints the draft order
		 testSingle();
		 
		 
		 // Test Multiple shows how often a particular draft order spot is given to each member.
		 // Adjust the input as needed to test. To test drafts of a greater length, you will need to go into the function to edit the input.
		 testMultiple(1);
	}
	
	
	private static void testMultiple(int spot) {
		Member[] members = new Member[10];
		int[] answeramount = new int[members.length]; // in same order as "members"
		for (int i = 0; i < members.length; i++) {
			answeramount[i] = 0;
		}
		for(int i = 0; i < 1000000; i++) {
			members[0] = new Member("one", .05, 10);
			members[1] = new Member("two", .06, 9);
			members[2] = new Member("three", .07, 8);
			members[3] = new Member("four", .08, 7);
			members[4] = new Member("five", .09, 6);
			members[5] = new Member("six", .11, 5);
			members[6] = new Member("seven", .12, 4);
			members[7] = new Member("eight", .13, 3);
			members[8] = new Member("nine", .14, 2);
			members[9] = new Member("ten", .15, 1);
			Draft draft = new Draft(members);
		
			Member[] answers = draft.evaluateDraft();
			answeramount[findinarr(answers[spot-1].getName(), members)] += 1;
		}
		
		for (int i = 0; i < members.length; i++) {
			System.out.println(members[i].getName() + " was selected " + answeramount[i] + " times");
		}
	}
	
	private static void testSingle() {
		Member[] members = new Member[10];
		members[0] = new Member("one", .05, 10);
		members[1] = new Member("two", .06, 9);
		members[2] = new Member("three", .07, 8);
		members[3] = new Member("four", .08, 7);
		members[4] = new Member("five", .09, 6);
		members[5] = new Member("six", .11, 5);
		members[6] = new Member("seven", .12, 4);
		members[7] = new Member("eight", .13, 3);
		members[8] = new Member("nine", .14, 2);
		members[9] = new Member("ten", .15, 1);
		Draft draft = new Draft(members);
		Member[] answers = draft.evaluateDraft();
		for (int i = 0; i < members.length; i++) {
			System.out.println(answers[i].getName());
		}
	}
	
	// helper method for test
	private static int findinarr(String name, Member[] members) {
		for (int i = 0; i < members.length; i++) {
			if (name.contentEquals(members[i].getName())) {
				return i;
			}
		}
		return -1; // this shouldn't happen
	}

}
