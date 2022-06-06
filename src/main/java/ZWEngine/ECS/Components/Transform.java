package ZWEngine.ECS.Components;


import org.joml.Matrix4f;

import ZWEngine.Math.Vector3f;
import ZWEngine.Util.Logger;

public class Transform extends Component {
    public Vector3f position;
    public Vector3f scale;
    public Vector3f rotation;

    public Vector3f forward = new Vector3f();
    public Vector3f right = new Vector3f();

    public Matrix4f transform = new Matrix4f();

    public Transform(Vector3f position, Vector3f scale, Vector3f rotation) {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
    }
     
    public Transform(Vector3f position) {
    	this.position = position;
    	this.scale = new Vector3f(25.0f);
    	this.rotation = new Vector3f();
    }

    public Transform() {
        this.position = new Vector3f();
        this.scale = new Vector3f();
        this.rotation = new Vector3f();
    }

    public void Translate(Vector3f vec) {
        this.position.inc(vec);
    }

    @Override
    public void Update() {
        transform.identity();
        transform.translate(position.toJoml());
        transform.scale(scale.toJoml());

        super.Update();
    }

    @Override
    public void Start() {    	
    	//Logger.Log("Starting component: " + this.getClass().getName());	
    	
        super.Start();
    }
}
