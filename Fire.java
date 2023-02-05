import java.util.Random;
/**
 * Class for fire type pokemon. Inherits Pokemon class.
 * @author Angeline Dequit
 */
public class Fire extends Pokemon{

  /**
  * initializes fire type pokemon
  * @param n name of pokemon
  * @param h current hp of pokemon
  * @param m maximum hp of pokemon
  */
  public Fire(String n, int h, int m){ 
    super(n, h, m);
  }

  /**
  * returns the special attack moves menu 
  * @param atkType type of attack: 1 - basic attack; 2 - special attack
  * @return specialAtkMenu list of special attack moves 
  */
  @Override
  public String getAttackMenu(int atkType){ 
    
    if (atkType == 1){
      return super.getAttackMenu(atkType);
    } else if (atkType == 2){
      String specialAtkMenu = "1. Ember\n2. Fire Blast \n3. Fire Punch";
      return specialAtkMenu;
    }
    return null;
    
  }

  /**
  * returns the number of attack menu items
  * @param atkType type of attack: 1 - basic attack; 2 - special attack
  * @return numAttackMenuItems number of items in attack menu
  */
  @Override
  public int getNumAttackMenuItems(int atkType){ 
    if (atkType == 1){
      return super.getNumAttackMenuItems(atkType);
    } else if (atkType == 2){
      int numAttackMenuItems = 3;
      return numAttackMenuItems;
    }
    return 0; //should i include this?
  }

  /**
  * returns the attack description (string) for a specificed attack move
  * @param atkType type of attack: 1 - basic attack; 2 - special attack
  * @param move the move (item in the attack menu) that is being described
  * @return string description of the specified move
  */
  @Override
  public String getAttackString(int atkType, int move){ 
    String string = "";
    if ( atkType == 1 ){
      return super.getAttackString(atkType, move);
    } else if (atkType == 2){
      switch (move){
        case 1:
          string = " is encased in EMBERS by ";
          break;
        case 2:
          string = " is scorched by ";
          break;
        case 3:
          string = " is punched by ";
          break;
      }
    }
    return string;
  }

  /**
  * returns the damage (integer) for a specificed attack move
  * @param atkType type of attack: 1 - basic attack; 2 - special attack
  * @param move the move (item in the attack menu) that is being used
  * @return dmg damage of the specified move 
  */
  @Override
  public int getAttackDamage(int atkType, int move){
    int dmg = 0;
		Random generator = new Random();

    if (atkType == 1){
      return super.getAttackDamage(atkType, move);
    } else if (atkType == 2){
        switch (move){
          case 1: //ember
            // calculate the damage
            dmg = (int) (generator.nextInt(3));
            // return dmg value
            return dmg;

          case 2: //fire blast
            dmg = (int) (generator.nextInt(4) + 1);
            return dmg;

          case 3: //fire punch
            dmg = (int) (generator.nextInt(3) + 1);
            return dmg;
        }
      }
      return dmg;
  }

  /**
  * returns the attack multiplier (double) for a specificed attack move
  * @param p the pokemon from which the attack multiplier is derived from
  * @param atkType type of attack: 1 - basic attack; 2 - special attack
  * @return mult multiplier of the attack move
  */
  @Override
  public double getAttackMultiplier(Pokemon p, int atkType){
    double mult = 0; 
    if(atkType == 1) {
      mult = super.getAttackMultiplier(p, atkType);
      return mult;
    } 
    mult = battleTable[this.getType()][p.getType()];
    return mult;
  }
}