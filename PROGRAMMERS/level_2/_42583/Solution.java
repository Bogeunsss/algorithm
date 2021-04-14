package PROGRAMMERS.level_2._42583;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public static class Truck {
        int time;
        int weight;

        public Truck(int time, int weight) {
            this.time = time;
            this.weight = weight;
        }
    }
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        Queue<Truck> bridge = new LinkedList<Truck>();
        List<Integer> fin = new ArrayList<Integer>();
        int seq = 0, total_weight = 0;

        while(fin.size() != truck_weights.length) {
            time++;
            if(!bridge.isEmpty()) {
                int on_bridge = bridge.size();
                for(int i=0; i<on_bridge; i++) {
                    Truck truck = bridge.poll();
                    if(truck.time == bridge_length) {
                        fin.add(truck.weight);
                        total_weight -= truck.weight;
                    }else {
                        truck.time++;
                        bridge.offer(truck);
                    }
                }
            }
            if(seq < truck_weights.length && total_weight + truck_weights[seq] <= weight) {
                bridge.offer(new Truck(1, truck_weights[seq]));
                total_weight += truck_weights[seq++];
            }
        }

        return time;
    }
}
