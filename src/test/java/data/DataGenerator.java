package data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    private static final Faker faker = new Faker(new Locale("ru"));

    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity(String locale) {
        String[] cities = new String[]{"Киров", "Иваново", "Волгоград", "Брянск", "Астрахань"};
        int n = (int)Math.floor(Math.random() * cities.length);

        return cities[n];
    }

    public static String generateName(String locale) {

        return faker.name().fullName().replace("ё","е");

    }

    public static String generatePhone(String locale) {

        return faker.phoneNumber().phoneNumber();
    }


}

