package RectIntersectionSquare;

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
		double x1, y1, x2, y2, x3, y3, x4, y4;
		
		Scanner scanner = new Scanner(System.in).useLocale(Locale.getDefault());
		System.out.println("First Rectangle.. ");
		x1 = args.length >= 1 ? tryParse(args[0]) : askInputUntilCorrect(scanner, "x1");
		y1 = args.length >= 2 ? tryParse(args[1]) : askInputUntilCorrect(scanner, "y1");
		x2 = args.length >= 3 ? tryParse(args[2]) : askInputUntilCorrect(scanner, "x2");
		y2 = args.length >= 4 ? tryParse(args[3]) : askInputUntilCorrect(scanner, "y2");
		System.out.println("Second Rectangle.. ");
		x3 = args.length >= 5 ? tryParse(args[4]) : askInputUntilCorrect(scanner, "x1");
		y3 = args.length >= 6 ? tryParse(args[5]) : askInputUntilCorrect(scanner, "y1");
		x4 = args.length >= 7 ? tryParse(args[6]) : askInputUntilCorrect(scanner, "x2");
		y4 = args.length >= 8 ? tryParse(args[7]) : askInputUntilCorrect(scanner, "y2");
		System.out.println("Input data successfully got! ");
		scanner.close();

		Rectangle intersect = Rectangle.intersection(new Rectangle(x1, y1, x2, y2), new Rectangle(x3, y3, x4, y4));
		
		System.out.println("Square = " + (intersect != null ? intersect.square() : 0));
	}

}
