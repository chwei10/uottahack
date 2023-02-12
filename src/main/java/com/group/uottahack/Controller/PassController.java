package com.group.uottahack.Controller;

import com.group.uottahack.Model.Passphrase;
import com.group.uottahack.Model.PassphraseDto;
import com.group.uottahack.Model.Password;
import com.group.uottahack.Model.PasswordDto;
import com.group.uottahack.Service.PassphraseService;
import com.group.uottahack.Service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PassController {
    private final PasswordService passwordService;
    private final PassphraseService passphraseService;

//    private PasswordDto passwordDto;
    @RequestMapping("/api/password/{passwordLength}/{num}/{cap}/{sym}")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PasswordDto> generatePassword(@PathVariable("passwordLength") int passwordLength, @PathVariable("num") boolean num,
                                                        @PathVariable("cap") boolean cap, @PathVariable("sym") boolean sym){
        Password password = passwordService.generate(passwordLength, num, cap, sym);
        PasswordDto passwordDto = new PasswordDto(password.getPassword());
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<PasswordDto> response = new ResponseEntity<>(passwordDto,headers,HttpStatus.OK);
        return response;
    }

    @RequestMapping("/api/passphrase/{passphraseLength}/{num}/{cap}/{sym}")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PassphraseDto> generatePassphrase(@PathVariable("passphraseLength") int passphraseLength, @PathVariable("num") boolean num,
                                                            @PathVariable("cap") boolean cap, @PathVariable("sym") boolean sym){
        Passphrase passphrase = passphraseService.generate(passphraseLength, num, cap, sym);
        PassphraseDto passphraseDto = new PassphraseDto(passphrase.getPassphrase());
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<PassphraseDto> response = new ResponseEntity<>(passphraseDto,headers,HttpStatus.OK);
        return response;
    }
}
