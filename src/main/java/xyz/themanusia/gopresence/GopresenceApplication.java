package xyz.themanusia.gopresence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class GopresenceApplication {
    public static String GOOGLE_CALENDAR_KEY;

    public static void main(String[] args) {
        GOOGLE_CALENDAR_KEY = args[0];
        SpringApplication.run(GopresenceApplication.class, args);
    }

}
