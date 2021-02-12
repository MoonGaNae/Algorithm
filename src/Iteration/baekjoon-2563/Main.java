package baekjoon.색종이_2563;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());	// 색종이수
		boolean map[][] =new boolean[101][101];		// 도화지
		int ans = 0;								// 구할 넓이
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x= Integer.parseInt(st.nextToken());	// x좌표
			int y= Integer.parseInt(st.nextToken());	// y좌표
			
			for(int j=x; j<x+10; j++) {					// 가로 + 10까지
				for(int k=y; k<y+10; k++) {				// 세로 + 10까지
					if(map[j][k]) continue;				// 이미 거쳐갔다면 포함 X
					ans++;								// 넓이 증가
					map[j][k] = true;					// 방문함 표시
				}
			}
		}
		System.out.println(ans);						// 정답출력
	}
}