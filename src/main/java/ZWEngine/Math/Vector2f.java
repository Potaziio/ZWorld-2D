package ZWEngine.Math;

import static org.joml.Math.sqrt;

public class Vector2f {
    public float x;
    public float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Vector2f() {
        this.x = 0.0f;
        this.y = 0.0f;
    }
    public Vector2f(float val) {
        this.x = val;
        this.y = val;
    }

    public static Vector2f sub(Vector2f v1, Vector2f v2) {
        return new Vector2f(
                v1.x - v2.x,
                v1.y - v2.y);
    }
    public Vector2f sub(Vector2f v) {
        return new Vector2f(x - v.x, y - v.y);
    }
    public static Vector2f add(Vector2f v1, Vector2f v2) {
        return new Vector2f(
                v1.x + v2.x,
                v1.y + v2.y);
    }
    public Vector2f add(Vector2f v) {
        return new Vector2f(x + v.x, y + v.y);
    }
    public void inc(Vector2f v) {
        x += v.x;
        y += v.y;
    }
    public void dec(Vector2f v) {
        x -= v.x;
        y -= v.y;
    }
    
    public Vector2f mul(float val) {
    	return new Vector2f(x * val, y * val);
    }

    public void Normalize() {
    	if (Length() == 0)
    		return;
    	
        this.x /= Length();
        this.y /= Length();
    }
    public Vector2f normalized() {
        return new Vector2f(this.x / Length(), this.y / Length());
    }
    public float Length() {            	
        return sqrt(x * x + y * y);
    }

    public org.joml.Vector2f toJoml() {
        return new org.joml.Vector2f(this.x, this.y);
    }

    public static Vector2f jomlToZVec(org.joml.Vector2f vec) {
        return new Vector2f(vec.x, vec.y);
    }
    
    public String toString() {
        return Float.toString(this.x) + ", " + Float.toString(this.y);
    }
}
