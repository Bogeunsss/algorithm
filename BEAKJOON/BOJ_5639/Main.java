package BEAKJOON.BOJ_5639;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    void create(int data) {
        if(root == null) {
            root = new Node(data);
        }else {
            search(root, data);
        }
    }

    void search(Node node, int data) {
        if(node == null) return;
        if(node.data > data) {
            if(node.left == null) node.left = new Node(data);
            else search(node.left, data);
        } else if(node.data < data) {
            if(node.right == null) node.right = new Node(data);
            else search(node.right, data);
        }
    }

    void postOrder(Node node) {
        if(node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.data);
        }
    }
}

public class Main {

    public static Tree tree;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        tree = new Tree();

        while(true) {
            String data = br.readLine();

            if(data == null) break;

            tree.create(Integer.parseInt(data));
        }
    }

    public static void getPostOrder() {

        tree.postOrder(tree.root);
    }

    public static void main(String[] args) throws IOException {

        input();
        getPostOrder();
    }
}