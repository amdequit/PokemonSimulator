import java.io.FileNotFoundException;
import java.util.Random;

class Main {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Welcome! What is your name? ");
		String name = CheckInput.getString();

		// Initializing all the necessary objects and variables needed for the trainer's
		// map.
		int mapCounter = 1;
    int levelCounter = 1;
    int currentMoney = 0;
		Map programMap = Map.getInstance();
		programMap.loadMap(mapCounter);
		char currentArea = 's';

		System.out.println("Hello, " + name + ", my name is Professor Oak. Welcome to the world of Pokemon.");
		System.out.println(
				"In this world, you can capture and battle with a wide variety of pocket monsters in multiple levels.\n");
		System.out.println("Before we begin, please select a starter pokemon.");
		System.out.println("1. Bulbasaur\n2. Charmander\n3. Squirtle");

		// Section of code that handles the starter Pokemon selection.
		int starterChoice = CheckInput.getIntRange(1, 3);
		Pokemon starterPokemon = new Fire("Charmander", 20, 20);
		switch (starterChoice) {
		case 1:
			starterPokemon = new Grass("Bulbasaur", 20, 20);
			break;
		case 2:
			break;
		case 3:
			starterPokemon = new Water("Squirtle", 20, 20);
			break;
		}

		// Initializing the trainer with their selected pokemon and give them $20 to buys
		// themselves something nice.
		Trainer playerTrainer = new Trainer(name, starterPokemon);
		playerTrainer.receiveMoney(20);
    playerTrainer.receivePotion();
    for (int i = 0; i<5;i++) playerTrainer.receivePokeball();

		System.out.println("\nExcellent choice. You're now ready to be the very best.");
		System.out.println("We'll drop you off on the first level. Have fun!\n");
		boolean stillPlaying = true;

