package net.shagie.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Util {
    private static final Logger LOG = LoggerFactory.getLogger(Util.class);
    private static final NavigableMap<Integer, String> A2RMAP = new TreeMap<>();
    private static final Map<String, Integer> R2AMAP = new HashMap<>();

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

        R2AMAP.put("I", 1);
        R2AMAP.put("V", 5);
        R2AMAP.put("X", 10);
        R2AMAP.put("L", 50);
        R2AMAP.put("C", 100);
        R2AMAP.put("D", 500);
        R2AMAP.put("M", 1000);
    }

    public static int romanToArabic(String roman) {
        final AtomicInteger state = new AtomicInteger(1);
        LOG.debug("Got {}", roman);
        String reversed = new StringBuilder(roman).reverse().toString().toUpperCase(Locale.ROOT);

        return Arrays.stream(reversed.split(""))
                .mapToInt(c -> {
                    int lookup = R2AMAP.getOrDefault(c, 0);
                    state.set(Math.max(state.get(), lookup));
                    lookup *= state.get() == lookup ? 1 : -1;
                    return lookup;
                }).sum();
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
