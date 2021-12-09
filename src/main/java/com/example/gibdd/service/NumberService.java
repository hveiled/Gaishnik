package com.example.gibdd.service;

import com.example.gibdd.model.VehicleRegistrationNumber;
import com.example.gibdd.repository.VehicleRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NumberService {

    private final VehicleRegistrationRepository registrationRepository;
    private static int changingLetterIndex;

    static {
        changingLetterIndex = 2;
    }

    public String getRandomNumber() {
        VehicleRegistrationNumber number = new VehicleRegistrationNumber();
        do {
            NumberGenerator lettersGenerator = new NumberGenerator.NumberGeneratorBuilder()
                    .useLetters(true)
                    .useDigits(false)
                    .build();
            NumberGenerator numbersGenerator = new NumberGenerator.NumberGeneratorBuilder()
                    .useLetters(false)
                    .useDigits(true)
                    .build();
            number.setPrefix(lettersGenerator.generate(1));
            number.setNumericValue(numbersGenerator.generate(3));
            number.setPostfix(lettersGenerator.generate(2));
            number.setGrantedNumber(number.toString());
        } while (registrationRepository.existsByGrantedNumber(number.getGrantedNumber()));
        registrationRepository.save(number);
        return number.toString();
    }

    public String getNextNumber() {
        VehicleRegistrationNumber lastNumber = registrationRepository.getById(registrationRepository.count());
        VehicleRegistrationNumber newNumber = new VehicleRegistrationNumber();
        StringBuilder letters = new StringBuilder(lastNumber.getPrefix() + lastNumber.getPostfix());
        int numericValue = Integer.parseInt(lastNumber.getNumericValue());
        numericValue++;
        if (numericValue > 999) {
            numericValue = 0;
            int index = NumberGenerator.LETTERS.lastIndexOf(letters.charAt(changingLetterIndex));
            index++;
            if (NumberGenerator.LETTERS.length() <= index) {
                index = 0;
                changingLetterIndex--;
                if (changingLetterIndex < 0) {
                    changingLetterIndex = letters.length() - 1;
                }
            }
            letters.setCharAt(changingLetterIndex, NumberGenerator.LETTERS.charAt(index));
        }
        newNumber.setPrefix(letters.substring(0,1));
        newNumber.setNumericValue(String.format("%03d", numericValue));
        newNumber.setPostfix(letters.substring(1,letters.length()));
        newNumber.setGrantedNumber(newNumber.toString());
        registrationRepository.save(newNumber);
        return newNumber.toString();
    }
}
