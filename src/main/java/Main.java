import ZWEngine.Core.Window;
import ZWEngine.Util.Color;

public class Main {
    public static void main(String[] args) {
        Window window = new Window(1920, 1080, "ZWorld", new Color(0.1f, 0.1f, 0.1f, 1.0f));
        window.Start();
        window.Update();
        window.Destroy();
    }
}
