package BEAKJOON.BOJ_2263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {

    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
    }
}

class Tree {

    Node root;
    int[] inOrder;
    int[] postOrder;
    int[] index;

    Tree(int n) {
        inOrder = new int[n+1];
        postOrder = new int[n+1];
        index = new int[n+1];
    }

    void preOrder(int inStart, int inEnd, int postStart, int postEnd) {

        if(inStart > inEnd || postStart > postEnd) return;

        int root = index[postOrder[postEnd]];
        int left = root - inStart;

        System.out.print(inOrder[root] + " ");

        preOrder(inStart, root - 1, postStart, postStart + left - 1);
        preOrder(root + 1, inEnd, postStart + left, postEnd - 1);
    }
}

public class Main {

    public static int n;
    public static Tree tree;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        tree = new Tree(n);

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=n; i++) {
            tree.inOrder[i] = Integer.parseInt(st.nextToken());
            tree.index[tree.inOrder[i]] = i;
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=n; i++) tree.postOrder[i] = Integer.parseInt(st.nextToken());
    }

    public static void getPreOrder() {

        tree.preOrder(1, n, 1, n);
    }

    public static void main(String[] args) throws IOException {

        input();
        getPreOrder();
    }
}