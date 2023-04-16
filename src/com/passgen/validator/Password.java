/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.passgen.validator;

/**
 *
 * @author eduar
 */
public class Password {

    private String[] exclude;
    private int minLength;
    private int maxLength;

    public Password() {
        this.exclude = null;
        this.minLength = 8;
        this.maxLength = 15;
    }

    public Password(int minLength, int maxLength, String exclude) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        if (exclude.equals("")) {
            this.exclude = null;
        } else {
            this.exclude = exclude.split(",");
        }

    }

    public void setExclude(String exclude) {
        if (exclude.equals("")) {
            this.exclude = null;
        } else {
            this.exclude = exclude.split(",");
        }
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public String Generate() {

        String newPassword = "";
        int limit = (int) (Math.random() * 100 % (this.maxLength - this.minLength)) - (int) (1 * Math.random() * 10 % 2);
        if (maxLength == minLength) {
            limit = -1;
        }

        for (int i = 0; i <= this.minLength + limit; i++) {
            newPassword += generateCharacter();
        }

        return newPassword;
    }

    private char generateCharacter() {
        int number = 0;
        do {
            number = (int) (Math.random() * 10000 % 255);
        } while (validate(number) || notFound(number));
        return (char) number;
    }

    private boolean validate(int number) {
        return !((number > 63 && number < 91)
                || (number > 96 && number < 123)
                || (number > 34 && number < 38)
                || (number > 47 && number < 58)
                || (number == 42)
                || (number == 43)
                || (number == 95));
    }

    private boolean notFound(int number) {

        if (exclude != null) {
            for (String ex : exclude) {
                if (number == Integer.valueOf(ex.charAt(0))) {
                    return true;
                }
            }
        }

        return false;
    }

}
