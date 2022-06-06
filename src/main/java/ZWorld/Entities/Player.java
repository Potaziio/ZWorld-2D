package ZWorld.Entities;

import ZWEngine.ECS.Entity;
import ZWEngine.ECS.Components.Component;
import ZWEngine.ECS.Components.SpriteRenderer;
import ZWEngine.ECS.Components.Transform;
import ZWEngine.Math.Vector2f;
import ZWEngine.Math.Vector3f;
import ZWEngine.Renderer.Rect;
import ZWEngine.Util.Color;
import ZWEngine.Util.Input;
import ZWEngine.Util.Logger;
import ZWEngine.Util.Time;
import ZWorld.World.World;
import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
    public Player(String name, World world) {
        super(name, world);
    }

    public Player(String name, World world, Transform transform) {
        super(name, world, transform);
    }

    public Player(String name, World world, Component... cArgs) {
        super(name, world, cArgs);
    }


    float moveSpeed = 200.0f;   
   
    
    @Override
    public void Start() {        
        super.Start();
    }
	
    @Override
    public void Update() {
    	Movement();    	
    	
        super.Update();
    }      
    
    public boolean GetCollision(Entity e, Entity b) {	
		Vector3f eP = e.transform.position;
		Vector3f eS = e.transform.scale; 
		Vector3f bP = b.transform.position;
		Vector3f bS = b.transform.scale;
		
		return (eP.x < bP.x + bS.x &&
		        eP.x + eS.x > bP.x &&
		        eP.y < bP.y + bS.y &&
		        eS.y + eP.y > bP.y);
	}                 
    
    public void Movement() {       		    	       
    	Vector2f movement = Input.GetMovement();
    	movement.Normalize();
    	
    	if (movement.x < 0 && world.map.GetCollisionWithTile(new Vector2f(transform.position.x - 1, transform.position.y)) == false) {
    		transform.position.x += moveSpeed * movement.x * Time.deltaTime();
    	}
    	else if (movement.x > 0 && world.map.GetCollisionWithTile(new Vector2f(transform.position.x + 61, transform.position.y)) == false) {
    		transform.position.x += moveSpeed * movement.x * Time.deltaTime();
    	}
    	if (movement.y < 0 && world.map.GetCollisionWithTile(new Vector2f(transform.position.x, transform.position.y - 1)) == false) {
    		transform.position.y += moveSpeed * movement.y * Time.deltaTime();
    	}
    	else if (movement.y > 0 && world.map.GetCollisionWithTile(new Vector2f(transform.position.x, transform.position.y + 61)) == false) {
    		transform.position.y += moveSpeed * movement.y * Time.deltaTime();          
    	}    	    	    
    }
}
