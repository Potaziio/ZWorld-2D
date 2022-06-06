package ZWEngine.Map;

import ZWEngine.Util.Color;

public class Tile {
	public Color color;
	public int id;
	
	public static final Tile AIR = new Tile(new Color(0.0f, 0.0f, 0.0f, 1.0f), 0);
	public static final Tile WALL = new Tile(new Color(1.0f, 1.0f, 1.0f, 1.0f), 1);
	public static final Tile BUSH = new Tile(new Color(0.0f, 1.0f, 0.0f, 0.5f), 2);
	public static final Tile FIRE = new Tile(new Color(0.8f, 0.2f, 0.0f, 0.6f), 3);
	
	public Tile(Color color, int id) {
		this.color = color;
		this.id = id;
	}
}
