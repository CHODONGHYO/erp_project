package com.erp.ezen25.etc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class KorToEng {
    private final Map<Character, String> koreanToRomanMap = createKoreanToRomanMap();


    public String convertToRoman(String korean) {
        StringBuilder romanStringBuilder = new StringBuilder();

        for (char c : korean.toCharArray()) {
            String roman = koreanToRomanMap.get(c);
            if (roman != null) {
                romanStringBuilder.append(roman);
            } else {
                romanStringBuilder.append(c);
            }
        }

        return romanStringBuilder.toString();
    }

    private Map<Character, String> createKoreanToRomanMap() {
        Map<Character, String> map = new HashMap<>();
        map.put('ㄱ', "r");
        map.put('ㄴ', "s");
        map.put('ㄷ', "e");
        map.put('ㄹ', "f");
        map.put('ㅁ', "a");
        map.put('ㅂ', "q");
        map.put('ㅅ', "t");
        map.put('ㅇ', "d");
        map.put('ㅈ', "w");
        map.put('ㅊ', "c");
        map.put('ㅋ', "z");
        map.put('ㅌ', "f");
        map.put('ㅍ', "v");
        map.put('ㅎ', "g");
        map.put('ㅏ', "k");
        map.put('ㅑ', "i");
        map.put('ㅓ', "j");
        map.put('ㅕ', "u");
        map.put('ㅗ', "h");
        map.put('ㅛ', "y");
        map.put('ㅜ', "n");
        map.put('ㅠ', "b");
        map.put('ㅡ', "m");
        map.put('ㅣ', "l");
        map.put('ㅐ', "o");
        map.put('ㅒ', "O");
        map.put('ㅔ', "p");
        map.put('ㅖ', "P");
        map.put('ㅘ', "hk");
        map.put('ㅙ', "ho");
        map.put('ㅚ', "hl");
        map.put('ㅝ', "nj");
        map.put('ㅞ', "np");
        map.put('ㅟ', "nl");
        map.put('ㅢ', "ml");

        return map;
    }
}