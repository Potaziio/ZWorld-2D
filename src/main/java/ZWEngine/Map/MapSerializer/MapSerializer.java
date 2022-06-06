package ZWEngine.Map.MapSerializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import ZWEngine.Map.Map;
import ZWEngine.Util.Logger;
import ZWEngine.Util.Logger.LogLevel;

public class MapSerializer {
	public String mapfile;
	static boolean openBrackets = false;
	static boolean closeBrackets = false;
	
	private static String handleCharacters(Reader reader) throws IOException {
		String mapString = new String(); 		
		int r;
	    while ((r = reader.read()) != -1) {	    		    
	    	char ch = (char) r;
	    	
	    	mapString += String.valueOf(ch);	    	 	  
	    }	   
	    
	    return mapString;
	}
	
	private static void handleFile(File file, Charset encoding, Map map) throws IOException {
		try (InputStream in = new FileInputStream(file);
			Reader reader = new InputStreamReader(in, encoding);
			
			Reader buffer = new BufferedReader(reader)) {
				map.map = handleCharacters(buffer);
			}
	}
	
	public static Map Deserialize(String location, int rowSize, int colSize) {
		Map map = new Map(rowSize, colSize);
		Logger.Log(String.format("Loading map: [%s]", location));

		String fileFormat = 
				String.valueOf(location.charAt(location.length() - 4)) + 
				String.valueOf(location.charAt(location.length() - 3)) + 
				String.valueOf(location.charAt(location.length() - 2)) +
				String.valueOf(location.charAt(location.length() - 1));

		if (!fileFormat.equals(".map"))  {
			Logger.Log(LogLevel.WARNING, String.format("Map file format not identified -> [%s] in map: [%s]", fileFormat, location));
		}

		try {
			Charset encoding = Charset.defaultCharset();
			File file = new File(location);
			handleFile(file, encoding, map);			
			map.file = location;		
			map.SetMapArray();
		} catch (IOException e) {
			e.printStackTrace();
			Logger.Log(LogLevel.ERROR, String.format("Could not load map: [%s] -> exception found, is this the right path??", location));
		}
		
		return map;
	}
}
