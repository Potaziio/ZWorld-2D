package ZWEngine.Map;

import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;

import ZWEngine.GLAbstraction.EBO;
import ZWEngine.GLAbstraction.VAO;
import ZWEngine.GLAbstraction.VBO;
import ZWEngine.Renderer.ColoredVertex;
import ZWEngine.Util.Color;
import ZWEngine.Util.Logger;
import ZWEngine.Util.Shader;

public class TileBatch {
	private VAO vao;
	private EBO ebo;
	private VBO vbo;	
	
	public int tilesInBatch = 0;	
	
	public final Shader batchingShader = new Shader("assets/shaders/vbShader.glsl", "assets/shaders/fbShader.glsl");
	
	private ColoredVertex[] vertexBatch;
	
	public int[] indices;
	
	/*
	public int[] indices = {
			0, 1, 2,
			1, 2, 3
	};
	*/
	
	public TileBatch(int tileNum) {		
		vertexBatch = new ColoredVertex[tileNum * 4];
		indices = new int[tileNum * 6];
	}
	
	private int indexCursor = 0;
	
	
	public void AddToBatch(int x, int y, float w, float h, Color col) {
		// TODO: Fix this 
		
		float width = 100.0f;
		float height = 100.0f;
		
		vertexBatch[tilesInBatch * 4] = new ColoredVertex(0.0f + x, 0.0f + y, 0.0f, 
				col.r, col.g, col.b, col.a);
		vertexBatch[(tilesInBatch * 4) + 1] = new ColoredVertex(1.0f + x + w, 0.0f + y, 0.0f, 
				col.r, col.g, col.b, col.a);
		vertexBatch[(tilesInBatch * 4) + 2] = new ColoredVertex(0.0f + x, 1.0f + y + h, 0.0f, 
				col.r, col.g, col.b, col.a);
		vertexBatch[(tilesInBatch * 4) + 3] = new ColoredVertex(1.0f + x + w, 1.0f + y + h, 0.0f, 
				col.r, col.g, col.b, col.a);
		
		indices[(tilesInBatch * 6)] = 0 + (tilesInBatch * 4);
		indices[(tilesInBatch * 6) + 1] = 1 + (tilesInBatch * 4);
		indices[(tilesInBatch * 6) + 2] = 2 + (tilesInBatch * 4);
		
		indices[(tilesInBatch * 6) + 3] = 1 + (tilesInBatch * 4);
		indices[(tilesInBatch * 6) + 4] = 2 + (tilesInBatch * 4);  
		indices[(tilesInBatch * 6) + 5] = 3 + (tilesInBatch * 4);	
		
		tilesInBatch++;			
	}
	
	public void Start() {		
		batchingShader.Compile();
		
		vao = new VAO(ColoredVertex.vArrToFloatBuffer(vertexBatch));
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
	}
	
	public void Update() {
		batchingShader.Use();
				
		vao.Bind();
		vbo.Bind();
		ebo.Bind();
		
		vbo.EnableVertexAttribArr(0);
		vbo.EnableVertexAttribArr(1);
		
		glDrawElements(GL_TRIANGLES, 6 * tilesInBatch, GL_UNSIGNED_INT, 0);
		
		vbo.DisableVertexAttribArr(0);
		vbo.DisableVertexAttribArr(1);
	}
}
