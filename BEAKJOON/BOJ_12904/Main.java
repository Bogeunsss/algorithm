package BEAKJOON.BOJ_12904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static Set<String> cache;
    public static String s, t;

    public static void input() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        t = br.readLine();

        cache = new HashSet<>();
    }

    public static boolean isMade() {

        StringBuilder sb;

        while(!s.equals(t)) {
            if(t.length() == 0) return false;
            if(t.charAt(t.length() - 1) == 'A') {
                t = t.substring(0, t.length() - 1);
            }else if(t.charAt(t.length() - 1) == 'B') {
                sb = new StringBuilder(t);
                t = sb.reverse().substring(1);
            }else return false;
        }
        return true;
    }

    public static void makeSameWord() {

        System.out.println(isMade() ? 1 : 0);
    }

    public static void main(String[] args) throws IOException {

        input();
        makeSameWord();
    }
}