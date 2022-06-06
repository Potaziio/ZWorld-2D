package ZWEngine.ECS;

import ZWEngine.ECS.Components.Component;
import ZWEngine.ECS.Components.Transform;
import ZWEngine.Util.Logger;
import ZWorld.World.World;
import org.joml.Vector2f;

import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.List;

public class Entity {
    public String name;
    public String id;
    public Transform transform;
    public List<Component> components = new ArrayList<Component>(5);
    public World world;


    public <T extends Component> T GetComponent(Class<T> component) {
        for (Component c : components)
            if (component.isAssignableFrom(c.getClass())) {
                try {
                    return component.cast(c);
                } catch (ClassCastException e) {
                    e.printStackTrace();
                    Logger.Log(Logger.LogLevel.ERROR, "Error casting component.");
                }
            }

        return null;
    }
    public void AddComponent(Component component) {
        if (components.contains(component)) {
            Logger.Log(Logger.LogLevel.WARNING, String.format("[%s] Skipping component: [%s], Entity has one already.", name, component.getClass().getName()));
            return;
        }

        component.entity = this;
        components.add(component);
        component.Start();
    }

    public void AddComponent(Component... comps) {
        for (Component c : components) {
            AddComponent(c);
        }
    }

    public <T extends Component> void RemoveComponent(Class<T> component) {
        for (int i = 0; i < components.size(); i++) {
            Component c = components.get(i);
            if (component.isAssignableFrom(c.getClass())) {
                components.remove(i);
                return;
            }
        }
    }

    public Entity(String name, World world) {
        //Logger.Log(Logger.LogLevel.MESSAGE,"Entity Created: " + name);
        this.name = name;
        this.transform = new Transform();
        this.world = world;
        AddComponent(this.transform);
    }
    public Entity(String name, World world, Transform transform) {
        //Logger.Log(Logger.LogLevel.MESSAGE,"Entity Created: " + name);
        this.name = name;
        this.transform = transform;
        this.world = world;
        AddComponent(this.transform);
    }

    public Entity(String name, World world, Component... cArgs) {
    	//Logger.Log(Logger.LogLevel.MESSAGE,"Entity Created: " + name);
        this.name = name;
        this.world = world;

        for (Component c : cArgs) {
            AddComponent(c);
        }

        this.AddComponent(new Transform());
        this.transform = GetComponent(Transform.class);
    }
    public void Start() {
    	//Logger.Log("Entity Started: " + this.name);
    	
    }
    public void Update() {}
}
