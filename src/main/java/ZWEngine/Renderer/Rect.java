package ZWEngine.Renderer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

import ZWEngine.GLAbstraction.EBO;
import ZWEngine.GLAbstraction.VAO;
import ZWEngine.GLAbstraction.VBO;
import ZWEngine.Util.Logger;

public class Rect extends Primitive {
	VAO vao;
	VBO vbo;
	EBO ebo;	
	
	public ColoredVertex[] vertices = new ColoredVertex[4];
	
	public int[] indices = {
			0, 1, 2,
			1, 2, 3
	};
	
	public Rect() {
		
	}
	
	private void GenVertices() {
		vertices[0] = new ColoredVertex(0.0f, 0.0f, 0.0f, color.r, color.g, color.b, color.a);
		vertices[1] = new ColoredVertex(1.0f, 0.0f, 0.0f, color.r, color.g, color.b, color.a);
		vertices[2] = new ColoredVertex(0.0f, 1.0f, 0.0f, color.r, color.g, color.b, color.a);
		vertices[3] = new ColoredVertex(1.0f, 1.0f, 0.0f, color.r, color.g, color.b, color.a);
	}
	
	@Override
	public void Start() {
		GenVertices();
		
		vao = new VAO(ColoredVertex.vArrToFloatBuffer(vertices));
		vbo = new VBO();
		ebo = new EBO(indices);
		
		vao.Bind();
		vbo.Bind();
		ebo.Bind();
		
		vbo.SendData(vao.GetFloatBuffer(), GL_STATIC_DRAW);
		ebo.SendData(GL_STATIC_DRAW);
		
		vbo.SetVertexAttribPtr(0, 3, GL_FLOAT, 7 * 4, 0);
		vbo.EnableVertexAttribArr(0);		
		vbo.SetVertexAttribPtr(1, 4, GL_FLOAT, 7 * 4, 3 * 4);
		vbo.EnableVertexAttribArr(1);
		
		super.Start();
	}
	
	@Override
	public void Update() {		
		vao.Bind();
		vbo.Bind();
		ebo.Bind();
		
		vbo.EnableVertexAttribArr(0);
		vbo.EnableVertexAttribArr(1);
		
		glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
		
		vbo.DisableVertexAttribArr(0);
		vbo.DisableVertexAttribArr(1);
		
		super.Update();
	}
}
