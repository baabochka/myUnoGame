

import java.util.ArrayList;

public class Player {
	private String name;
	ArrayList<Card> handCards = new ArrayList<Card>();

	public Player() {
		name = "Computer";
	}

	public Player(String playerName) {
		name = playerName;
	}

	public String getName() {
		return name;
	}

	public int amountOfCards() {
		return handCards.size();
	}

	public ArrayList<Card> getHandCards() {
		return handCards;
	}

	public void setHandCards(ArrayList<Card> handCards) {
		this.handCards = handCards;
	}

	public Card outCard(int n) {
		Card o = handCards.get(n);
		handCards.remove(n);
		return o;
	}
	public void countColors (Player comp, Card card) {
		int[] countColor = { 0, 0, 0, 0 };
		for (int k = 0; k < comp.amountOfCards(); k++) {
			switch (comp.handCards.get(k).getColor()) 
			{
			case 	"red": countColor[0]++; break;
			case "yellow": countColor[1]++; break;
			case   "blue": countColor[2]++; break;
			case  "green": countColor[3]++; break;
			}
		}
		int maxColor = 0, max = 0;
		for (int k = 0; k < 4; k++) {
			if (countColor[k] > maxColor) {
				maxColor = countColor[k];
				max = k;
			}
		}
		card.setColor(Card.number2Color(max));
	}
	
	public void inCard(Card newCard) {
		handCards.add(newCard);
		sortCards(handCards);
	}
	public void sortCards(ArrayList<Card> cards){
		cards.sort(null);
		
	}
	

}