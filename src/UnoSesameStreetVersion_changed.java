

import java.util.Scanner;

import java.util.ArrayList;
import java.util.Collection;

public class UnoSesameStreetVersion_changed {

	public static void showWhatInYourHands (Player player){
		if (player.amountOfCards() == 0) {
			if(player.getName().equals("Computer")) System.out.print(player.getName()+" has no cards,");
			else System.out.print("You don't have any cards,");
			System.out.println(player.getName()+"but 'Draw card' should be covered with any regular card or Wild card.");
			if(!player.getName().equals("Computer"))
			System.out.println("Choose a card you want to push on pile (1,2,3...), or -1 if you want to give up, or 0 if you want to draw one more card:");
		}
		else {
			System.out.println(player.getName()+" has " + player.amountOfCards() + " cards: \n---------------------------");
			for(int i = 0; i < player.amountOfCards(); i++){
		          System.out.print("["+(i+1)+"]"+player.handCards.get(i)+"\n"); 
		          //if(i!=player.amountOfCards()-1) System.out.print(", ");
		         // if ((i+1)%5==0 && i!=0 && i!=player.amountOfCards()-1) System.out.println("");
		       }
			System.out.println("---------------------------");
			
		}
		
	}
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		String name;
		Pile p = new Pile();
		p.fill();
		// System.out.println(p);
		p.shuffle();
		// System.out.println("Shuffled: \n"+p);
		System.out.println("Enter your name: ");
		name = stdin.nextLine();
		Player comp = new Player();
		Player human = new Player(name);
		
		int turn = whoseTurn(p,name);
		letTheGameBegins(p, comp, human, name);
		
