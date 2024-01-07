package utils;

public class StringTools {
    public static String formatString(String inp){
        inp = inp.trim();
        while (inp.contains("  ")) {
            inp = inp.replaceAll("\\s\\s", " ");
        }
        return inp;
    }
}
