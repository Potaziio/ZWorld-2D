package ZWEngine.Util;

public class Color {
    public float r, g, b, a;

    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color Normalize() {
        // Normalizes color to range 0, 1

        this.r /= 255.0f;
        this.g /= 255.0f;
        this.b /= 255.0f;
        this.a /= 255.0f;

        return this;
    }

    public Color normalized() {
        // Normalizes color without changing its values

        return new Color(
                this.r / 255.0f,
                this.g / 255.0f,
                this.b / 255.0f,
                this.a / 255.0f);
    }
    
    public String toString() {
    	return Float.toString(r) + ", " + Float.toString(g) + ", " + Float.toString(b) + ", " + Float.toString(a);
    }
    
    public Color denormalize() {
    	this.r *= 255.0f;
    	this.g *= 255.0f;
    	this.b *= 255.0f;
    	this.a *= 255.0f;
    	
    	return this;
    }
    
    public Color denormalized() {
    	return new Color(
    			this.r * 255.0f, 
    			this.g * 255.0f,
    			this.b * 255.0f,
    			this.a * 255.0f);
    }
}
