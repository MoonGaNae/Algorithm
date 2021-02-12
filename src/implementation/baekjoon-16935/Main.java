package baekjoon.배열돌리기3_16935;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실버3
public class Main {
	static int N, M, R;
	static int [][] map = new int [100][100];
	static int [][] tmpMap = new int [100][100];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<R; i++) {
			int command = Integer.parseInt(st.nextToken());
			switch(command) {
			case 1:
				reverseTB();
				break;
			case 2:
				reverseLR();
				break;
			case 3:
				turnR();
				break;
			case 4:
				turnL();
				break;
			case 5:
				subArrayR();
				break;
			case 6:
				subArrayL();
				break;
			}
//			print();
		}

		print();
	}
	// (1) 연산 : 상하반전
	static void reverseTB() {
		for(int i=0; i<N/2; i++) {
			int tmp[] = map[i];
			map[i] = map[N-1-i];
			map[N-1-i] = tmp;
		}
	}
	// (2) 연산 : 좌우반전
	static void reverseLR() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M/2; j++) {
				int tmp = map[i][j];
				map[i][j] = map[i][M-1-j];
				map[i][M-1-j] = tmp;
			}
		}
	}
	
	// (3) 연산 : 오른쪽 90도 회전
	static void turnR() {
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				tmpMap[i][j] = map[N-1-j][i];
			}
		}
		int tmp = N;
		N = M;
		M = tmp;
		copyMap();
	}
	
	// (4) 연산 : 왼족 90도 회전
	static void turnL() {
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				tmpMap[i][j] = map[j][M-1-i];
			}
		}
		int tmp = N;
		N = M;
		M = tmp;
		copyMap();
	}
	/*	하나의 배열을 다음과 같이 4개의 부분배열로 생각
	 		1	2
	 		4	3
	 */
	
	// (5) 연산 : 부분배열 1 -> 2 -> 3 -> 4
	static void subArrayR() {
		int halfN = N/2;
		int halfM = M/2;
		// 1 -> 2
		for(int i=0; i<halfN; i++) {
			for(int j=0; j<halfM; j++) {
				tmpMap[i][j+halfM] = map[i][j]; 
			}
		}
		// 2 -> 3
		for(int i=0; i<halfN; i++) {
			for(int j=halfM; j<M; j++) {
				tmpMap[i+halfN][j] = map[i][j]; 
			}
		}
		// 3 -> 4
		for(int i=halfN; i<N; i++) {
			for(int j=halfM; j<M; j++) {
				tmpMap[i][j-halfM] = map[i][j]; 
			}
		}
		// 4 -> 1
		for(int i=halfN; i<N; i++) {
			for(int j=0; j<halfM; j++) {
				tmpMap[i-halfN][j] = map[i][j]; 
			}
		}
		copyMap();
	}
	// (6) 연산 : 부분배열 1 <- 2 <- 3 <- 4
	static void subArrayL() {
		int halfN = N/2;
		int halfM = M/2;
		// 4 -> 3
		for(int i=halfN; i<N; i++) {
			for(int j=0; j<halfM; j++) {
				tmpMap[i][j+halfM] = map[i][j]; 
			}
		}
		// 3 -> 2
		for(int i=halfN; i<N; i++) {
			for(int j=halfM; j<M; j++) {
				tmpMap[i-halfN][j] = map[i][j]; 
			}
		}
		// 2 -> 1
		for(int i=0; i<halfN; i++) {
			for(int j=halfM; j<M; j++) {
				tmpMap[i][j-halfM] = map[i][j]; 
			}
		}
		// 1 -> 4
		for(int i=0; i<halfN; i++) {
			for(int j=0; j<halfM; j++) {
				tmpMap[i+halfN][j] = map[i][j]; 
			}
		}
		copyMap();
	}
	
	static void copyMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = tmpMap[i][j];
			}
		}
	}
	
	static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
/*
8 8 1
1 2 3 4 5 6 7 8 
9 10 11 12 13 14 15 16 
17 18 19 20 21 22 23 24 
25 26 27 28 29 30 31 32 
33 34 35 36 37 38 39 40 
41 42 43 44 45 46 47 48 
49 50 51 52 53 54 55 56 
57 58 59 60 61 62 63 64 

8 7 6 5 4 3 2 1 
16 15 14 13 12 11 10 9 
24 23 22 21 20 19 18 17 
32 31 30 29 28 27 26 25 
40 39 38 37 36 35 34 33 
48 47 46 45 44 43 42 41 
56 55 54 53 52 51 50 49 
64 63 62 61 60 59 58 57 

57 49 41 33 25 17 9 1 
58 50 42 34 26 18 10 2 
59 51 43 35 27 19 11 3 
60 52 44 36 28 20 12 4 
61 53 45 37 29 21 13 5 
62 54 46 38 30 22 14 6 
63 55 47 39 31 23 15 7 
64 56 48 40 32 24 16 8 

5 6 7 8 37 38 39 40 
13 14 15 16 45 46 47 48 
21 22 23 24 53 54 55 56 
29 30 31 32 61 62 63 64 
1 2 3 4 33 34 35 36 
9 10 11 12 41 42 43 44 
17 18 19 20 49 50 51 52 
25 26 27 28 57 58 59 60 

33 34 35 36 1 2 3 4 
41 42 43 44 9 10 11 12 
49 50 51 52 17 18 19 20 
57 58 59 60 25 26 27 28 
37 38 39 40 5 6 7 8 
45 46 47 48 13 14 15 16 
53 54 55 56 21 22 23 24 
61 62 63 64 29 30 31 32 
*/