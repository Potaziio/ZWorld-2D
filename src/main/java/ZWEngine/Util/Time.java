package ZWEngine.Util;

import org.lwjgl.glfw.GLFW;

public class Time {
    private static float dt = -1.0f;
    private static float startTime;
    private static float endTime;

    public Time() {
        startTime = GetMilliseconds();
        endTime = GetMilliseconds();
    }

    public static void OnFrameEnd() {
        endTime = GetMilliseconds();
        dt = endTime - startTime;
        startTime = endTime;
    }

    public static float deltaTime() {
        return Time.dt;
    }

    public static float GetMilliseconds() {
        return (float)GLFW.glfwGetTime();
    }
}
