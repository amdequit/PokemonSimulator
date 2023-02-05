import java.util.Random;
/**
 * Water class for water type Pokemon
 * @author Daniel Moreno
 */
public class Water extends Pokemon{

    /**
   * Create's a Water type pokemon.
   * @param n pokemon's name
   * @param h pokemon's current HP
   * @param m pokemon's max HP
   */
  public Water(String n, int h, int m){
    super(n,h,m);
  }

    /**
   * Displays a list of the moves the Pokemon can perform
   * @param atkType user's input that determines normal or water attacks
   * @return returns the list of moves the Pokemon can perform:
   *if atkType = 1, normal attacks displayed
   *if atkType = 2, water attacks displayed
   */
  @Override
  public String getAttackMenu(int atkType){
  if (atkType == 1){
    return super.getAttackMenu(atkType);
    }
  if(atkType == 2){
    String specialAtkMenu = "1. Water Gun\n2. Bubble Beam \n3. Water Fall";
    return specialAtkMenu;
  }
  return null;
  }

  /**
   * Returns the amount of menu items the Pokemon has depending on atkType
   * @param atkType user's input that determines the number of menu items that are available to the user
   * @return returns the amount of moves the Pokemon can perform
   */
  @Override
  public int getNumAttackMenuItems(int atkType){
    if(atkType == 1){
       return super.getNumAttackMenuItems(atkType);
    }
    if(atkType == 2){
      return 3;
    }
    return 0;
  }
  
  /**
   * Returns text depending on what move was used
   * @param atkType user's input what attack was used, where atkType = 1 is a normal attack and atkType = 2 is a water attack 
   * @param move the move that was determined by the atkType
   * @return returns text depending what move was used; defaults to "Attacks"
   */
  @Override
  public String getAttackString(int atkType, int move){
  if(atkType == 1){
     return super.getAttackString(atkType, move);
  }

  if(atkType == 2){
    
    switch(move){
      case 1:
        return "SOAKS";
      case 2:
        return "OBLITERATES";
      case 3:
        return "DRENCHES";
      }
    }
    return "ATTACKS";
  }
  
  /**
   * Gets the damage based on the move.
   * @param atkType atkType user's input what attack was used, where atkType = 1 is a normal attack and atkType = 2 is a water attack 
   * @param move the move that was determined by the atkType
   * @return a pokemon's damage for a given attack move
   */
  @Override
  public int getAttackDamage(int atkType, int move){

    Random r = new Random();
    
    if(atkType == 1){
      return super.getAttackDamage(atkType, move);
    }

    if(atkType == 2){
      switch(move){ 
        case 1:
            return (int) (r.nextInt(4) + 2);

        case 2:
            return (int) (r.nextInt(3) + 1);
    

        case 3:
            return (int) (r.nextInt(4) + 1);
      }
    }
    return 0;
  }

  /**
   * Gets the multiplier based on the Pokemon Battle Table where 
   * certain elemental attacks deal different damage based on the 
   * element of the enemy Pokemon
   * @param p enemy pokemon
   * @param atkType atkType user's input what attack was used, where atkType = 1 is a normal attack and atkType = 2 is a water attack 
   * @return damage multiplier is returned
   */
   @Override
  public double getAttackMultiplier(Pokemon p, int atkType){
    
    if(atkType == 1){
      return super.getAttackMultiplier(p, atkType);
    }
    
    return battleTable[this.getType()][p.getType()];
  }

}