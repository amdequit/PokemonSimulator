
/**
 * Decorator class that Increases a Pokemon's ATK by 1 - 2 points.
 *
 * @author  Daniel Moreno
 */
public class AttackUp extends PokemonDecorator{
  
  /**
   * The constructor takes the passed pokemon and Increases it's Damage and adds "+ATK"
   * to its name.
   */
  public AttackUp(Pokemon p){
    super(p, " +ATK", 0);
  }

  /**
   * Gets a pokemon's attack bonus according to the buffs it has.
   * @param atkType user's input that determines the Pokemon's move
   * @return additional damage to be added on top of base damage
   */
  public int getAttackBonus(int type){
    return super.getAttackBonus(type) + (int) (Math.random() * 2 + 1);
  }
  
}