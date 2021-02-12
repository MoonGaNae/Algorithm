package baekjoon.배열돌리기5_17470;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 골드1
public class Main {
			// 원본 배열의 크기(N,M), 연산자수(R),  연산적용 배열의 크기(NN, MM) 
	static int N, M, R, NN, MM;
	static int [][] map = new int [101][101];		// 원본 배열
	static int [][] tmpMap = new int [101][101];	// 연산적용 배열
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		NN = N;
		MM = M;
		
		// 원본배열 초기화
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<R; i++) {
			// 연산자입력
			int command = Integer.parseInt(st.nextToken());
			
			// 처리
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
			
		}
		setArray();
		print();
		
		
	}
	
	// subArray의 8가지 상태
	// state[연산자번호][curState]
							//	0   1  2  3  4  5  6  7  8
	static int[][] state = {	{},
								{0, 4, 3, 2, 1, 6, 5, 8, 7},
								{0, 2, 1, 4, 3, 8, 7, 6, 5},
								{0, 5, 6, 7, 8, 3, 4, 1, 2},
								{0, 7, 8, 5, 6, 1, 2, 3, 4}};
	//  연산자에 따른 subArray 위치 변환
	//	sub[연산자번호][curSub]
							//	0   1  2  3  4
	static int[][] sub = {		{},
								{0, 4, 3, 2, 1},
								{0, 2, 1, 4, 3},
								{0, 2, 3, 4, 1},
								{0, 4, 1, 2, 3}};
							
	// 상태별 이웃좌표 delta
	// (0,0)을 기준점으로 (0,1)=(y1,x1), (1,0)=(y2,x2)
	// delta[curState][y1,x1,y2,x2]
							// y1,x1,y2,x2
	static int [][] delta = {	{},
								{0,1,1,0},
								{0,-1,1,0},
								{0,-1,-1,0},
								{0,1,-1,0},
								{1,0,0,-1},
								{-1,0,0,-1},
								{-1,0,0,1},
								{1,0,0,1}};
	
	static int curSub=1;		// 초기의 1번 subArray가 있는 영역
	static int curState=1;		// 각 subArray 의 상태(8가지)
	static int subDir = 1;		// subArray들의 방향(정방향, 역방향)
	// (1) 연산 : 상하반전
	static void reverseTB() {
		
		curState = state[1][curState];
		curSub = sub[1][curSub];
		subDir *= -1;
	}
	// (2) 연산 : 좌우반전
	static void reverseLR() {
		curState = state[2][curState];
		curSub = sub[2][curSub];
		subDir *= -1;
	}
	
	// (3) 연산 : 오른쪽 90도 회전
	static void turnR() {
		curState = state[3][curState];
		curSub = sub[3][curSub];
		swapNM();
	}
	
	// (4) 연산 : 왼족 90도 회전
	static void turnL() {
		curState = state[4][curState];
		curSub = sub[4][curSub];
		swapNM();
	}
	/*	하나의 배열을 다음과 같이 4개의 부분배열로 생각
	 		1	2
	 		4	3
	 */
	
	// (5) 연산 : 부분배열 1 -> 2 -> 3 -> 4
	static void subArrayR() {
		curSub = sub[3][curSub];
	}
	// (6) 연산 : 부분배열 1 <- 2 <- 3 <- 4
	static void subArrayL() {
		curSub = sub[4][curSub];
	}
	
	static Point getPoint(int subNum) {
		if(subNum > 4) {
			subNum = subNum%4;
		} else if(subNum < 1) {
			subNum = 4 + subNum;
		}
		Point p = new Point();
		
		if(subNum == 1) {
			p.x=0;
			p.y=0;
		} else if(subNum == 2) {
			p.x=MM/2;
			p.y=0;
		} else if(subNum == 3) {
			p.x=MM/2;
			p.y=NN/2;
		} else if(subNum == 4) {
			p.x=0;
			p.y=NN/2;
		} 
		
		
		if(curState == 2 || curState == 5) {
			p.x += MM/2 - 1;
		} else if(curState == 3 || curState == 6) {
			p.x += MM/2 - 1;
			p.y += NN/2 - 1;
		} else if(curState == 4 || curState == 7) {
			p.y += NN/2 - 1;
		}
		return p;
	}
	static void setArray() {
		// 원형기준 1번 subArray		
		Point p = getPoint(curSub);
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				int zy = p.y + delta[curState][0]*j;
				int zx = p.x + delta[curState][1]*j;

				if(zx<0 || zy<0 || zx>=MM || zy>=NN) continue;
				tmpMap[zy][zx] = map[i][j];
			}
			p.y += delta[curState][2];
			p.x += delta[curState][3];
		}
		// 원형기준 2번 subArray
		p = getPoint(curSub+1*subDir);
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				int zy = p.y + delta[curState][0]*j;
				int zx = p.x + delta[curState][1]*j;

				if(zx<0 || zy<0 || zx>=MM || zy>=NN) continue;
				tmpMap[zy][zx] = map[i][j + M/2];
			}
			p.y += delta[curState][2];
			p.x += delta[curState][3];
		}
		// 원형기준 3번 subArray
		p = getPoint(curSub+2*subDir);
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				int zy = p.y + delta[curState][0]*j;
				int zx = p.x + delta[curState][1]*j;

				if(zx<0 || zy<0 || zx>=MM || zy>=NN) continue;
				tmpMap[zy][zx] = map[i+N/2][j+M/2];
			}
			p.y += delta[curState][2];
			p.x += delta[curState][3];
		}
		// 원형기준 4번 subArray
		p = getPoint(curSub+3*subDir);
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				int zy = p.y + delta[curState][0]*j;
				int zx = p.x + delta[curState][1]*j;

				if(zx<0 || zy<0 || zx>=MM || zy>=NN) continue;
				tmpMap[zy][zx] = map[i+N/2][j];
			}
			p.y += delta[curState][2];
			p.x += delta[curState][3];
		}
	}
	
	static void swapNM() {
		int tmp = NN;
		NN = MM;
		MM = tmp;
	}
	static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<NN; i++) {
			for(int j=0; j<MM; j++) {
				sb.append(tmpMap[i][j] + " ");
			}
			sb.setLength(sb.length()-1);
			sb.append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	static class Point{
		int x, y;
	}
}

