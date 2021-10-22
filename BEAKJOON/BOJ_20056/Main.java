package BEAKJOON.BOJ_20056;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	public static final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static List<List<List<FireBall>>> map = new ArrayList<>();
	public static int n, m, k;
	
	public static class FireBall {
		
		int m;
		int s;
		int d;
		boolean moved;
		
		public FireBall(int m, int s, int d, boolean isMove) {
			
			this.m = m;
			this.s = s;
			this.d = d;
			this.moved = isMove;
		}
		
		public FireBall clone() {
			return new FireBall(m, s, d, moved);
		}
	}
	
	public static void move() {
		
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(map.get(i).get(j).size() > 0) {
					
					List<Integer> removed = new ArrayList<>();
					int index = -1;
					
					for(FireBall fireBall : map.get(i).get(j)) {
						index++;
						if(fireBall.moved) continue;
						
						int d = fireBall.d;
						int s = fireBall.s;
						
						int nx = i + dx[d] * s;
						int ny = j + dy[d] * s;
						
						while(nx > n && ny <= n) nx = nx - n;
						while(nx <= 0 && ny > 0) nx = n + nx;
						while(nx <= n && ny > n) ny = ny - n;
						while(nx > 0 && ny <= 0) ny = n + ny;
						if(nx > n && ny > n) {
							while(nx > n) nx = nx - n;
							while(ny > n) ny = ny - n;
						}
						if(nx <= 0 && ny <= 0) {
							while(nx <= 0) nx = n + nx;
							while(ny <= 0) ny = n + ny;
						}
						if(i != nx || j != ny) {
							FireBall newFireBall = fireBall.clone();
							newFireBall.moved = true;
							map.get(nx).get(ny).add(newFireBall);
							removed.add(index);							
						}
					}
					
					List<FireBall> remains = new ArrayList<>();
					for(int f=0; f<map.get(i).get(j).size(); f++) {
						if(removed.contains(f)) continue;
						remains.add(map.get(i).get(j).get(f));
					}
					
					map.get(i).set(j, remains);
				}
			}
		}
	}
	
	public static int oddOrEvenAll(List<FireBall> list) {
		
		int size = list.size();
		int odd = 0, even = 0;
		
		for(FireBall fireBall : list) {
			if(fireBall.d % 2 == 0) even++;
			else odd++;
		}
		
		if(odd == size || even == size) return 0;
		return 1;
	}
	
	public static void transform() {
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(map.get(i).get(j).size() >= 2) {
					
					List<FireBall> separation = new ArrayList<>();
					
					FireBall sum = new FireBall(0, 0, 0, false);
					int size = map.get(i).get(j).size();
					int d = oddOrEvenAll(map.get(i).get(j));
					
					for(FireBall fireBall : map.get(i).get(j)) {
						sum.m += fireBall.m;
						sum.s += fireBall.s;
					}
					for(int f=0; f<4; f++) {
						if(sum.m / 5 > 0) {
							separation.add(new FireBall(sum.m / 5, sum.s / size, d, false));
						}
						d += 2;
					}
					
					map.get(i).set(j, separation);
				}
			}
		}
	}
	
	public static void switchMoveState() {
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				for(int f=0; f<map.get(i).get(j).size(); f++) {
					map.get(i).get(j).get(f).moved = false;
				}
			}
		}
	}
	
	public static int totalMass() {
		
		int ret = 0;
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(map.get(i).get(j).size() > 0) {
					for(FireBall fireBall : map.get(i).get(j)) {
						ret += fireBall.m;
					}
				}
			}
		}
		
		return ret;
	}
	
	public static void order() {
		
		while(k-- > 0) {
			
			move();
			transform();
			switchMoveState();
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<=n; i++) {
			map.add(new ArrayList<>());
			for(int j=0; j<=n; j++) {
				map.get(i).add(new ArrayList<>());
			}
		}
		for(int i=1; i<=m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int ri = Integer.parseInt(st.nextToken());
			int ci = Integer.parseInt(st.nextToken());
			int mi = Integer.parseInt(st.nextToken());
			int si = Integer.parseInt(st.nextToken());
			int di = Integer.parseInt(st.nextToken());
			
			FireBall fireBall = new FireBall(mi, si, di, false);
			map.get(ri).get(ci).add(fireBall);
		}
		
		order();
		
		System.out.println(totalMass());
	}
}