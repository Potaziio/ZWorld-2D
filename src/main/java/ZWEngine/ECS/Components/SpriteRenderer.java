package ZWEngine.ECS.Components;

import ZWEngine.Renderer.Primitive;
import ZWEngine.Util.Color;
import ZWEngine.Util.Logger;
import ZWEngine.Util.Shader;
import ZWEngine.Util.Sprite;

public class SpriteRenderer extends Component {
    public Shader shader;
    public Sprite sprite;
    Primitive primitive;
    public Color color;

    public SpriteRenderer(Shader shader, Sprite sprite, Primitive prim, Color color) {
    	this.color = color;
    	prim.color = this.color;
        this.shader = shader;
        this.sprite = sprite;
        this.primitive = prim;              
    }
    
    public SpriteRenderer(Shader shader, Primitive prim, Color color) {
    	this.color = color;
    	prim.color = this.color;
    	this.primitive = prim;
    	this.shader = shader;
    	this.sprite = null;
    }
       
    @Override
    public void Start() {    	
        //Logger.Log("Starting component: " + this.getClass().getName());
        
        if (this.shader != null)
        	this.shader.Compile();
        
        primitive.Start();        
        super.Start();       
    }

    public void Update() {
    	shader.Use();
    	shader.SendMat4f("projection", entity.world.camera.GetProjectionMatrix());
    	shader.SendMat4f("view", entity.world.camera.GetViewMatrix());
    	shader.SendMat4f("model", entity.transform.transform);
    	primitive.Update();       	   
    }
}