		while (!(comp.amountOfCards() == 0 || human.amountOfCards() == 0)) {
			if (p.isEmpty()) {
				p.discard2Pile();
				//we did it in more natural way, so the card from the top of discard pile is the same as it was before shuffling
			}
			if (turn % 2 == 0 && human.amountOfCards() != 0) {
				int choice = 0;
				ArrayList<Card> suitCards = new ArrayList<Card>();
				if (comp.amountOfCards() == 0) {
					System.out.println("Computer has no cards, but he need to cover 'Draw card' with any regular card or Wild card.");
				}
				else {
					showWhatInYourHands (comp);
				}
				
				
					for (int k = 0; k < comp.amountOfCards(); k++) {
						if (comp.handCards.get(k).equalTo(p.peekD())) {
							suitCards.add(comp.handCards.get(k));
						}
					}
					if (suitCards.isEmpty()) {
						System.out.println("Computer sais: \"Oh, no! I don't have suitable cards, I'll take one more!\"");
						if (p.isEmpty()) {
							p.discard2Pile();
							//in more natural way we keep the top card from discard pile
							//p.pile2Discard();
						}
						Card a = p.pop();
						
						if (a==null) {
							int c = comp.amountOfCards();
							int h = human.amountOfCards();
							int scoreC = 0;
							int scoreH = 0;
							for(int k = 0; k<c; k++) {
								scoreC+=comp.handCards.get(k).getNumber();
							}
							for(int k = 0; k<h; k++) {
								scoreH+=human.handCards.get(k).getNumber();
							}
							if(scoreC>scoreH) System.out.println("You have "+h+" cards and total amount of scores you have is "+scoreH+", and computer has "+c+" cards and total amount of scores you have is "+scoreC+". Less score is better! That means you WIN!" );
							else System.out.println("You have "+h+" cards and total amount of scores you have is "+scoreH+", and computer has "+c+" cards and total amount of scores you have is "+scoreC+". Less score is better! That means computer WIN!" );
							return;
						}
						else {
						System.out.println("Computer took {" + a + "}");
						comp.handCards.add(a);
						if (a.equalTo(p.peekD())) 
								suitCards.add(a);
						}

					}
//				if (suitCards.size()==0) turn++;
				if (suitCards.size()>1)
					System.out.println("Computer sais: \"Hmmm, let me think! I have such suitable cards: " + suitCards);
				for (int k = 0; k < suitCards.size(); k++) {
					if (suitCards.get(k).getNumber() == 9) {
						choice = k;
						break;
					}
					if (suitCards.get(k).getNumber() == 8) {
						choice = k;
						break;
					}
				}
				if (suitCards.size()!=0&&suitCards.get(choice).getNumber() == 9) {
					Card a = p.pop();
					if (a==null) {
						System.out.println("You are lucky! Someone was too greedy and took all cards!");
						
					}
					else{ 
						human.handCards.add(a);
						a = p.pop();
						if (a==null) {
							System.out.println("You are lucky! Someone was too greedy and took all cards!");
							System.out.println("So, "+name + " took only 1 cardx" );
							
						}
						else  {
						human.handCards.add(a);
						System.out.println(name + " takes 2 cards (card draw 2)" );
						}
					}
					
					turn--;
					
				} else if (suitCards.size()!=0&&suitCards.get(choice).getNumber() == 8) {
					Card a = p.pop();
					if (a==null) {
						System.out.println("You are lucky! Someone was too greedy and took all cards!");
					}
					else {
						human.handCards.add(a);
						System.out.println(name + " takes 1 cards (card draw 1)" );
					}
					turn--;
					
				} else if (suitCards.size()!=0&&suitCards.get(choice).getNumber() == 10) { 
					// by default he will choose first element, but if it is Wild card we will try to find something better
					for (int k = 0; k < suitCards.size(); k++) {
						if (suitCards.get(k).getNumber() != 10) {
							choice = k;
							break;
						}
					}
				}
				
				if (suitCards.size() != 0) {
					Card card = comp.outCard(comp.handCards.indexOf(suitCards.get(choice)));

					if (card.getNumber() == 10) {
						comp.countColors(comp, card);
					}
					if (p.peekD().getNumber() == 10) {
						Card o = p.popD(); // Changing color of Wild card back to any color
						o.defColor();
						p.pushD(o);
					}
					
					System.out.println("Computer has chosen {" + card + "}");
					p.pushD(card);
					p.showPiles();
					if(comp.amountOfCards()==1)System.out.println("Computer says: \"UNO\"!");
				}
				turn++;
				
			}
			if (turn % 2 == 1 && comp.amountOfCards() != 0)
				System.out.println("It is " + name + "'s turn.");
			boolean tookAcard = false;
			while (turn % 2 == 1 && comp.amountOfCards() != 0) {
				
				if(human.amountOfCards() == 0) {
					System.out.println("You don't have any cards but you should cover your 'Draw card' with any regular card or Wild card."
							+ "\nChoose a card you want to push on pile (1,2,3...), or -1 if you want to give up, or 0 if you want to draw one more card:");
					}
				else {
					
					showWhatInYourHands (human);
					if(tookAcard==false)
						System.out.println("Choose a card you want to push on pile (1,2,3...), or -1 if you want to give up, or 0 if you want to draw one more card:");
					else System.out.println("Choose a card you want to push on pile (1,2,3...), or -1 if you want to give up, or 0 if you want to skip your turn:");
				
				}
				int choice=-1;
				
				while(true) {
					try{ 
						choice = stdin.nextInt();
						break;
					}
					catch(Exception ex){
						System.out.print("Wrong input, please enter number!");
						stdin.next();
						continue;
					}
				}
				
				String s = stdin.nextLine().trim();
				if(!s.toUpperCase().equals("UNO")&&human.amountOfCards()==2&&choice!=0) {
					System.out.println("You forgot to say \"UNO\"! Take 2 cards!");
					Card a = p.pop();
					if (a==null) {
						System.out.println("You are lucky, there is no more cards in a pile!");
					}
					else {
						human.handCards.add(a);
						a = p.pop();
						if (a==null) {
							System.out.println("You are lucky, there is no more cards in a pile, so you took only 1 card!");
						}
						else human.handCards.add(p.pop());
					}

					showWhatInYourHands (human);
					System.out.println("\nChoose a card you want to push on pile (1,2,3...), or -1 if you want to give up, or 0 if you want to draw one more card:");
				}
				if (choice > human.amountOfCards())
					System.out.println("Careful, you don't have so many cards");
				else if (choice < -1)
					System.out.println("Are you kidding me? Negative number of cards?");
				else if (choice == 0 && tookAcard==false) {
					Card a = p.pop();
					if (a == null) {
						System.out.println("There is no more cards, probably you can try to play with yours or '-1' to end the game");
					}
					else {
					human.inCard(a);
					System.out.println("You have got a new card: {" + a + "}");
					tookAcard = true;
					}
				}
				else if (choice == 0 && tookAcard==true) {
					turn++;
				}
				else if (choice == -1) {
					System.out.println("You decided to give up, maybe you will be more lucky next time!");
					return;
				}
				else {
					Card card = human.outCard(choice - 1);
					System.out.println("You have chosen {" + card + "}");
					if (card.equalTo(p.peekD())) {
						if (card.getNumber() == 8) {
							Card a = p.pop();
							if (a==null) {
								System.out.println("Computer is lucky, there is no more cards in a pile!");
							}
							else {
							comp.handCards.add(a);
							System.out.println("Computer takes 1 cards (card draw 1)");
							}
							turn--;
							
						}
						if (card.getNumber() == 9) {
							Card a = p.pop();
							if (a==null) {
								System.out.print("Computer is lucky, there is no more cards in a pile!");
							}
							else {
								comp.handCards.add(a);
								a = p.pop();
								if (a==null) {
									System.out.println("Computer takes 1 card (card draw 2)");
									System.out.print("Computer is lucky, there is no more cards in a pile!");
								}
								else {
									comp.handCards.add(a);
									System.out.println("Computer takes 2 cards (card draw 2)");
								}
							}
							turn--;
							
						}
						if (card.getNumber() == 10) {
							System.out.print("Choose new color for Wild card: ");
							System.out.println("\n[1] red \n[2] yellow \n[3] blue \n[4] green");
							choice = stdin.nextInt();
							card.setColor(Card.number2Color(choice-1));
						}
						if (p.peekD().getNumber() == 10) { // Changing color of Wild card back to any color
							Card o = p.popD();
							o.defColor();
							p.pushD(o);
						}
						p.pushD(card);
						p.showPiles();

						turn++;
					} else {
						System.out.println("Your card {" + card + "} doesn't match card in discard pile {" + p.peekD()+"}");
						human.inCard(card);
					}
				}
//				System.out.println("Size of a pile "+p.getSize());
//				System.out.println("Size of a discard "+p.discardPile.size());
			}
		}
		if (comp.amountOfCards() == 0)
			System.out.println("Computer WIN");
		else if (human.amountOfCards() == 0)
			System.out.println(name + " WIN");
		else {
			int a = comp.amountOfCards();
			int b = human.amountOfCards();
			int scoreA = 0;
			int scoreB = 0;
			for(int k = 0; k<a; k++) {
				scoreA+=comp.handCards.get(k).getNumber();
			}
			for(int k = 0; k<a; k++) {
				scoreB+=human.handCards.get(k).getNumber();
			}
			if(scoreA>scoreB) System.out.println("You have "+b+" cards and total amount of scores you have is "+scoreB+", and computer has "+a+" cards and total amount of scores you have is "+scoreA+". Less score is better! That means you WIN!" );
			else System.out.println("You have "+b+" cards and total amount of scores you have is "+scoreB+", and computer has "+a+" cards and total amount of scores you have is "+scoreA+". Less score is better! That means computer WIN!" );
			
		}
		stdin.close();
	}
	public static int whoseTurn (Pile p, String name) {
		int turn = 0;
		boolean again = true;
		System.out.println("Whose turn? Lets draw a card.");
		do {
			Card card1 = p.pop();
			Card card2 = p.pop();
			System.out.printf("Computer draw {%s card}. %s draw a {%s card}.", card1.toString(), name,
					card2.toString());
			if(card1.getNumber()>7||card2.getNumber()>7) {
				System.out.printf("\nIt should be card with number 1-7, draw again.\n");
				again = true;
			}
			else {
			if (card1.compareTo(card2) > 0) {
				System.out.printf("\nIt is Computer's first turn\n");
				again = false;
			} else if (card1.compareTo(card2) < 0) {
				System.out.printf("\n" + name + "'s first turn\n\n");
				turn = 1;
				again = false;
			} else
				System.out.printf("\nOh, it seems you have the same cards numbers! Lets do it again!");
			}
		} while (again);
		return turn;
	}
	public static void letTheGameBegins(Pile p, Player comp, Player human, String name) {
		System.out.println("-----------------------------------------------");
		System.out.println("Lets shuffle pile and then the game will begin!");
		System.out.println("-----------------------------------------------");
		p.fill();
		p.shuffle();

		for (int k = 0; k < 5; k++) { // Each player get 5 cards
			comp.handCards.add(p.pop());
			human.handCards.add(p.pop());
		}
		
		comp.handCards.sort(null);
		human.handCards.sort(null);
		System.out.println("Computer's cards: " + comp.handCards + "\n");
		System.out.println(name+"'s cards: " + human.handCards + "\n");
		p.pile2Discard();
	}
	public static boolean checkIfNull(Card a, Player player) {
		if (a==null) {
			System.out.println(player.getName()+", you are lucky, there is no more cards in a pile!");
			return true;
		}
		return false;
	}
	public static int actionCards(Pile p, Card card, Player otherPlayer, int turn) {
		if (card.getNumber() == 8 || card.getNumber() == 9) {
			Card a = p.pop();
			if (!checkIfNull(a,otherPlayer)) {
				otherPlayer.handCards.add(a);
				if (card.getNumber() == 8) System.out.println(otherPlayer.getName()+" takes 1 cards (card 'draw 1')");
			}
			if (card.getNumber() == 9) {
				a = p.pop();
				System.out.println(otherPlayer.getName()+" takes first card (card 'draw 2')");
				if (!checkIfNull(a, otherPlayer)) {
					otherPlayer.handCards.add(a);
					System.out.println("Computer takes second cards (card 'draw 2')");
				}
			}
			turn--;
		}
		return turn;
	}
	
}
