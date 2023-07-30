package me.ymir.mongoy;

import java.util.regex.Pattern;

public class CaseInsensitiveString {
    private String string;

    public CaseInsensitiveString(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }

    public Pattern compile() {
        return Pattern.compile(string, Pattern.CASE_INSENSITIVE);
    }
}
