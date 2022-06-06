package ZWorld;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ZWEngine.Core.Window;
import ZWEngine.ECS.Entity;
import ZWEngine.ECS.Components.Component;
import ZWEngine.Util.Logger;
import ZWEngine.Util.Shader;
import ZWorld.World.World;

public class Game {
    public World world = new World();
    public Window window;

    public Shader gameMainShader = new Shader("assets/shaders/vertex.glsl", "assets/shaders/fragment.glsl");   
   
    private List<Entity> removeQuery = new ArrayList<Entity>();
    private List<Entity> addQuery = new ArrayList<Entity>();

    public Game() {
        world.game = this;
    }        

    public void AddEntity(Entity e) {
    	e.Start();
    	world.entities.add(e);
    	addQuery.add(e);      
    }

    public <T extends Entity> void RemoveEntity(T e) {
    	world.entities.remove(e);
    	removeQuery.add(e);     	
    }

    public void RemoveEntity(String name) {
    	world.entities.remove(GetEntity(name));
    	removeQuery.add(GetEntity(name));    
    }

    public Entity GetEntity(String name) {    	
        for (Entity e : world.entities) {
            //if (e.name == name)
                //return e;
        }

        Logger.Log(Logger.LogLevel.ERROR, "No entity with name: " + name + "!");
        return new Entity("NULL", world);
    }

    public void Start() {
        Logger.Log("On game/world start");       
        world.Start();
        
        for (Entity e : world.entities) {
            e.Start();
        }
        
        gameMainShader.Compile();   
    }
    
    public void OnWindowCreation() {
    	Logger.DisableLogs(false);
    	Logger.ShowColors(false);
    }

    public void Update() {
        world.Update();     
        
        world.map.GetTileBatchShader().SendMat4f("projection", world.camera.GetProjectionMatrix());
        world.map.GetTileBatchShader().SendMat4f("view", world.camera.GetViewMatrix());
        world.map.Update();          
        
        gameMainShader.Use();

        gameMainShader.SendMat4f("projection", world.camera.GetProjectionMatrix());
        gameMainShader.SendMat4f("view", world.camera.GetViewMatrix());  
                
        for (Entity e : world.entities) {
        	e.Update();
        	for (Component c : e.components) {
        		c.Update();
        	}
        	gameMainShader.SendMat4f("model", e.transform.transform); 
        }                 
        
        world.entities.addAll(addQuery);
        world.entities.removeAll(removeQuery);
        
        removeQuery.clear();
        addQuery.clear();
        
    }
}
