package PROGRAMMERS.level_3._1830;

public class Solution {
    static int N;
    static int[] group;
    static boolean[] rule1;
    static boolean[] rule2;
    static boolean[] used;
    static int index;

    public static boolean checkRule1(String sentence, int[] info, char c) {
        if(used[c - 'a']) return false;
        if(info[0] - 1 < 0 || info[1] + 1 >= N) return false;
        if(Character.isLowerCase(sentence.charAt(info[0]-1)) || Character.isLowerCase(sentence.charAt(info[1]+1))) return false;

        int lower = 0, upper = 0;
        for(int i=info[0]-1; i<=info[1]+1; i++) {
            char now = sentence.charAt(i);
            if(Character.isLowerCase(now)) {
                if(now == c) {
                    group[i] = index;
                    lower++;
                    upper = 0;
                }else {
                    return false;
                }
            }else if(Character.isUpperCase(now)) {
                lower = 0;
                upper++;
                if(rule1[i]) return false;
                else {
                    group[i] = index;
                    rule1[i] = true;
                }
            }
            if(lower > 1 || upper > 1) return false;
        }
        index++;
        used[c - 'a'] = true;
        return true;
    }

    public static boolean checkRule2(String sentence, int[] info, char c) {
        if(used[c - 'a']) return false;
        if(info[1] - info[0] < 2) return false;

        group[info[0]] = index;
        group[info[1]] = index;
        for(int i=info[0]+1; i<info[1]; i++) {
            char now = sentence.charAt(i);
            if(Character.isUpperCase(now)) {
                if(rule2[i]) return false;
                else {
                    group[i] = index;
                    rule2[i] = true;
                }
            }
        }
        index++;
        used[c - 'a'] = true;
        return true;
    }

    public static int[] getCharInfo(String sentence, char c) {
        int[] info = new int[3];
        info[0] = -1;
        for(int i=0; i<N; i++) {
            if(sentence.charAt(i) == c) {
                if(info[0] < 0) info[0] = i;
                info[2]++;
                info[1] = i;
            }
        }
        return info;
    }

    public static boolean decryptingPossible(String sentence) {
        for(int i=0; i<N; i++) {
            char now = sentence.charAt(i);
            if(now == ' ') return false;
            if(group[i] == 0) {
                if(Character.isUpperCase(now)) {
                    if(i + 1 >= N) {
                        group[i] = index++;
                        continue;
                    }
                    char next = sentence.charAt(i+1);
                    if(Character.isUpperCase(next)) group[i] = index++;
                    else if(Character.isLowerCase(next)) {
                        int[] info = getCharInfo(sentence, next);
                        if(info[2] == 2) group[i] = index++;
                        else {
                            if(!checkRule1(sentence, info, next)) return false;
                        }
                    }
                }else if(Character.isLowerCase(now)) {
                    int[] info = getCharInfo(sentence, now);
                    if(info[2] == 2) {
                        if(Character.isUpperCase(sentence.charAt(i+1)) && rule2[i+1]) {
                            if(!checkRule1(sentence, info, now)) return false;
                        }else {
                            if(!checkRule2(sentence, info, now)) return false;
                        }
                    }else {
                        if(!checkRule1(sentence, info, now)) return false;
                    }
                }
            }
        }

        return true;
    }

    public static String makeAnswer(String sentence) {
        int last = 0;
        for(int i=0; i<N; i++) {
            if(Character.isUpperCase(sentence.charAt(i))) {
                last = group[i];
                break;
            }
        }

        String answer = "";
        for(int i=0; i<N; i++) {
            if(Character.isUpperCase(sentence.charAt(i))) {
                if(group[i] != last) answer += " ";
                answer += sentence.charAt(i);
                last = group[i];
            }
        }

        return answer;
    }

    public String solution(String sentence) {
        N = sentence.length();
        group = new int[N];
        rule1 = new boolean[N];
        rule2 = new boolean[N];
        used = new boolean[26];
        index = 1;

        if(!decryptingPossible(sentence)) return "invalid";
        return makeAnswer(sentence);
    }
}
