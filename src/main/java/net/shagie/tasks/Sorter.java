package net.shagie.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("java:S106")
public class Sorter {
    private static final Logger LOG = LoggerFactory.getLogger(Sorter.class);

    public static void main(String[] args) {
        List<String> numbers = new ArrayList<>();

        Path path = Paths.get("numbers.txt");
        try {
            numbers = Files.readAllLines(path);
        } catch (IOException e) {
            LOG.error("Error reading numbers.txt", e);
        }

        numbers.stream()
                .map(s -> {
                    int arabic = Util.romanToArabic(s);
                    String simple = Util.arabicToRoman(arabic);
                    return new Roman(s, arabic, simple);
                })
                .sorted(Comparator.comparing(Roman::numeric))
                .map(Roman::simplified)
                .forEach(System.out::println);
    }
}
