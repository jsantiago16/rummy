# Project 2: Rummy Card Game

Javier Santiago  
University of Puerto Rico, Río Piedras  
Department of Computer Science  
CCOM-4029



# Project Description


 In this project, we implement a game of the Rummy card game in Java. In particular, this game will consist of two interactive, human players, which will use a Java GUI as a model for a table in a card game. This table will contain the hands of both players, and any sets they have laid down. Per Rummy rules, the game ends either when a player has laid out of its cards, or when the deck runs out of cards. If the deck runs out cards, then each hand will get evaluated by rank value. 



# Structure of the Game


Upon starting the game, the fist turn is for player 1 (left side). At the beginning of each turn, the player has to add a card to its hand. However, the player may choose to add from the stack pile (a known, face up card) or from the deck (an unknown, facedown card). 
After adding a card to its hand, the player looks in its hand to find a set (three cards or four cards of the same rank) or to complete a set, by adding the final card. 
After the player lays all the cards it can, then the player must discard a card from its hand onto the stack. After this, the turn switches to the opponent.
The goal is to have the minimum amount of rank value in your hand, that is, the sum of the ranks of all the cards left in your hand. Of course, if you do not have any cards left, you win. 



# Note on the GUI 

The buttons in the GUI for the table will not respond if you are trying to do an action that is not available for your player at the moment. For example, if you just discarded a card from your hand, you will not be able to press any of the buttons corresponding to your player, since it is your opponents turn to play.



# Added functionality 

- Players may lay cards that fit in sets already in the table.

- The game begins with the first card being on the stack. 

- A class called SortedListModel was added so that the cards in the JList appear sorted. 


# Running the program 

Having all the files in the proj2 directory, compile: 

javac proj2/Proj2.java 

Then, run the program

java proj2/Proj2

# References 


http://www.java2s.com/Tutorial/Java/0240__Swing/SortedListModelsortableJList.htm
Here, I found how to construct the JList such that the cards appear in sorted order. Once we have this, the other thing that needed to change is the compareTo method for cards, so that no two cards result being equal. 

https://docs.oracle.com/en/java/
The java documentation was extremely helpful. I include the general documentation because I searched here for so many things through the project, that I do not remember each and every one of the specific pages used. 

# Collaboration 

I did not collaborate with anyone specifically. However, I did discuss the project at various times with the students in my group for the different class-works. These discussions were mainly centered on how to attack the problem, and specific parts someone might have stuck in. 


