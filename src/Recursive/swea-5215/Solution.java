package swexpert.햄버거다이어트_5215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// D3
public class Solution {
	
	static int good[];		// 재료에 대한 민기의 맛평가 배열
	static int calory[];	// 재료 칼로리 배열
	static int result[];	// 민기가 선택한 재료들을 담을 배열

	static int N;			// 재료의 수
	static int L;			// 제한칼로리
	static int ans;			// 
	static int goodMax;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			good = new int[N];
			calory = new int[N];
			goodMax = 0;
			ans = 0;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				good[i] = Integer.parseInt(st.nextToken());
				calory[i] = Integer.parseInt(st.nextToken());
			}
			
			comb(0,0,0,0);
			System.out.println("#"+t + " " + goodMax);
		}
	}
	
	static void comb(int cnt, int start, int calo, int fGood) {
		
		
		if (calo>L) return;			// 제한 칼로리를 넘으면 함수종료
		
		
		if(fGood > goodMax) {		// 선호도가 높으면
			ans = calo;				// 칼로리저장
			goodMax=fGood;			// 맛의 점수 저장
		}
		if(cnt==N) {				// 모든 재료를 찾아봣으면
			return;					// 함수종료
		}
		for(int i=start; i<N; i++) {
			comb(cnt+1, i+1, calo+calory[i], fGood+good[i]);
		}
	}

}
