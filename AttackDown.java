
/**
 * Decorator class that Decreases a Pokemon's ATK by 1 - 2 points.
 *
 * @author  Daniel Moreno
 */
public class AttackDown extends PokemonDecorator{
  
  /**
   * The constructor takes the passed pokemon and Decreases it's Damage and adds "-ATK"
   * to its name.
   */
  public AttackDown(Pokemon p){
    super(p, " -ATK", 0);
  }

  /**
   * Gets a pokemon's attack bonus according to the buffs it has.
   * @param atkType user's input that determines the Pokemon's move
   * @return damage to be subtracted from the Pokemon's base damage
   */
  public int getAttackBonus(int type){
    return super.getAttackBonus(type) - 1;
  }
  
}