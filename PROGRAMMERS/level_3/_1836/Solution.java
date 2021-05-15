package PROGRAMMERS.level_3._1836;

import java.util.*;

public class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] friends;
    static int M;
    static int N;

    public static class Tuple {
        char first;
        int[] second;

        public Tuple(char first, int[] second) {
            this.first = first;
            this.second = second;
        }
    }

    public static boolean find(int x, int y) {
        char target = friends[x][y];

        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            while(0 <= nx && nx < M && 0 <= ny && ny < N && (friends[nx][ny] == '.' || friends[nx][ny] == target)) {
                if(friends[nx][ny] == target) {
                    friends[x][y] = '.';
                    friends[nx][ny] = '.';
                    return true;
                }
                for(int j=0; j<4; j++) {
                    if(i == j || (i == 0 && j == 1) || (i == 1 && j == 0) || (i == 2 && j == 3) || (i == 3 && j == 2)) continue;
                    int tx = nx + dx[j];
                    int ty = ny + dy[j];
                    while(0 <= tx && tx < M && 0 <= ty && ty < N && (friends[tx][ty] == '.' || friends[tx][ty] == target)) {
                        if(friends[tx][ty] == target) {
                            friends[x][y] = '.';
                            friends[tx][ty] = '.';
                            return true;
                        }
                        tx += dx[j];
                        ty += dy[j];
                    }
                }
                nx += dx[i];
                ny += dy[i];
            }
        }
        return false;
    }

    public String solution(int m, int n, String[] board) {
        friends = new char[m][n];
        String answer = "";
        M = m;
        N = n;

        List<Tuple> reserve = new ArrayList<>();
        Map<Character,Integer> check = new HashMap<>();

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                friends[i][j] = board[i].charAt(j);
                if('A' <= friends[i][j] && friends[i][j] <= 'Z' && check.get(friends[i][j]) == null) {
                    check.put(friends[i][j], 1);
                    reserve.add(new Tuple(friends[i][j], new int[]{i, j}));
                }
            }
        }
        reserve.sort(new Comparator<Tuple>() {
            @Override
            public int compare(Tuple o1, Tuple o2) {
                return o1.first - o2.first;
            }
        });

        boolean flag = true;
        while(flag) {
            flag =false;
            for(int i=0; i<reserve.size(); i++) {
                flag = find(reserve.get(i).second[0], reserve.get(i).second[1]);

                if(flag) {
                    answer += reserve.get(i).first;
                    reserve.remove(i);
                    break;
                }
            }
        }
        if(reserve.size() > 0) answer = "IMPOSSIBLE";
        return answer;
    }
}