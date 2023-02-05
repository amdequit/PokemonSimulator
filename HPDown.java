
/**
 * Decorator class that decreases a pokemon's HP by 1 - 2 points.
 *
 * @author  Rex Tabora
 */
public class HPDown extends PokemonDecorator{
  
  /**
   * The constructor takes the passed pokemon and decreases it's health and adds "-HP"
   * to its name.
   */
  public HPDown(Pokemon p){
    super(p, " -HP", -1);
  }
  
}