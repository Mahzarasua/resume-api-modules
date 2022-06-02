package com.mahzarasua.resumeapi.configuration.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
    public static boolean isValidString(String str) {
        if (str == null || str.isEmpty()) return false;
        // Check if str is comprised only of one or more whitespaces (unaccepted).
        Pattern patternString = Pattern.compile(
                "^\\s+$"
        );
        Matcher matcherString = patternString.matcher(str);
        return !matcherString.find();
    }

    public static boolean isValidEmail(String email) {
        // Regex for emails.
        Pattern patternEmail = Pattern.compile(
                "^[\\p{L}\\p{N}\\._%+-]+@[\\p{L}\\p{N}\\.\\-]+\\.[\\p{L}]{2,}$"
        );
        Matcher matcherEmail = patternEmail.matcher(email);
        return matcherEmail.find();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Regex for phone numbers starting with country code (+521234567891, for example).
        Pattern phonePattern = Pattern.compile("^\\+[0-9]{1,3}[0-9]{10}$");
        Matcher phoneMatcher = phonePattern.matcher(phoneNumber);
        return phoneMatcher.find();
    }
}