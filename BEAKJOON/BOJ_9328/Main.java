package BEAKJOON.BOJ_9328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int h, w, totalKey;
    public static char[][] map;
    public static List<Character> keys;
    public static List<int[]> entrance;

    public static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        map = new char[h][w];
        entrance = new ArrayList<>();
        keys = new ArrayList<>();
        totalKey = 0;

        for(int i=0; i<h; i++) {
            String s = br.readLine();
            for(int j=0; j<w; j++) {
                map[i][j] = s.charAt(j);

                if(map[i][j] == '$') totalKey++;
                if(i == 0 || i == h - 1 || j == 0 || j == w - 1) {
                    if(map[i][j] != '*') {
                        entrance.add(new int[]{i, j});
                    }
                }
            }
        }

        String keyInfo = br.readLine();
        for(char key : keyInfo.toCharArray()) {
            if(key != '0') {
                keys.add(key);
            }
        }
    }

    public static int exploreDoor(int _x, int _y, char key) {

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[h][w];
        int ret = 0;

        q.offer(new int[]{_x, _y});
        visited[_x][_y] = true;

        if(map[_x][_y] == '$') {
            ret++;
            map[_x][_y] = '.';
        }else if(Character.isAlphabetic(map[_x][_y])) {
            if(Character.isLowerCase(map[_x][_y])) {
                if(!keys.contains(map[_x][_y])) {
                    keys.add(map[_x][_y]);
                    map[_x][_y] = '.';
                }
            }else {
                if(keys.contains(Character.toLowerCase(map[_x][_y]))) {
                    map[_x][_y] = '.';
                }else return 0;
            }
        }

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            q.poll();

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                if(visited[nx][ny] || map[nx][ny] == '*') continue;

                visited[nx][ny] = true;

                if(Character.isAlphabetic(map[nx][ny])) {
                    if(Character.isUpperCase(map[nx][ny])) {
                        if(keys.contains(Character.toLowerCase(map[nx][ny]))) {
                            map[nx][ny] = '.';
                            q.offer(new int[]{nx, ny});
                        }
                    }else {
                        if(!keys.contains(map[nx][ny])) keys.add(map[nx][ny]);
                        map[nx][ny] = '.';
                        q.offer(new int[]{nx, ny});
                    }
                }else {
                    if(map[nx][ny] == '$') {
                        ret++;
                        map[nx][ny] = '.';
                    }
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        return ret;
    }

    public static int exploreKey(int _x, int _y) {

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[h][w];
        int ret = 0;

        q.offer(new int[]{_x, _y});
        visited[_x][_y] = true;

        if(map[_x][_y] == '$') {
            ret++;
            map[_x][_y] = '.';
        }else if(Character.isAlphabetic(map[_x][_y])) {
            if(Character.isLowerCase(map[_x][_y])) {
                if(!keys.contains(map[_x][_y])) {
                    keys.add(map[_x][_y]);
                    map[_x][_y] = '.';
                }
            }
        }

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            q.poll();

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                if(visited[nx][ny] || map[nx][ny] == '*') continue;

                visited[nx][ny] = true;

                if(Character.isAlphabetic(map[nx][ny])) {
                    if(Character.isLowerCase(map[nx][ny])) {
                        if(!keys.contains(map[nx][ny])) {
                            keys.add(map[nx][ny]);
                        }
                        map[nx][ny] = '.';
                        q.offer(new int[]{nx, ny});
                    }
                }else if(map[nx][ny] == '.') {
                    q.offer(new int[]{nx, ny});
                }else if(map[nx][ny] == '$') {
                    ret++;
                    map[nx][ny] = '.';
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        return ret;
    }

    public static void stealDocuments() throws IOException {

        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            input();

            int cnt = 0;

            if(keys.size() == 0) {
                for(int i=0; i<entrance.size(); i++) {
                    int x = entrance.get(i)[0];
                    int y = entrance.get(i)[1];

                    if(Character.isAlphabetic(map[x][y]) && Character.isUpperCase(map[x][y])) continue;

                    cnt += exploreKey(x, y);
                }
            }
            if(cnt < totalKey) {
                search:
                for(int i=0; i<keys.size(); i++) {
                    for(int j=0; j<entrance.size(); j++) {
                        int x = entrance.get(j)[0];
                        int y = entrance.get(j)[1];

                        cnt += exploreDoor(x, y, keys.get(i));

                        if(cnt == totalKey) break search;
                    }
                }
            }
            answer.append(cnt).append("\n");
        }

        System.out.print(answer);
    }

    public static void main(String[] args) throws IOException {

        stealDocuments();
    }
}