package BEAKJOON.BOJ_3954;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static final int MAX = (int) Math.pow(10, 7) * 5;
    public static final int MOD = (int) Math.pow(2, 8);

    public static int sm, sc, si;
    public static int[] memory;
    public static String programCode, programInput;
    public static Map<Integer,Integer> pairs;
    public static StringBuilder answer;

    public static void input(BufferedReader br) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        sm = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());
        si = Integer.parseInt(st.nextToken());
        memory = new int[sm+1];
        programCode = br.readLine();
        programInput = br.readLine();
        pairs = new HashMap<>();

        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<sc; i++) {
            if(programCode.charAt(i) == '[') {
                stack.push(i);
            }else if(programCode.charAt(i) == ']') {
                pairs.put(stack.peek(), i);
                pairs.put(i, stack.peek());
                stack.pop();
            }
        }
    }

    public static void brainfxxk() {

        int pointer = 0;
        int cursor = 0;
        int index = 0;
        int loop = Integer.MAX_VALUE;
        boolean isTerminated = true;

        for(int i=0; i<sc; i++) {

            if(cursor == MAX) loop = i;
            if(cursor >= MAX * 2) {
                isTerminated = false;
                break;
            }

            switch(programCode.charAt(i)) {
                case '+':
                    memory[pointer] = (memory[pointer] + 1) % MOD;
                    break;
                case '-':
                    memory[pointer] = (memory[pointer] - 1 + MOD) % MOD;
                    break;
                case '<':
                    pointer = (pointer - 1 + sm) % sm;
                    break;
                case '>':
                    pointer = (pointer + 1) % sm;
                    break;
                case '[':
                    if(memory[pointer] == 0) i = pairs.get(i);
                    break;
                case ']':
                    if(memory[pointer] != 0) i = pairs.get(i);
                    break;
                case ',':
                    memory[pointer] = index == si ? MOD - 1 : programInput.charAt(index++);
                    break;
            }

            if(++cursor > MAX) {
                loop = Math.min(loop, i);
            }
        }

        if(isTerminated) answer.append("Terminates").append("\n");
        else answer.append("Loops ").append(loop).append(" ").append(pairs.get(loop)).append("\n");
    }

    public static void interpreter() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        answer = new StringBuilder();

        while(T-- > 0) {
            input(br);
            brainfxxk();
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {

        interpreter();

    }
}