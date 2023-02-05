import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * Trainer class that extends from the Entity class.
 * @author Daniel Moreno
 */
public class Trainer extends Entity {
	private int money;
	private int potions;
	private int pokeballs;
	private Point loc;
	private ArrayList<Pokemon> pokemon;

	/**
	 * Creates a Trainer object from the Entity Class
	 * @param n Name of the Trainer
	 * @param p Trainer's starter Pokemon
	 */
	// I would set loc to m.findStart();
	public Trainer(String n, Pokemon p) {
		super(n, 25, 25);
		pokemon = new ArrayList<Pokemon>();
		pokemon.add(p);
		loc = Map.getInstance().findStart();

	}

	/**
	 * Checks how much money the trainer has
	 * @return returns the amount of money as an int
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * Subtracts the amount of money the trainer has, used for purchases
	 * @param amt the amount of money that an item costs
	 * @return returns a boolean value to determine of the purchase was successful
	 */
	public boolean spendMoney(int amt) {
		if (money >= amt) {
			money = money - amt;
			return true;
		}
		return false;

	}

	/**
	 * Trainer recieves money which accumulates as a total
	 */
	public void receiveMoney(int amt) {
		money += amt;
	}

	/**
	 * CHecks if the trainer has potions in inventory
	 * @return returns a boolean value used to determine if a potion can be used
	 */
	public boolean hasPotion() {
		if (potions > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Adds a potion to the trainer's inventory
	 */
	public void receivePotion() {
		potions += 1;

	}

	/**
	 * If the trainer has a potion, they can heal a Pokemon of their choice to full
	 * health. Also grants a random buff.
	 * @param pokeIndex the index of the Pokemon the trainer wishes to heal
	 */
	public void usePotion(int pokeIndex) {
		int randNum = (int) (Math.random() * 2 + 1);
    Pokemon newPoke = pokemon.get(pokeIndex);
    if (this.hasPotion()) {
			pokemon.get(pokeIndex).heal();
      if(randNum == 1){
        newPoke = new HPUp(pokemon.get(pokeIndex));
      }
      if(randNum == 2){
        newPoke = new AttackUp(pokemon.get(pokeIndex));
      }
      pokemon.set(pokeIndex, newPoke);
			potions -= 1;
		}
	}

	/**
	 * Checks to see if the trainer has Pokeballs in their inventory
	 * @return returns a boolean that determines if the trainer has a Pokeball
	 */
	public boolean hasPokeball() {
		if (pokeballs > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Adds a Pokeball to the trainer's inventory
	 */
	public void receivePokeball() {
		pokeballs += 1;

	}

	/**
	 * Determines whether a trainer catches a wild Pokemon that they are currently
	 * battling
	 * @param p the wild Pokemon that will or will not be caught
	 * @return returns a boolean value that determines whether the Pokemon was
	 *         caught successfully
	 */
	public boolean catchPokemon(Pokemon p) {
    if(pokeballs == 0){
      return false;
    }
		float catchChance = (((float) p.getMaxHp() - p.getHp()) / p.getMaxHp()) * 100;
		float playerChance = (int) (Math.random() * 101);
		pokeballs--;
    if (playerChance <= catchChance) {
			pokemon.add(p);
			return true;
		} else
			return false;
	}

	/**
	 * Used to check the trainer's current location
	 * 
	 * @return returns the trainer's current location
	 */

	public Point getLocation() {
		return loc;

	}

	/**
	 * Checks to see if the direction the trainer chooses is valid
	 * @return returns a character that determines whether the trainer can move in
	 *         that direction (North)
	 */
	public char goNorth() {
    Map map = Map.getInstance();
		Point tempPoint = new Point((int) loc.getX() - 1, (int) loc.getY());
		char temp = map.getCharAtLoc(tempPoint);

		if (temp == 'x') {
			return 'x';
		}

		loc = tempPoint;
		map.reveal(loc);
		return temp;

	}

	/**
	 * Checks to see if the direction the trainer chooses is valid
	 * @return returns a character that determines whether the trainer can move in
	 *         that direction (South)
	 */
	public char goSouth() {
    Map map = Map.getInstance();
		Point tempPoint = new Point((int) loc.getX() + 1, (int) loc.getY());
		char temp = map.getCharAtLoc(tempPoint);

		if (temp == 'x') {
			return 'x';
		}

		loc = tempPoint;
		map.reveal(loc);
		return temp;

	}

	/**
	 * Checks to see if the direction the trainer chooses is valid
	 * @return returns a character that determines whether the trainer can move in
	 *         that direction (East)
	 */
	public char goEast() {
    Map map = Map.getInstance();
		Point tempPoint = new Point((int) loc.getX(), (int) loc.getY() + 1);
		char temp = map.getCharAtLoc(tempPoint);

		if (temp == 'x') {
			return 'x';
		}

		loc = tempPoint;
		map.reveal(loc);
		return temp;

	}

	/**
	 * Checks to see if the direction the trainer chooses is valid
	 * @return returns a character that determines whether the trainer can move in
	 *         that direction (West)
	 */
	public char goWest() {
    Map map = Map.getInstance();
		Point tempPoint = new Point((int) loc.getX(), (int) loc.getY() - 1);
		char temp = map.getCharAtLoc(tempPoint);

		if (temp == 'x') {
			return 'x';
		}

		loc = tempPoint;
		map.reveal(loc);
		return temp;

	}

	/**
	 * Checks to see the size of the Pokemon array ArrayList
	 * @return returns the size of the Pokemon array list
	 */
	public int getNumPokemon() {
		return pokemon.size();
	}

	/**
	 * Heals all Pokemon that the trainer contains used when the Trainer goes into
	 * the PokeHospital
	 */
	public void healAllPokemon() {
		for (int i = 0; i < pokemon.size(); i++) {
			pokemon.get(i).heal();
		}

	}

  /**
   * Gives all trainer's owned pokemon a random buff.
   */
  void buffAllPokemon(){
    Random rand = new Random();
    int randNum = 0;
    Pokemon modPokemon = null;
		for(int i = 0; i < pokemon.size(); i++) {
      randNum = rand.nextInt(2) + 1;
      if(randNum == 1) modPokemon = new AttackUp(pokemon.get(i));
      else if(randNum == 2) modPokemon = new HPUp(pokemon.get(i));
      pokemon.set(i, modPokemon);
    }
  }

  /**
   * Gives a random debuff to one pokemon.
   * @param index selected pokemon's index in the owned pokemon arraylist
   */
  void debuffPokemon(int index){
    Random rand = new Random();
    int randNum = rand.nextInt(2) + 1;
    Pokemon newPoke = null;

    if(randNum == 1){
      newPoke = new AttackDown(pokemon.get(index));
    }
    else if(randNum == 2){
      newPoke = new HPDown(pokemon.get(index));
    }
    pokemon.set(index, newPoke);
  }

	/**
	 * Gets the Pokemon that the Trainer chooses used when battling and healing
	 * @param index Trainer inputs the index of the Pokemon from the Pokemon array
	 *               list
	 */
	public Pokemon getPokemon(int index) {
		return pokemon.get(index);

	}

	/**
	 * Returns all the Pokemon that the Trainer currently has
	 * @return returns all the Pokemon along with their current health as a string
	 */
	public String getPokemonList() {
		String text = "";
		for (int i = 0; i < pokemon.size(); i++) {
			text += Integer.toString(i + 1) + ". " + pokemon.get(i) + "\n";
		}

		return text;
	}

  /**
   * Removes a pokemon from the trainer's inventory.
   * @param index index of the selected pokemon to remove
   */
  Pokemon removePokemon(int index){
    Pokemon removedPokemon = pokemon.remove(index);
    return removedPokemon;
  }

	/**
	 * Overrides the toString method in order to print out all the items and Pokemon
	 * the trainer currently has
	 * @return returns everything the trainer currently contains as a string
	 */
	@Override
	public String toString() {
		String description = this.getName() + " HP: " + super.getHp() + "/" + this.getMaxHp() + "\nMoney: $"
				+ this.getMoney() + "\nPotions: " + this.potions + "\nPoke Balls: " + this.pokeballs + "\nPokemon"
				+ "\n------\n" + this.getPokemonList() + "\nMap:";

		return description;

	}

}