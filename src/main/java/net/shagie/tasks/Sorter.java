package net.shagie.tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Sorter {
    public static void main(String[] args) throws IOException {
        System.out.println("hello world");
        Path path = Paths.get("numbers.txt");
        String read = Files.readAllLines(path).get(0);
        System.out.println(read);
    }
}
