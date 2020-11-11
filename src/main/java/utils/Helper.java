package utils;

public class Helper {

    public String removeSpaces(String a) {
        String withoutSpaces = "";
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != ' ') {
                withoutSpaces += a.charAt(i);
            }
        }
        return withoutSpaces;
    }
}
