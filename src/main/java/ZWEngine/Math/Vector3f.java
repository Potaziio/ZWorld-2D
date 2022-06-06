package ZWEngine.Math;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.joml.Math.sqrt;

public class Vector3f {
    public float x;
    public float y;
    public float z;

    final int length = 3;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector3f() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }
    public Vector3f(float val) {
        this.x = val;
        this.y = val;
        this.z = val;
    }
    public Vector3f(Vector2f v) {
    	this.x = v.x;
    	this.y = v.y;
    	this.z = 0.0f;
    }
    public static Vector3f sub(Vector3f v1, Vector3f v2) {
        return new Vector3f(
            v1.x - v2.x,
            v1.y - v2.y,
            v1.z - v2.z);
    }
    public Vector3f sub(Vector3f v) {
        return new Vector3f(x - v.x, y - v.y, z - v.z);
    }
    public static Vector3f add(Vector3f v1, Vector3f v2) {
        return new Vector3f(
                v1.x + v2.x,
                v1.y + v2.y,
                v1.z + v2.z);
    }
    public Vector3f add(Vector3f v) {
        return new Vector3f(x + v.x, y + v.y, z + v.z);
    }
    public Vector3f mul(Vector3f v) {
        this.x *= v.x;
        this.y *= v.y;
        this.z *= v.z;

        return this;
    }
    public Vector3f mul(float x) {
        this.x *= x;
        this.y *= y;
        this.z *= z;

        return this;
    }
    public Vector3f nMul(Vector3f v) {
        return new Vector3f(this.x * v.x, this.y * v.y, this.z * v.z);
    }
    public Vector3f nMul(float x) {
        return new Vector3f(this.x * x, this.y * x, this.z * x);
    }
    public void inc(Vector3f v) {
        x += v.x;
        y += v.y;
        z += v.z;
    }
    public void inc(Vector2f v) {
    	x += v.x;
    	y += v.y;
    	z += 0.0f;
    }   
    public void dec(Vector3f v) {
        x -= v.x;
        y -= v.y;
        z -= v.z;
    }
    public static Vector3f cross(Vector3f v1, Vector3f v2) {
        return new Vector3f(
                v1.y * v2.z - v1.z * v2.y,
                v1.z * v2.x - v1.x * v2.z,
                v1.x - v2.y - v1.y * v2.x
        );
    }
    public void Normalize() {
        this.x /= Length();
        this.y /= Length();
        this.z /= Length();
    }
    public Vector3f normalized() {
        return new Vector3f(this.x / Length(), this.y / Length(), this.z / Length());
    }
    public float Length() {
        return sqrt(x * x + y * y + z * z);
    }
    public org.joml.Vector3f toJoml() {
        return new org.joml.Vector3f(this.x, this.y, this.z);
    }
    public static Vector3f jomlToZVec(org.joml.Vector3f vec) {
        return new Vector3f(vec.x, vec.y, vec.z);
    }
    public String toString() {
        return Float.toString(this.x) + ", " + Float.toString(this.y) + ", " + Float.toString(this.z);
    }

    public FloatBuffer toFoatBuffer() {
        float[] buff = {
                this.x, this.y, this.z
        };

        FloatBuffer buffer = BufferUtils.createFloatBuffer(buff.length);
        buffer.put(buff).flip();

        return buffer;
    }

    public static FloatBuffer vArrToFloatBuffer(Vector3f[] fb) {
        FloatBuffer buff = BufferUtils.createFloatBuffer(fb.length * 3);

        for (int i = 0; i < fb.length; i++) {
            buff.put(fb[i].x);
            buff.put(fb[i].y);
            buff.put(fb[i].z);
        }

        buff.flip();
        return buff;
    }
}
