package swexpert.정사각형방_1861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 기저조건 변경
public class Solution2 {

	static int[][] map;
	static int T, N;
	static int NO, CNT;
	
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
					dfs(i, j, map[i][j], 1);
				}
			}
			System.out.println("#"+t+" "+NO+" "+CNT);
		}
		br.close();
	}
	static int dy[] = {1, 0, -1, 0};
	static int dx[] = {0, 1, 0, -11};
	static void dfs(int y, int x, int no, int cnt) {
		
		// 방문횟수 계산
		if(cnt > CNT) {
			CNT = cnt;
			NO = no;
		} else if(cnt == CNT && no < NO) {
			NO = no;
		}
		
		for(int z=0; z<4; z++) {
			int zx = x + dx[z];
			int zy = y + dy[z];
			
			if(zx<0 || zy<0 || zx>=N || zy>=N) continue;
			if(cnt != 1 && map[zy][zx] != map[y][x] + 1) continue;
			
			dfs(zy, zx, no, cnt+1);
		}
	}
}

