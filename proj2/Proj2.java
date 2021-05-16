package proj2;

/**
* File: Proj2.java
* Project2
* @author Javier Santiago
* Date 5/16/21
* Course: CCOM4029

*/

//package proj2;

public class Proj2 {

	Player player1;
	Player player2;
	Table table;
	boolean gameOver;

	public Proj2(){
		player1 = new Player(1);
		player2 = new Player(2);
		table = new Table(player1, player2);
		gameOver = false;
	}

	public void gameSleep(int seconds){
		// Sleep for one second
		try{
			Thread.sleep(1000 * seconds);
		}

		catch (InterruptedException e) {
			System.out.println(e);
		}
	}


	public void playRummy(){

		int valueHand1;
		int valueHand2;
		int winner;

		table.setVisible(true);
		player1.setTurn(true);

		player1.displayHand(true);
		player2.displayHand(true);



		while(!gameOver){

			// If it is player one's turn
			if (player1.getTurn() && !player2.isHandEmpty()){

				while(player1.getTurn())
					gameSleep(2);

				if (player1.isHandEmpty())
					gameOver = true;

				player1.displayStatus();


			}

			else{

				while(player2.getTurn() && !player2.isHandEmpty())
					gameSleep(2);

				if (player2.isHandEmpty())
					gameOver = true;

				player2.displayStatus();

			}


			if (table.isDeckEmpty())
				gameOver = true;

		}

		valueHand1 = player1.getHandEval();
		valueHand2 = player2.getHandEval();

		System.out.println("Points: " + valueHand1 + " to " + valueHand2);

		if (valueHand1 < valueHand2)
			System.out.println("Player 1 Wins!");
		else if (valueHand2 < valueHand1)
			System.out.println("Player 2 Wins!");
		else
			System.out.println("Its a tie!");

		table.setVisible(false);
		table.dispose();



	}

	public static void main(String args[]){


		Proj2 p= new Proj2();
		p.playRummy();
	}



}
