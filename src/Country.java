import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Country {

	// Constants indicating the nation (color) occupying the territory
	static final int OPEN = -1, YELLOW_ARMY = 0, GREEN_ARMY = 1, RED_ARMY = 2, BLUE_ARMY = 3, ORANGE_ARMY = 4;
	// Name of the country
	String name;
	// Integer representation of the army in question: based on the army constants
	int army;
	// The amount of troops of the occupying nation on the specified country
	int troops;

	/**
	 * Class Country constructor. 
	 * @param countryName The name of the country.
	 */
	Country(String countryName) {
		name = countryName;
		army = OPEN;
		troops = 0;
	}
	/**
	 * Updates the army that owns this territory.
	 * @param teamNumber The team constant color to occupy this territory
	 */
	void occupy(int teamNumber) {
		army = teamNumber;
		troops = 1;
	}
	/**
	 * Simulates an invasion of the country by a foreign country.
	 * @param attacker The country attacking this country
	 * @return true if invasion successful, false if not
	 */
	boolean invade(Country attacker) {
		Random die = new Random();
		System.out.println(attacker + "\n" + toString() + "\n\nThe " + getColor(attacker.army) + " attacks " + name + " from " + attacker.name + ".\n");
		int attackDice = attacker.troops - 1, defendDice = troops;
		if (attackDice == 0) return false;
		if (attackDice > 3) attackDice = 3;
		if (defendDice > 2) defendDice = 2;
		ArrayList<Integer> defend = new ArrayList<Integer>(), attack = new ArrayList<Integer>();
		for (int a = 0; a < defendDice; a++)
			defend.add(die.nextInt(6) + 1);
		for (int b = 0; b < attackDice; b++)
			attack.add(die.nextInt(6) + 1);
		Collections.sort(defend);
		Collections.sort(attack);
		Collections.reverse(defend);
		Collections.reverse(attack);
		System.out.println("Attacker: " + attack + " (" + attacker.name + ")\nDefender: " + defend + " (" + name + ")\n");
		if (attack.size() < defend.size()) defend.remove(1);
		while (defend.size() < attack.size())
			attack.remove(attack.size() - 1);
		for (int i = 0; i < attack.size(); i++)
			if (attack.get(i) > defend.get(i))
				troops--;
			else
				attacker.troops--;
		if (troops == 0) {
			troops = attacker.troops - 1;
			attacker.troops = 1;
			army = attacker.army;
			System.out.println(name + " has been conquered by the " + getColor(attacker.army) + ".\n\n" + attacker + "\n" + toString());
			return true;
		}
		System.out.println(name + " defends the attack by the " + getColor(attacker.army) + ".\n\n" + attacker + "\n" + toString());
		return false;
	}
	/**
	 * Converts integer representation of army to a string
	 * @param teamNumber Integer representation of the army in question: based on the army constants
	 * @return String representation of the army in question: based on the color of the army
	 */
	public String getColor(int teamNumber) {
		String armyColor = "No Man's Land";
		switch (teamNumber) {
			case 0 : armyColor = "Yellow Army";	break;
			case 1 : armyColor = "Green Army";	break;
			case 2 : armyColor = "Red Army";	break;
			case 3 : armyColor = "Blue Army";	break;
			case 4 : armyColor = "Orange Army";	break;
		}
		return armyColor;
	}
	/**
	 * Prints the status of the country
	 * @return name, army, troops
	 */
	public String toString() {
		String print = name + ": " + getColor(army);
		if (army == OPEN) return print;
		print += " (" + troops;
		if (troops == 1) return print + " troop)";
		return print + " troops)";
	}
	public static void main(String[] args) {
		Country alpha = new Country("America");
		alpha.occupy(0);
		alpha.troops += 2;
		Country beta = new Country("Mexico");
		beta.occupy(1);
		beta.troops ++;
		beta.invade(alpha);
	}
}
