/**
 * Entity - represents an entity object (trainers, pokemon) with attributes
 * including hp and max hp
 * @author Daniel Moreno
 * @since 11-27-2021
 */
public abstract class Entity {

	private String name;
	private int hp;
	private int maxHp;

	/**
	 * initializes entity
	 * @param n   the name of this entity
   * @param h the current hp of this entity
   * @param m max hp of this entity
	 */
	public Entity(String n, int h, int m) {
		this.name = n;
		this.maxHp = m;
		this.hp = h;
	}

	/**
	 * Accesses current hp of this entity
	 * @return current hp of entity
	 */
	public int getHp() {
		return this.hp;
	}

	/**
	 * Accesses max hp of this entity
	 * @return maxHp max hp of entity
	 */
	public int getMaxHp() {
		return this.maxHp;
	}

	/**
	 * Takes damage that entity receives and subtracts it from this entity's hp
	 * @param damage that this entity receives
	 */
	public void takeDamage(int d) {
		this.hp -= d;
		if (this.hp < 0) {
			this.hp = 0;
		}
	}

	/**
	 * Fully recovers this entity's hp
	 */
	public void heal() {
		this.hp = this.maxHp;
	}

	/**
	 * Accesses this entity's name
	 * @return this entity's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the entity's name and hp in the format 'Name HP: hp/maxHp'
	 * @return text of entity's name and hp
	 */
	public String toString() {
		String eString;
		eString = this.name + " HP: " + this.hp + "/" + this.maxHp;
		return eString;
	}

}