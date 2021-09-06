package PROGRAMMERS.level_1._85002;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    public static class Boxer {
        int id;
        int weight;
        double rate;
        int overWeight;

        public Boxer(int id, int weight, double rate, int overWeight) {
            this.id = id;
            this.weight = weight;
            this.rate = rate;
            this.overWeight = overWeight;
        }
    }

    public int[] solution(int[] weights, String[] head2head) {
        int N = weights.length, index = 0;
        int[] answer = new int[N];

        PriorityQueue<Boxer> pq = new PriorityQueue<>(new Comparator<Boxer>() {
            @Override
            public int compare(Boxer o1, Boxer o2) {
                if(o1.rate == o2.rate) {
                    if(o1.overWeight == o2.overWeight) {
                        if(o1.weight == o2.weight) {
                            return o1.id - o2.id;
                        }
                        return o2.weight - o1.weight;
                    }
                    return o2.overWeight - o1.overWeight;
                }
                return o2.rate > o1.rate ? 1 : -1;
            }
        });

        for(int i=0; i<N; i++) {
            String matches = head2head[i];
            int weight = weights[i];
            int total = 0, win = 0, overWeight = 0;

            for(int j=0; j<matches.length(); j++) {
                if(matches.charAt(j) != 'N') {
                    if(matches.charAt(j) == 'W') {
                        if(weight < weights[j]) {
                            overWeight++;
                        }
                        win++;
                    }
                    total++;
                }
            }

            double rate = total != 0 ? (double) win / total * 100 : 0;
            Boxer boxer = new Boxer(i+1, weight, rate, overWeight);

            pq.offer(boxer);
        }

        while(!pq.isEmpty()) {
            answer[index++] = pq.poll().id;
        }

        return answer;
    }
}
