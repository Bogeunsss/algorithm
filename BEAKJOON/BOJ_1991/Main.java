package BEAKJOON.BOJ_1991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {

    char data;
    Node left;
    Node right;

    Node(char data) {
        this.data = data;
    }
}

class Tree {

    Node root;

    void create(char data, char left, char right) {
        if(root == null) {
            root = new Node(data);
            root.left = left == '.' ? null : new Node(left);
            root.right = right == '.' ? null : new Node(right);
        }else {
            search(root, data, left, right);
        }
    }

    void search(Node node, char data, char left, char right) {
        if(node == null) return;
        if(node.data == data) {
            node.left = left == '.' ? null : new Node(left);
            node.right = right == '.' ? null : new Node(right);
        }else {
            search(node.left, data, left, right);
            search(node.right, data, left, right);
        }
    }

    void preOrder(Node node) {
        if(node != null) {
            System.out.print(node.data);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    void inOrder(Node node) {
        if(node != null) {
            inOrder(node.left);
            System.out.print(node.data);
            inOrder(node.right);
        }
    }

    void postOrder(Node node) {
        if(node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data);
        }
    }
}

public class Main {

    public static int n;
    public static Tree tree;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        tree = new Tree();

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            char root = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            tree.create(root, left, right);
        }
    }

    public static void traversal() {

        tree.preOrder(tree.root);
        System.out.println();
        tree.inOrder(tree.root);
        System.out.println();
        tree.postOrder(tree.root);
    }

    public static void main(String[] args) throws IOException {

        input();
        traversal();
    }
}