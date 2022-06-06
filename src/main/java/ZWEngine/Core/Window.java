package ZWEngine.Core;

import ZWEngine.Util.Color;
import ZWEngine.Util.Input;
import ZWEngine.Util.Time;

import ZWorld.Game;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
    public static int width;
    public static int height;
    public String name;
    public Color color;
    private long glfwWindow;

    public Game game;

    boolean mouse_enabled = true;
    boolean draw_wireframe = true;

    public Window(int width, int height, String name, Color color) {
        this.width = width;
        this.height = height;
        this.name = name;
        this.color = color;
    }

    public long GetGLFWwindow() {
        return glfwWindow;
    }
    
    private static GLFWFramebufferSizeCallback resizeWindow = new GLFWFramebufferSizeCallback() {
		@Override
		public void invoke(long window, int width, int height) {
			Window.width = width;
			Window.height = height;
			//Window win = (Window)glfwGetWindowUserPointer(window);			
			glViewport(0, 0, width, height);			
		}
	}; 
    		
    		
    public void Start() {
        GLFWErrorCallback.createPrint(System.err).set();
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
        
        glfwWindow = glfwCreateWindow(width, height, name, NULL, NULL);

        if (glfwWindow == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwMakeContextCurrent(glfwWindow);
        glfwSwapInterval(0);
        glfwShowWindow(glfwWindow);
        GL.createCapabilities();                       

        glfwSetKeyCallback(glfwWindow, Input::keyCallback);
        glfwSetMouseButtonCallback(glfwWindow, Input::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, Input::mouseScrollCallback);
        glfwSetCursorPosCallback(glfwWindow, Input::mousePosCallback);
        glfwSetFramebufferSizeCallback(glfwWindow, resizeWindow);
        
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_BLEND);

        game = new Game();      
        game.window = this;
        game.OnWindowCreation();
        game.Start();
    }

    public void Update() {
        GL.createCapabilities();
        glClearColor(color.r, color.g, color.b, color.a);              

        while ( !glfwWindowShouldClose(glfwWindow) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            if (Input.GetKeyDown(GLFW_KEY_ESCAPE)) {
                break;
            }

            if (Input.GetKeyDown(GLFW_KEY_P)) {
                if (mouse_enabled) {
                    glfwSetInputMode(GetGLFWwindow(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
                    mouse_enabled = false;
                }
                else {
                    glfwSetInputMode(GetGLFWwindow(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);
                    mouse_enabled = true;
                }
            }

            if (Input.GetKeyDown(GLFW_KEY_J)) {
                if (draw_wireframe) {
                    glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
                    draw_wireframe = false;
                }
                else {
                    glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
                    draw_wireframe = true;
                }
            }

            game.Update();

            Input.endKeyFrame();
            Input.endMouseFrame();
            
            Time.OnFrameEnd();

            glfwSwapBuffers(glfwWindow);
            glfwPollEvents();
        }
    }

    public void Destroy() {
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}
