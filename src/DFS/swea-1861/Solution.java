package swexpert.정사각형방_1861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// D4
public class Solution {

	static int[][] map;					// 방의 번호 배열
	static int[][] link;				// 연결된 방의 수 저장
	static int T, N;					// 테스트케이스, 공간의 길이
	static int NO, CNT;					// [정답] 방번호, 연결된 방의 수
	// delta array
	static int dy[] = {1, 0, -1, 0};	
	static int dx[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			CNT = 1;
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			link = new int[N][N];
			
			// 방번호 초기화
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(link[i][j] > 0) 	continue; // 이미 방문한 곳이라면? 넘어간다
					
					Stack<Room> s = new Stack<>();	// DFS를 위한 스택
					
					s.push(new Room(i,j));	// 현재 방 push
					link[i][j] = 1;			// 방문 체크
					int cnt=1;				// 연결방의 수(본인 포함)
					int no=map[i][j];		// 현재방의 수
					// DFS 시작
					while(!s.empty()) {
						Room cur = s.pop();
						
						for(int z=0; z<4; z++) {
							int zx = cur.x + dx[z];
							int zy = cur.y + dy[z];
							// 예외처리
							if(zx<0 || zy<0 || zx>=N || zy>=N) continue;					// 범위 벗어난경우
							if(link[zy][zx] > 0) continue;									// 이미 방문한 곳
							if(Math.abs(map[zy][zx] - map[cur.y][cur.x]) != 1) continue;	// 방번호 차이가 1이 아닐경우
							
							cnt++;							// 연결된 방의 수 증가
							link[zy][zx] = 1;				// 방문 체크
							s.push(new Room(zy,zx));		// 스택에 push
							no = Math.min(no, map[zy][zx]);	// 연결된 방번호중 가장 작은 방번호
						}
					}
					if(cnt >= CNT) {						// 현재 답보다 연결된 방의수가 크다면
						CNT = cnt;							// 정답교체
						if(no < NO) NO = no;				// 현재 답보다 방의 번호가 작다면 정답교체
					}
				}
			}
			
			System.out.println("#"+t+" "+NO+" "+CNT);		// 정답출력
		}
		br.close();
	}
	static class Room{
		int y, x;	// 방의 좌표

		public Room(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}

