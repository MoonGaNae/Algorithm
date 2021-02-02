package swexpert.달팽이숫자_1954;

import java.util.Scanner;
// 달팽이숫자_1954
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		
		// 			하 좌 상 우 
		int dx[] = {0, -1, 0, 1};
		int dy[] = {1, 0, -1, 0};
		
		for(int t=1; t<=T; t++) {
			
			int N = sc.nextInt();
			int snail[][] = new int[N][N];
			
			int cnt = 1;
			
			// 맨 처음행은 우측으로 N-1번만큼 그리기
			for(int i=0; i<N; i++) {
				snail[0][i]= cnt++;
			}
			
			int dir=0;						// 진행방향을 나타내는 변수(0:하, 1:좌, 2:상, 3:우)
			int curX=N-1;					// 사각형의 우상끝에서 시작
			int curY=0;
			
			for(int n=N-1; n>0; n--) {			// 한번움직일때 n번씩 움직여 사용하는 반복문
				for(int i=0; i<2; i++) {		// n번만큼 총 2번씩 이동하는 반복문
					for(int j=0; j<n; j++) {	// 실제로 n 이동하는 반복문
						curX = curX + dx[dir];	// 현재 방향으로 X좌표 이동
						curY = curY + dy[dir];	// 현재 방향으로 Y좌표 이동
						
						snail[curY][curX] = cnt++;	// 달팽이 그리기
					}
					dir = (dir+1) % 4;			// 방향전환
				}
			}
			
			// 결과출력
			System.out.println("#" + t);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.print(snail[i][j] + " ");
				}
				System.out.println();
			}
		}
		sc.close();
	}

}
