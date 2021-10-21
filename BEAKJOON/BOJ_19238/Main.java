package BEAKJOON.BOJ_19238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static final int[] dx = {-1, 1, 0, 0};
	public static final int[] dy = {0, 0, -1, 1};
	
	public static int n, m, fuel;
	public static int[][] map, customers;
	public static boolean[] complete;
	
	public static void drive(int _x, int _y, int[][] d) {
		Queue<int[]> q = new LinkedList<>();
		
		q.offer(new int[] {_x, _y});
		d[_x][_y] = 0;
		
		while(!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				if(map[nx][ny] == 1) continue;
				if(d[nx][ny] > d[x][y] + 1) {
					d[nx][ny] = d[x][y] + 1;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}
	
	public static int taxi(int x, int y) {
		
		int T = m;
		
		while(T-- > 0) {
			int[][] d = new int[n][n];
			int min = Integer.MAX_VALUE;
			int id = -1;
			
			for(int i=0; i<n; i++) {
				Arrays.fill(d[i], Integer.MAX_VALUE);
			}
			
			drive(x, y, d);
			
			for(int i=0; i<m; i++) {
				if(complete[i]) continue;
				
				int a = customers[i][0];
				int b = customers[i][1];
				
				if(min >= d[a][b]) {
					min = d[a][b];
					x = a;
					y = b;
					id = i;
				}
			}
			
			if(fuel <= d[x][y]) return -1;
			fuel -= d[x][y];
			
			for(int i=0; i<n; i++) {
				Arrays.fill(d[i], Integer.MAX_VALUE);
			}
			
			drive(x, y, d);
			
			int ex = customers[id][2];
			int ey = customers[id][3];
			
			if(fuel < d[ex][ey]) return -1;
			complete[id] = true;
			fuel += d[ex][ey];
			x = ex;
			y = ey;
		}
		
		return fuel;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		customers = new int[m][4];
		complete = new boolean[m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int x = Integer.parseInt(st.nextToken()) - 1;
		int y = Integer.parseInt(st.nextToken()) - 1;
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<4; j++) {
				customers[i][j] = Integer.parseInt(st.nextToken()) - 1;
			}
			
			map[customers[i][0]][customers[i][1]] = 2;
			map[customers[i][2]][customers[i][3]] = 3;
		}
		
		Arrays.sort(customers, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) return o2[1] - o1[1];
				return o2[0] - o1[0];
			}
		});
		
		System.out.println(taxi(x, y));
	}
}