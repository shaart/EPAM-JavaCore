package RectSquare;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	private static double askInputUntilCorrect(Scanner scanner, String argumentName) {
		double number = 0;

		boolean gotCorrect = false;
		while (!gotCorrect) {
			try {
				System.out.format("Enter %s: ", argumentName);
				number = scanner.nextDouble();
				gotCorrect = true;
			} catch (Exception e) {
				scanner.next();
				System.out.println("Error: invalid input");
			}
		}

		return number;
	}

	private static double tryParse(String value) {
		NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());

		double result;
		try {
			result = nf.parse(value).doubleValue();
		} catch (Exception e) {
			result = 0;
		}

		return result;
	}

	public static void main(String[] args) {
		double x1 = 0;
		double y1 = 0;
		double x2 = 0;
		double y2 = 0;
		
		Scanner scanner = new Scanner(System.in);
		scanner.useLocale(Locale.getDefault());
		x1 = args.length >= 1 ? tryParse(args[0]) : askInputUntilCorrect(scanner, "x1");
		y1 = args.length >= 2 ? tryParse(args[1]) : askInputUntilCorrect(scanner, "y1");
		x2 = args.length >= 3 ? tryParse(args[2]) : askInputUntilCorrect(scanner, "x2");
		y2 = args.length >= 4 ? tryParse(args[3]) : askInputUntilCorrect(scanner, "y2");
		scanner.close();

		System.out.println("Square = " + Rectangle.square(x1, y1, x2, y2));
	}

}
