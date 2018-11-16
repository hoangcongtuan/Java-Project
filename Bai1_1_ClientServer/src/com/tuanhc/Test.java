package com.tuanhc;

public class Test {
    public static void main(String[] args) {
        String str = "a b";
        System.out.println(countWord(str));
    }

    public static String delSpace(String str) {
        String res = "";
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (res.length() > 0) {
                if (res.charAt(res.length() - 1) == ' ' && c == ' ')
                    continue;
                else res += c;
            }
            else
                res += c;
        }
        return res;
    }

    public static int countWord(String str) {
        str = " " + str + " ";
        int count = 0;
        boolean last_char_is_space = true;
        for(int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                last_char_is_space = true;
                continue;
            }

            if (last_char_is_space) {
                count++;
                last_char_is_space = false;
            }
        }
        return count;
    }

    public static String dellSpace2(String str) {
        String res = "";
        for(int i = 0; i < str.length(); i++) {
            while ((str.charAt(i) == ' ') && (i < str.length() - 1))
                i++;

            if (i == str.length() - 1)
                break;

            if (res.length() != 0)
                res += " ";

            while (str.charAt(i) != ' ' && i < str.length() - 1) {
                res += str.charAt(i);
                i++;
            }
        }

        return res;
    }
}
