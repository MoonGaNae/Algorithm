package swexpert.한빈이와SpotMart_9229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// D3
public class Solution {

	static int N;			// 과자봉지수
	static int M;			// 한빈이가 들고갈 수 있는 최대무게 제한
	static int snacks[];	// 과자무게 저장 배열
	static int ans;			// 한빈이가 들고갈 수 있는 최대무게합
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());	// 테스트케이스 갯수입력
		
		for(int t=1; t<=T; t++) {	// 테스트케이스 순회시작
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N =Integer.parseInt(st.nextToken());	// 과자봉지 수 입력
			M =Integer.parseInt(st.nextToken());	// 최대무게 제한 입력
			
			snacks = new int[N];
			ans = -1;								// 못들고가면 -1
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				snacks[i] = Integer.parseInt(st.nextToken()); // 과자배열에 무게 삽입
			}
			comb(0,0,0,0);	// 조합시작
			System.out.println("#"+t + " " + ans);
		}
	}
	/*
	 * cnt : 현재 진행 인덱스
	 * start : 뽑기를 시작할 인덱스 위치
	 * weight : 현재까지 뽑은 과자봉지무게합
	 * bong : 고른 과자봉지 수
	 */
	static void comb(int cnt, int start, int weight, int bong) {
		
		if(bong == 2) {							// 봉지수가 2개라면
			if(weight <= M)						// 그리고 제한무게 이하라면
				ans = Math.max(ans,  weight);	// 최댓값 저장
			return;								// 함수종료
		}
		if(cnt == N) return;					// 끝까지 선택했다면 함수종료
		
		
		for(int i=start; i<N; i++) {			// 뽑기 시작
			comb(cnt+1, i+1, weight+snacks[i], bong+1);	// 지금과자 뽑아봅시다
		}
	}
}
