import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Map - reads and stores the contents of the area files; displays map.
 * Undiscovered areas are marked as 'x' and the state of the area (e.g 'n' nothing,
 * 'c' city) is revealed as the player explores the map. Player's current location
 * is marked as "*" on the displayed map.
 * @author Angeline Dequit
 * @since 2021-11-24
 * */
public class Map {

	private char[][] map;
	private boolean[][] revealed;
  private static Map instance = null;

  /**
	 * initializes single instance of map
	 */
  private Map() {
    this.map = new char[5][5];
		this.revealed = new boolean[5][5];
  } 

  /**
  * Map Singleton - Returns the original instance of the Map which is stored
  * as a private static member of Map class
  * @return instance original instance of Map
  */
  public static Map getInstance() { 
    if (instance == null) {
      instance = new Map();
    }
    return instance;
  }
	/**
	 * loads current map by reading the corresponding Area text files into an array.
	 * @param mapNum area/map number
	 */
	public void loadMap(int mapNum) throws FileNotFoundException {
		int row = 5;
		int col = 5;

		try {
			Scanner nextMap = new Scanner(new File("Area1.txt"));
			switch (mapNum) {
			case 1:
				break;

			case 2:
				nextMap = new Scanner(new File("Area2.txt"));
				break;

			case 3:
				nextMap = new Scanner(new File("Area3.txt"));
				break;

			default:
				break;
			}

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (nextMap.hasNext()) {
						String s = nextMap.next();
						map[i][j] = s.charAt(0);
						if (map[i][j] == 's') {
							revealed[i][j] = true;
						} else {
							revealed[i][j] = false;
						}
					}
				}
			} // nfl

		} catch (FileNotFoundException fnf) {
			System.out.println("ERROR: File not found. ");
		}

	} // loadMap method

	/**
	 * Accesses character at the location passed in.
	 * @param p a point on the map
	 * @return  character at given point p
	 */
	public char getCharAtLoc(Point p) {
		try {
			char pt = this.map[(int) p.getX()][(int) p.getY()];
			return pt;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 'x';
		}
	}

	/**
	 * Displays map (hidden or shown spaces); displays trainer's position as a "*"
	 * @param p trainer's location on the map, displayed as "*"
	 * @return  string that displays the map
	 */
	public String mapToString(Point p) {
		String line = "";
		int x = (int) p.getX();
		int y = (int) p.getY();
		for (int i = 0; i < this.map.length; i++) {
			if (i > 0) {
				line += "\n";
			}
			for (int j = 0; j < this.map.length; j++) {
				if (revealed[i][j]) {
					if (i == x && j == y) {
						line += "* ";
					} else {
						line += this.map[i][j] + " ";
					}
				} else {
					line += "x ";
				}
			}
		}
		return line;
	}

	/**
	 * Iterates through the map array to find 's' which indicates start. returns the
	 * location (point) of the start
	 * @return  the point at which the character 's' is found, i.e the starting
	 *          point
	 */
	public Point findStart() {
		Point p = new Point(0, 0);
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map.length; j++) {
				if (map[i][j] == 's') {
					p.setLocation(i, j);
					return p;
				}
			}
		} // nfl
		return p;
	}

	/**
	 * Reveals character at a point on the map. The point in the boolean array
	 * (passed in) is set to true
	 * @param p	a point on the map whose character that is to be revealed
	 */
	public void reveal(Point p) {
		revealed[(int) p.getX()][(int) p.getY()] = true;
	}

	/**
	 * Removes object at a given point. Sets the character at the location to "n"
	 * (nothing)
	 * @param p	a point on the map whose character is to be removed (or changed to
	 *          'n')
	 */
	public void removeCharAtLoc(Point p) {
		map[(int) p.getX()][(int) p.getY()] = 'n';
	}
} // map class