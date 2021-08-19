package PROGRAMMERS.level_3._84021;

import java.util.*;

public class Solution {

    public static class Board {
        public Map<Node,Block> map;

        public Board(int[][] data, int value) {
            map = new HashMap<>();
            initialize(data, value);
        }

        public void initialize(int[][] data, int value) {
            int size = data.length;

            for(int i=0; i<size; i++) {
                for(int j=0; j<size; j++) {
                    Node node = new Node(i, j);

                    if(data[node.x][node.y] == value && !map.containsKey(node)) {
                        connect(getConnectedBlock(node), node);
                    }
                }
            }
        }

        public List<int[][]> getBlocks() {
            List<int[][]> ret = new ArrayList<>();

            map.values().stream().distinct().forEach(e -> ret.add(e.toArray()));

            return ret;
        }

        private void connect(Block block, Node node) {
            block.add(node);
            map.put(node, block);
        }

        private Block getConnectedBlock(Node node) {
            Node leftNode = new Node(node.x, node.y - 1);
            Node topNode = new Node(node.x - 1, node.y);

            Block left = map.getOrDefault(leftNode, null);
            Block top = map.getOrDefault(topNode, null);

            if(left != top && left != null && top != null) {
                combine(left, top);
            }

            if(left != null) return left;
            else if(top != null) return top;
            return new Block();
        }

        private void combine(Block a, Block b) {
            for(Node node : b.list) {
                a.list.add(node);
                map.put(node, a);
            }
        }
    }

    public static class Block {
        public Set<Node> list = new HashSet<>();

        public void add(Node node) {
            list.add(node);
        }

        public int[][] toArray() {
            int pivotX = list.stream().min((o1, o2) -> o1.x - o2.x).get().x;
            int pivotY = list.stream().min((o1, o2) -> o1.y - o2.y).get().y;

            int height = list.stream().max((o1, o2) -> o1.x - o2.x).get().x - pivotX + 1;
            int width = list.stream().max((o1, o2) -> o1.y - o2.y).get().y - pivotY + 1;

            int[][] ret = new int[height][width];

            for(Node node : list) {
                ret[node.x - pivotX][node.y - pivotY] = 1;
            }

            return ret;
        }
    }

    public static class Node {
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(o == null || getClass() != o.getClass()) return false;

            Node other = (Node) o;

            return other.x == x && other.y == y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static int[][] rotate(int[][] data) {
        int height = data.length;
        int width = data[0].length;
        int[][] ret = new int[width][height];

        for(int i=0; i<width; i++) {
            for(int j=0; j<height; j++) {
                ret[i][j] = data[j][width-i-1];
            }
        }
        return ret;
    }

    public static boolean isEquals(int[][] A, int[][] B) {
        if(A.length != B.length || A[0].length != B[0].length) return false;
        for(int i=0; i<A.length; i++) {
            for(int j=0; j<A[0].length; j++) {
                if(A[i][j] != B[i][j]) return false;
            }
        }
        return true;
    }

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;

        List<int[][]> boardList = new Board(game_board, 0).getBlocks();
        List<int[][]> tableList = new Board(table, 1).getBlocks();

        Iterator<int[][]> boardIterator = boardList.iterator();
        while(boardIterator.hasNext()) {
            int[][] found = null;
            int[][] boardBlock = boardIterator.next();
            Iterator<int[][]> tableIterator = tableList.iterator();

            match:
            while(tableIterator.hasNext()) {
                int[][] tableBlock = tableIterator.next();

                if(isEquals(boardBlock, tableBlock)) {
                    found = tableBlock;
                    tableIterator.remove();
                    break;
                }else {
                    int[][] rotated = tableBlock;

                    for(int i=0; i<3; i++) {
                        rotated = rotate(rotated);

                        if(isEquals(boardBlock, rotated)) {
                            found = tableBlock;
                            tableIterator.remove();
                            break match;
                        }
                    }
                }
            }
            if(found != null) {
                int count = 0;
                for(int i=0; i<boardBlock.length; i++) {
                    count += Arrays.stream(boardBlock[i]).sum();
                }
                answer += count;
            }
        }
        return answer;
    }
}