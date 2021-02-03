package swexpert.농작물수확하기_2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	static int ans;
	static int N;
	static char bat[][];
	
	//			좌하, 우하, 좌상, 우상
	static int dx[]= {-1,1,-1,1};
	static int dy[]= {1,1,-1,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			ans = 0;
			
			// 밭 초기화
			N = Integer.parseInt(br.readLine());
			bat = new char[N][];
			for(int i=0; i<N; i++) {
				bat[i] = br.readLine().toCharArray();
			}
			
			
			calValue(N);
			
			System.out.println("#"+t + " " +ans);
		}
	}
	
	static void calValue(int n) {
		// 기저조건
		if(n <= 1) {
			ans += bat[N/2][N/2] - '0';
			bat[N/2][N/2] = 'V';
			return;
		}
		
		calValue(n-2);
		
		int cnt = n/2;
		// 대각선만 더하기
		// 좌하, 우하 -> n/2번 (좌우 꼭짓점 포함)
		int cur_x = N/2;
		int cur_y = (N-n)/2;
		
		ans += bat[cur_y][cur_x] - '0';
		
		for(int i=1; i<=cnt; i++) {
			for(int j=0; j<2; j++) {
				int nx = cur_x + dx[j] * i;
				int ny = cur_y + dy[j] * i;
				ans += bat[ny][nx] - '0';
			}
		}
		
		// 좌상, 우상 -> n/2 -1 번
		cur_x = N/2;
		cur_y = (N-1) - cur_y;
		ans += bat[cur_y][cur_x] - '0';
		for(int i=1; i<=cnt-1; i++) {
			for(int j=2; j<4; j++) {
				int nx = cur_x + dx[j] * i;
				int ny = cur_y + dy[j] * i;
				ans += bat[ny][nx] - '0';
			}
		}
	}

}
