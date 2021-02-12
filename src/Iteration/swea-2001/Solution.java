package swexpert.파리퇴치_2001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			int ans = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int map[][] = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
				 	map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int cnt = (N+1-M) * (N+1-M);
			int cur_x = 0, cur_y=0;
			int cur_dir=1;
			
			
			for(int i=0; i<M; i++) {
				for(int j=0; j<M; j++) {
					ans += map[i][j];
				}
			}
			
			int cur_sum = ans;
			while(--cnt > 0) {
				// 임시좌표 이동
				cur_x += cur_dir;
				
				// 영역이 넘어가면? -> 밑으로 이동
				if(cur_x+M-1 == N || cur_x == -1) {
					// y, x 좌표 보정
					cur_y++; cur_x += -cur_dir;
					// 진행방향 반전
					cur_dir *= -1;
					
					// 영역새로 계산
					for(int i=0; i<M; i++) {
						cur_sum -= map[cur_y-1][cur_x+i];
						cur_sum += map[cur_y+M-1][cur_x+i];
					}
					
				}
				// 좌우방향으로 이동
				else {
					int offset = (-1 - cur_dir) / 2;
					// 영역새로 계산
					for(int i=0; i<M; i++) {
						cur_sum -= map[cur_y+i][cur_x + offset] * cur_dir;
						cur_sum += map[cur_y+i][cur_x + M + offset] * cur_dir;
					}
					
				}
				ans = Math.max(ans,  cur_sum);
			}
			
			System.out.println("#"+t+" "+ans);
		}

	}

}
