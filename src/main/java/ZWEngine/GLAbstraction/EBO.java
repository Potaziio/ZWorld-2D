package ZWEngine.GLAbstraction;

import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL20.*;

public class EBO {
	private int id;
	private IntBuffer ibuff;
	
	public EBO(int[] src) {
		id = glGenBuffers();
		ibuff = BufferUtils.createIntBuffer(src.length);
		ibuff.put(src).flip();	
	}
	
	public void Bind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
	}
	
	public void Unbind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public IntBuffer GetIntBuffer() {
		return ibuff;
	}
	
	public void SendData(int draw_type) {
		Bind();
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, ibuff, GL_STATIC_DRAW);
	}
	
	public int GetID() {
		return id;
	}
}
