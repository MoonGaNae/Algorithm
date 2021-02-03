package swexpert.상호의배틀필드_1873;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int H;									// 맵의 높이
	static int W;									// 맵의 너비
	static char map[][];							// 게임맵
	
	static int N;									// 사용자입력 갯수
	static char user_com[];							// 사용자입력 저장배열
	
	// 필요한 인덱스 반환을 위한 배열
	static char tank_dir[] = {'^', 'v', '<', '>'};	// 탱크방향 저장
	static char user_dir[] = {'U', 'D', 'L', 'R'};	// 사용자입력 방향저장
	
	// Delta Array -> 상 하 좌 우
	static int dx[] = {0,0,-1,1};	
	static int dy[] = {-1,1,0,0};
	
	// 탱크의 현재 좌표 및 방향
	static int cur_x = 0;
	static int cur_y = 0;
	static int cur_dir = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());	// 테스트번호 입력
		
		for(int t=1; t<=T; t++) {
			
			// 게임맵의 높이 너비 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][];	// 게임맵 크기 설정
			
			
			for(int i=0; i<H; i++) {
				map[i] = br.readLine().toCharArray();	// 맵의 구성요소 한행씩 입력
				
				// 탱크찾기
				for(int j=0; j<W; j++) {
					for(int z=0; z<4; z++) {
						// 탱크를 찾고, 현재좌표, 방향을 저장
						if(map[i][j] == tank_dir[z]) {
							cur_y = i;
							cur_x = j;
							cur_dir = z;
						}
					}
				}
			}
			
			st = new StringTokenizer(br.readLine());
			// 사용자입력의 수 및 사용자입력 저장
			N = Integer.parseInt(st.nextToken());
			user_com = br.readLine().toCharArray();
			
			// Logic
			for(char c : user_com) {
				command(c);
			}
			
			// Reslut
			System.out.print("#" + t + " ");
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}
	/*
	 * 사용자입력에 대한 명령처리함수
	 */
	static void command(char c) {
		
		if(c == 'S') {
			int hwMax = Math.max(H, W);					// 높이, 넓이 중 큰 값 저장
			
			for(int i=1; i<=hwMax; i++) {
				// 현재방향에 맞게 포탄좌표 설정
				int nx = cur_x + dx[cur_dir] * i;
				int ny = cur_y + dy[cur_dir] * i;
				
				// 범위를 벗어나면 중단
				if(nx<0 || ny<0 || nx>=W ||ny>=H) return;
				// 부술수 없는 강철벽일경우 중단
				if(map[ny][nx] == '#') return;
				// 벽돌벽일 경우 벽돌을 평지로 변환
				if(map[ny][nx] == '*') {
					map[ny][nx] = '.';
					break;
				}
			}
			
		} else {
			// 사용자 입력에 맞는 방향인덱스 가져오기
			cur_dir = getDirIdx(c);
			
			// 이동할 좌표 설정
			int nx = cur_x + dx[cur_dir];
			int ny = cur_y + dy[cur_dir];
			
			// 현재 탱크방향 모양 수정
			map[cur_y][cur_x] = tank_dir[cur_dir];
			
			// 범위를 벗어날경우 중단
			if(nx<0 || ny<0 || nx>=W ||ny>=H) return;
			// 평지라면 이동
			if(map[ny][nx] == '.') {
				map[ny][nx] = map[cur_y][cur_x];
				map[cur_y][cur_x] = '.';
				cur_x = nx; cur_y = ny;
			}
		}
	}
	/*
	 * 유저입력에 따른 방향 인덱스 반환
	 */
	static int getDirIdx(char u) {
		for(int i=0; i<4; i++) {
			if(user_dir[i] == u) {
				return i;
			}
		}
		return -1;
	}

}
