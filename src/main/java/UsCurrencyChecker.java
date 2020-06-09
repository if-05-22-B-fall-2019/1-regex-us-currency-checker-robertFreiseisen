

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsCurrencyChecker {
    public static Matcher getMatcher(String usCurrencyString) {
        String regex = "\\$\\s?\\d+(\\.\\d{1,2})?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(usCurrencyString);

        return matcher;
    }
}
