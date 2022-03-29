package net.shagie.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Sorter {
    private static final Logger LOG = LoggerFactory.getLogger(Sorter.class);

    public static void main(String[] args) {
        Path path = Paths.get("numbers.txt");
        try {
            String read = Files.readAllLines(path).get(0);
            System.out.println(read);
        } catch (IOException e) {
            LOG.error("Error reading numbers.txt", e);
        }
    }
}
