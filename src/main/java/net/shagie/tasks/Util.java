package net.shagie.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Util {
    private static final Logger LOG = LoggerFactory.getLogger(Util.class);
    private static final NavigableMap<Integer, String> A2RMAP = new TreeMap<>();

    private Util() {
        // Empty constructor - do not instantiate
    }

    static {
        A2RMAP.put(1, "I");
        A2RMAP.put(4, "IV");
        A2RMAP.put(5, "V");
        A2RMAP.put(9, "IX");
        A2RMAP.put(10, "X");
        A2RMAP.put(40, "XL");
        A2RMAP.put(50, "L");
        A2RMAP.put(90, "XC");
        A2RMAP.put(100, "C");
        A2RMAP.put(400, "CD");
        A2RMAP.put(500, "D");
        A2RMAP.put(900, "CM");
        A2RMAP.put(1000, "M");
    }

    public static int romanToArabic(String roman) {
        int state = 1;
        int sum = 0;
        LOG.debug("Got {}", roman);
        String reversed = new StringBuilder(roman).reverse().toString().toUpperCase(Locale.ROOT);

        for (String c : reversed.split("")) {
            LOG.debug("  state: {}, sum: {}; c: {}", state, sum, c);
            sum +=
                    switch (c) {
                        case "I" -> ((state < 2) ? 1 : -1);
                        case "V" -> {
                            if (state < 3) {
                                state = 2;
                            }
                            yield 5 * ((state < 3) ? 1 : -1);
                        }
                        case "X" -> {
                            if (state < 4) {
                                state = 3;
                            }
                            yield 10 * ((state < 4) ? 1 : -1);
                        }
                        case "L" -> {
                            if (state < 5) {
                                state = 4;
                            }
                            yield 50 * ((state < 5) ? 1 : -1);
                        }
                        case "C" -> {
                            if (state < 6) {
                                state = 5;
                            }
                            yield 100 * ((state < 6) ? 1 : -1);
                        }
                        case "D" -> {
                            if (state < 7) {
                                state = 6;
                            }
                            yield 500 * ((state < 7) ? 1 : -1);
                        }
                        case "M" -> {
                            state = 7;
                            yield 1000;
                        }
                        default -> {
                            LOG.error("Invalid character {}", c);
                            yield 0;
                        }
                    };
            LOG.debug("    sum: {}", sum);
        }

        return sum;

    }

    public static String arabicToRoman(int num) {
        if (num < 1) {
            throw new NumberFormatException("Negative and zero roman numerals are not defined");
        }
        int tmp = num;
        StringBuilder rv = new StringBuilder();
        while (tmp > 0) {
            int key = A2RMAP.floorKey(tmp);
            rv.append(A2RMAP.get(key));
            tmp -= key;
        }
        return rv.toString();
    }
}
