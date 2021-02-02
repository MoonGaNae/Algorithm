package swexpert.ladder1_1210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Ladder1_1210
public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int LEFT = -1;
		int RIGHT = 1;
		int UP = 0;
		
		for(int t=1; t<=10; t++) {
			
			br.readLine();
			int map[][] = new int[100][100];
			
			StringTokenizer st;
			
			for(int i=0; i<100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			/*	<풀이방향성>
			 * 출발지점에서 찾는것이 아니라 도착지점에서 출발지를 찾는 역추적방식을 이용할 것이다.
			 * 따라서 행을 의미하는 y좌표를 99로 설정해서 찾는다.
			 */
			int curX=0;					// 현재 이동좌표	
			int curY = 99;				// 99: 마지막행에 2가 있을것이기 때문에
			
			for(int i=0; i<100; i++) {	// 도착지점 찾기
				if(map[curY][i] == 2) {
					curX = i;
				}
			}
			
			
			int prevDir=0;					// 이전방향을 저장할 변수
			
			
			while(curY > 0) {
				
				// 이전방향이 오른쪽이 X && 범위안넘고 && 왼쪽으로 갈수있는 경우
				if((prevDir != RIGHT) && curX+LEFT >= 0 && map[curY][curX + LEFT] == 1) {
					prevDir = LEFT;
					curX--;
				} 
				// 이전방향이 왼쪽이 X && 범위안넘고 && 오른쪽으로 갈수있는 경우
				else if((prevDir != LEFT) && curX+RIGHT < 100 && map[curY][curX + RIGHT] == 1) {
					prevDir = RIGHT;
					curX++;
				} else {						// 위로가기
					prevDir = UP;
					curY--;
				}
				
			}
			
			System.out.println("#" + t + " " + curX);
		}

	}

}
