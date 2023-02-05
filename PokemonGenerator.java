import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;



public class PokemonGenerator{
  
  private HashMap<String,String> pokemon;
  private static PokemonGenerator instance = null;

  private PokemonGenerator(){ 
    try{
      Scanner pokemonList = new Scanner(new File("PokemonList.txt"));
      pokemon = new HashMap<String,String>();
      
      while (pokemonList.hasNextLine()){
        String curr = pokemonList.nextLine();
        String[] tokens = curr.split(",",2);
        pokemon.put(tokens[0],tokens[1]);
      }
    }
    catch (FileNotFoundException e){
      System.out.println("File Not Found");
    }
  }

  public static PokemonGenerator getInstance( ) {
      if( instance == null ) {
         instance = new PokemonGenerator();
      }
	   return instance;
   }

   public Pokemon generateRandomPokemon(int level){ //FIXME
    String element = "";
    String pokeName = "";
    int randElement = (int) (Math.random() * 3);
    List<String> pokeArray = new ArrayList<>(); 

    switch(randElement){
      case 0:
        element = "Fire";
        break;
      case 1:
        element = "Water";
        break;
      case 2:
        element = "Grass";
        break;
    }
    
    for(String key : pokemon.keySet()){
      if(pokemon.get(key).equalsIgnoreCase(element)){
        pokeArray.add(key);
      }
    }
    
    int randPokemon = (int) (Math.random() * pokeArray.size());
    pokeName = pokeArray.get(randPokemon);

    int randHealth = (int) (Math.random() * 9 + 20);
    Pokemon returnPoke = null;

    switch(element){
      case "Fire":
        returnPoke = new Fire(pokeName, randHealth, randHealth);
        break;
      case "Water":
        returnPoke = new Water(pokeName, randHealth, randHealth);
        break;
      case "Grass":
        returnPoke = new Grass(pokeName, randHealth, randHealth);
        break;
    }

    int randNum = 0;
    for(int i = 0; i < level - 1; i++){
      randNum = (int) (Math.random() * 2);
      if(randNum == 0){
        returnPoke = new HPUp(returnPoke);
      }
      if(randNum == 1){
        returnPoke = new AttackUp(returnPoke);
      }
    }
    return returnPoke;
   }

   public Pokemon getPokemon(String name){

    String element = pokemon.get(name);
    int randNum = (int) (Math.random() * 9 + 20);
    Pokemon newPoke = null;
    if(element.equalsIgnoreCase("water")){
      newPoke = new Water(name, randNum, randNum);
    }
    if(element.equalsIgnoreCase("fire")){
      newPoke = new Fire(name, randNum, randNum);
    }
    if(element.equalsIgnoreCase("grass")){
      newPoke = new Grass(name, randNum, randNum);
    }
    return newPoke;
   }

   public Pokemon addRandomBuff(Pokemon p){//FIXME
    int randNum = (int) (Math.random() * 2 + 1);
    Pokemon newPoke = null;
    if(randNum == 1){
      newPoke = new AttackUp(p);
    }
    if(randNum == 2){
      newPoke = new HPUp(p);
    }
    return newPoke;
   }

   public Pokemon addRandomDebuff(Pokemon p){//FIXME
    int randNum = (int) (Math.random() * 2 + 1);
    Pokemon newPoke = null;
    if(randNum == 1){
      newPoke = new AttackDown(p);
    }
    if(randNum == 2){
      newPoke = new HPDown(p);
    }
    return newPoke;
   }

}

