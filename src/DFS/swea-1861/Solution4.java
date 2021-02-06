package swexpert.정사각형방_1861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS
// D4
public class Solution4 {

	static int[][] map;
	static int T, N;
	static int NO, CNT;
	static Queue<Node> q = new LinkedList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			CNT = 1;
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					q.offer(new Node(i, j, map[i][j], 1));
					bfs();
				}
			}
			System.out.println("#"+t+" "+NO+" "+CNT);
			q.clear();
		}
		br.close();
	}
	static int dy[] = {1, 0, -1, 0};
	static int dx[] = {0, 1, 0, -1};
	static void bfs() {
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			// 방문횟수 계산
			if(node.cnt > CNT) {
				CNT = node.cnt;
				NO = node.no;
			} else if(node.cnt == CNT && node.no < NO) {
				NO = node.no;
			}
			
			for(int z=0; z<4; z++) {
				int zx = node.x + dx[z];
				int zy = node.y + dy[z];
				
				if(zx<0 || zy<0 || zx>=N || zy>=N) continue;
				if(map[zy][zx] != map[node.y][node.x] + 1) continue;
				q.offer(new Node(zy, zx, node.no, node.cnt+1));	
			}
		}
	}
	static class Node{
		int y, x;
		int no, cnt;
		public Node(int y, int x, int no, int cnt) {
			this.y = y;
			this.x = x;
			this.no = no;
			this.cnt = cnt;
		}
		
	}
}

