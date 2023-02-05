
/**
 * A pokemon abstract class. Used to implement subclasses for the different
 * types of pokemon. Inherits from the Entity class.
 * 
 * @author Rex Tabora
 * @version 2.0
 * @since 11/27/2021
 */
public abstract class Pokemon extends Entity {
	public static final double[][] battleTable = { { 1, .5, 2 }, { 2, 1, .5 }, { .5, 2, 1 } };

	/**
	 * All pokemon uses the constructor of the Entity superclass to assign
	 * themselves a name and a random maxHP value between 20 - 28.
	 * @param n the name of the pokemon
   * @param h the current hp of the pokemon
   * @param m max hp of the pokemon
	 */
	public Pokemon(String n, int h, int m) {
		super(n , h, m);
	}

  /**
   * Gets the list of all available attack types for a pokemon.
   * @return  A list of the type of attacks a pokemon can do.
   */
  String getAttackTypeMenu(){
    String Attacks = "1. Basic Attack\n2. Special Attack";
		return Attacks;
  }
  
  /**
   * Gets the number of all unique attack types.
   * @return  a count for all unique attack types
   */
  int getNumAttackTypeMenuItems(){
    return 2;
  }

  /**
   * Gets the list of specific attacks that a pokemon can do
   * according to a given attack type
   * @param atkType number that represents an attack type
   * @return        a list of attacks under one attack type
   */
  String getAttackMenu(int atkType){
    String basicMenu = "";
    if(atkType == 1) basicMenu = "1. Slam\n2. Tackle\n3. Punch";
		return basicMenu;
  }

  /**
   * Gets the number of all unique attack moves according to a given type.
   * @param atkType number that represents an attack type
   * @return        a count for all unique attack moves under the atkType
   */
  int getNumAttackMenuItems(int atkType){
    if(atkType == 1) return 3;
    return 0;
  }
  
  /**
   * Calculates the damage of a certain move and inflicts it on a pokemon.
   * @param p       enemy pokemon
   * @param atkType number that represents an attack type
   * @param move    the attack move according to the atkType
   * @returns       a phrase that describes which pokemon attacked
   *                who and the amount of damage
   */
  String attack(Pokemon p, int atkType, int move){
    int calculatedDamage = (int) (this.getAttackDamage(atkType, move) * this.getAttackMultiplier(p, atkType) + this.getAttackBonus(atkType));
    if(calculatedDamage < 0) calculatedDamage = 0;
    p.takeDamage(calculatedDamage);
    String attackString = String.format(this.getName() + " " + this.getAttackString(atkType, move) + " " + p.getName() + " and deals %d damage.", calculatedDamage);
    return attackString;
  }
  
  /**
   * Returns an attack string that describes what the pokemon did
   * according to the type of the attack and the move performed.
   * @param atkType number that represents the type of pokemon attack
   * @param move    the attack move according to the atkType
   * @return        a phrase that signifies what certain attack move was used
   */
  String getAttackString(int atkType, int move){
    if(atkType == 1){
      switch(move){
        case 1:
          return "SLAMS";
        case 2:
          return "TACKLES";
        case 3:
          return "PUNCHES";
      }
    }
    return "ATTACKS";
  }

  /**
   * Gets the attack damage of an attack move.
   * @param atkType number that represents the type of pokemon attack
   * @param move    the attack move according to the atkType
   * @return        a pokemon's damage for a specific attack move
   */
  int getAttackDamage(int atkType, int move){
    if(atkType == 1){
      switch(move){
        case 1:
          return (int) (Math.random() * 6);
        case 2:
          return (int) (Math.random() * 2 + 2);
        case 3:
          return (int) (Math.random() * 4 + 1);
      }
    }
    return 0;
  }

  /**
   * Gets the number to be multiplied to the attack damage
   * according to pokemon elemental types.
   * @param p enemy pokemon
   * @param atkType number that represents the type of pokemon attack
   * @return        a multiplier number
   */
  double getAttackMultiplier(Pokemon p, int atkType){
    return 1;
  }

  /**
   * Gets a pokemon's attack bonus according to the buffs it has.
   * @param atkType number that represents the type of pokemon attack
   * @return        additional damage to be added on top of base damage
   */
  int getAttackBonus(int atkType){
    return 0;
  }

  /**
   * Gets a pokemon's elemental type.
   * @return a number that represents a pokemon's elemental type.
   */
  int getType(){
		int typeValue = 0;

		if (this instanceof Fire)
			typeValue = 0;
		else if (this instanceof Water)
			typeValue = 1;
		else if (this instanceof Grass)
			typeValue = 2;

		return typeValue;
  }

}