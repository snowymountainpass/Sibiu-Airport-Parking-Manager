package com.clockworkcode.sibiuairportparkingmanager.util;

import java.util.regex.*;

public class Validator {

    public static final String licensePlateValidPattern = "^(?!.*(?:DROP\\s+(?:TABLE|DATABASE)|TRUNCATE\\s+TABLE|ALTER\\s+TABLE|UPDATE|DELETE|GRANT|REVOKE|INSERT\\s+INTO|CREATE\\s+(?:TABLE|INDEX)|DROP\\s+INDEX)).*[\\p{L}\\p{N}-]+$";
    public static final String airportNameValidPattern = "^(?!.*(?:DROP\\s+(?:TABLE|DATABASE)|TRUNCATE\\s+TABLE|ALTER\\s+TABLE|UPDATE|DELETE|GRANT|REVOKE|INSERT\\s+INTO|CREATE\\s+(?:TABLE|INDEX)|DROP\\s+INDEX))(?=[\\p{L}\\s-]{1,150}$).*";
    public static final String airportCodeValidPattern = "^[a-zA-Z]{0,4}$";
    public static final String costValidPattern = "^[1-9]\\d*$";
    public static final String parkingSpaceNamePattern = "^(?!0000)[0-9]{4}$";

    public static boolean isValidPattern(String input,String regExPattern) {
        Pattern pattern = Pattern.compile(regExPattern);
        Matcher matcher = pattern.matcher(input);// match input against the pattern
        return matcher.matches();
    }

}
