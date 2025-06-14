// These imports give us access to List and built-in shuffling
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// --- Main application class ---
// This is where the game is run
public class App {
    public static void main(String[] args) {
        System.out.println("Starting WAR card game...\n");

        // Step a: Create a deck and two players
        Deck deck = new Deck();
        deck.shuffle();

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        // Step b: Deal all 52 cards, alternating between players
        for (int i = 0; i < 52; i++) {
            if (i % 2 == 0) {
                player1.draw(deck); // even index: Player 1 draws
            } else {
                player2.draw(deck); // odd index: Player 2 draws
            }
        }

        // Step c: Play 26 rounds of flipping cards
        for (int round = 1; round <= 26; round++) {
            System.out.println("Round " + round + ":");

            Card p1Card = player1.flip();
            Card p2Card = player2.flip();

            System.out.print("Player 1 plays: ");
            p1Card.describe();

            System.out.print("Player 2 plays: ");
            p2Card.describe();

            // Compare values and assign point
            if (p1Card.getValue() > p2Card.getValue()) {
                player1.incrementScore();
                System.out.println("→ Player 1 wins the round!\n");
            } else if (p1Card.getValue() < p2Card.getValue()) {
                player2.incrementScore();
                System.out.println("→ Player 2 wins the round!\n");
            } else {
                System.out.println("→ It's a tie! No points awarded.\n");
            }
        }

        // Step d + e: Show final scores and winner
        System.out.println("=== Final Score ===");
        System.out.println("Player 1: " + player1.getScore());
        System.out.println("Player 2: " + player2.getScore());

        if (player1.getScore() > player2.getScore()) {
            System.out.println("🏆 Player 1 wins the game!");
        } else if (player1.getScore() < player2.getScore()) {
            System.out.println("🏆 Player 2 wins the game!");
        } else {
            System.out.println("🤝 It's a draw!");
        }
    }
}


// --- Card class ---
// This class represents a single card (like "Ace of Spades")
class Card {
    private int value;       // The value of the card (2–14)
    private String name;     // The name of the card (e.g., "Queen of Hearts")

    // Constructor: creates a card with given value and name
    public Card(int value, String name) {
        this.value = value;
        this.name = name;
    }

    // Returns the numeric value of the card
    public int getValue() {
        return value;
    }

    // Sets the value of the card
    public void setValue(int value) {
        this.value = value;
    }

    // Returns the name of the card
    public String getName() {
        return name;
    }

    // Sets the name of the card
    public void setName(String name) {
        this.name = name;
    }

    // Prints out card name and value (e.g., "Ace of Spades (Value: 14)")
    public void describe() {
        System.out.println(name + " (Value: " + value + ")");
    }
}

// --- Deck class ---
// This class represents a full 52-card deck
class Deck {
    private List<Card> cards = new ArrayList<>();  // List of all cards in the deck

    // Constructor: builds the full deck of 52 cards
    public Deck() {
        // All four suits
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        // All 13 card names
        String[] names = {
            "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"
        };

        // Create one card for each suit and name
        for (String suit : suits) {
            for (int i = 0; i < names.length; i++) {
                int value = i + 2; // card values: 2 through 14
                String cardName = names[i] + " of " + suit;
                cards.add(new Card(value, cardName));
            }
        }
    }

    // Shuffles the deck randomly
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Removes and returns the top card of the deck
    public Card draw() {
        return cards.remove(0);
    }
}

// --- Player class ---
// This class represents a player in the game
class Player {
    private List<Card> hand = new ArrayList<>();  // Cards in the player's hand
    private int score = 0;                        // Player's score (starts at 0)
    private String name;                          // Player's name

    // Constructor: creates a player with a given name
    public Player(String name) {
        this.name = name;
    }

    // Describes the player's hand (used for debugging or displaying cards)
    public void describe() {
        System.out.println(name + "'s hand:");
        for (Card card : hand) {
            card.describe();
        }
    }

    // Removes and returns the top card in the player's hand
    public Card flip() {
        return hand.remove(0);
    }

    // Draws a card from the deck and adds it to the player's hand
    public void draw(Deck deck) {
        Card drawnCard = deck.draw();
        hand.add(drawnCard);
    }

    // Increases the player's score by 1 point
    public void incrementScore() {
        score++;
    }

    // Returns the current score
    public int getScore() {
        return score;
    }

    // Returns the player's name
    public String getName() {
        return name;
    }
}
