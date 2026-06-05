package api.utils;

import net.datafaker.Faker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TestDataFactory {
    private String name;
    private String email;
    private Integer age;

    /**
    public TestDataFactory() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        Random random = new Random();
        this.name = "Test User: " + LocalDateTime.now().format(format);
        this.email = "test" +  LocalDateTime.now().format(format) + "@gmail.com";
        this.age = random.nextInt(150) + 1;
    }**/

    public TestDataFactory(){
        Faker faker = new Faker();
        this.name = faker.name().fullName();
        this.email = faker.internet().emailAddress();
        this.age = faker.number().numberBetween(1,150);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }
}
