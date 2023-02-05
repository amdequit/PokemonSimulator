/**
 * Class for grass type pokemon. Inherits from the Pokemon class.
 * @author Rex Tabora
 */
public class Grass extends Pokemon {
	
  /**
   * Create's a grass type pokemon.
   * @param n pokemon's name
   * @param h pokemon's current HP
   * @param m pokemon's max HP
   */
  public Grass(String n, int h, int m){
    super(n, h, m);
  }

  /**
   * Get's the list of attacks of the pokemon.
   * @param atkType what type of pokemon attack is being asked for
   *                (1 - basic, 2 - special)
   * @return        a list of all specific attacks of a pokemon
   */
  @Override
  String getAttackMenu(int atkType){
    if(atkType == 1) return super.getAttackMenu(atkType);
    if(atkType == 2) return "1. Vine Whip\n2. Razor Leaf \n3. Solar Beam";
    return "";
  }

  /**
   * Gets the number of attacks a pokemon has according to a type.
   * @param atkType number that represents the type of pokemon attack
   * @return        a count of how many unique attack moves can be used    
   */
  @Override
  int getNumAttackMenuItems(int atkType){
    if(atkType == 1) return super.getNumAttackMenuItems(atkType);
    if(atkType == 2) return 3;
    return 0;
  }

  /**
   * Returns an attack string that describes what the pokemon did
   * according to the type of the attack and the move performed.
   * @param atkType number that represents the type of pokemon attack
   * @param move    the attack move according to the atkType
   * @return        a phrase that signifies what certain attack move was used
   */
  @Override
  String getAttackString(int atkType, int move){
    if(atkType == 1) return super.getAttackString(atkType, move);
    if(atkType == 2){
      switch(move){
        case 1:
          return "WHIPS";
        case 2:
          return "CUTS";
        case 3:
          return "SHOOTS A BEAM AT";
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
  @Override
  int getAttackDamage(int atkType, int move){
    if(atkType == 1) return super.getAttackDamage(atkType, move);
    if(atkType == 2){
      switch(move){
        case 1:
          return (int) (Math.random() * 3 + 1);
        case 2:
          return (int) (Math.random() * 3 + 2);
        case 3:
          return (int) (Math.random() * 6);
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
  @Override
  double getAttackMultiplier(Pokemon p, int atkType){
    if(atkType == 1) return super.getAttackMultiplier(p, atkType);
    return battleTable[this.getType()][p.getType()];
  }
}