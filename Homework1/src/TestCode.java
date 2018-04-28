
public class TestCode {
	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public static int getScrabbleScore(String string) {
		// TODO Write an implementation for this method declaration
		int score = 0;
		
		for(int i=0; i < string.length(); i++) {
			char letter = string.charAt(i);
			switch(letter) {
				case 'a':
				case 'e':
				case 'i':
				case 'o':
				case 'u':
				case 'l':
				case 'n':
				case 'r':
				case 's':
				case 't':
					score += 1;
					break;
				case 'd':
				case 'g':
					score += 2;
					break;
				case 'b':
				case 'c':
				case 'm':
				case 'p':
					score += 3;
					break;
				case 'f':
				case 'h':
				case 'v':
				case 'w':
				case 'y':
					score += 4;
					break;
				case 'k':
					score += 5;
					break;
				case 'j':
				case 'x':
					score += 8;
					break;
				case 'q':
				case 'z':
					score += 10;
					break;
			}
		}
		return score;
	}
	
	public static void main(String[] args) {
		int scoreTest1 = getScrabbleScore("f");
		System.out.println(scoreTest1);
		
		int scoreTest2 = getScrabbleScore("zoo");
		System.out.println(scoreTest2);
		
		int scoreTest3 = getScrabbleScore("street");
		System.out.println(scoreTest3);
		
	}

}
