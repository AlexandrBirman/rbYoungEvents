package util;

public class StringsUtil{

    public static String deleteCommos(String string){

        try {
            if (string.charAt(0) == 34 && string.charAt(string.length() - 1) == 34){
                return string.substring(1, string.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }
}
