package org.example.UtilityClasses;

public final class Cryptographer {
    public static String hashPassword(String password) {
        StringBuilder result = new StringBuilder();
        String salt = password.length() % 2 == 0 ? "131" : "59-0";
        for (int i = password.length()-1; i >= 0; i--) {
            result.append(password.charAt(i));
            result.append(salt);
        }
        return result.toString();
    }
}
