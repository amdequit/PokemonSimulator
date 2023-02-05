/**
 * Decorator for adding buffs and debuffs to pokemon. Has base pokémon types of Fire, Water, or 
 * Grass. Base types will then be decorated with different types of 
 * buffs and debuffs to update the pokémon’s stats. Inherits the Pokemon class
 * @author Angeline Dequit
 */
public abstract class PokemonDecorator extends Pokemon{
  
  private Pokemon pokemon;

  /**
  * initializes pokemon decorator
  * @param n name of pokemon being decorated
  * @param extraName name of the buff or debuff that is being attached to the pokemon's name
  * @param extraHp the amount of hp that is added onto the hp pokemon who is being decorated
  */
  public PokemonDecorator(Pokemon p, String extraName, int extraHp){ 
    //super(p, this.pokemon.getName() + extraName, this.pokemon.getHp() + extraHp);
    super(p.getName() + extraName, p.getHp() + extraHp, p.getMaxHp() + extraHp);
    this.pokemon = p;
    //String name = p.getName();
    //int hp = p.getHp();
  }

  /**
  * returns a description (string) of attack moves menu 
  * @param atkType type of attack: 1 - basic attack; 2 - special attack
  * @return basicMenu description of basic attack move
  */
  @Override
  public String getAttackMenu(int atkType){ 
    String basicMenu = pokemon.getAttackMenu(atkType);
    return basicMenu;
  }

  /**
  * returns the number of attack menu items
  * @param atkType type of attack: 1 - basic attack; 2 - special attack
  * @return numAttackMenuItems number of items in the attack menu
  */
  @Override
  public int getNumAttackMenuItems(int atkType){ 
    int numAttackMenuItems = pokemon.getNumAttackMenuItems(atkType);
    return numAttackMenuItems;
  }

  /**
  * returns the attack description (string) for a specificed attack move
  * @param atkType type of attack: 1 - basic attack; 2 - special attack
  * @param move the move (item in the attack menu) that is being described
  * @return atkStr description of specificed attack move
  */
  @Override
  public String getAttackString(int atkType, int move){ 
    String atkStr = pokemon.getAttackString(atkType, move);
    return atkStr;
  }

  /**
  * returns the damage (integer) for a specificed attack move
  * @param atkType type of attack: 1 - basic attack; 2 - special attack
  * @param move the move (item in the attack menu) that is being used
  * @return atkDmg damage for a specificed attack move
  */
  @Override
  public int getAttackDamage(int atkType, int move){ 
    int atkDmg = pokemon.getAttackDamage(atkType, move);
    return atkDmg;
  }

  /**
  * returns the attack multiplier (double) for a specificed attack move
  * @param p the pokemon from which the attack multiplier is derived from
  * @param atkType type of attack: 1 - basic attack; 2 - special attack
  * @return mult attack multiplier for a specificed attack move
  */
  @Override
  public double getAttackMultiplier(Pokemon p, int atkType){
    double mult = pokemon.getAttackMultiplier(p, atkType);
    return mult;
  }

  /**
  * returns the attack bonus (integer) that will be added to the calculated damage
  * @param atkType type of attack: 1 - basic attack; 2 - special attack
  * @return bonus attack bonus that will be added to the calculated damage
  */
  @Override
  public int getAttackBonus(int atkType){ 
    int bonus = pokemon.getAttackBonus(atkType);
    return bonus;
  }
  
  /**
   * returns pokemon's elemental type.
   * @return a number that represents a pokemon's elemental type.
   */
  @Override
  public int getType(){
    return pokemon.getType();
  }

}