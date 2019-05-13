

import java.util.ArrayList;

public class Pile {
	private ArrayList<Card> pile = new ArrayList<Card>();
	private ArrayList<Card> discardPile = new ArrayList<Card>();

	public boolean isEmpty() {
		return pile.isEmpty();
	}

	public int getSize() {
		return pile.size();
	}

	public void shuffle() {
		java.util.Collections.shuffle(pile);
	}

	public void erase(ArrayList<Card> p) {
		int s = p.size();
		for (int k = 0; k < s; k++)
			p.clear();
	}

	public void fill() {
		if (!pile.isEmpty())
			erase(pile);
		for (int k = 0; k < 4; k++) {
			for (int l = 1; l <= 7; l++) {
				pile.add(new Card(k, l));
			}
			if (k % 2 == 0)
				pile.add(new Card(k, 8));
			if (k % 2 == 1)
				pile.add(new Card(k, 9));
			pile.add(new Card(4, 10));
		}
		
	}

	public Card peek() {
		return pile.get(getSize() - 1);
	}

	public Card peekD() {
		return discardPile.get(discardPile.size() - 1);
	}

	public void discard2Pile() {
		System.out.println("The pile is empty. I'll refresh it with discard pile!");
		
		// lets try to do it in more realistic way
		Card temp = discardPile.get(discardPile.size() - 1);
		discardPile.remove(discardPile.size() - 1);
		int size = discardPile.size();
		//
		if (discardPile.size()==0) {
			System.out.println("Oh, dear! There is nothing in discard pile too. It seems like game is over!");
			discardPile.add(temp);
			return;
		}
		else {
			for (int k = 0; k < size; k++) {
				push(discardPile.get(k));
			}
			shuffle();
		}
		// lets try to do it in more realistic way
		erase(discardPile);
		discardPile.add(temp);
		showPiles();
		//
		
//		erase(discardPile);
//		pile2Discard();
	}

	public Card pop() {
		if (isEmpty()&&discardPile.size()>1)
			discard2Pile();
		if (isEmpty()&&discardPile.size()==1) {
			return null;
		}
		Card o = pile.get(getSize() - 1);
		pile.remove(getSize() - 1);
		return o;
		
	}

	public void pile2Discard() {
		if (isEmpty())
			discard2Pile();
		do {
			pushD(pop());
			showPiles();
		} while (peekD().getNumber() >= 8 && peekD().getNumber() <= 10);
	}

	public void push(Card o) {
		pile.add(o);
	}

	public void pushD(Card o) {
		discardPile.add(o);
	}

	public String toString() {
		return "stack: " + pile.toString();
	}

	public void showPiles() {
		// System.out.println(" pile discard");
		System.out.println(" .____________.  .____________.");
		System.out.println(" |XXXXXXXXXXXX|  |            |");
		System.out.println(" |XXXXXXXXXXXX|  |            |");
		
		if (peekD().getColor().equals("red"))
			System.out.printf(" |XXXXXXXXXXXX|  |    %s     |\n", peekD().getColor());
		if (peekD().getColor().equals("yellow"))
			System.out.printf(" |XXXXXXXXXXXX|  |   %s   |\n", peekD().getColor());
		if (peekD().getColor().equals("blue"))
			System.out.printf(" |XXXXXXXXXXXX|  |    %s    |\n", peekD().getColor());
		if (peekD().getColor().equals("green"))
			System.out.printf(" |XXXXXXXXXXXX|  |   %s    |\n", peekD().getColor());
		if (peekD().getColor().substring(0, 3).equals("any"))
			System.out.printf(" |XXXXXXXXXXXX|  |    %s    |\n", peekD().getColor().substring(0, 3));
		if (peekD().getNumber()==1)
			System.out.printf(" |XXXXXXXXXXXX|  |   %s  |\n", peekD().getName());
		if (peekD().getNumber()==2)
			System.out.printf(" |XXXXXXXXXXXX|  |    %s     |\n", peekD().getName());
		if (peekD().getNumber()==3) {
			System.out.printf(" |XXXXXXXXXXXX|  |   %s   |\n", peekD().getName().substring(0,6));
			System.out.printf(" |XXXXXXXXXXXX|  |   %s  |\n", peekD().getName().substring(6));
		}
		if (peekD().getNumber()==8) {
			System.out.printf(" |XXXXXXXXXXXX|  |    %s   |\n", peekD().getName().substring(0,5));
			System.out.printf(" |XXXXXXXXXXXX|  |   %s  |\n", peekD().getName().substring(5));
		}
		if (peekD().getNumber()==9) {
			System.out.printf(" |XXXXXXXXXXXX|  |   %s    |\n", peekD().getName().substring(0,5));
			System.out.printf(" |XXXXXXXXXXXX|  |  %s |\n", peekD().getName().substring(5));
		}
		if (peekD().getNumber()==4||peekD().getNumber()==10)
			System.out.printf(" |XXXXXXXXXXXX|  |    %s    |\n", peekD().getName());
		if (peekD().getNumber()==5)
			System.out.printf(" |XXXXXXXXXXXX|  |  %s  |\n", peekD().getName());
		if (peekD().getNumber()==6||peekD().getNumber()==7)
			System.out.printf(" |XXXXXXXXXXXX|  |   %s   |\n", peekD().getName());
		if (peekD().getNumber() == 10)
			System.out.printf(" |XXXXXXXXXXXX|  |    #%s     |\n", peekD().getNumber());
		else
			System.out.printf(" |XXXXXXXXXXXX|  |     #%s     |\n", peekD().getNumber());
		if (peekD().getNumber()!=3||peekD().getNumber()!=8||peekD().getNumber()!=9)System.out.println(" |XXXXXXXXXXXX|  |            |");
		System.out.println(" |XXXXXXXXXXXX|  |            |");
		System.out.println(" |XXXXXXXXXXXX|  |            |");
		System.out.println(" |XXXXXXXXXXXX|  |____________|");
		System.out.println("");
	}

	public Card popD() {
		
		Card o = discardPile.get(discardPile.size() - 1);
		discardPile.remove(discardPile.size() - 1);
		return o;
	}

}
