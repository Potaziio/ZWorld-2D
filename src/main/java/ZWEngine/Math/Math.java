package ZWEngine.Math;

import java.util.Random;

public class Math {
	
	private static Random rand = new Random();
	
    public static float ClampF(float min, float max, float val) {
        float v = val;

        if (v > max)
            v = max;
        if (v < min)
            v = min;

        return v;
    }
    
    public static int to1DArray(int x, int y, int z, int height, int depth) {
    	return (x * (depth * height) + (y + height * z));
    }
    
    public static float RandF(float min, float max) {      
    	return rand.nextFloat(max - min) + min;
    }
    
    public static float RandF(float max) {
    	return rand.nextFloat(max);
    }   
    
    public static int RandI(int min, int max) {
    	return rand.nextInt(max - min) + min;
    }
    
    public static int RandI(int max) {
    	return rand.nextInt(max);
    }
    
}
