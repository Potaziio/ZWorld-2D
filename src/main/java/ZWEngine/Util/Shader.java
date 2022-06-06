package ZWEngine.Util;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.io.*;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

    private String vertexSource;
    private String fragmentSource;
    private String vertexLocation;
    private String fragmentLocation;
    private boolean beingUsed;
    private int vertexID, fragmentID, shaderProgram;
    private FloatBuffer fbuff = BufferUtils.createFloatBuffer(16);

    public Shader(String vLoc, String fLoc) {
    	//Logger.Log(String.format("Creating new shader [%s] [%s]", vLoc, fLoc));
        try {
            vertexSource = new String(Files.readAllBytes(Paths.get(vLoc)));
            vertexLocation = vLoc;
        } catch (IOException e) {
            e.printStackTrace();
            Logger.Log(Logger.LogLevel.ERROR, "Could not open file for shader " + vLoc);
        }

        try {
        	fragmentLocation = fLoc;
            fragmentSource = new String(Files.readAllBytes(Paths.get(fLoc)));
        } catch (IOException e) {
            e.printStackTrace();
            Logger.Log(Logger.LogLevel.ERROR, "Could not open file for shader " + fLoc);
        }
    }

    public void Compile() {
    	//Logger.Log(String.format("Compiling shader [%s] [%s]", vertexLocation, fragmentLocation));
    	
        vertexID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexID, vertexSource);
        glCompileShader(vertexID);

        int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'defaultShader.glsl'\n\tVertex shader compilation failed.");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false : "";
        }

        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        // Pass the shader source to the GPU
        glShaderSource(fragmentID, fragmentSource);
        glCompileShader(fragmentID);

        // Check for errors in compilation
        success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'defaultShader.glsl'\n\tFragment shader compilation failed.");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false : "";
        }

        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexID);
        glAttachShader(shaderProgram, fragmentID);
        glLinkProgram(shaderProgram);

        // Check for linking errors
        success = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            int len = glGetProgrami(shaderProgram, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'defaultShader.glsl'\n\tLinking of shaders failed.");
            System.out.println(glGetProgramInfoLog(shaderProgram, len));
            assert false : "";
        }
    }
    public void Use() {
        if (!beingUsed) {
            beingUsed = true;
            glUseProgram(shaderProgram);
        }
    }
    public void Detach() {
        glUseProgram(0);
        beingUsed = false;
    }

    public void SendMat4f(String varName, Matrix4f mat) {
        Use();
        mat.get(fbuff);
        glUniformMatrix4fv(glGetUniformLocation(shaderProgram, varName), false, fbuff);
    }
    public void SendInt(String varName, int val) {}
    public void SendFloat(String varName, float val) {}

    public String GetVertexSource() {
        return this.vertexSource;
    }
    public String GetFragmentSource() {
        return this.fragmentSource;
    }
    public String GetVertexLocation() {
        return this.vertexLocation;
    }
    public String GetFragmentLocation() {
        return this.fragmentLocation;
    }
    public int GetProgram() {
        return this.shaderProgram;
    }
    public int GetVertexID() {
        return this.vertexID;
    }
    public int GetFragmentID() {
        return this.fragmentID;
    }
}
