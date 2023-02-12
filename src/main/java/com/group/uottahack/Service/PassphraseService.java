package com.group.uottahack.Service;

import com.group.uottahack.Model.Passphrase;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassphraseService {
    private Passphrase passphrase;
    public Passphrase generate(int passwordLength, boolean num, boolean cap, boolean sym) {
        Passphrase passphrase = new Passphrase();
        passphrase.setPassphrase(generateCommonLangPassword(passwordLength,num, cap, sym));
        return passphrase;
    }

    public String generateCommonLangPassword(int length, boolean num, boolean cap, boolean sym) {
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
