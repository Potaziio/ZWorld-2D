package ZWEngine.Util;

public class Logger {
    public static int Messages;
    public static int Warnings;
    public static int Errors;
    
    // Colors
    
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m"; 

    public enum LogLevel {
        MESSAGE,
        WARNING,
        ERROR,
    }
    
    public enum PrintMode {
    	SAME_LINE,
    	NEW_LINE
    }

    private static boolean show_colors = true;
    private static boolean disable_logs = false;   
    
    private static PrintMode printMode;

    public static <T> void Log(T message) {
    	if (disable_logs)
    		return;
    	
    	if (printMode == PrintMode.SAME_LINE)
    		System.out.print(String.format("[DEBUG::MESSAGE::%.2f] %s", Time.GetMilliseconds(), message));
    	else 
    		System.out.println(String.format("[DEBUG::MESSAGE::%.2f] %s", Time.GetMilliseconds(), message));
    }

    public static <T> void Log(LogLevel level, T message) {    	
    	if (disable_logs)
    		return;
    	
        switch (level) {
            case MESSAGE -> {
            	if (printMode == PrintMode.SAME_LINE) System.out.print("[DEBUG::MESSAGE] " + message);
            	else System.out.println("[DEBUG::MESSAGE] " + message);
            	
                Messages++;
            }
            case WARNING -> {
                if (show_colors)             	
                    System.out.print(ANSI_YELLOW);                               
                
                if (printMode == PrintMode.SAME_LINE) System.out.print("[DEBUG::WARNING] " + message);
                else System.out.println("[DEBUG::WARNING] " + message); 
                
                if (show_colors) {
                	if (printMode == PrintMode.SAME_LINE) System.out.print(ANSI_RESET);
                	else System.out.println(ANSI_RESET);
                }
                
                Warnings++;
            }
            case ERROR -> {
                if (show_colors) {
                    System.out.print(ANSI_RED);
                }
                
                if (printMode == PrintMode.SAME_LINE) System.out.print("[DEBUG::ERROR] " + message);
                else System.out.println("[DEBUG::ERROR] " + message);
                
                if (show_colors) {
                	if (printMode == PrintMode.SAME_LINE) System.out.print(ANSI_RESET);
                	else System.out.println(ANSI_RESET);
                }
                
                Errors++;
            }
        }
    }
    
    public static void SetPrintMode(PrintMode mode) {
    	printMode = mode;
    }

    public static void ShowColors(boolean f) {
        show_colors = f;
    }
    
    public static void DisableLogs(boolean f) {
    	disable_logs = f;
    }
}
