package ZWEngine.Util;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

import static org.lwjgl.glfw.GLFW.*;

import ZWEngine.Math.Vector2f;

public class Input {
    // Key Input
    private static boolean keyPressed[] = new boolean[350];
    private static boolean keyDown[] = new boolean[350];
    private static boolean keyUp[] = new boolean[350];

    private Input() {}

    public static void keyCallback(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            Input.keyPressed[key] = true;
            Input.keyDown[key] = true;
        } else if (action == GLFW_RELEASE) {
            Input.keyPressed[key] = false;
            Input.keyUp[key] = false;
        }
    }

    public static boolean GetKey(int keyCode) {
        return Input.keyPressed[keyCode];
    }

    public static boolean GetKeyDown(int keyCode) {
        return Input.keyDown[keyCode];
    }

    public static boolean GetKeyUp(int keyCode) {
        return Input.keyUp[keyCode];
    }

    public static void endKeyFrame() {
        keyDown = new boolean[350];
        keyUp = new boolean[350];
    }
    
    public static Vector2f GetMovement() {
    	float x = 0.0f;
    	float y = 0.0f;
    	
    	if (GetKey(GLFW_KEY_A))
    		x = -1.0f;
    	else if (GetKey(GLFW_KEY_D))
    		x = 1.0f;
    	
    	if (GetKey(GLFW_KEY_W)) 
    		y = -1.0f;
    	else if (GetKey(GLFW_KEY_S))
    		y = 1.0f;
    	
    	return new Vector2f(x, y);
    }
 

    // Mouse Input

    private static float scroll;
    private static Vector2f position = new Vector2f();
    private static Vector2f lastPosition = new Vector2f();
    private static double scrollX = 0.0f, scrollY = 0.0f;
    private static double xPos = 0.0f, yPos = 0.0f, lastY = 0.0f, lastX = 0.0f;
    private static boolean MouseHold[] = new boolean[3];
    private static boolean mButtonDown[] = new boolean[3];
    private static boolean mButtonUp[] = new boolean[3];
    private static boolean isDragging;

    public static void mousePosCallback(long window, double xpos, double ypos) {
        Input.lastPosition = new Vector2f(Input.position.x, Input.position.y);
        Input.position = new Vector2f((float)xpos, (float)ypos);
        Input.isDragging = Input.MouseHold[0] || Input.MouseHold[1] || Input.MouseHold[2];
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            if (button < Input.MouseHold.length) {
                Input.MouseHold[button] = true;
                Input.mButtonDown[button] = true;
            }
        } else if (action == GLFW_RELEASE) {
            if (button < Input.MouseHold.length) {
                Input.MouseHold[button] = false;
                Input.isDragging = false;
                Input.mButtonUp[button] = true;
            }
        }
    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        Input.scroll = (float)yOffset;
        Input.scrollX = xOffset;
        Input.scrollY = yOffset;
    }

    public static void endMouseFrame() {
        Input.scroll = 0;
        Input.scrollX = 0;
        Input.scrollY = 0;
        Input.lastPosition = new Vector2f(Input.position.x, Input.position.y);
        Input.lastX = Input.xPos;
        Input.lastY = Input.yPos;

        mButtonUp = new boolean[3];
        mButtonDown = new boolean[3];
    }

    public static Vector2f GetMousePosition() {
        return Input.position;
    }

    public static Vector2f GetDeltaMousePosition() {
        return Vector2f.sub(Input.lastPosition, Input.position);
    }

    public static Vector2f GetLastMousePos() {
        return Input.lastPosition;
    }

    public static float GetScroll() {
        return Input.scroll;
    }

    public static boolean isDragging() {
        return Input.isDragging;
    }

    public static boolean GetMouseButton(int button) {
        if (button < Input.MouseHold.length) {
            return Input.MouseHold[button];
        } else {
            return false;
        }
    }

    public static boolean GetMouseButtonDown(int button) {
        if (button < Input.mButtonDown.length) {
            return Input.mButtonDown[button];
        } else {
            return false;
        }
    }

    public static boolean GetMouseButtonUp(int button) {
        if (button < Input.mButtonUp.length) {
            return Input.mButtonUp[button];
        } else {
            return false;
        }
    }
}
