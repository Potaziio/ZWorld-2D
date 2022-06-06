package ZWorld.World;

import java.util.ArrayList;
import java.util.List;

import ZWEngine.Camera.Camera;
import ZWEngine.Core.Window;
import ZWEngine.ECS.Entity;
import ZWEngine.ECS.Components.SpriteRenderer;
import ZWEngine.ECS.Components.Transform;
import ZWEngine.Map.Map;
import ZWEngine.Map.MapSerializer.MapSerializer;
import ZWEngine.Math.Vector2f;
import ZWEngine.Math.Vector3f;
import ZWEngine.Renderer.Rect;
import ZWEngine.Util.Color;
import ZWEngine.Util.Logger;
import ZWEngine.Util.Shader;
import ZWorld.Game;
import ZWorld.Entities.Player;

public class World {
    //public Camera camera = new Camera(new Transform(new Vector3f(0.0f, 0.0f, 5.0f)), 120, 800.0f / 600.0f, 0.1f, 100.0f);
	public Camera camera = new Camera(new Transform(), 0.0f, Window.width, Window.height, 0.0f, 0, 1000.0f);
    public Game game;
    public List<Entity> entities = new ArrayList<Entity>();     
    public Player player = new Player("Player", this,
            new Transform(
            		new Vector3f(600.0f, 600.0f, 0.0f),
            		new Vector3f(60.0f, 60.0f, 0.0f),
            		new Vector3f()),            
            new SpriteRenderer(
            		new Shader("assets/shaders/rectv.glsl", "assets/shaders/rectf.glsl"), 
            		new Rect(), 
            		new Color(1.0f, 1.0f, 0.0f, 1.0f)));   

    public World() {
    }          
    
    public Map map = MapSerializer.Deserialize("assets/maps/level0.map", 18, 32);
    
    public void Start() {    
    	map.SetObjects(this);
	
    	camera.UpdateBounds(0.0f, game.window.width, 0.0f, game.window.height);          	    
        
        game.AddEntity(player);                
    }   
    

    public void Update() {
    }    
}
