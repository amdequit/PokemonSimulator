
/**
 * Decorator class that increases a pokemon's HP by 1 - 2 points.
 *
 * @author  Rex Tabora
 */
public class HPUp extends PokemonDecorator{

  /**
   * The constructor takes the passed pokemon and increases it's health and adds "+HP"
   * to its name.
   */
  public HPUp(Pokemon p){
    super(p, " +HP", (int) (Math.random() * 2 + 1));
  }
  
}