package com.group.uottahack.Controller;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        System.out.println(generateCommonLangPassword(10, false, false, false));
    }

    public static String  generateCommonLangPassword(int length, boolean num, boolean cap, boolean sym) {
        String upperCaseLetters = RandomStringUtils.random(cap ? 40 : 0, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(40, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(num ? 40 : 0);
        String specialChar = RandomStringUtils.random(sym ? 40 : 0, 33, 47, false, false);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password.substring(0,length);
    }
}
