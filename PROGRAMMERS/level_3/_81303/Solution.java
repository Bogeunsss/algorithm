package PROGRAMMERS.level_3._81303;

import java.util.*;

public class Solution {
    public  String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> deleted = new Stack<>();
        int size = n;
        int cursor = k;

        StringTokenizer st;
        for(int i=0; i<cmd.length; i++) {
            st = new StringTokenizer(cmd[i], " ");

            switch(st.nextToken()) {
                case "U":
                    cursor -= Integer.parseInt(st.nextToken());
                    break;
                case "D":
                    cursor += Integer.parseInt(st.nextToken());
                    break;
                case "C":
                    size--;
                    deleted.push(cursor);
                    if(cursor == size) cursor--;
                    break;
                case "Z":
                    size++;
                    int data = deleted.pop();
                    if(cursor >= data) cursor++;
                    break;
            }
        }

        for(int i=0; i<size; i++) {
            sb.append("O");
        }
        while(!deleted.isEmpty()) {
            sb.insert(deleted.pop().intValue(), 'X');
        }

        return sb.toString();
    }
}
