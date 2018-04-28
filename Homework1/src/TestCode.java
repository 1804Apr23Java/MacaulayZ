
public class TestCode {

	public static void main(String[] args) {
		// Number 20
		String s = "What is 5 plus 13?";
		String t = "What is 7 minus 5?";
		String u = "What is 6 multiplied by 4?";
		String v = "What is 25 divided by 5?";
		int resultAdd = solveWordProblem(s);
		int resultSub = solveWordProblem(t);
		int resultMulti = solveWordProblem(u);
		int resultDiv = solveWordProblem(v);
		System.out.println(s);
		System.out.println(resultAdd);
		
		System.out.println(t);
		System.out.println(resultSub);
		
		System.out.println(u);
		System.out.println(resultMulti);
		
		System.out.println(v);
		System.out.println(resultDiv);
	}
	
	public static int solveWordProblem(String string) {
		
		String[] twoNums = string.split(" ");
		
		int num1 = Integer.parseInt(twoNums[2]);
		String numberString = "";
		if (twoNums[3].equals("plus")) {
			for (int i=0; i<twoNums[4].length(); i++) {
				switch(twoNums[4].charAt(i)) {
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case '-':
					numberString += twoNums[4].charAt(i);
					break;
				default:
				}
			}
			int num2 = Integer.parseInt(numberString);
			int sum = num1+num2;
			return sum;
		}
		
		else if (twoNums[3].equals("minus")) {
			for (int i=0; i<twoNums[4].length(); i++) {
				switch(twoNums[4].charAt(i)) {
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case '-':
					numberString += twoNums[4].charAt(i);
					break;
				default:
				}
			}
			int num2 = Integer.parseInt(numberString);
			int difference = num1-num2;
			return difference;
		}
		
		else if (twoNums[3].equals("multiplied")) {
			for (int i=0; i<twoNums[5].length(); i++) {
				switch(twoNums[5].charAt(i)) {
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case '-':
					numberString += twoNums[5].charAt(i);
					break;
				default:
				}
			}
			int num2 = Integer.parseInt(numberString);
			int product = num1*num2;
			return product;
		}
		
		else if (twoNums[3].equals("divided")) {
			for (int i=0; i<twoNums[5].length(); i++) {
				switch(twoNums[5].charAt(i)) {
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case '-':
					numberString += twoNums[5].charAt(i);
					break;
				default:
				}
			}
			int num2 = Integer.parseInt(numberString);
			int quotient = num1/num2;
			return quotient;
		}
		
		return 0;
	}

}
