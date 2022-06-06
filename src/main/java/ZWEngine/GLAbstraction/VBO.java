package ZWEngine.GLAbstraction;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VBO {
    private int id;


    public VBO() {
        id = glGenBuffers();
    }
    
    public int GetId() {
    	return this.id;
    }

    public void Bind() {
        glBindBuffer(GL_ARRAY_BUFFER, id);
    }

    public void Unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void SendData(FloatBuffer fb, int draw_type) {
    	Bind();
        glBufferData(GL_ARRAY_BUFFER, fb, draw_type);
    }

    public void SetVertexAttribPtr(int index, int size, int type, int stride, long ptr) {
        glVertexAttribPointer(index, size, type, false, stride, ptr);
        glEnableVertexAttribArray(index);
    }

    public void EnableVertexAttribArr(int index) {
        glEnableVertexAttribArray(index);
    }

    public void DisableVertexAttribArr(int index) {
        glDisableVertexAttribArray(index);
    }
}
