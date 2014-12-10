import java.util.*;
/**
 * Class Player allows decks and hands to be created for specific players. 
 * It also keeps track of that players health. It also keeps track of if players have exhausted the cards 
 * they are allowed to play. 
 *
 * @author (Michael Arteaga, Joseph Carbone)
 * @version (Oct 24, 2014)
 */
public class Player{
	private String name;
	private int health = 5, removedCard=0;
	private Deck deck;
	private List<Card> currentHand;
	private boolean playedMonster,playedMagic;


	/**
	 * Constructor for objects of class Player
	 */
	public Player() { // default constructor
		name = "[default]";
		deck= new Deck(true);
		playedMonster=false;
		playedMagic=false;
		currentHand = new ArrayList<Card>();
	}
	public Player(String name) { //constructor
		this.name = name;
		deck= new Deck(true);
		playedMonster=false;
		playedMagic=false;
		currentHand = new ArrayList<Card>();
	}
	public String getName() {
		return name;
	}
	public int loseAPoint(){
		return health--;
	}
	public List<Card> getHand() {
		while(currentHand.size()<5)
			draw();
		return currentHand;
	}
	
	public boolean completedMove() {
		return(playedMonster&&playedMagic);
	}
	/** @return The list of words generated
	 * by the constructor.
	 */

	public Map<String,Card> getDeck() {						
		return deck.getDeck();

	}
	/**
	 * The player's health
	 * @return health
	 */
	public int getHealth() {
		return health;
	}
	public Card draw(){
		if(currentHand.size()<5){
		Card result=deck.pickCard();
		currentHand.add(result);
		return result;}
		return currentHand.get(4);
		
		}
		
	
	public Card remove(String name){
		int x=0;
		for(int i=0;i<5; i++){
			x=i;
			if(name.equals(currentHand.get(x).getName())){
				Card result= currentHand.get(x);
					currentHand.remove(x);
					if(result instanceof CreatureCard)
						playedMonster=true;
					if(result instanceof MagicCard)
						playedMagic=true;
					return result;
				}
			removedCard=x;
			}
		return null;
		}
	public int getRemovedCard(){
		return removedCard;
	}
	
	public void newTurn(){
		playedMonster=false;
		playedMagic=false;}
	
	public boolean playedMonster()
	{return playedMonster;}
	
	public boolean playedMagic()
	{return playedMagic;}
	
	public static void main(String[] args)
	{
		Player p = new Player();
		p.getDeck();
		System.out.println("THIS IS THE HAND " + p.getHand());
		System.out.println("THIS CARD WAS REMOVED "+ p.remove(p.currentHand.get(1).getName()));
		System.out.println("THIS IS NOW THE HAND" + p.getHand());
		System.out.println("THE NEW CARD IS "+ p.draw().getName());
		System.out.println("NOW THIS IS THE NEW HAND "+ p.getHand());	
		System.out.println("PlayedMonster: "+p.playedMonster()+"\n"+ "PlayedMagic: "+p.playedMagic());
		System.out.println("Completed Turn: "+p.completedMove());
	}

	
}
