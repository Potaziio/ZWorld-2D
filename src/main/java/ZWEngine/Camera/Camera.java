package ZWEngine.Camera;

import ZWEngine.ECS.Components.Transform;
import ZWEngine.Math.Math;
import ZWEngine.Math.Vector2f;
import ZWEngine.Util.Input;
import ZWEngine.Util.Logger;
import ZWEngine.Util.Time;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;

public class Camera {
    public Transform transform;
    public float fov;
    public float aspect;
    public float left;
    public float right;
    public float top;
    public float bottom;
    public float near;
    public float far;

    //public ZWEngine.Math.Vector3f forward = new ZWEngine.Math.Vector3f();
    //public ZWEngine.Math.Vector3f right = new ZWEngine.Math.Vector3f();

    public ZWEngine.Math.Vector3f up = new ZWEngine.Math.Vector3f(0.0f, 1.0f, 0.0f);
    public ZWEngine.Math.Vector3f front = new ZWEngine.Math.Vector3f(0.0f, 0.0f, -1.0f);
    
    private Matrix4f viewMatrix = new Matrix4f(), projectionMatrix = new Matrix4f();

    /*
    public Camera(Transform t, float fov, float aspect, float near, float far) {
        this.fov = fov;
        this.transform = t;        
        this.aspect = aspect;
        this.near = near;
        this.far = far;

        Vector3f model = new Vector3f();
        t.position.toJoml().add(front.toJoml(), model);

        viewMatrix.identity();
        viewMatrix.lookAt(
                t.position.toJoml(),
                model, up.toJoml());

        projectionMatrix.identity();
        projectionMatrix.ortho(left, right, bottom, top, near, far);
        //projectionMatrix.perspective((float)org.joml.Math.toRadians(fov), aspect, near, far);
    }
    */
    
    public Camera(Transform t, float left, float right, float bottom, float top, float near, float far) {
        this.transform = t;        
        this.near = near;
        this.far = far;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;

        Vector3f model = new Vector3f();
        t.position.toJoml().add(front.toJoml(), model);
        
        viewMatrix.identity();
        viewMatrix.lookAt(
                t.position.toJoml(),
                model, up.toJoml());

        projectionMatrix.identity();
        projectionMatrix.ortho(left, right, bottom, top, near, far);
        //projectionMatrix.perspective((float)org.joml.Math.toRadians(fov), aspect, near, far);
    }

    public void Update() {
        Vector3f model = new Vector3f();
        transform.position.toJoml().add(front.toJoml(), model);

        viewMatrix.identity();
        viewMatrix.lookAt(
                transform.position.toJoml(),
                model, up.toJoml());

        projectionMatrix.identity();
        projectionMatrix.ortho(this.left, this.right, this.bottom, this.top, near, far);
        //projectionMatrix.perspective((float) org.joml.Math.toRadians(fov), aspect, near, far);

        Vector3f ff = new Vector3f();
        Matrix4f mat = new Matrix4f();
        GetViewMatrix().invert(mat).getColumn(2, ff);

        //forward = ZWEngine.Math.Vector3f.jomlToZVec(ff);

        //SetForwardVec();
        //SetRightVec();
    }

    /*
    private void SetForwardVec() {
        Vector3f f = new Vector3f();
        Matrix4f mat = new Matrix4f();
        GetViewMatrix().invert(mat).getColumn(2, f);

        forward = ZWEngine.Math.Vector3f.jomlToZVec(f);
    }
    */

    /*
    private void SetRightVec() {
        Vector3f f = new Vector3f();
        Vector3f r = new Vector3f();
        f = forward.toJoml();
        f.cross(up.toJoml(), r);

        right = ZWEngine.Math.Vector3f.jomlToZVec(r);
    }

    public ZWEngine.Math.Vector3f GetForwardVec() {
        return forward;
    }

    public ZWEngine.Math.Vector3f GetRightVec() {
        return right;
    }
    */

    public void UpdateBounds(float left, float right, float bottom, float top) {
    	this.left = left;
    	this.right = right;
        this.bottom = bottom;
        this.top = top;    	
    }

    public Matrix4f GetViewMatrix() {
        return this.viewMatrix;
    }
    public Matrix4f GetProjectionMatrix() { return this.projectionMatrix; }
}
