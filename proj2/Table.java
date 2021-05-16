package proj2;

/**
* File: Table.java
* Project2
* @author Javier Santiago and Patti Ordonez
* Date 5/16/21
* Course: CCOM4029

*/


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;




public class Table extends JFrame implements ActionListener
{
	final static int numDealtCards = 9;
	JPanel player1;
	JPanel player2;
	JPanel deckPiles;
	JLabel deck;
	JLabel stack;
	JList p1HandPile;
	JList p2HandPile;
	Deck cardDeck;
	//Deck stackDeck;
	Pile<Card> stackDeck;

	SetPanel [] setPanels = new SetPanel[13];
	JLabel topOfStack;
	JLabel deckPile;
	JButton p1Stack;
	JButton p2Stack;

	JButton p1Deck;
	JButton p2Deck;

	JButton p1Lay;
	JButton p2Lay;

	JButton p1LayOnStack;
	JButton p2LayOnStack;

	SortedListModel p1Hand;
	SortedListModel p2Hand;

	Player plyr1;
	Player plyr2;

	private void deal(Card [] cards)
	{
		for(int i = 0; i < cards.length; i ++)
			cards[i] = (Card)cardDeck.dealCard();
	}

	public Table(Player plyr1, Player plyr2)
	{
		super("The Card Game of the Century");

		this.plyr1 = plyr1;
		this.plyr2 = plyr2;

		setLayout(new BorderLayout());
		setSize(1200,700);


		cardDeck = new Deck();

		for(int i = 0; i < Card.suit.length; i++){
			for(int j = 0; j < Card.rank.length; j++){
				Card card = new Card(Card.suit[i],Card.rank[j]);
				cardDeck.addCard(card);
			}
		}
		cardDeck.shuffle();
		stackDeck = new Pile<Card>();

		JPanel top = new JPanel();

		for (int i = 0; i < Card.rank.length;i++)
			setPanels[i] = new SetPanel(Card.getRankIndex(Card.rank[i]));


		top.add(setPanels[0]);
		top.add(setPanels[1]);
		top.add(setPanels[2]);
		top.add(setPanels[3]);

		player1 = new JPanel();

		player1.add(top);




		add(player1, BorderLayout.NORTH);
		JPanel bottom = new JPanel();


		bottom.add(setPanels[4]);
		bottom.add(setPanels[5]);
		bottom.add(setPanels[6]);
		bottom.add(setPanels[7]);
		bottom.add(setPanels[8]);

		player2 = new JPanel();




		player2.add(bottom);
		add(player2, BorderLayout.SOUTH);


		JPanel middle = new JPanel(new GridLayout(1,3));

		p1Stack = new JButton("Stack");
		p1Stack.addActionListener(this);
		p1Deck = new JButton("Deck ");
		p1Deck.addActionListener(this);
		p1Lay = new JButton("Lay  ");
		p1Lay.addActionListener(this);
		p1LayOnStack = new JButton("LayOnStack");
		p1LayOnStack.addActionListener(this);


		Card [] cardsPlayer1 = new Card[numDealtCards];
		deal(cardsPlayer1);
		p1Hand = new SortedListModel();
		for(int i = 0; i < cardsPlayer1.length; i++){
			p1Hand.addElement(cardsPlayer1[i]);
			plyr1.addCardToHand(cardsPlayer1[i]); // Add cards to the hand attribute in player
		}

		p1HandPile = new JList(p1Hand);



		middle.add(new HandPanel("Player 1", p1HandPile, p1Stack, p1Deck, p1Lay, p1LayOnStack));

		deckPiles = new JPanel();
		deckPiles.setLayout(new BoxLayout(deckPiles, BoxLayout.Y_AXIS));
		deckPiles.add(Box.createGlue());
		JPanel left = new JPanel();
		left.setAlignmentY(Component.CENTER_ALIGNMENT);


		stack = new JLabel("Stack");
		stack.setAlignmentY(Component.CENTER_ALIGNMENT);

		left.add(stack);
		topOfStack = new JLabel();
		topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));
		topOfStack.setAlignmentY(Component.CENTER_ALIGNMENT);
		left.add(topOfStack);
		deckPiles.add(left);
		deckPiles.add(Box.createGlue());

		JPanel right = new JPanel();
		right.setAlignmentY(Component.CENTER_ALIGNMENT);

		deck = new JLabel("Deck");

		deck.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deck);
		deckPile = new JLabel();
		deckPile.setIcon(new ImageIcon(Card.directory + "b.gif"));
		deckPile.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deckPile);
		deckPiles.add(right);
		deckPiles.add(Box.createGlue());
		middle.add(deckPiles);


		p2Stack = new JButton("Stack");
		p2Stack.addActionListener(this);
		p2Deck = new JButton("Deck ");
		p2Deck.addActionListener(this);
		p2Lay = new JButton("Lay  ");
		p2Lay.addActionListener(this);
		p2LayOnStack = new JButton("LayOnStack");
		p2LayOnStack.addActionListener(this);

		Card [] cardsPlayer2 = new Card[numDealtCards];
		deal(cardsPlayer2);
		p2Hand = new SortedListModel();

		for(int i = 0; i < cardsPlayer2.length; i++){
			p2Hand.addElement(cardsPlayer2[i]);
			plyr2.addCardToHand(cardsPlayer2[i]);
		}


		p2HandPile = new JList(p2Hand);

		middle.add(new HandPanel("Player 2", p2HandPile, p2Stack, p2Deck, p2Lay, p2LayOnStack));

		add(middle, BorderLayout.CENTER);

		JPanel leftBorder = new JPanel(new GridLayout(2,1));


		setPanels[9].setLayout(new BoxLayout(setPanels[9], BoxLayout.Y_AXIS));
		setPanels[10].setLayout(new BoxLayout(setPanels[10], BoxLayout.Y_AXIS));
		leftBorder.add(setPanels[9]);
		leftBorder.add(setPanels[10]);
		add(leftBorder, BorderLayout.WEST);

		JPanel rightBorder = new JPanel(new GridLayout(2,1));

		setPanels[11].setLayout(new BoxLayout(setPanels[11], BoxLayout.Y_AXIS));
		setPanels[12].setLayout(new BoxLayout(setPanels[12], BoxLayout.Y_AXIS));
		rightBorder.add(setPanels[11]);
		rightBorder.add(setPanels[12]);
		add(rightBorder, BorderLayout.EAST);

		stackDeck.push(cardDeck.dealCard());
		Card topCard = stackDeck.top();
		topOfStack.setIcon(topCard.getCardImage());

	}

	public boolean isDeckEmpty(){
		return cardDeck.isEmpty();
	}

	public void actionPerformed(ActionEvent e)
	{
		Object src = e.getSource();
		// Verrify that the player clicking has the button available in the game
		if ((p1Deck == src && plyr1.isDeckAvail()) || (p2Deck == src && plyr2.isDeckAvail()))
		{

			Card card = cardDeck.dealCard();

			if (card != null){
				if(src == p1Deck){


					p1Hand.addElement(card);

					// Change the availability of all the buttons and add card to player
					plyr1.setDeckAvail(false);
					plyr1.setStackAvail(false);
					plyr1.setLayAvail(true);
					plyr1.setLayOnStackAvail(true);

					plyr1.setAddedCard(card);


				}

				else{
					p2Hand.addElement(card);

					// Change the availability of all the buttons and add card to player

					plyr2.setDeckAvail(false);
					plyr2.setStackAvail(false);
					plyr2.setLayAvail(true);
					plyr2.setLayOnStackAvail(true);

					plyr2.setAddedCard(card);

				}

			}


			if(cardDeck.getSizeOfDeck() == 0)
				deckPile.setIcon(new ImageIcon(Card.directory + "blank.gif"));

		}

		if((p1Stack == src && plyr1.isStackAvail()) || (p2Stack == src && plyr2.isStackAvail()))
		{

			Card card = stackDeck.pop();

			if(card != null){

				if (stackDeck.isEmpty())
					topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));
				else{
					Card topCard = stackDeck.top();
					topOfStack.setIcon(topCard.getCardImage());
				}


				if(p1Stack == src){
					if (plyr1.isStackAvail()){
						p1Hand.addElement(card);

						plyr1.setDeckAvail(false);
						plyr1.setStackAvail(false);
						plyr1.setLayAvail(true);
						plyr1.setLayOnStackAvail(true);

						plyr1.setAddedCard(card);

					}
				}

					else{
						if (plyr2.isStackAvail()){
							p2Hand.addElement(card);

							plyr2.setDeckAvail(false);
							plyr2.setStackAvail(false);
							plyr2.setLayAvail(true);
							plyr2.setLayOnStackAvail(true);

							plyr2.setAddedCard(card);
						}
					}


			}

		}

		if(p1Lay == src && plyr1.isLayAvail()){

			Object [] cards = p1HandPile.getSelectedValues();

			// Verify if its a new set
			if (cards.length == 3 || cards.length == 4){
				if (isOfSameRank(cards)){
					Card tempCard = (Card) cards[0];

					int rank = Card.getRankIndex( tempCard.getRank());
					setPanels[rank].update();
					for(int i = 0; i < cards.length; i++)
					{
						Card card = (Card)cards[i];
						layCard(card);
						p1Hand.removeElement(card);

						plyr1.addToDiscardedCards(card);
					}
				}
			}

			// Verify if adding card to existing set
			else if (cards.length == 1){
				Card card = (Card)cards[0];
				// Check if occupied
				int rank = Card.getRankIndex(card.getRank());


				if (setPanels[rank].isOccupied()){

					layCard(card);
					p1Hand.removeElement(card);

					plyr1.addToDiscardedCards(card);
				}
			}


		}


		if(p2Lay == src && plyr2.isLayAvail()){


			Object [] cards = p2HandPile.getSelectedValues();
			if (cards.length == 3 || cards.length == 4){

				if (isOfSameRank(cards)){


					Card tempCard = (Card) cards[0];

					int rank = Card.getRankIndex( tempCard.getRank());
					setPanels[rank].update();

					for(int i = 0; i < cards.length; i++)
					{
						Card card = (Card)cards[i];
						layCard(card);
						p2Hand.removeElement(card);

						plyr2.addToDiscardedCards(card);
					}
				}
			}
			else if (cards.length == 1){
				Card card = (Card)cards[0];
				// Check if occupied
				int rank = Card.getRankIndex(card.getRank());
				if (setPanels[rank].isOccupied()){
					layCard(card);
					p2Hand.removeElement(card);

					plyr2.addToDiscardedCards(card);
				}
			}
		}


		if(p1LayOnStack == src && plyr1.isLayOnStackAvail()){
			int [] num  = p1HandPile.getSelectedIndices();
			if (num.length == 1)
			{
				Object obj = p1HandPile.getSelectedValue();
				if (obj != null)
				{

					p1Hand.removeElement(obj);
					Card card = (Card)obj;
					//stackDeck.addCard(card);
					stackDeck.push(card);
					topOfStack.setIcon(card.getCardImage());

					plyr1.addToDiscardedCards(card);

					plyr1.setTurn(false);
					plyr2.setTurn(true);

				}
			}
		}


		if(p2LayOnStack == src && plyr2.isLayOnStackAvail()){
			int [] num  = p2HandPile.getSelectedIndices();
			if (num.length == 1)
			{
				Object obj = p2HandPile.getSelectedValue();
				if (obj != null)
				{

					p2Hand.removeElement(obj);
					Card card = (Card)obj;
					//stackDeck.addCard(card);
					stackDeck.push(card);
					topOfStack.setIcon(card.getCardImage());

					plyr2.addToDiscardedCards(card);

					plyr2.setTurn(false);
					plyr1.setTurn(true);

				}
			}
		}

	}
	/*
	public static void main(String args[])
	{
		Table t = new Table();
		t.setVisible(true);
	}
	*/
	void layCard(Card card)
	{
		char rank = card.getRank();
		char suit = card.getSuit();
		int suitIndex =  Card.getSuitIndex(suit);
		int rankIndex =  Card.getRankIndex(rank);
		//setPanels[rankIndex].array[suitIndex].setText(card.toString());
		//System.out.println("laying " + card);
		setPanels[rankIndex].array[suitIndex].setIcon(card.getCardImage());
	}

	// Function to see if all the cards in an array are of the same rank -- static?
	public boolean isOfSameRank(Object cards[]) {
		Card firstCard = (Card)cards[0];
		int firstRank = Card.getRankIndex(firstCard.getRank());

		for (int i = 1; i < cards.length; i++){
			Card tempCard = (Card)cards[i];
			int tempRank = Card.getRankIndex(tempCard.getRank());
			if (tempRank != firstRank)
				return false;
		}

		return true;
	}

}

class HandPanel extends JPanel
{

	public HandPanel(String name,JList hand, JButton stack, JButton deck, JButton lay, JButton layOnStack)
	{
		//model = hand.createSelectionModel();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		add(Box.createGlue());
		JLabel label = new JLabel(name);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);
		stack.setAlignmentX(Component.CENTER_ALIGNMENT);
//		add(Box.createGlue());
		add(stack);
		deck.setAlignmentX(Component.CENTER_ALIGNMENT);
//		add(Box.createGlue());
		add(deck);
		lay.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lay);
		layOnStack.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(layOnStack);
		add(Box.createGlue());
		add(hand);
		add(Box.createGlue());
	}

}
class SetPanel extends JPanel
{
	private Set data;
	JButton [] array = new JButton[4];
	boolean occupied = false;

	public SetPanel(int index)
	{
		super();
		data = new Set(Card.rank[index]);

		for(int i = 0; i < array.length; i++){
			array[i] = new JButton("   ");
			add(array[i]);
		}
	}

	// Checks if Panel is occupied
	public boolean isOccupied(){
		return occupied;

	}

	public void update(){
		occupied = true;
	}

}
