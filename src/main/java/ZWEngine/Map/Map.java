package ZWEngine.Map;

import ZWEngine.ECS.Entity;
import ZWEngine.Math.Vector2f;
import ZWEngine.Util.Color;
import ZWEngine.Util.Shader;
import ZWorld.World.World;

public class Map {
	World world;
	
	public String map;
	public String file;
	
	public int rows;
	public int cols;	
		
	private final Tile AIR = new Tile(new Color(0.0f, 0.0f, 0.0f, 1.0f), 0);
	private final Tile WALL = new Tile(new Color(1.0f, 1.0f, 1.0f, 1.0f), 1);
	private final Tile BUSH = new Tile(new Color(0.0f, 1.0f, 0.0f, 0.5f), 2);
	private final Tile FIRE = new Tile(new Color(0.8f, 0.2f, 0.0f, 0.6f), 3);
	
	private int[][] MapArray;
	private Tile[][] TileArray;
	
	private TileBatch tileBatch;
	
	private boolean openBrackets = false;
	private boolean closeBrackets = false;
	
	public void SetMapArray() {		
		int xIndex = 0;
		int yIndex = 0;
		
		int tileCount = 0;
		
		for (int i = 0; i < map.length(); i++) {
			char CurrentChar = map.charAt(i);
			int CurrectCharVal = Character.getNumericValue(map.charAt(i));
			
			if (CurrentChar == '}') {
				openBrackets = false;
				closeBrackets = true;				
				yIndex++;
			}
			
			if (openBrackets == true) {
				if (CurrentChar != '{' && CurrentChar != '}') {
					MapArray[yIndex][xIndex] = CurrectCharVal;
					
					if (CurrectCharVal == Tile.AIR.id)
						TileArray[yIndex][xIndex] = Tile.AIR;
					
					if (CurrectCharVal == Tile.WALL.id)
						TileArray[yIndex][xIndex] = Tile.WALL;
					
					if (CurrectCharVal == Tile.BUSH.id)
						TileArray[yIndex][xIndex] = Tile.BUSH;
					
					if (CurrectCharVal == Tile.FIRE.id)
						TileArray[yIndex][xIndex] = Tile.FIRE;
					
					
					if (CurrentChar != '0')
						tileCount++;
				}

				xIndex++;
			}
				
			if (CurrentChar == '{') {
				xIndex = 0;
				openBrackets = true;
				closeBrackets = false;
			}
		}			
		
		tileBatch = new TileBatch(tileCount);
	}	
	
	public Map(int rows, int cols) {	
		this.rows = rows;		
		this.cols = cols;		
	
		MapArray = new int[rows][cols];		
		TileArray = new Tile[rows][cols];
	}
	
	public int[][] GetMapArray() {		
		return MapArray;		
	}
		
	public void SetObjects(World world) {			
		this.world = world;
		
		int x = 60;
		int y = 60;
		float w = 60.0f;
		float h = 60.0f;
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (MapArray[row][col] == WALL.id) 
					tileBatch.AddToBatch(col * x, row * y, w, h, WALL.color);
				else if (MapArray[row][col] == BUSH.id) 
					tileBatch.AddToBatch(col * x, row * y, w, h, BUSH.color);
				else if (MapArray[row][col] == FIRE.id)
					tileBatch.AddToBatch(col * x, row * y, w, h, FIRE.color);
				/*
				 else if (MapArray[row][col] == AIR.id) 
					tileBatch.AddToBatch(col * x, row * y, w, h, AIR.color);
				*/							
			}
		}
		
		tileBatch.Start();
	}
	
	public Shader GetTileBatchShader() {
		return this.tileBatch.batchingShader;
	}
	
	public void Update() {
		tileBatch.Update();			
	}
	
	public Vector2f GetEntityTilePosition(Entity e) {
		Vector2f pos = new Vector2f((float)Math.floor(e.transform.position.x  / 60), (float)Math.floor(e.transform.position.y / 60));
		
		if (pos.x < 0 ||
    			pos.x > 32 || 
    			pos.y < 0 ||
    			pos.y > 18)
    		return new Vector2f();
		
		return pos;
	}
	
	public Vector2f GetVector2TilePosition(Vector2f e) {
		Vector2f pos = new Vector2f((float)Math.floor(e.x  / 60), (float)Math.floor(e.y / 60));
		
		if (pos.x < 0 ||
    			pos.x > 32 || 
    			pos.y < 0 ||
    			pos.y > 18)
    		return new Vector2f();
		
		return pos;
	}
	
	public Tile GetTileCollided(Entity e) {
		Vector2f tilePosition = GetEntityTilePosition(e);
		
		if (TileArray[(int)tilePosition.y][(int)tilePosition.x] != null)
			return TileArray[(int)tilePosition.y][(int)tilePosition.x];
		else
			return Tile.AIR;
	}
	
	public boolean GetCollisionWithTile(Entity e) {
		Vector2f tilePosition = GetEntityTilePosition(e);
		
		if (TileArray[(int)tilePosition.y][(int)tilePosition.x] != null && MapArray[(int)tilePosition.y][(int)tilePosition.x] != 0)
			return true;
		else
			return false;
	}
	
	public boolean GetCollisionWithTile(Vector2f p) {		
		Vector2f pos = GetVector2TilePosition(p);
		
		if (TileArray[(int)pos.y][(int)pos.x] != null && MapArray[(int)pos.y][(int)pos.x] != 0)
			return true;
		else
			return false;
	}
}