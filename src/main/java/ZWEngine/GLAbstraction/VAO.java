package ZWEngine.GLAbstraction;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VAO {
    private int id;
    private FloatBuffer fbuff;

    public VAO(float[] src) {
        id = glGenVertexArrays();
        fbuff = BufferUtils.createFloatBuffer(src.length);
        fbuff.put(src).flip();
    }
    
    public void SetFloatBuffer(FloatBuffer fb) {
    	fbuff = fb;    	
    }

    public VAO(FloatBuffer fb) {
        id = glGenVertexArrays();
        this.fbuff = fb;
        //fb.flip();
    }
    public FloatBuffer GetFloatBuffer() {
        return this.fbuff;
    }

    public void Bind() {
        glBindVertexArray(id);
    }

    public void Unbind() {
        glBindVertexArray(0);
    }

    public int GetID() {
        return id;
    }
}
