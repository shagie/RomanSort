package net.shagie.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class Util {
    private static final Logger LOG = LoggerFactory.getLogger(Util.class);

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
                            yield ((state < 3) ? 5 : -5);
                        }
                        case "X" -> {
                            if (state < 4) {
                                state = 3;
                            }
                            yield ((state < 4) ? 10 : -10);
                        }
                        case "L" -> {
                            if (state < 5) {
                                state = 4;
                            }
                            yield ((state < 5) ? 50 : -50);
                        }
                        case "C" -> {
                            if (state < 6) {
                                state = 5;
                            }
                            yield ((state < 6) ? 100 : -100);
                        }
                        case "D" -> {
                            if (state < 7) {
                                state = 6;
                            }
                            yield ((state < 7) ? 500 : -500);
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
        return "";
    }
}
