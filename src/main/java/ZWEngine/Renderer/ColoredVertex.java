package ZWEngine.Renderer;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class ColoredVertex {
	float x, y, z, r, g, b, a;
	
	public ColoredVertex(float x, float y, float z, float r, float g, float b, float a) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public FloatBuffer toFloatBuffer() {
		float[] buff = {
				this.x, this.y, this.z, 
				this.r, this.g, this.b, this.a
		};
		
		FloatBuffer buffer = BufferUtils.createFloatBuffer(buff.length);
		buffer.put(buff).flip();
		
		return buffer;
	}
	
	public static FloatBuffer vArrToFloatBuffer(ColoredVertex[] vArr) {
		FloatBuffer buff = BufferUtils.createFloatBuffer(vArr.length * 7);
		
		for (int i = 0; i < vArr.length; i++) {
			buff.put(vArr[i].x);
			buff.put(vArr[i].y);
			buff.put(vArr[i].z);
			buff.put(vArr[i].r);
			buff.put(vArr[i].g);
			buff.put(vArr[i].b);
			buff.put(vArr[i].a);			
		}
		
		buff.flip();
		return buff;
	}
}
