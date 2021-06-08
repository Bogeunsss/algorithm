package PROGRAMMERS.level_3._42892;

import java.util.*;

public class Solution {
    public static class Tree {
        Tree parent;
        Tree left;
        Tree right;
        int depth;
        List<Integer> data;
    }

    public static List<Integer> preOrder(Tree root, List<Integer> vector) {
        if(root == null) return vector;
        vector.add(root.depth);
        preOrder(root.left, vector);
        preOrder(root.right, vector);
        return vector;
    }

    public static List<Integer> postOrder(Tree root, List<Integer> vector) {
        if(root == null) return vector;
        postOrder(root.left, vector);
        postOrder(root.right, vector);
        vector.add(root.depth);
        return vector;
    }

    public int[][] solution(int[][] nodeinfo) {
        Tree root = null;

        List<List<Integer>> nodes = new ArrayList<>();
        for(int i=0; i<nodeinfo.length; i++) {
            nodes.add(new ArrayList<>());
            for(int j=0; j<nodeinfo[i].length; j++) {
                nodes.get(i).add(nodeinfo[i][j]);
            }
            nodes.get(i).add(i+1);
        }
        nodes.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o2.get(1) - o1.get(1);
            }
        });
        for(int i=0; i<nodeinfo.length; i++) {
            Tree newTree = new Tree();
            newTree.depth = nodes.get(i).get(2);
            newTree.data = nodes.get(i);
            if(root == null) root = newTree;
            else {
                Tree curTree = root;
                while(true) {
                    if(curTree.data.get(0) < newTree.data.get(0)) {
                        if(curTree.right == null) {
                            curTree.right = newTree;
                            newTree.parent = curTree;
                            break;
                        }
                        curTree = curTree.right;
                    }else {
                        if(curTree.left == null) {
                            curTree.left = newTree;
                            newTree.parent = curTree;
                            break;
                        }
                        curTree = curTree.left;
                    }
                }
            }
        }

        int[] pre = preOrder(root, new ArrayList<>()).stream().mapToInt(i->i).toArray();
        int[] post = postOrder(root, new ArrayList<>()).stream().mapToInt(i->i).toArray();

        return new int[][]{pre, post};
    }
}
