package BEAKJOON.BOJ_17471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int n;
    public static int[] population;
    public static boolean[] visited;
    public static List<List<Integer>> locations;
    public static int answer;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        population = new int[n+1];
        visited = new boolean[n+1];
        locations = new ArrayList<>();
        answer = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=n; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<=n; i++) locations.add(new ArrayList<>());
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int m = Integer.parseInt(st.nextToken());

            for(int j=0; j<m; j++) {
                locations.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }
    }

    public static boolean connect(List<Integer> district) {

        Queue<Integer> q = new LinkedList<>();
        boolean[] connectionCheck = new boolean[n+1];
        int cnt = 1;

        q.offer(district.get(0));
        connectionCheck[district.get(0)] = true;

        while(!q.isEmpty()) {
            int now = q.poll();

            for(int next : locations.get(now)) {
                if(connectionCheck[next]) continue;

                if(district.contains(next)) {
                    cnt++;
                    connectionCheck[next] = true;
                    q.offer(next);
                }
            }
        }

        return district.size() == cnt;
    }

    public static boolean check() {

        List<Integer> districtA = new ArrayList<>();
        List<Integer> districtB = new ArrayList<>();

        for(int i=1; i<=n; i++) {
            if(visited[i]) districtA.add(i);
            else districtB.add(i);
        }

        if(districtA.size() == 0 || districtB.size() == 0) return false;
        if(!connect(districtA) || !connect(districtB)) return false;

        return true;
    }

    public static void calculation() {

        int A = 0, B = 0;

        for(int i=1; i<=n; i++) {
            if(visited[i]) A += population[i];
            else B += population[i];
        }

        answer = Math.min(answer, Math.abs(A - B));
    }

    public static void electionDistrict(int index, int count) {

        if(count >= 1) {
            if(check()) {
                calculation();
            }
        }

        for(int i=index; i<=n; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            electionDistrict(i, count + 1);
            visited[i] = false;
        }
    }

    public static void gerrymandering() {

        electionDistrict(1, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        gerrymandering();
    }
}