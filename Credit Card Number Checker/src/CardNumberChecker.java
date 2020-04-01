import java.util.Scanner;

public class CardNumberChecker {

	final int MAX_LENGTH = 16;
	/*
	Luhn check to test whether or not a credit card number is valid:
		Step 1a. For a card number with an even number of digits
			(e.g., Visa or MasterCard), double alternating digits starting with the first digit in the sequence.
		
		Step 1b. For a card with an odd number of digits
			(e.g., American Express), double alternating digits starting with the second digit in the sequence.
		
		Step 2. If the doubling resulted in a number with two digits, add them together to get a single digit number
		
		Step 3. Now go back to the original credit number and replace the digits that you doubled with the new value
			 — either the doubled value, or the doubled value with the digits added together — and add it all up.
			 
		Step 4. Check to see if the sum is evenly divisible by 10 
			(you can simply look to see whether or not it ends with a zero).
			If the card number does not pass this check, then it is not a valid number.
			If, on the other hand, it does pass, then it may be a valid number with valid
	*/

	public void askCard() {
		Scanner sc = new Scanner(System.in);
		int cardName = 0;
		do {
			System.out.println("Choose your card name by its number: ");
			System.out.println("1. VISA\n2. mastercard\n3. DISCOVER\n");
			cardName = sc.nextInt();
		}while(cardName > 3 || cardName<1);
		
		System.out.println("Enter your card number: ");
		int[] num = new int[MAX_LENGTH];
		for(int i=0;i<MAX_LENGTH; i++) 
			num[i] = sc.nextInt();
		
		checkCard(cardName, num);
	}
	
	
	public boolean checkCard(int cardName, int[] cardNumber) {
		if(cardName == 1 || cardName == 2 || cardName == 3) {
			int[] temp = cardNumber;
			for(int i=0;i<cardNumber.length - 1 ;i++) { //without last digit
				temp[i] = (i % 2 == 0) ? cardNumber[i]*2 : cardNumber[i];
			}
			for(int i=0;i<cardNumber.length - 1 ;i++) {
				if(hasTwoDigits(temp[i]))
					temp[i] = sumTwoDigits(temp[i]);
			}
			int sum = 0;
			for(int i=0;i<cardNumber.length - 1 ;i++) {
				sum += temp[i];
			}
			return (sum + temp[temp.length -1])  % 10 == 0;
		}
		return false;
		}

	private boolean hasTwoDigits(int num) {
		if(num > 9 && num < 19 ) // any single int number multiplied by 2 is always smaller than 19, and greater than 9. 
			return true;
		return false;
	}
	
	private int sumTwoDigits(int num) {
		int first = num % 10;
		int second = (num/10) % 10;
		return first + second;
	}
	
	public static void main(String[] args) {
		// you can either call askCard() or initialize your card here, example:
		//CardNumberChecker card = new CardNumberChecker();
		//card.askCard();
		// ------------------------------------ OR -------------------------------------
		//int[] number = {4,9,0,4,8,3,9,8,2,2,4,8,5,9,5,9};
		//System.out.println(card.checkCard(1, number));
	}
}




