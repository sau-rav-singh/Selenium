package utils;

import com.github.javafaker.Faker;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Thread-safe factory for generating dynamic test data using Faker.
 * Supports parallel execution by using thread-local Faker instances.
 */
public class TestDataFactory {

    private static final ThreadLocal<Faker> threadLocalFaker = ThreadLocal.withInitial(Faker::new);

    private TestDataFactory() {
        // Private constructor to prevent instantiation
    }

    private static Faker getFaker() {
        return threadLocalFaker.get();
    }

    public static String generateUniqueUsername() {
        String baseUsername = getFaker().name().username().replaceAll("[^a-zA-Z0-9]", "");
        String uuidSuffix = UUID.randomUUID().toString().substring(0, 4);
        return baseUsername + "_" + System.currentTimeMillis() + "_" + uuidSuffix;
    }

    public static String generatePassword() {
        return getFaker().internet().password(8, 16, true, true, true);
    }

    public static String generateFirstName() {
        return getFaker().name().firstName();
    }

    public static String generateLastName() {
        return getFaker().name().lastName();
    }

    public static String generateAddress() {
        return getFaker().address().fullAddress();
    }

    public static String generateCity() {
        return getFaker().address().city();
    }

    public static String generateState() {
        return getFaker().address().state();
    }

    public static String generateZipCode() {
        return getFaker().address().zipCode();
    }

    public static String generatePhoneNumber() {
        return getFaker().phoneNumber().cellPhone();
    }

    public static String generateSSN() {
        return getFaker().idNumber().ssnValid();
    }

    public static UserRegistrationData generateUserRegistrationData() {
        return UserRegistrationData.builder().firstName(generateFirstName()).lastName(generateLastName()).address(generateAddress()).city(generateCity()).state(generateState()).zipCode(generateZipCode()).phoneNumber(generatePhoneNumber()).ssn(generateSSN()).username(generateUniqueUsername()).password(generatePassword()).repeatPassword(null) // Will be set separately
                .build();
    }

    public static void cleanup() {
        threadLocalFaker.remove();
    }

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
