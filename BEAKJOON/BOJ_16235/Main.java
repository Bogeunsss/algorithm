package BEAKJOON.BOJ_16235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static int n, m, k;
    public static int[][] land, nutrient;
    public static PriorityQueue<Tree> trees;
    public static List<Tree> store, dead;

    public static class Tree {
        int x;
        int y;
        int age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }

    public static void spring() {
        store = new ArrayList<>();
        dead = new ArrayList<>();

        while(!trees.isEmpty()) {
            Tree tree = trees.poll();

            int x = tree.x;
            int y = tree.y;

            if(land[x][y] >= tree.age) {
                land[x][y] -= tree.age;
                tree.age++;
                store.add(tree);
            }else {
                dead.add(tree);
            }
        }

        trees.addAll(store);
    }

    public static void summer() {
        for(int i=0; i<dead.size(); i++) {
            Tree tree = dead.get(i);

            land[tree.x][tree.y] += tree.age / 2;
        }
    }

    public static void autumn() {
        store = new ArrayList<>();

        while(!trees.isEmpty()) {
            Tree tree = trees.poll();

            int x = tree.x;
            int y = tree.y;

            if(tree.age % 5 == 0) {
                for(int i=0; i<8; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(nx <= 0 || nx > n || ny <= 0 || ny > n) continue;

                    store.add(new Tree(nx, ny, 1));
                }
            }
            store.add(tree);
        }

        trees.addAll(store);
    }

    public static void winter() {
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                land[i][j] += nutrient[i][j];
            }
        }
    }

    public static void seasons(int year) {
        while(year++ < k) {
            spring();
            summer();
            autumn();
            winter();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        land = new int[n+1][n+1];
        nutrient = new int[n+1][n+1];
        trees = new PriorityQueue<>(new Comparator<Tree>() {
            @Override
            public int compare(Tree o1, Tree o2) {
                return o1.age - o2.age;
            }
        });

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for(int j=1; j<=n; j++) {
                nutrient[i][j] = Integer.parseInt(st.nextToken());
                land[i][j] = 5;
            }
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            Tree tree = new Tree(x, y, z);
            trees.offer(tree);
        }

        seasons(0);

        System.out.println(trees.size());
    }
}