package swexpert.flatten_1208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Flatten_1208
public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=1; t<=10; t++) {
			
			int cnt = Integer.parseInt(br.readLine()); // 이동횟수
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int boxs[] = new int [100];						// 상자의 높이저장 배열
			int hMax = -1, maxI=0;							// 최대값과 인덱스를 관리할 변수
			int hMin = 101, minI=0;							// 최솟값과 인덱스를 관리할 변수
			
			for(int i=0; i<100; i++) {
				int val = Integer.parseInt(st.nextToken());	// 현재 입력값
				boxs[i] = val;
				
				if(hMax < val) {							// 최댓값 찾기
					maxI=i;
					hMax = val;
				}
				if(hMin > val) {							// 최솟값 찾기
					minI=i;
					hMin = val;
				}
			}
			
			
			while(cnt>0 && (hMax-hMin) > 0) {				// 이동횟수가 끝나거나 평탄화작업 완료라면
															
				hMax = --boxs[maxI];						// 블럭이동
				hMin = ++boxs[minI];
				
				cnt--;										// 이동가능횟수감소
				
				for(int i=0; i<100; i++) {					// 최댓값 최솟값 최신화시작
					int val = boxs[i];
					
					if(hMax <= val) {
						maxI=i;
						hMax = val;
					}
					if(hMin >= val) {
						minI=i;
						hMin = val;
					}
				}
			}
			
			System.out.println("#"+t + " " + (hMax-hMin));	// 정답출력
		}

	}

}