		// stillPlaying is only set to false when the player voluntarily quits or their
		// HP is 0.
		while (stillPlaying) {
			System.out.println("\n" + playerTrainer);
			System.out.println(programMap.mapToString(playerTrainer.getLocation()));
			int choice = mainMenu();
			System.out.println();

			// Choice reflects the choice the player made based on the presented main menu.
			// See mainMenu() method.
			switch (choice) {
			case 1:
				currentArea = playerTrainer.goNorth();
				break;
			case 2:
				currentArea = playerTrainer.goSouth();
				break;
			case 3:
				currentArea = playerTrainer.goEast();
				break;
			case 4:
				currentArea = playerTrainer.goWest();
				break;
			case 5:
				stillPlaying = false;
				currentArea = 'z';
				System.out.println("See you next time!");
				break;
			default:
				System.out.println("Hmm, I don't quite think that's an option!");
				break;
			}

			switch (currentArea) {
			// Landed on the starting space on the map. Nothing happens.
			case 's':
				System.out.println("This is where the adventure began!");
				break;

			// Landed on the finish space on the map. The program will now move on to the
			// next level.
			case 'f':
				System.out.println("You walk down the path and notice a fearsome foe ahead...");
        System.out.println("You encountered a gym leader! Be prepared; their pokemon is stronger than normal!");
				currentMoney = playerTrainer.getMoney();
        Pokemon gymPokemon = PokemonGenerator.getInstance().generateRandomPokemon(levelCounter + 2);
        trainerAttack(playerTrainer, gymPokemon);
				if (playerTrainer.getMoney() > currentMoney){
          System.out.println("You won against the gym leader! Congratulations~");
          playerTrainer.buffAllPokemon();
          System.out.println("Beating the gym leader has caused all of your pokemon to be buffed!");
          System.out.println("Moving you to the next level... You will meet more powerful pokemon here!");
          mapCounter += 1;
          levelCounter += 1;
          if (mapCounter == 4)
            mapCounter = 1;
          programMap.loadMap(mapCounter);
        }
				else if (playerTrainer.getHp() == 0)
					stillPlaying = false;
        else{
          System.out.println("Looks like you weren't strong enough for the gym leader!");
          System.out.println("Come back once you are more prepared...");
        }        
				break;

			// Landed on a space on the map that has no special event. Nothing happens.
			case 'n':
				System.out.println("Wow look, nothing!");
				break;

			/**
			 * Landed on a space on the map with an item. The program will give the trainer
			 * a random item.
			 */
			case 'i':
				int chanceAI = (int) (Math.random() * 2);
				if (chanceAI == 0) {
					System.out.println("You found a potion!");
					playerTrainer.receivePotion();
				} else {
					System.out.println("You found a pokeball!");
					playerTrainer.receivePokeball();
				}
				programMap.removeCharAtLoc(playerTrainer.getLocation());
				break;

			// Landed on a space on the map with a wild pokemon. The battle sequence begins.
			case 'w':
				System.out.println("Something's rumbling in the grass...");
				currentMoney = playerTrainer.getMoney();
				trainerAttack(playerTrainer, PokemonGenerator.getInstance().generateRandomPokemon(levelCounter));
				if (playerTrainer.getMoney() > currentMoney)
					programMap.removeCharAtLoc(playerTrainer.getLocation());
				if (playerTrainer.getHp() == 0)
					stillPlaying = false;
				break;

			// Random person encounter. Encounter depends on the value of the
			// randomEncounter variable.
			case 'p':
				int randomEncounter = (int) (Math.random() * 6);
				/**
				 * We generate a random number here to decide what random encounter we will get
				 * with the Pokemon cast, or other Nintendo intellectual property.
				 */
				switch (randomEncounter) {

				case 0:
					System.out.println("Peony smiles and waves. He walks up to you and says,");
					System.out.println(
							"\"I would fight you, but it looks like there are no steel type pokemon around! Take this instead.\"");
					System.out.println("He gives you a pokeball!");
					playerTrainer.receivePokeball();
					break;

				case 1:
					System.out.println("You come across Misty; she looks at you with worry.");
					System.out.println(
							"\"You look down " + name + "... Have a jelly-filled donut! These are Brock's favorite.\"");
					System.out.println(
							"They look more like rice balls, but you take one anyways. The donut fills you to full health!");
					playerTrainer.heal();
					break;

				case 2:
					System.out.println(
							"You stumble upon a letter with a red, circle stamp with a cross on the bottom-left corner.");
					System.out.println(
							"You decided to pick it up, until a guy with a masquerade mask and black coat begins to shoot at you with his gun and takes the letter!");
          final int DAMAGE_TAKEN = 6;
					System.out.printf("You end up not getting the letter and taking %d damage...\n", DAMAGE_TAKEN);
					playerTrainer.takeDamage(DAMAGE_TAKEN);
					if (playerTrainer.getHp() == 0) {
						stillPlaying = false;
					}
					break;

				case 3:
					System.out.println(
							"You see some corporate men walking towards your location. You decide that it's none of your business, until one of them tackles you to the ground.");
					System.out.println(
							"One of them with a red and white suit exclaims, \"You have violated our intellectual property rights. Prepare to be smitten.\"");
					System.out.println("Wait, wha-");
					System.out.println("They knocked you out cold...");
          final int MONEY_STOLEN = 15;
					boolean nintendoStoleMoney = playerTrainer.spendMoney(MONEY_STOLEN);
					if (nintendoStoleMoney)
						System.out.printf("You wake up the next morning with %d monies missing!\n", MONEY_STOLEN);
					else
						System.out.println(
								"You wake up the next morning with nothing stolen from you! Looks like you got lucky.");
					break;

        case 4:
          System.out.println("You walked through the next area until you hear a sudden explosion.");
          System.out.println("You look up and see a red-hair and blue-hair person, and their Meowth being launched to the sky.");
          System.out.println("They shout, \"Looks like Team Rocket's blasting off agaaaaaaiiinn!\"");
          final int ROCKET_MONEY = 10;
          System.out.printf("While you're not sure what happened, they seem to have dropped %d monies!\n", ROCKET_MONEY);
          playerTrainer.receiveMoney(ROCKET_MONEY);
          System.out.println("You're sure that they won't miss it so you pick the money up. Lucky!");
          break;

        case 5:
          System.out.println("You come across a man with a long white beard.");
          System.out.println("\"It's dangerous to go alone! Take this.\"");
          System.out.println("Something about him sort of looks like Brock in a costume, but you took what he gave you anyways.");
          playerTrainer.receivePotion();
          System.out.println("You got a potion!");
          break;

				}

				programMap.removeCharAtLoc(playerTrainer.getLocation());
				break;

			// Landed on the space on the map that contains the city.
			case 'c':
				System.out.println("We reached the city! Where would you like to go?");
				System.out.println("1. Store \n2. Pokemon Hospital");
				int cityChoice = CheckInput.getIntRange(1, 2);
				switch (cityChoice) {
				case 1:
					store(playerTrainer);
					break;
				case 2:
					System.out.println("Hello! Welcome to the Pokemon Hospital.");
					System.out.println("Let's heal all of your pokemons...");
					for (int i = 0; i < playerTrainer.getNumPokemon(); i++) {
						playerTrainer.getPokemon(i).heal();
					}
					System.out.println("There we go, you're all set! Bye for now!");
					break;
				}
				break;

			/**
			 * Player tried to go out of bounds. Dialogue varies here--but they all make the
			 * user go back to their previous location.
			 */
			case 'x':
				int randomBounds = (int) (Math.random() * 3);
				switch (randomBounds) {

				case 0:
					System.out.println(
							"You ran and almost fell off a cliff and into the ocean! You walk back to the previous area.");
					System.out.println("Let's not go there...");
					break;

				case 1:
					System.out.println("You stumbled upon a legendary Mewtwo! Awesome!");
					System.out.println(
							"But wait... It looks like he's in an intense battle with some guy with a keyblade...");
					System.out.println(
							"The fight looks fierce; you decide to avoid it and head back to your previous location.");
					System.out.println("Let's not go there...");
					break;

				case 2:
					System.out.println(
							"You reached the shores and it turned cloudy all of a sudden, until you see a single ray of light miles away.");
					System.out.println("This feels like a movie scenario...");
					System.out.println("Confused, you decide to go back and the weather returns to normal.");
					System.out.println("Let's not go there...");
					break;

				}
				break;

			}

      if (playerTrainer.getHp() == 0) {
        System.out.println("Oh no, you lost all your health! Game over...");
        stillPlaying = false;
      }
		}
	}

	/**
	 * Display for the trainer's Main Menu Checks to make sure inputted integer is
	 * in the range of 1-5
	 * 
	 * @return returns the user input
	 */
	public static int mainMenu() {
		System.out.println("Main Menu:\n1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit");
		// Gets an input from user in the range 1-5
		int userIn = CheckInput.getIntRange(1, 5);
		return userIn;

	}
  
	/**
	 * Main logic and method behind the battle sequences inside the game. Primarily
	 * used for wild pokemon encounters.
	 * 
	 * @param t    pokemon trainer that is participating in battle (usually the
	 *             player's trainer)
	 * @param wild random, enemy pokemon that will be battled with
	 */
	public static void trainerAttack(Trainer t, Pokemon wild) {
		boolean isGym = (Map.getInstance().getCharAtLoc(t.getLocation()) == 'f') ? true : false;

    String encounterMenu = "What do you want to do?\n1. Fight\n2. Use Potion\n3. Throw Poke Ball\n4. Run Away";

		if(!isGym) System.out.println("A wild " + wild.getName() + " appeared.");
    else System.out.println("A gym trainer appears with a strong " +  wild.getName() + "!");

		// checks if the player has died from combat
		boolean isDefeated = false;
		// checks if the player has successfully made a move.
		boolean hasMoved = false;
		// check for when the player tries to run
		boolean wentPlaces = false;

		int numPokemon = 0;
    Pokemon battlePokemon = null;

		while (!isDefeated && !wentPlaces) {
			hasMoved = false;

			System.out.println(wild);
			System.out.println(encounterMenu);

			int choice = CheckInput.getIntRange(1, 4);
			hasMoved = (choice > 0) && (choice <= 3);

			switch (choice) {

			// Player decides to use a pokemon to fight.
			case 1:
				if (t.getNumPokemon() == 0) {
					System.out.println("\nYou have no pokemon!");
					break;
				}

				System.out.println("\nChoose a Pokemon:\n" + t.getPokemonList());
				numPokemon = CheckInput.getIntRange(1, t.getNumPokemon()) - 1;
        battlePokemon = t.getPokemon(numPokemon);

				if (battlePokemon.getHp() <= 0) {
					System.out.println("This pokemon has no energy! \n");
					break;
				}

				System.out.println(battlePokemon.getAttackTypeMenu());
				choice = CheckInput.getIntRange(1, battlePokemon.getNumAttackTypeMenuItems());
        System.out.println();
        System.out.println(battlePokemon.getAttackMenu(choice));
        System.out.println(battlePokemon.attack(wild, choice, CheckInput.getIntRange(1, battlePokemon.getNumAttackMenuItems(choice))));

        //25% chance a trainer's pokemon will debuff enemy pokemon
        double debuffChance = 25;
          Random rand = new Random();
          int r = rand.nextInt(100) + 1;
          if (r <= debuffChance) {
            System.out.print(battlePokemon.getName() + " has DEBUFFED " + wild.getName() + " into... ");
            wild = PokemonGenerator.getInstance().addRandomDebuff(wild);
            System.out.println(wild.getName() + "!");
          }
				System.out.println();
        break;

			// Player decides to use a potion.
			case 2:
				if (!t.hasPotion() || t.getNumPokemon() == 0) {
					System.out.println("\nYou have no potions!");
          hasMoved = false;
					break;
				}

				System.out.println("\nWhich pokemon do you want to heal?\n" + t.getPokemonList());

				int pokeNum = CheckInput.getIntRange(1, t.getNumPokemon());
				t.usePotion(pokeNum - 1);

				System.out.println(t.getPokemon(pokeNum - 1).getName() + " has been healed and buffed!");
				break;

			// Player decides to try and catch the wild pokemon.
			case 3:
				if (!t.hasPokeball() || isGym) {
					if(isGym) System.out.println("You can't catch a gym leader's pokemon!");
          else System.out.println("\nYou have no pokeballs!");
					hasMoved = false;
          break;
				}

				System.out.println("\nShake... Shake... Shake...");

				boolean caught = t.catchPokemon(wild);
				if (caught) {
					System.out.println("You caught the wild " + wild.getName() + "!");

          //Max 6 Pokemon: If # of pokemon > 6, player choose a pokemon to release into the wild
          if (t.getNumPokemon() == 7){
            System.out.println("Uh-oh.. You don't have enough room to keep " + wild.getName() + ".");
            System.out.println("Choose a pokemon to release in the wild (T_T): \n" + t.getPokemonList() + "\n");
            int release = CheckInput.getIntRange(1, 7);
            System.out.println(t.getPokemon(release - 1) + " was released. :(");
            t.removePokemon(release);
          }
          
					isDefeated = true;
				} else
					System.out.println("You failed to catch the wild pokemon!");
				break;

			// Player decides to run from the wild encounter.
			case 4:
        if(isGym){
          System.out.println("You can't run away from a gym leader!");
          hasMoved = false;
          break;
        }

				System.out.print("You try to make a run for it, and ");

				char runningChar = 'x';

				while (!wentPlaces) {
					int directionAI = (int) (Math.random() * 4);

					switch (directionAI) {

					case 0:
						runningChar = t.goNorth();
						if (runningChar == 'x')
							break;
						System.out.println("went north!");
						wentPlaces = true;
						break;

					case 1:
						runningChar = t.goSouth();
						if (runningChar == 'x')
							break;
						System.out.println("went south!");
						wentPlaces = true;
						break;

					case 2:
						runningChar = t.goEast();
						if (runningChar == 'x')
							break;
						System.out.println("went east!");
						wentPlaces = true;
						break;

					case 3:
						runningChar = t.goWest();
						if (runningChar == 'x')
							break;
						System.out.println("went west!");
						wentPlaces = true;
						break;

					default:
						System.out.println("couldn't... for some reason!");
						break;
					}
				}
				break;

			default:
				System.out.println("Please choose a valid option!");
				break;
			}

			// At this point in the code, the player has already used his turn and now the
			// enemy (if alive) will attack.
			// Scenario that the enemy pokemon's HP is 0. The trainer has won the battle.
			if (wild.getHp() == 0 || isDefeated) {
				System.out.println("\nYou defeated the wild " + wild.getName() + "!");
				isDefeated = true;
				int moneyAmount = (int) (wild.getMaxHp() / 2);
				t.receiveMoney(moneyAmount);
				System.out.printf("You have received %d monies.\n", moneyAmount);
			}

			/**
			 * Scenario where enemy pokemon will attack either the trainer (if current
			 * pokemon is dead or he has not yet chosen a pokemon) or his current pokemon.
			 */
			if (!isDefeated && hasMoved) {
				if (battlePokemon == null || battlePokemon.getHp() == 0) {
					int randomDamage = (int) (Math.random() * 9);
					System.out.printf("The wild " + wild.getName() + " lashes out at " + t.getName()
							+ " and scratches them for %d health! \n", randomDamage);
					t.takeDamage(randomDamage);
				} else {
					int battleAI = (int) (Math.random() * 2 + 1);
					if (battleAI == 1)
						System.out.println(wild.attack(battlePokemon, 1, (int) (Math.random() * wild.getNumAttackMenuItems(1) + 1)));
					if (battleAI == 2)
						System.out.println(wild.attack(battlePokemon, 2, (int) (Math.random() * wild.getNumAttackMenuItems(2) + 1)));

          //10% chance an enemy pokemon will debuff trainer's pokemon
          double debuffChance = 10;
            Random rand = new Random();
            int r = rand.nextInt(100) + 1;
            if (r <= debuffChance) {
              t.debuffPokemon(numPokemon);
              System.out.println("The " + wild.getName() + " has DEBUFFED your " + battlePokemon.getName() + "!");
            }
				}
        System.out.println();
			}

      for(int i = 0; i < t.getNumPokemon(); i++){
        if(t.getPokemon(i).getHp() != 0){
          break;
        }
        if(i == t.getNumPokemon() - 1){
          System.out.println("\nAll of your pokemon have no health!");
          System.out.println("The wild " + wild.getName() + " hits you for 5 health and flees temporarily!");
          isDefeated = true;
          t.takeDamage(5);
          System.out.println("You should go back to a city and heal your pokemon before fighting again!");
        }
      }

			// Scenario that an enemy pokemon has attacked the trainer to death. Game ends
			// if so.
			if (t.getHp() == 0) {
				isDefeated = true;
			}
		}
	}

	/**
	 * Displays menu items (potions, pokeballs) and allows players to buy them.
	 * @param t - trainer who enters the store, makes transaction
	 */
	public static void store(Trainer t) {
		int userIn;
		int amt;
		int potionCost = 5;
		int pokeballCost = 3;
		int cost;
		int maxPurchaseAmt;
		int cont; // user verifies purchase
		System.out.println("\nWelcome to the PokeMart! What do you need?");

		do {
			System.out.println("\n1. Potions: $5\n2. Poke Ball: $3\n3. Exit");
			System.out.print("Enter the menu item # or 3 to quit: ");
			userIn = CheckInput.getIntRange(1, 3);

			if (userIn != 3) {
				amt = 0; // reset transaction item count
				System.out.println("\nMoney: $" + t.getMoney());
				if (userIn == 1) {
					System.out.print("Potion? Certainly. How many would you like? ");
				} else {
					System.out.print("Pokeball? Certainly. How many would you like? ");
				}
				amt = CheckInput.getInt();

				if (userIn == 1) { // potion
					cost = amt * potionCost;
					maxPurchaseAmt = t.getMoney() / potionCost;
				} else { // pokeball
					cost = amt * pokeballCost;
					maxPurchaseAmt = t.getMoney() / pokeballCost;
				}

				while (amt > maxPurchaseAmt) {
					if (amt == 1) {
						// singular potion or pokeball
						if (userIn == 1) {
							System.out.println(
									"\nApologies! Looks like you don't have enough money for " + amt + " potion.");
						} else {
							System.out.println(
									"\nApologies! Looks like you don't have enough money for " + amt + " pokeball.");
						}
					} else {
						// plural (text) potions or pokeballs
						if (userIn == 1) {
							System.out.println(
									"\nApologies! Looks like you don't have enough money for " + amt + " potions.");
						} else {
							System.out.println(
									"\nApologies! Looks like you don't have enough money for " + amt + " pokeballs.");
						}
					}

					amt = 0; // reset transaction item count
					if (t.getMoney() != 0) {
						System.out.println("\nMoney: $" + t.getMoney());
						if (userIn == 1) {
							System.out.print("How many potions would you like? ");
							amt = CheckInput.getInt(); // get new amount (should be < maxPurchaseAmt)
							cost = amt * potionCost; // calculate new cost
						} else {
							System.out.print("How many pokeballs would you like? ");
							amt = CheckInput.getInt(); // get new amount (should be < maxPurchaseAmt)
							cost = amt * pokeballCost; // calculate new cost
						}

					} else {
						System.out.println(
								"Bestie! Your balance is $0. Perhaps battling some trainers to earn some money would be a good idea...");
					}
				} // (while (amt > maxPurchaseAmt))

				if (amt != 0) {
					// if amt is 0 (at this point, skip to end)
					System.out.println("\nFor " + amt + " that will be $" + cost + " please.");
					System.out.print("Are you sure you would like to purchase this? Press 1 to continue: ");
					cont = CheckInput.getInt();

					if (cont == 1) { // complete transaction
						if (userIn == 1) {
							// add potions to trainer (up potion count by x)
							for (int i = 0; i < amt; i++) {
								t.receivePotion();
							}
							// subtract money from their money
							t.spendMoney(cost);
						} else {
							// add pokeballs to trainer (up pokeball count by x)
							for (int i = 0; i < amt; i++) {
								t.receivePokeball();
							}
							// subtract money from their money
							t.spendMoney(cost);
						}
						System.out.println("\nHere you are!\nThank you!");
						if (userIn == 1) {
							System.out.println("\nYou put away the Potions in your bag.");
						} else {
							System.out.println("\nYou put away the Pokeballs in your bag.");
						}
					} // complete transaction

				}
			}
		} while (userIn != 3);
		System.out.println("Thank you! Please come again!");
	}// store method
}