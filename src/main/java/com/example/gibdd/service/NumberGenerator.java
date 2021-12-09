package com.example.gibdd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class NumberGenerator {

    public static final String LETTERS = "АВЕКМНОРСТУХ";
    private static final String DIGITS = "0123456789";
    private boolean useLetters;
    private boolean useDigits;

    private NumberGenerator(NumberGeneratorBuilder builder) {
        this.useLetters = builder.useUpper;
        this.useDigits = builder.useDigits;
    }

    public static class NumberGeneratorBuilder {
        private boolean useUpper;
        private boolean useDigits;

        public NumberGeneratorBuilder() {
            this.useUpper = false;
            this.useDigits = false;
        }

        public NumberGeneratorBuilder useLetters(boolean useUpper) {
            this.useUpper = useUpper;
            return this;
        }

        public NumberGeneratorBuilder useDigits(boolean useDigits) {
            this.useDigits = useDigits;
            return this;
        }

        public NumberGenerator build() {
            return new NumberGenerator(this);
        }
    }

    public String generate(int length) {

        StringBuilder resultSequence = new StringBuilder(length);
        Random random = new Random(System.nanoTime());

        List<String> charSequences = new ArrayList<>(3);
        if (useLetters) {
            charSequences.add(LETTERS);
        }
        if (useDigits) {
            charSequences.add(DIGITS);
        }
        for (int i = 0; i < length; i++) {
            String charSequence = charSequences.get(random.nextInt(charSequences.size()));
            int position = random.nextInt(charSequence.length());
            resultSequence.append(charSequence.charAt(position));
        }
        return new String(resultSequence);
    }
}
