package PROGRAMMERS.level_2._67257;

import java.util.*;

public class Solution {
    static char[] operator = {'+', '-', '*'};
    static List<Long> list;
    static List<Character> operators;
    static boolean[] visited;
    static long answer;

    public static void combination(char[] comb, int depth) {
        if(depth == comb.length) {
            List<Long> copiedList = new ArrayList<>(list);
            List<Character> copiedOperators = new ArrayList<>(operators);

            for(int i=0; i<comb.length; i++) {
                for(int j=0; j<copiedOperators.size(); j++) {
                    if(comb[i] == copiedOperators.get(j)) {
                        long leftHand = copiedList.remove(j);
                        long rightHand = copiedList.remove(j);
                        long calc = 0L;

                        switch (comb[i]) {
                            case '+': {
                                calc = leftHand + rightHand;
                                break;
                            }
                            case '-': {
                                calc = leftHand - rightHand;
                                break;
                            }
                            case '*': {
                                calc = leftHand * rightHand;
                                break;
                            }
                        }
                        copiedList.add(j, calc);
                        copiedOperators.remove(j);
                        j--;
                    }
                }
            }
            answer = Math.max(answer, Math.abs(copiedList.get(0)));
            return;
        }
        for(int i=0; i<comb.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                comb[depth] = operator[i];
                combination(comb, depth+1);
                visited[i] = false;
            }
        }
    }

    public long solution(String expression) {
        list = new ArrayList<>();
        operators = new ArrayList<>();
        visited = new boolean[3];
        answer = 0;

        StringBuilder number = new StringBuilder();
        for(char exp : expression.toCharArray()) {
            if(Character.isDigit(exp)) number.append(exp);
            else {
                operators.add(exp);
                list.add(Long.parseLong(number.toString()));
                number = new StringBuilder();
            }
        }
        list.add(Long.parseLong(number.toString()));

        combination(new char[3], 0);
        return answer;
    }
}
