package baekjoon.배열돌리기1_16926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실버4
public class Main {
	static int N;
	static int M;
	static int R;
	static int dLimit;
	
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int [N][M];
		
		dLimit = Math.min(N, M) / 2;	// depth가 몇개인가
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int depth=0; depth<dLimit; depth++) {
			
			// 행 범위 (Map Top, Map Bottom)
			int MT = depth;
			int MB = N - 1 - depth;
			
			// 열 범위 (Map Left, Map Right)
			int ML = depth;
			int MR = M - 1 - depth;
			
			// 회전수 계산 == 돌을 옮길 갯수
			int r = R%( 2 * (MB-MT+1 + MR-ML+1) - 4);
			
			while(r > 0) {
				int tmp = map[MT][ML];	// 시작부분 임시저장
				// 위쪽라인 이동
				for(int i=ML; i<MR; i++) {
					map[MT][i] = map[MT][i+1];
				
				}
				// 오른쪽라인 이동
				for(int i=MT; i<MB; i++) {
					map[i][MR] = map[i+1][MR];
				}
				// 아래쪽 라인 이동
				for(int i=MR; i>ML; i--) {
					map[MB][i] = map[MB][i-1];
				}
				// 왼쪽 라인 이동
				for(int i=MB; i>MT; i--) {
					map[i][ML] = map[i-1][ML];
				}
				map[MT+1][ML] = tmp;	//	시작부분 넣기
				r--;
			}
		}
		// 배열출력
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
