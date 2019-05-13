

public class Card implements Comparable<Card>{
	private String color;
	private String name;
	private int number;

	public Card() {
		number = (int) Math.random() * 10 + 1;
		name = number2Name(number);
		if (number <= 7 && number >= 1) {
			int colorNumber = (int) Math.random() * 4;
			color = number2Color(colorNumber);
		} else if (number == 8) {
			int colorNumber = 2 * (int) Math.random() * 2;
			color = number2Color(colorNumber);
		} else if (number == 9) {
			int colorNumber = 2 * (int) Math.random() * 2 + 1;
			color = number2Color(colorNumber);
		} else
			color = "any color";
	}

	public Card(int colorNumber, int number) {
		color = number2Color(colorNumber);
		name = number2Name(number);
		this.number = number;
	}
	public static String number2Name(int number) {
		String name = "";
		switch (number) {
		case 1:
			name = "BigBird";
			break;
		case 2:
			name = "Zoe";
			break;
		case 3:
			name = "CookieMonster";
			break;
		case 4:
			name = "Elmo";
			break;
		case 5:
			name = "BabyBear";
			break;
		case 6:
			name = "Rosita";
			break;
		case 7:
			name = "Grover";
			break;
		case 8:
			name = "ErnieAndBert";
			break;
		case 9:
			name = "OscarTheGrouch";
			break;
		case 10:
			name = "Wild";
			break;
		}
		return name;
	}

	public static String number2Color(int colorNumber) {
		String color = "";
		switch (colorNumber) {
		case 0:
			color = "red";
			break;
		case 1:
			color = "yellow";
			break;
		case 2:
			color = "blue";
			break;
		case 3:
			color = "green";
			break;
		case 4:
			color = "any color";
			// case 4: color = "Any";
		}
		return color;
	}
	
	public static int color2Number(String col) {
		int number = 0;
		switch (col) {
		case "red":
			number = 0;
			break;
		case "yellow":
			number = 1;
			break;
		case "blue":
			number = 2;
			break;
		case "green":
			number = 3;
			break;
		case "any color":
			number = 4;
			// case 4: color = "Any";
		}
		return number;
	}

	public String getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public void setName(int number) {
		name = number2Name(number);
	}

	public void setColor(String newColor) {
		color = newColor;
	}

	public void defColor() {
		color = "any color";
	}

	public boolean equalTo(Card card) {
		return (color.equals(card.getColor())||number == card.getNumber()||color.startsWith("any"));
//		return (color.indexOf(card.getColor()) >= 0 || number == card.getNumber()
//				|| card.getColor().indexOf(color) >= 0);// card.getColor().indexOf(color)>=0
	}

	public String toString() {
		// return color+" "+name + "'s card #" + number;
		return color + " " + name + " #" + number;

	}

	@Override
	public int compareTo(Card card) {
		return number - card.getNumber();
	}

}

