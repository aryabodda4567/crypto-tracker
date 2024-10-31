package org.project.sih.core.utility;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

public class Utility {
    private static final Random random = new Random();

    // Generates a random string of the specified length using alphanumeric characters
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }

    // Generates a random double within a specified range
    public static double generateRandomDouble(int length) {
        // Example: Generates a double between 0 and the specified length
        return random.nextDouble() * length;
    }

    // Generates a random date within a specific range
    public static Date generateRandomDate() {
        long currentTimeMillis = System.currentTimeMillis();
        // Generate a random number of days in the past (up to 365 days)
        long randomDaysInPast = random.nextInt(365) * 24 * 60 * 60 * 1000L; // Days to milliseconds
        long randomDateMillis = currentTimeMillis - randomDaysInPast;

        return new Date(randomDateMillis);
    }

    // Returns a random string selected from the provided list
    public static String generateRandomStringFromList(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null; // Handle empty list case
        }
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
