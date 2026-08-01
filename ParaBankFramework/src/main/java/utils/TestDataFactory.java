package utils;

import com.github.javafaker.Faker;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Thread-safe factory for generating dynamic test data using Faker.
 * Supports parallel execution by using thread-local Faker instances.
 */
public class TestDataFactory {

    private static final ThreadLocal<Faker> threadLocalFaker = ThreadLocal.withInitial(Faker::new);

    private TestDataFactory() {
        // Private constructor to prevent instantiation
    }

    /**
     * Get thread-local Faker instance for parallel execution safety
     */
    private static Faker getFaker() {
        return threadLocalFaker.get();
    }

    /**
     * Generate a unique username with UUID to prevent collisions in parallel execution
     */
    public static String generateUniqueUsername() {
        //String baseUsername = getFaker().name().username().replaceAll("[^a-zA-Z0-9]", "");
        String baseUsername = "saurav";
        String uuidSuffix = UUID.randomUUID().toString().substring(0, 4);
        //return baseUsername + "_" + System.currentTimeMillis() + "_" + uuidSuffix;
        return baseUsername + "_" + uuidSuffix;
    }

    /**
     * Generate a unique email address
     */
    public static String generateUniqueEmail() {
        String baseEmail = getFaker().internet().emailAddress();
        String uuidSuffix = UUID.randomUUID().toString().substring(0, 8);
        return baseEmail.replace("@", "_" + uuidSuffix + "@");
    }

    /**
     * Generate a random password
     */
    public static String generatePassword() {
        return getFaker().internet().password(8, 16, true, true, true);
    }

    /**
     * Generate a first name
     */
    public static String generateFirstName() {
        return getFaker().name().firstName();
    }

    /**
     * Generate a last name
     */
    public static String generateLastName() {
        return getFaker().name().lastName();
    }

    /**
     * Generate a full address
     */
    public static String generateAddress() {
        return getFaker().address().fullAddress();
    }

    /**
     * Generate a city
     */
    public static String generateCity() {
        return getFaker().address().city();
    }

    /**
     * Generate a state
     */
    public static String generateState() {
        return getFaker().address().state();
    }

    /**
     * Generate a zip code
     */
    public static String generateZipCode() {
        return getFaker().address().zipCode();
    }

    /**
     * Generate a phone number
     */
    public static String generatePhoneNumber() {
        return getFaker().phoneNumber().cellPhone();
    }

    /**
     * Generate a social security number (SSN)
     */
    public static String generateSSN() {
        return getFaker().idNumber().ssnValid();
    }

    /**
     * Generate complete user registration data with unique identifiers
     */
    public static UserRegistrationData generateUserRegistrationData() {
        return UserRegistrationData.builder()
                .firstName(generateFirstName())
                .lastName(generateLastName())
                .address(generateAddress())
                .city(generateCity())
                .state(generateState())
                .zipCode(generateZipCode())
                .phoneNumber(generatePhoneNumber())
                .ssn(generateSSN())
                .username(generateUniqueUsername())
                .password(generatePassword())
                .repeatPassword(null) // Will be set separately
                .build();
    }

    /**
     * Clean up thread-local Faker instance
     */
    public static void cleanup() {
        threadLocalFaker.remove();
    }

    /**
     * Data class for user registration information
     */
    @Data
    @Builder
    public static class UserRegistrationData {
        private String firstName;
        private String lastName;
        private String address;
        private String city;
        private String state;
        private String zipCode;
        private String phoneNumber;
        private String ssn;
        private String username;
        private String password;
        private String repeatPassword;

        /**
         * Set repeat password to match password
         */
        public UserRegistrationData withRepeatPassword() {
            this.repeatPassword = this.password;
            return this;
        }
    }
}
